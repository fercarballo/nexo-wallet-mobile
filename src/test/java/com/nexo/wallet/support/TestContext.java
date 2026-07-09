package com.nexo.wallet.support;

import com.nexo.wallet.WalletActions;

/**
 * Estado compartido entre hooks y steps dentro de un escenario. Cucumber (vía PicoContainer)
 * crea una instancia por escenario y la inyecta por constructor, de modo que todos operan sobre
 * la misma billetera (simulada o real).
 */
public class TestContext {
  public WalletActions wallet;
}
