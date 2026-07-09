package com.nexo.wallet.appium.screens;

import io.appium.java_client.android.AndroidDriver;

/** Pantalla de ingreso de la app Nexo Wallet. */
public class LoginScreen extends BaseScreen {

  public LoginScreen(AndroidDriver driver) {
    super(driver);
  }

  public void login(String username, String password) {
    type(id("username"), username);
    type(id("password"), password);
    tap(id("login-button"));
  }

  public boolean hasError() {
    return isPresent(id("login-error"));
  }

  public String error() {
    return visible(id("login-error")).getText();
  }
}
