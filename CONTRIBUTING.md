# Cómo contribuir

## Flujo

1. Rama desde `main`: `git checkout -b feat/mi-cambio`.
2. Incrementos verticales: escenario + acción en `WalletActions` (si hace falta) + ambas
   implementaciones (simulada y Appium) + documentación + evidencia.
3. Verificá localmente antes del PR.
4. Abrí el PR con la plantilla.

## Regla de las dos implementaciones

Si agregás una acción a `WalletActions`, **debés implementarla en las dos**: `SimulatedWallet`
(para que la suite siga corriendo sin emulador) y `AppiumWallet` (para el dispositivo real). Si no,
los modos divergen y la suite deja de ser confiable.

## Verificación local (obligatoria)

```bash
mvn test                        # modo simulado — debe quedar en verde
mvn test -DwalletMode=appium    # si tenés emulador: verificá también el modo real
```

## Estándar de commits

```
feat: agrega el escenario de cuenta de destino inexistente
fix: estabiliza la espera del saldo en la pantalla principal
docs: documenta cómo levantar el emulador
```

## Reglas de oro

- **Nunca** afirmar que algo se ejecutó sin la salida real. Si sólo corriste el modo simulado, decilo.
- **Esperas explícitas**, jamás `Thread.sleep`.
- **Selectores por accessibility id**, no por texto ni XPath frágil.
- No versionar APKs, secretos ni credenciales. Todo por variables de entorno (`.env.example`).

La Definición de Hecho completa está en
[`docs/quality/definition-of-done.md`](docs/quality/definition-of-done.md).
