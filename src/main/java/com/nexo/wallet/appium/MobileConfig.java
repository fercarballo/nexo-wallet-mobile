package com.nexo.wallet.appium;

import io.appium.java_client.android.options.UiAutomator2Options;

/**
 * Configuración del entorno mobile, tomada de variables de entorno (con valores por defecto para
 * documentación). Convierte esa configuración en las {@link UiAutomator2Options} que Appium usa
 * para instalar y lanzar la app en el dispositivo/emulador.
 *
 * <p>NUNCA se versionan secretos: todo se recibe por entorno. Ver {@code .env.example}.
 */
public class MobileConfig {

  private final String appiumUrl;
  private final String deviceName;
  private final String platformVersion;
  private final String app;
  private final String appPackage;
  private final String appActivity;

  /** Configuración desde variables de entorno (o valores de referencia por defecto). */
  public MobileConfig() {
    this(
        env("NEXO_APPIUM_URL", "http://127.0.0.1:4723"),
        env("NEXO_DEVICE_NAME", "Android Emulator"),
        env("NEXO_PLATFORM_VERSION", "14"),
        env("NEXO_APP", "apps/nexo-wallet.apk"),
        env("NEXO_APP_PACKAGE", "com.nexo.wallet"),
        env("NEXO_APP_ACTIVITY", "com.nexo.wallet.MainActivity"));
  }

  /** Constructor explícito (útil para pruebas de configuración). */
  public MobileConfig(String appiumUrl, String deviceName, String platformVersion,
      String app, String appPackage, String appActivity) {
    this.appiumUrl = appiumUrl;
    this.deviceName = deviceName;
    this.platformVersion = platformVersion;
    this.app = app;
    this.appPackage = appPackage;
    this.appActivity = appActivity;
  }

  public String appiumUrl() {
    return appiumUrl;
  }

  /** Arma las capabilities de Appium (UiAutomator2) a partir de la configuración. */
  public UiAutomator2Options toOptions() {
    return new UiAutomator2Options()
        .setPlatformName("Android")
        .setAutomationName("UiAutomator2")
        .setDeviceName(deviceName)
        .setPlatformVersion(platformVersion)
        .setApp(app)
        .setAppPackage(appPackage)
        .setAppActivity(appActivity)
        .setNewCommandTimeout(java.time.Duration.ofSeconds(120));
  }

  private static String env(String key, String defaultValue) {
    String value = System.getenv(key);
    return (value == null || value.isBlank()) ? defaultValue : value;
  }
}
