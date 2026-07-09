package com.nexo.wallet.appium.screens;

import io.appium.java_client.android.AndroidDriver;

/** Pantalla de transferencia de la app Nexo Wallet. */
public class TransferScreen extends BaseScreen {

  public TransferScreen(AndroidDriver driver) {
    super(driver);
  }

  public void transfer(String sourceAccount, String destinationAccount, String amount) {
    // La cuenta de origen se elige de un selector (spinner): se abre y se toca la opción.
    tap(id("source-account"));
    tap(id("source-option-" + sourceAccount));
    type(id("dest-account"), destinationAccount);
    type(id("amount"), amount);
    tap(id("submit-transfer"));
  }

  public boolean isSuccess() {
    return isPresent(id("transfer-success"));
  }

  public String successText() {
    return visible(id("transfer-success")).getText();
  }

  public String errorText() {
    return visible(id("transfer-error")).getText();
  }
}
