# language: es
Característica: Transferencias en la billetera Nexo Wallet
  Como usuario autenticado
  Quiero transferir dinero entre cuentas
  Para mover mis fondos, con las validaciones del negocio

  Antecedentes:
    Dado que "ana" inició sesión con la contraseña "ana123"

  Escenario: Transferencia exitosa
    Cuando transfiere "1000" desde "acc-ana-001" hacia "acc-luis-001"
    Entonces la transferencia es exitosa

  Escenario: Rechazo por fondos insuficientes
    Cuando transfiere "999999" desde "acc-ana-002" hacia "acc-luis-001"
    Entonces ve el error de transferencia "Fondos insuficientes en la cuenta de origen."

  Escenario: Rechazo por origen y destino iguales
    Cuando transfiere "100" desde "acc-ana-001" hacia "acc-ana-001"
    Entonces ve el error de transferencia "Origen y destino no pueden ser la misma cuenta."

  Escenario: Rechazo por monto inválido
    Cuando transfiere "0" desde "acc-ana-001" hacia "acc-luis-001"
    Entonces ve el error de transferencia "El monto debe ser mayor a 0."
