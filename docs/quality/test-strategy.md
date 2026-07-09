# Estrategia de pruebas

## Objetivo

Verificar los **journeys de negocio** del canal mobile (ingreso, saldos, transferencia) de forma
legible, estable y **verificable siempre**, incluso sin una granja de dispositivos.

## Niveles y responsabilidades

| Qué | Dónde se prueba | Por qué ahí |
|---|---|---|
| Reglas de negocio del servicio | `nexo-transfer-api` (unitarias + API) | Es su fuente de la verdad; barato y rápido. |
| Journeys del **canal web** | `nexo-web-banking-e2e` (Selenium) | Regresión web profunda. |
| Journeys del **canal mobile** | **este repo** (Appium) | Regresión mobile profunda. |
| Smoke cross-channel | `nexo-cross-channel-regression` (Katalon) | Un puñado de journeys de punta a punta. |

No se duplica cobertura: Appium **no** reimplementa la validación de reglas que ya cubre la API.

## Los dos modos de ejecución

```mermaid
flowchart LR
    s["Modo simulado (default)<br/>✅ siempre verificable · CI"] --- r["Modo real (Appium)<br/>🔴 requiere emulador"]
    style s fill:#1e7a4f,color:#fff
    style r fill:#b8860b,color:#fff
```

- El **simulado** verifica que la suite de negocio está completa y correcta (features, steps, reglas).
- El **real** verifica, además, la UI y la integración con la app. Se corre donde haya un emulador
  (local o una granja de dispositivos), y su resultado se declara explícitamente.

Este repositorio **nunca** presenta una corrida simulada como si fuera una corrida en dispositivo.

## Prácticas anti-flakiness

Esperas explícitas (`BaseScreen`), selectores por accessibility id (ADR-003), aislamiento por
escenario (billetera nueva y estado re-sembrado).

## Fuera de alcance (por ahora)

iOS, pruebas de accesibilidad automatizadas, gestos complejos (swipe/scroll largos), y la granja de
dispositivos en la nube.
