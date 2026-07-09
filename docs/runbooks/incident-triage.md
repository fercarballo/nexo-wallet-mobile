# Runbook — Triage de incidentes (fallos de la suite)

Guía para actuar cuando la suite se pone en rojo.

## 1. Primera pregunta: ¿en qué modo falló?

| Modo | Qué significa un rojo |
|---|---|
| **Simulado** | Falla la **lógica de negocio** o el cableado de la suite. No involucra la app ni Appium. Es reproducible en cualquier máquina. |
| **Real (Appium)** | Puede ser la app, el emulador, el servidor Appium o un test frágil. |

Un rojo en simulado es **siempre** un problema de código o de escenario: empezar por ahí.

## 2. Clasificar el rojo (modo real)

| Tipo | Señal | Acción |
|---|---|---|
| **Bug de la app** | Falla consistentemente con un mensaje de negocio coherente | Reportar defecto |
| **Entorno** | `No se pudo crear el AndroidDriver`, emulador caído, driver ausente | Arreglar el entorno (ver `troubleshoot.md`) |
| **Test frágil** | Intermitente; pasa al reintentar | Prioridad alta: revisar la espera explícita. Nunca `sleep` ni reintentos para taparlo |
| **Contrato de testabilidad roto** | `NoSuchElementException` en un accessibility id | La app dejó de exponer un `content-desc`: coordinar con desarrollo (ADR-003) |
| **Divergencia entre modos** | Verde en simulado, rojo en real (o al revés) | Riesgo RT1: una implementación quedó desactualizada |

## 3. Recolectar evidencia

```bash
adb logcat -d | tail -100          # logs del dispositivo
appium --log-level debug           # logs del servidor Appium
```

`appium inspector` permite ver la jerarquía real y los `content-desc` disponibles.

## 4. Cierre

Todo incidente que revele un hueco de cobertura se convierte en un **escenario nuevo**, implementado
en **ambos modos**, y se registra en `traceability.md`.
