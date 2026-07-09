# Trazabilidad regla → escenario

| Regla de negocio | Escenario (feature) | Cubierto en modo simulado | Cubierto en modo real |
|---|---|---|---|
| Ingreso con credenciales válidas | "Ingreso exitoso" (`login`) | ✅ | 🔴 no ejecutado aquí |
| Rechazo de credenciales inválidas | "Rechazo con credenciales inválidas" | ✅ | 🔴 |
| Saldo visible por cuenta | "Ingreso exitoso" (aserción de saldo) | ✅ | 🔴 |
| Transferencia exitosa | "Transferencia exitosa" (`transfer`) | ✅ | 🔴 |
| Fondos insuficientes | "Rechazo por fondos insuficientes" | ✅ | 🔴 |
| Origen = destino | "Rechazo por origen y destino iguales" | ✅ | 🔴 |
| Monto inválido (≤ 0) | "Rechazo por monto inválido" | ✅ | 🔴 |
| Capabilities de Appium bien construidas | `AppiumCapabilitiesTest` | ✅ (2 pruebas) | n/a |

🔴 = el código existe y compila, pero no se ejecutó en este entorno (sin emulador). Ver `evidence/`.

## Correspondencia con el contrato de la API

Las reglas espejan las de `nexo-transfer-api` (repo 1): titularidad, fondos, monto, cuentas
distintas. La API las prueba a nivel de servicio; aquí se validan a nivel del **canal mobile**.

## Huecos conocidos (backlog)

- Cuenta de destino inexistente (implementada; falta escenario dedicado).
- Verificación del saldo **después** de una transferencia exitosa.
- Ejecución del modo real en una granja de dispositivos.

## Mantenimiento

Cada escenario nuevo o modificado actualiza esta tabla en el mismo PR.
