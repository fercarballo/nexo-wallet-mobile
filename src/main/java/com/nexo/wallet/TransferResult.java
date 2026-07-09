package com.nexo.wallet;

/**
 * Resultado de una transferencia: si fue exitosa y el mensaje mostrado al usuario (de éxito o de
 * error). Es el contrato que verifican los escenarios, igual en modo simulado y en modo real.
 */
public record TransferResult(boolean success, String message) {

  public static TransferResult ok(String message) {
    return new TransferResult(true, message);
  }

  public static TransferResult failed(String message) {
    return new TransferResult(false, message);
  }
}
