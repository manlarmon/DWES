package Laboral.Excepciones;

/**
 * La excepción DatosNoCorrectosException se lanza cuando se encuentran datos incorrectos
 * al crear un objeto de la clase Empleado.
 */
public class DatosNoCorrectosException extends Exception {

    /**
     * Constructor que recibe un mensaje de error para la excepción.
     *
     * @param mensaje El mensaje de error que describe la causa de la excepción.
     */
    public DatosNoCorrectosException(String mensaje) {
        super(mensaje);
    }
}
