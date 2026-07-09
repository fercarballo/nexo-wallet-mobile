package com.nexo.wallet;

import static org.assertj.core.api.Assertions.assertThat;

import com.nexo.wallet.appium.MobileConfig;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Verifica que el camino REAL de Appium esté correctamente configurado, sin necesidad de un
 * dispositivo: arma las capabilities de UiAutomator2 desde la configuración y comprueba cada valor.
 * Es la evidencia de que, en un entorno con emulador, el driver se crearía con los parámetros
 * correctos.
 */
class AppiumCapabilitiesTest {

  @Test
  @DisplayName("Construye las capabilities de UiAutomator2 desde la configuración")
  void buildsUiAutomator2Capabilities() {
    MobileConfig config = new MobileConfig(
        "http://127.0.0.1:4723",
        "Pixel_7_API_34",
        "14",
        "apps/nexo-wallet.apk",
        "com.nexo.wallet",
        "com.nexo.wallet.MainActivity");

    UiAutomator2Options options = config.toOptions();

    assertThat(config.appiumUrl()).isEqualTo("http://127.0.0.1:4723");
    assertThat(options.getPlatformName()).isNotNull();
    assertThat(options.getAutomationName()).contains("UiAutomator2");
    assertThat(options.getDeviceName()).contains("Pixel_7_API_34");
    assertThat(options.getPlatformVersion()).contains("14");
    assertThat(options.getApp()).contains("apps/nexo-wallet.apk");
    assertThat(options.getAppPackage()).contains("com.nexo.wallet");
    assertThat(options.getAppActivity()).contains("com.nexo.wallet.MainActivity");
  }

  @Test
  @DisplayName("Usa valores por defecto de referencia cuando no hay variables de entorno")
  void usesReferenceDefaults() {
    MobileConfig config = new MobileConfig();
    UiAutomator2Options options = config.toOptions();

    assertThat(options.getAutomationName()).contains("UiAutomator2");
    assertThat(options.getAppPackage()).contains("com.nexo.wallet");
  }
}
