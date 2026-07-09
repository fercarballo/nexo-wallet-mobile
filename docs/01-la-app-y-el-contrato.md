# La aplicación y el contrato que imita

## La aplicación bajo prueba

**Nexo Wallet**, una billetera Android ficticia con tres pantallas:

- **Ingreso**: usuario y contraseña.
- **Principal**: saludo y saldos de las cuentas del usuario.
- **Transferencia**: origen (selector), destino, monto y confirmación.

Cada elemento relevante expone un **accessibility id** (`content-desc`) estable: `username`,
`password`, `login-button`, `login-error`, `welcome`, `balance-<cuenta>`, `nav-transfer`,
`source-account`, `source-option-<cuenta>`, `dest-account`, `amount`, `submit-transfer`,
`transfer-success`, `transfer-error`.

> El APK no se versiona (ver `.gitignore`). Su ubicación se configura por entorno (`.env.example`).

## Por qué "imita el contrato"

El canal mobile aplica las **mismas reglas de negocio** que `nexo-transfer-api`: titularidad de la
cuenta de origen, fondos suficientes, monto mayor a cero y cuentas distintas. Los datos ficticios
también coinciden con los de la API y del canal web, de modo que los tres canales cuentan la misma
historia.

## Datos ficticios (seed)

| Usuario | Contraseña | Cuentas |
|---|---|---|
| `ana` | `ana123` | `acc-ana-001` (100.000 ARS), `acc-ana-002` (5.000 ARS) |
| `luis` | `luis123` | `acc-luis-001` (25.000 ARS) |

## Reglas verificadas

Ingreso válido/inválido; transferencia exitosa; y los rechazos por fondos insuficientes, cuentas
iguales y monto inválido. Correspondencia regla→escenario en
[quality/traceability.md](quality/traceability.md).
