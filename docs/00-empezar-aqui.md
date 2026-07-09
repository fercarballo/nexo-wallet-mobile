# Empezar aquí

Punto de entrada para quien llega por primera vez.

## En qué orden leer

1. **[README.md](../README.md)** — qué es, el estado de verificación y cómo correr.
2. **[01-la-app-y-el-contrato.md](01-la-app-y-el-contrato.md)** — la billetera y el contrato que imita.
3. **[architecture/containers.md](architecture/containers.md)** — cómo está armado el framework.
4. **[adr/](adr/)** — por qué se decidió así (Appium+Cucumber+POM, la abstracción de dos modos, los selectores).
5. **[quality/test-strategy.md](quality/test-strategy.md)** — la estrategia de pruebas.

## Correr en 1 paso (sin emulador)

```bash
mvn test        # modo simulado: 8 pruebas en verde
```

## Correr contra un emulador real

Requiere Android SDK, un emulador, Appium Server y el APK. La guía completa está en
**[runbooks/local-setup.md](runbooks/local-setup.md)**.

```bash
mvn test -DwalletMode=appium
```

## ¿Un término no te suena?

Appium, UiAutomator2, accessibility id, capabilities, BDD… están en
**[learning/glossary.md](learning/glossary.md)**.
