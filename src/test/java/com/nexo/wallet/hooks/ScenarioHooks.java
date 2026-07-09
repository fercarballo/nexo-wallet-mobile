package com.nexo.wallet.hooks;

import com.nexo.wallet.support.TestContext;
import com.nexo.wallet.support.WalletFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * Ciclo de vida por escenario: crea la billetera según el modo (simulado por defecto, o Appium
 * con {@code -DwalletMode=appium}), la inicializa y, al terminar, la cierra. Un estado nuevo por
 * escenario garantiza aislamiento y datos deterministas.
 */
public class ScenarioHooks {

  private final TestContext context;

  public ScenarioHooks(TestContext context) {
    this.context = context;
  }

  @Before
  public void setUp() {
    context.wallet = WalletFactory.create();
    context.wallet.open();
  }

  @After
  public void tearDown() {
    if (context.wallet != null) {
      context.wallet.close();
    }
  }
}
