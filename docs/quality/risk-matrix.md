# Matriz de riesgos

Escala: Alto / Medio / Bajo.

## Riesgos técnicos

| ID | Riesgo | Impacto | Prob. | Mitigación | Verificación |
|---|---|---|---|---|---|
| RT1 | **Divergencia entre el modo simulado y el real** | Alto | Media | Regla del CONTRIBUTING: toda acción de `WalletActions` se implementa en ambos; el contrato es único | Revisión de PR; corridas periódicas del modo real |
| RT2 | **La suite no puede correr sin emulador** | Alto | Alta | Abstracción de dos modos (ADR-002): el simulado corre siempre | `mvn test` en verde en CI |
| RT3 | **Selectores frágiles** ante rediseños | Medio | Media | Accessibility ids (ADR-003) encapsulados en Page Objects | Sin XPath ni texto en el repo |
| RT4 | **Flakiness de timing** en la app real | Alto | Media | Esperas explícitas; nunca `sleep` | Corridas repetidas del modo real |
| RT5 | **Incompatibilidad de versiones Appium/Selenium** | Medio | Media | Selenium fijado explícitamente (java-client usa rangos) | Compilación en CI |
| RT6 | **Emulador lento o inestable en CI** | Medio | Alta | Aceptado: CI corre el modo simulado; el real se ejecuta bajo demanda | Documentado |

## Riesgos de negocio (cobertura)

| ID | Riesgo | Impacto | Prob. | Mitigación | Verificación |
|---|---|---|---|---|---|
| RB1 | **Falso sentido de cobertura**: creer que el modo simulado prueba la app | Alto | Media | Declaración explícita en README, `evidence/` y en la plantilla de PR | Auditoría de la evidencia |
| RB2 | Un journey crítico sin cubrir | Alto | Baja | Trazabilidad regla→escenario | `traceability.md` |

## Lectura

**RB1 es el riesgo más importante de este repositorio**, y es un riesgo de *comunicación*, no de
código: la mitigación es la honestidad explícita sobre qué se ejecutó y qué no.
