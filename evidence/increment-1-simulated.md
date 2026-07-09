# Evidencia — Incremento 1: suite BDD (modo simulado) y configuración de Appium

Fecha: 2026-07-09 · Entorno: local (JDK 21, Maven, Appium java-client 9.3.0, Selenium 4.23.0)

> ## ⚠️ Alcance honesto de esta evidencia
>
> **Lo que SÍ está verificado aquí:** los escenarios BDD ejecutados contra la implementación
> **simulada** de la billetera, y la construcción de las **capabilities de Appium** desde la
> configuración.
>
> **Lo que NO está verificado aquí:** la ejecución contra un **emulador/dispositivo Android real**
> con Appium. Este entorno no dispone de Android SDK ni emulador. El código de `AppiumWallet`,
> los Page Objects mobile y el `AppiumDriverFactory` son **reales y compilan**, pero **no se
> ejecutaron**. La documentación describe con precisión cómo correrlos (`docs/runbooks/local-setup.md`).
>
> No se afirma ninguna corrida que no haya ocurrido.

## Resultado real de `mvn test`

```
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0  -- com.nexo.wallet.RunWalletTests
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0  -- com.nexo.wallet.AppiumCapabilitiesTest
Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### 1. Escenarios BDD (modo simulado) — 6 en verde

Ejecutados contra `SimulatedWallet`, que **imita el contrato** de `nexo-transfer-api`:

**Ingreso** — ingreso exitoso (saludo + saldo) · rechazo con credenciales inválidas.
**Transferencias** — exitosa · fondos insuficientes · origen y destino iguales · monto inválido.

Esto verifica que la **suite de negocio está completa y correctamente cableada** (features, steps,
hooks, inyección de dependencias) y que las **reglas de negocio** imitadas son correctas.

### 2. Configuración de Appium — 2 en verde

`AppiumCapabilitiesTest` construye las `UiAutomator2Options` desde `MobileConfig` y verifica cada
capability (`automationName`, `deviceName`, `platformVersion`, `app`, `appPackage`, `appActivity`)
y la URL del servidor Appium. Es la evidencia de que, **en un entorno con emulador**, el driver se
crearía con los parámetros correctos — sin poder ejecutarlo aquí.

## Cómo regenerar

```bash
mvn test                        # modo simulado (por defecto): 8 pruebas
mvn test -DwalletMode=appium    # modo real: requiere Appium + emulador + APK (no ejecutado aquí)
```

## Hallazgo técnico registrado

`io.appium:java-client:9.3.0` declara Selenium mediante un **rango de versiones**, que resolvía
Selenium **4.45.0** — versión en la que ya no existen clases que el `AndroidDriver` compilado
referencia (`ContextAware`, `html5.LocationContext`), rompiendo la compilación. Se fijó
explícitamente `selenium-java:4.23.0`. Detalle en `docs/learning/common-mistakes.md`.
