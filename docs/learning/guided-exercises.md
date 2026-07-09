# Ejercicios guiados

Después de cada uno: `mvn test`.

## 1. (Fácil) Nuevo escenario: cuenta de destino inexistente

La regla ya existe en `SimulatedWallet` ("La cuenta de destino no existe."). Agregá el escenario en
`transfer.feature` reutilizando los steps existentes y actualizá `traceability.md`.

## 2. (Media) Verificar el saldo después de transferir

1. Agregá un step "Entonces el saldo de {string} es {string}".
2. Usá `WalletActions.balanceOf(...)` (ya existe en ambas implementaciones).
3. Escenario: transferir 1000 desde `acc-ana-001` y verificar el nuevo saldo.

## 3. (Media) Nueva acción en la abstracción: cerrar sesión

1. Agregá `logout()` a `WalletActions`.
2. Implementalo en **ambas**: `SimulatedWallet` (limpia el usuario) y `AppiumWallet`
   (toca el accessibility id `logout`).
3. Escenario: tras cerrar sesión, ingresar de nuevo funciona.

> Este ejercicio enseña la regla más importante del repo: **toda acción vive en los dos modos**.

## 4. (Avanzada) Correr el modo real

Seguí `runbooks/local-setup.md` (Android SDK, emulador, Appium, APK) y ejecutá
`mvn test -DwalletMode=appium`. Documentá el resultado en `evidence/` — indicando **explícitamente**
que fue una corrida en dispositivo.

## 5. (Avanzada) Granja de dispositivos en la nube

Agregá un tercer modo que apunte las capabilities a un proveedor cloud (credenciales por entorno).
Ninguna feature ni step debería cambiar: esa es la prueba de que la abstracción está bien diseñada.
