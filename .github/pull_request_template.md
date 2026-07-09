# Pull Request

## Qué resuelve
<!-- Descripción y requisito/escenario que aborda. -->

## Tipo de cambio
- [ ] feat (escenario / capacidad del framework)
- [ ] fix (corrección / estabilización)
- [ ] docs
- [ ] refactor / chore

## Checklist (Definición de Hecho)
- [ ] `mvn test` en verde (modo simulado)
- [ ] Si agregué una acción a `WalletActions`, está implementada en **ambos** modos
- [ ] Sin `Thread.sleep`; esperas explícitas
- [ ] Selectores por accessibility id
- [ ] No versiono APKs, secretos ni credenciales
- [ ] Documentación afectada actualizada (incl. `traceability.md`)

## Evidencia
<!-- Salida real de `mvn test`. Si corriste el modo real (emulador), indicalo explícitamente. -->

## Alcance de verificación
- [ ] Verificado en modo **simulado**
- [ ] Verificado en modo **real (Appium + emulador)**
