# ADR-001 — Appium + Cucumber + Page Object Model (Java)

- **Estado:** Aceptada · **Fecha:** 2026-07-09

## Contexto

El rol objetivo pide **Appium**, **Cucumber**, **Java** y frameworks mantenibles. Hay que automatizar
la regresión profunda del canal mobile sin duplicar la cobertura del canal web (Selenium).

## Decisión

- **Appium 9 (java-client) con UiAutomator2** para Android: es el estándar de automatización mobile
  y el driver recomendado para Android moderno.
- **Cucumber (BDD, español)**: los escenarios describen journeys de negocio, legibles por cualquiera.
- **Page Object Model** por pantalla (`LoginScreen`, `DashboardScreen`, `TransferScreen`).

## Alternativas consideradas

| Alternativa | Por qué se descartó |
|---|---|
| **Espresso / UI Automator nativos** | Más rápidos y estables, pero atados a Android y al código de la app; el rol pide Appium (multiplataforma, caja negra). |
| **Maestro / Detox** | Modernos, pero fuera del stack pedido. |
| **Scripts sin POM** | Insostenibles: selectores duplicados y frágiles. |

## Consecuencias

- (+) Alineado con el stack del rol; el mismo lenguaje BDD que el canal web.
- (+) Caja negra: no requiere el código fuente de la app.
- (−) Appium necesita servidor + dispositivo/emulador: la ejecución real es costosa en CI. Se mitiga
  con la abstracción de dos modos (ver ADR-002).

## Cómo se verifica

`mvn test` corre los escenarios (modo simulado) y `AppiumCapabilitiesTest` valida la configuración
del driver real. La ejecución contra emulador se documenta en los runbooks.
