package com.nexo.wallet.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import com.nexo.wallet.TransferResult;
import com.nexo.wallet.support.TestContext;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

/**
 * Step definitions que hablan el lenguaje de la billetera ({@code WalletActions}) y no conocen
 * Appium. Por eso los mismos escenarios corren tanto en modo simulado como contra el dispositivo real.
 */
public class WalletSteps {

  private final TestContext context;
  private TransferResult result;

  public WalletSteps(TestContext context) {
    this.context = context;
  }

  @Cuando("ingresa con usuario {string} y contraseña {string}")
  public void ingresaCon(String username, String password) {
    context.wallet.login(username, password);
  }

  @Entonces("ve el saludo {string}")
  public void veElSaludo(String saludo) {
    assertThat(context.wallet.welcome()).isEqualTo(saludo);
  }

  @Entonces("ve el saldo {string} en la cuenta {string}")
  public void veElSaldo(String saldo, String cuenta) {
    assertThat(context.wallet.balanceOf(cuenta)).isEqualTo(saldo);
  }

  @Entonces("ve el mensaje de error de ingreso {string}")
  public void veErrorDeIngreso(String mensaje) {
    assertThat(context.wallet.loginError()).isEqualTo(mensaje);
  }

  @Dado("que {string} inició sesión con la contraseña {string}")
  public void inicioSesion(String username, String password) {
    assertThat(context.wallet.login(username, password)).isTrue();
  }

  @Cuando("transfiere {string} desde {string} hacia {string}")
  public void transfiere(String monto, String origen, String destino) {
    result = context.wallet.transfer(origen, destino, monto);
  }

  @Entonces("la transferencia es exitosa")
  public void laTransferenciaEsExitosa() {
    assertThat(result.success()).isTrue();
    assertThat(result.message()).contains("Transferencia realizada");
  }

  @Entonces("ve el error de transferencia {string}")
  public void veErrorDeTransferencia(String mensaje) {
    assertThat(result.success()).isFalse();
    assertThat(result.message()).isEqualTo(mensaje);
  }
}
