package com.nexo.wallet;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 * Runner que ejecuta los escenarios de Cucumber. Por defecto corren en modo <b>simulado</b>
 * (sin emulador), lo que permite verificarlos en cualquier entorno y en CI. Para correrlos contra
 * la app real: {@code mvn test -DwalletMode=appium} con un servidor Appium y un emulador arriba.
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.nexo.wallet")
@ConfigurationParameter(
    key = PLUGIN_PROPERTY_NAME,
    value = "pretty, html:target/cucumber/report.html, json:target/cucumber/report.json")
public class RunWalletTests {
}
