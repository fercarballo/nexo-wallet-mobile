# Runbook — Setup local

## A) Modo simulado (sin emulador) — recomendado para empezar

| Herramienta | Versión |
|---|---|
| JDK | 21 |
| Maven | 3.9+ |

```bash
export JAVA_HOME="$(brew --prefix openjdk@21)/libexec/openjdk.jdk/Contents/Home"
export PATH="$JAVA_HOME/bin:$PATH"
mvn test        # 8 pruebas en verde
```

No hace falta nada más: la billetera simulada corre en memoria.

## B) Modo real (Appium + emulador Android)

> Este modo **no se ejecutó** en el entorno donde se construyó el repositorio. Los pasos siguientes
> son la guía precisa para correrlo donde sí haya un emulador.

### 1. Android SDK y emulador

```bash
# Con Android Studio, o con las command-line tools:
brew install --cask android-commandlinetools
sdkmanager "platform-tools" "platforms;android-34" "system-images;android-34;google_apis;arm64-v8a"
avdmanager create avd -n Pixel_7_API_34 -k "system-images;android-34;google_apis;arm64-v8a"
emulator -avd Pixel_7_API_34 &
adb devices        # debe listar el emulador
```

### 2. Appium Server

```bash
npm install -g appium
appium driver install uiautomator2
appium            # queda escuchando en http://127.0.0.1:4723
```

### 3. La app (APK)

Colocá el APK de Nexo Wallet en `apps/nexo-wallet.apk` (no se versiona) o apuntá `NEXO_APP` a su ruta.

### 4. Variables de entorno

Copiá `.env.example` a `.env` y ajustá los valores (URL de Appium, dispositivo, versión, APK,
paquete y actividad).

### 5. Correr

```bash
mvn test -DwalletMode=appium
```

## Verificar la configuración sin emulador

`AppiumCapabilitiesTest` comprueba que las capabilities se construyen correctamente desde la
configuración. Corre siempre, con `mvn test`.
