# Glosario

- **Appium:** herramienta que automatiza apps móviles (Android/iOS) por fuera del código de la app
  (caja negra), usando el protocolo WebDriver.
- **UiAutomator2:** el "driver" que Appium usa para hablar con Android moderno.
- **Capabilities:** el conjunto de parámetros con los que se inicia una sesión de Appium
  (dispositivo, versión, APK, paquete, actividad). Acá se arman en `MobileConfig`.
- **Accessibility id:** el `content-desc` de un elemento Android. Es el selector recomendado:
  estable, rápido y portable; además mejora la accesibilidad real de la app.
- **APK:** el paquete instalable de una app Android.
- **AVD / emulador:** un dispositivo Android virtual sobre el que se instala y corre la app.
- **adb:** la herramienta de línea de comandos para hablar con dispositivos/emuladores Android.
- **Page Object Model (POM):** patrón donde cada pantalla es una clase que expone acciones de
  negocio; los tests no tocan selectores.
- **BDD / Gherkin / Cucumber:** describir el comportamiento en lenguaje de negocio
  (Dado/Cuando/Entonces) y automatizarlo.
- **Espera explícita:** esperar por una condición (elemento visible/clickeable) en lugar de un tiempo
  fijo. Elimina el flakiness de timing.
- **Modo simulado:** ejecutar los escenarios contra una implementación en memoria, sin emulador.
  Verifica la lógica de negocio y el cableado de la suite, **no** la UI real.
- **PicoContainer:** el contenedor de inyección que usa Cucumber para compartir objetos entre steps.
