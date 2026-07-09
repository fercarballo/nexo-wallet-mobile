# Runbook — Resolución de problemas

## Modo simulado

| Síntoma | Causa probable | Solución |
|---|---|---|
| `Unable to locate a Java Runtime` | Sin JDK o `JAVA_HOME` sin setear | Instalar JDK 21 y exportar `JAVA_HOME`. |
| `cannot access org.openqa.selenium.ContextAware` | Selenium resuelto por el **rango** de `java-client` es demasiado nuevo | Ya mitigado: el `pom.xml` fija `selenium-java` explícitamente. No quitar esa fijación. |
| Undefined step | Falta el step o el glue no apunta al paquete | Verificar `RunWalletTests` (`GLUE_PROPERTY_NAME = com.nexo.wallet`). |

## Modo real (Appium)

| Síntoma | Causa probable | Solución |
|---|---|---|
| `No se pudo crear el AndroidDriver` | Appium Server o emulador caídos | `appium` corriendo y `adb devices` listando el dispositivo. |
| `Could not find a connected Android device` | El emulador no arrancó | `emulator -avd <nombre>` y esperar a que termine de bootear. |
| `An unknown server-side error ... uiautomator2` | Driver no instalado | `appium driver install uiautomator2`. |
| `Package/Activity not found` | `NEXO_APP_PACKAGE` / `NEXO_APP_ACTIVITY` incorrectos | Obtenerlos con `adb shell dumpsys window | grep -i mCurrentFocus` con la app abierta. |
| `NoSuchElementException` en un accessibility id | La app no expone ese `content-desc` | Coordinar con desarrollo: los accessibility ids son un contrato de testabilidad (ADR-003). |
| Timeouts intermitentes | Emulador lento | Aumentar el timeout de `BaseScreen` o usar un dispositivo más rápido; nunca agregar `sleep`. |

## Inspeccionar la jerarquía de la app

`appium inspector` (o `adb shell uiautomator dump`) permite ver los `content-desc` disponibles.
