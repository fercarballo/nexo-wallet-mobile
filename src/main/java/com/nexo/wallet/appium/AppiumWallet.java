package com.nexo.wallet.appium;

import com.nexo.wallet.TransferResult;
import com.nexo.wallet.WalletActions;
import com.nexo.wallet.appium.screens.DashboardScreen;
import com.nexo.wallet.appium.screens.LoginScreen;
import com.nexo.wallet.appium.screens.TransferScreen;
import io.appium.java_client.android.AndroidDriver;

/**
 * Implementación REAL de {@link WalletActions} que conduce la app Android Nexo Wallet con Appium.
 *
 * <p>El código es completo y compila, pero requiere un servidor Appium + un emulador/dispositivo
 * con la app instalada para ejecutarse (ver docs/runbooks/local-setup.md). En modo simulado
 * —el usado en CI y por defecto— esta clase no se instancia; se usa {@code SimulatedWallet}.
 */
public class AppiumWallet implements WalletActions {

  private final MobileConfig config;
  private AndroidDriver driver;
  private String lastLoginError = "";

  public AppiumWallet(MobileConfig config) {
    this.config = config;
  }

  @Override
  public void open() {
    driver = AppiumDriverFactory.create(config);
  }

  @Override
  public boolean login(String username, String password) {
    LoginScreen screen = new LoginScreen(driver);
    screen.login(username, password);
    if (screen.hasError()) {
      lastLoginError = screen.error();
      return false;
    }
    lastLoginError = "";
    return true;
  }

  @Override
  public String loginError() {
    return lastLoginError;
  }

  @Override
  public String welcome() {
    return new DashboardScreen(driver).welcome();
  }

  @Override
  public String balanceOf(String accountId) {
    return new DashboardScreen(driver).balanceOf(accountId);
  }

  @Override
  public TransferResult transfer(String sourceAccount, String destinationAccount, String amount) {
    new DashboardScreen(driver).goToTransfer();
    TransferScreen screen = new TransferScreen(driver);
    screen.transfer(sourceAccount, destinationAccount, amount);
    return screen.isSuccess()
        ? TransferResult.ok(screen.successText())
        : TransferResult.failed(screen.errorText());
  }

  @Override
  public void close() {
    if (driver != null) {
      driver.quit();
    }
  }
}
