package com.nexo.wallet.appium.screens;

import io.appium.java_client.android.AndroidDriver;

/** Pantalla principal (saldos) de la app Nexo Wallet. */
public class DashboardScreen extends BaseScreen {

  public DashboardScreen(AndroidDriver driver) {
    super(driver);
  }

  public String welcome() {
    return visible(id("welcome")).getText();
  }

  public String balanceOf(String accountId) {
    return visible(id("balance-" + accountId)).getText();
  }

  public void goToTransfer() {
    tap(id("nav-transfer"));
  }
}
