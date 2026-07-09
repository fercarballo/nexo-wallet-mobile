# language: es
Característica: Ingreso a la billetera Nexo Wallet
  Como usuario de la billetera
  Quiero ingresar con mis credenciales
  Para ver mis cuentas de forma segura

  Escenario: Ingreso exitoso con credenciales válidas
    Cuando ingresa con usuario "ana" y contraseña "ana123"
    Entonces ve el saludo "Hola, Ana"
    Y ve el saldo "100.000,00 ARS" en la cuenta "acc-ana-001"

  Escenario: Rechazo con credenciales inválidas
    Cuando ingresa con usuario "ana" y contraseña "incorrecta"
    Entonces ve el mensaje de error de ingreso "Usuario o contraseña incorrectos."
