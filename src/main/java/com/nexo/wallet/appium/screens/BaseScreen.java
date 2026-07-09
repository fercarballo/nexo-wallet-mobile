package com.nexo.wallet.appium.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base de los Page Objects mobile. Centraliza las esperas explícitas y la estrategia de
 * localización por <b>accessibility id</b> (el {@code content-desc} de Android), que es el
 * selector recomendado para automatización: estable, independiente del texto y de la jerarquía
 * visual, y compartido con el equipo de desarrollo como contrato de testabilidad.
 */
public abstract class BaseScreen {

  protected final AndroidDriver driver;
  protected final WebDriverWait wait;

  protected BaseScreen(AndroidDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
  }

  protected By id(String accessibilityId) {
    return AppiumBy.accessibilityId(accessibilityId);
  }

  protected WebElement visible(By locator) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  protected void tap(By locator) {
    wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
  }

  protected void type(By locator, String text) {
    WebElement element = visible(locator);
    element.clear();
    element.sendKeys(text);
  }

  protected boolean isPresent(By locator) {
    return !driver.findElements(locator).isEmpty();
  }
}
