# Definición de Hecho (Definition of Done)

Checklist del Pull Request.

- [ ] **Funciona:** `mvn test` en verde (modo simulado).
- [ ] **Ambos modos:** si se agregó una acción a `WalletActions`, está implementada en
      `SimulatedWallet` **y** en `AppiumWallet`.
- [ ] **Probado:** el journey nuevo/cambiado tiene su escenario con aserciones sobre el resultado.
- [ ] **Mantenible:** interacciones a través de Page Objects; selectores por accessibility id.
- [ ] **Estable:** esperas explícitas; sin `Thread.sleep`.
- [ ] **Trazable:** `traceability.md` actualizado.
- [ ] **Documentado:** README/docs afectados; ADR si hubo una decisión con alternativas.
- [ ] **Seguro:** no se versionan APKs, secretos ni credenciales.
- [ ] **Honesto:** la evidencia declara **explícitamente** si se ejecutó en modo simulado, real, o ambos.

## Por qué el último punto

Es el más importante de este repositorio: presentar una corrida simulada como si fuera una corrida
en dispositivo destruiría la confianza en toda la evidencia del portfolio.
