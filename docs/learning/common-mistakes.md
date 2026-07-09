# Errores frecuentes (y cómo evitarlos)

## 1. Presentar una corrida simulada como si fuera una corrida en dispositivo

Es el error más grave y el más tentador. El modo simulado verifica **la lógica de negocio y el
cableado de la suite**, no la UI ni Appium. **Solución:** declarar el alcance en cada evidencia,
como hace este repositorio (README, `evidence/`, plantilla de PR).

## 2. Dejar que las dos implementaciones diverjan

Si agregás una acción sólo al simulado, la suite "pasa" pero el modo real ni compila o falla.
**Solución:** la regla del CONTRIBUTING — toda acción de `WalletActions` se implementa en ambas.

## 3. Usar XPath o texto visible como selector

En mobile el XPath es además **el localizador más lento** de Appium. **Solución:** accessibility ids
(`content-desc`), acordados con desarrollo como contrato de testabilidad (ADR-003).

## 4. `Thread.sleep` para "esperar que cargue la pantalla"

Frágil y lento; en un emulador (más lento que un dispositivo) el problema se agrava.
**Solución:** esperas explícitas en `BaseScreen`.

## 5. Poner lógica de test en los step definitions

Los steps orquestan; los Page Objects (o la implementación de `WalletActions`) hacen. Así un cambio
de pantalla toca un solo archivo.

## 6. Versionar el APK o las credenciales

Los binarios inflan el repositorio y las credenciales son un riesgo de seguridad.
**Solución:** `.gitignore` para `*.apk` y toda la configuración por entorno (`.env.example`).

## 7. Confiar en la resolución transitiva de Selenium que trae Appium

Caso real de este repositorio: `io.appium:java-client:9.3.0` declara Selenium mediante un **rango de
versiones**, que resolvió Selenium **4.45.0**. En esa versión ya no existen clases que el
`AndroidDriver` compilado referencia (`ContextAware`, `html5.LocationContext`), y **la compilación
falla** con `cannot access ... class file not found`. **Solución:** fijar explícitamente
`selenium-java` a la versión con la que el `java-client` fue construido (4.23.0). Lección general:
ante un `cannot access X` de una clase de terceros, sospechá de un **conflicto de versiones**, no de
tu código; revisá `mvn dependency:tree`.
