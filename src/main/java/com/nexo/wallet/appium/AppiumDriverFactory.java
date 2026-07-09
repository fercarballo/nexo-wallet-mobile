package com.nexo.wallet.appium;

import io.appium.java_client.android.AndroidDriver;
import java.net.URI;
import java.net.URL;

/**
 * Crea el {@link AndroidDriver} contra un servidor Appium, con las capabilities de {@link MobileConfig}.
 * Requiere un servidor Appium en ejecución y un emulador/dispositivo disponible (ver runbooks).
 */
public final class AppiumDriverFactory {

  private AppiumDriverFactory() {
  }

  public static AndroidDriver create(MobileConfig config) {
    try {
      URL server = URI.create(config.appiumUrl()).toURL();
      return new AndroidDriver(server, config.toOptions());
    } catch (Exception e) {
      throw new IllegalStateException(
          "No se pudo crear el AndroidDriver. ¿Está el servidor Appium y el emulador arriba? "
              + "Ver docs/runbooks/local-setup.md", e);
    }
  }
}
