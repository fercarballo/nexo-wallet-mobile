package com.nexo.wallet.support;

import com.nexo.wallet.WalletActions;
import com.nexo.wallet.appium.AppiumWallet;
import com.nexo.wallet.appium.MobileConfig;
import com.nexo.wallet.simulated.SimulatedWallet;

/**
 * Elige la implementación de {@link WalletActions} según el modo de ejecución:
 * <ul>
 *   <li>por defecto → {@link SimulatedWallet} (sin emulador; usado en CI y en local);</li>
 *   <li>{@code -DwalletMode=appium} → {@link AppiumWallet} (contra la app real en un emulador).</li>
 * </ul>
 * Esta única perilla intercambia el destino sin tocar ni los escenarios ni los steps.
 */
public final class WalletFactory {

  public static final String MODE_PROPERTY = "walletMode";

  private WalletFactory() {
  }

  public static WalletActions create() {
    String mode = System.getProperty(MODE_PROPERTY, "simulated");
    if ("appium".equalsIgnoreCase(mode)) {
      return new AppiumWallet(new MobileConfig());
    }
    return new SimulatedWallet();
  }
}
