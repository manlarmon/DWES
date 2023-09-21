package Laboral;

/**
 * La clase CalculaNominas se utiliza para calcular y mostrar las nóminas de empleados.
 */
public class CalculaNominas {

    /**
     * El método principal de la aplicación.
     *
     * @param args Los argumentos de la línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        try {
            // Crear dos empleados
            Empleado e1 = new Empleado("James Cosling", "32000032G", 'M', 4, 7);
            Empleado e2 = new Empleado("Ada Lovelace", "32000031R", 'F');

            // Muestra los detalles iniciales de los empleados
            escribe(e1, e2);

            // Incrementa el año de servicio del segundo empleado
            e2.incrAnyo();

            // Cambia la categoría del primer empleado
            e1.setCategoria(9);

            // Muestra los detalles actualizados de los empleados
            escribe(e1, e2);

        } catch (DatosNoCorrectosException e) {
            System.out.println("Datos no correctos: " + e.getMessage());
        }
    }

    /**
     * Muestra los detalles y el sueldo de un empleado.
     *
     * @param e1 El primer empleado.
     * @param e2 El segundo empleado.
     */
    private static void escribe(Empleado e1, Empleado e2) {
        Nomina n = new Nomina();

        // Muestra los detalles del primer empleado
        e1.Imprime();
        System.out.println("Sueldo: " + n.sueldo(e1) + "\n");

        // Muestra los detalles del segundo empleado
        e2.Imprime();
        System.out.println("Sueldo: " + n.sueldo(e2) + "\n");
    };
}