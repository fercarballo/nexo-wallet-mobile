# Arquitectura — Componentes del framework (C4 nivel 2)

```mermaid
flowchart TB
    subgraph suite["Suite (Maven / JUnit Platform)"]
        runner["RunWalletTests"]
        feat["features/*.feature"]
        steps["stepdefs/WalletSteps"]
        hooks["hooks/ScenarioHooks"]
    end
    api{{"WalletActions (abstracción)"}}
    factory["WalletFactory<br/>(-DwalletMode)"]
    sim["SimulatedWallet<br/>(en memoria)"]
    app["AppiumWallet"]
    screens["screens/ (Page Objects)"]
    base["BaseScreen (esperas explícitas)"]
    drv["AndroidDriver (UiAutomator2)"]
    dev["Emulador / dispositivo + APK"]

    runner --> feat --> steps --> api
    hooks --> factory --> api
    api --> sim
    api --> app --> screens --> base --> drv --> dev
    style api fill:#1a3a5c,color:#fff
    style sim fill:#1e7a4f,color:#fff
```

## Componentes

| Componente | Responsabilidad |
|---|---|
| **features/** | Escenarios de negocio (Gherkin, español). |
| **stepdefs/** | Traducen cada paso a llamadas de `WalletActions`. **No conocen Appium.** |
| **WalletActions** | La abstracción: `login`, `welcome`, `balanceOf`, `transfer`, `loginError`. |
| **SimulatedWallet** | Implementación en memoria; imita el contrato. Corre sin emulador. |
| **AppiumWallet** | Implementación real; conduce la app con Page Objects mobile. |
| **WalletFactory** | Elige el modo (`simulated` por defecto, `appium` con la propiedad). |
| **BaseScreen** | Esperas explícitas y localización por accessibility id. |

## Compartir estado entre steps

`TestContext` (inyectado por **PicoContainer**) guarda la billetera activa del escenario, de modo
que hooks y steps operen sobre la misma instancia sin estado estático.
