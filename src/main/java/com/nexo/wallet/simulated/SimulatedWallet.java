package com.nexo.wallet.simulated;

import com.nexo.wallet.TransferResult;
import com.nexo.wallet.WalletActions;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Implementación en memoria de la billetera. No usa Appium ni un dispositivo: reproduce las mismas
 * reglas de negocio que {@code nexo-transfer-api} (titularidad, fondos, monto, cuentas distintas)
 * y los mismos datos ficticios. Permite ejecutar y verificar los escenarios en cualquier entorno
 * (incluido CI) sin emulador.
 *
 * <p>El estado se re-siembra en cada {@link #open()}, garantizando datos deterministas por escenario.
 */
public class SimulatedWallet implements WalletActions {

  private record User(String password, String name, List<String> accounts) {
  }

  private static final class Account {
    final String owner;
    final String currency;
    BigDecimal balance;

    Account(String owner, String currency, BigDecimal balance) {
      this.owner = owner;
      this.currency = currency;
      this.balance = balance;
    }
  }

  private final Map<String, User> users = Map.of(
      "ana", new User("ana123", "Ana", List.of("acc-ana-001", "acc-ana-002")),
      "luis", new User("luis123", "Luis", List.of("acc-luis-001")));

  private final Map<String, Account> accounts = new HashMap<>();
  private String currentUser;
  private String loginError = "";

  @Override
  public void open() {
    accounts.clear();
    accounts.put("acc-ana-001", new Account("ana", "ARS", new BigDecimal("100000")));
    accounts.put("acc-ana-002", new Account("ana", "ARS", new BigDecimal("5000")));
    accounts.put("acc-luis-001", new Account("luis", "ARS", new BigDecimal("25000")));
    currentUser = null;
    loginError = "";
  }

  @Override
  public boolean login(String username, String password) {
    User user = users.get(username);
    if (user == null || !user.password().equals(password)) {
      loginError = "Usuario o contraseña incorrectos.";
      currentUser = null;
      return false;
    }
    loginError = "";
    currentUser = username;
    return true;
  }

  @Override
  public String loginError() {
    return loginError;
  }

  @Override
  public String welcome() {
    return "Hola, " + users.get(currentUser).name();
  }

  @Override
  public String balanceOf(String accountId) {
    Account account = accounts.get(accountId);
    return format(account.balance) + " " + account.currency;
  }

  @Override
  public TransferResult transfer(String sourceAccount, String destinationAccount, String amount) {
    BigDecimal value;
    try {
      value = new BigDecimal(amount);
    } catch (NumberFormatException e) {
      value = BigDecimal.valueOf(-1);
    }
    if (value.compareTo(BigDecimal.ZERO) <= 0) {
      return TransferResult.failed("El monto debe ser mayor a 0.");
    }
    if (sourceAccount.equals(destinationAccount)) {
      return TransferResult.failed("Origen y destino no pueden ser la misma cuenta.");
    }
    Account source = accounts.get(sourceAccount);
    if (source == null || !source.owner.equals(currentUser)) {
      return TransferResult.failed("La cuenta de origen no pertenece al usuario autenticado.");
    }
    if (!accounts.containsKey(destinationAccount)) {
      return TransferResult.failed("La cuenta de destino no existe.");
    }
    if (source.balance.compareTo(value) < 0) {
      return TransferResult.failed("Fondos insuficientes en la cuenta de origen.");
    }
    source.balance = source.balance.subtract(value);
    accounts.get(destinationAccount).balance = accounts.get(destinationAccount).balance.add(value);
    return TransferResult.ok("Transferencia realizada. Nuevo saldo de " + sourceAccount + ": "
        + format(source.balance) + " " + source.currency + ".");
  }

  @Override
  public void close() {
    currentUser = null;
  }

  private String format(BigDecimal amount) {
    NumberFormat nf = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-AR"));
    nf.setMinimumFractionDigits(2);
    nf.setMaximumFractionDigits(2);
    return nf.format(amount);
  }
}
