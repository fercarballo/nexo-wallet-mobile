package com.nexo.wallet;

/**
 * Acciones que la automatización necesita ejecutar sobre la billetera, con independencia de si
 * corren contra un dispositivo real (Appium) o contra una implementación simulada.
 *
 * <p>Esta abstracción es la clave del repositorio: los escenarios de Cucumber hablan este lenguaje
 * y NO conocen Appium. Así, la misma suite de negocio corre en dos modos:
 * <ul>
 *   <li><b>simulado</b> (por defecto): sin emulador, verificable en cualquier entorno y en CI;</li>
 *   <li><b>appium</b> (real): contra la app Android en un emulador/dispositivo (ver documentación).</li>
 * </ul>
 * Es el mismo patrón mock-vs-proveedor-real que se usa para testear sistemas con dependencias
 * externas gateadas por device, licencia o credenciales.
 */
public interface WalletActions {

  /** Inicializa la sesión de automatización (arranca la app o el estado simulado). */
  void open();

  /** Intenta iniciar sesión. Devuelve {@code true} si fue exitoso. */
  boolean login(String username, String password);

  /** Mensaje de error del último intento de login (cadena vacía si no hubo error). */
  String loginError();

  /** Saludo mostrado tras un login exitoso, ej: "Hola, Ana". */
  String welcome();

  /** Saldo formateado de una cuenta, ej: "100.000,00 ARS". */
  String balanceOf(String accountId);

  /** Ejecuta una transferencia y devuelve su resultado (éxito + mensaje). */
  TransferResult transfer(String sourceAccount, String destinationAccount, String amount);

  /** Libera recursos (cierra el driver / limpia el estado). */
  void close();
}
