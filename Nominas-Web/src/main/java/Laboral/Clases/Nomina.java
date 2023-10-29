package Laboral.Clases;

/**
 * La clase Nomina se utiliza para calcular el sueldo de un empleado.
 */
public class Nomina {

    // Array que contiene los sueldos base para cada categoría (índice 0 es categoría 1, índice 9 es categoría 10)
    private static final int SUELDO_BASE[] = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000 };

    /**
     * Calcula el sueldo de un empleado. Sueldo = Su sueldo base por categoría más 5000 por los años trabajados.
     *
     * @param e El empleado del que se calculará el sueldo.
     * @return El sueldo calculado para el empleado.
     */
    public double sueldo(Empleado e) {
        int sueldoBase = SUELDO_BASE[e.getCategoria() - 1];
        double sueldo = sueldoBase + 5000 * e.anyos;
        return sueldo;
    }
}
