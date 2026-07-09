# ADR-002 — Abstracción `WalletActions` con modo simulado y modo real

- **Estado:** Aceptada · **Fecha:** 2026-07-09

## Contexto

Appium requiere un emulador o dispositivo. Eso implica que, sin una granja de dispositivos, la suite
**no puede correr en CI ni en la máquina de cualquier integrante**. Necesitamos que los escenarios
de negocio sean verificables siempre, sin renunciar a la automatización real.

## Decisión

Los escenarios hablan una **abstracción** (`WalletActions`), no Appium. Dos implementaciones:

- **`SimulatedWallet`** — en memoria, imita el contrato. Sin emulador. **Modo por defecto** (CI y local).
- **`AppiumWallet`** — conduce la app Android real vía Appium. Se activa con `-DwalletMode=appium`.

Una sola perilla intercambia el destino; features y steps quedan intactos.

## Alternativas consideradas

| Alternativa | Por qué se descartó |
|---|---|
| **Solo modo real** | La suite no correría en CI ni sin emulador; nadie podría verificarla. |
| **Mockear el AndroidDriver** | Se probaría el mock de Appium, no el comportamiento de negocio. Frágil y de poco valor. |
| **Granja de dispositivos en la nube** (BrowserStack/Sauce) | Válido y documentado como extensión, pero requiere cuenta y credenciales; fuera del alcance de un portfolio reproducible. |

## Consecuencias

- (+) Los escenarios de negocio se verifican **siempre**, en cualquier entorno y en CI.
- (+) El código Appium real permanece en el repositorio, listo para correr donde haya un emulador.
- (−) El modo simulado **no ejercita Appium ni la UI real**. Esto se declara explícitamente en el
  README y en `evidence/`: nunca se presenta como si fuera una corrida en dispositivo.
- (Riesgo) Las dos implementaciones pueden divergir. Se mitiga con la regla del CONTRIBUTING: toda
  acción nueva se implementa en ambas.

## Precedente

Es el mismo patrón que se usa para dependencias gateadas (proveedor de LLM, servicios de pago,
Jira/Xray): interfaz + implementación simulada + adaptador real por configuración.
