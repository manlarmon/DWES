package Laboral;

/**
 * La clase Persona representa a una persona con información personal básica.
 */
public class Persona {

    public String nombre;
    public String dni;
    public char sexo;

    /**
     * Constructor para crear una persona con información completa.
     *
     * @param nombre El nombre de la persona.
     * @param dni    El número de DNI de la persona.
     * @param sexo   El sexo de la persona ('M' para masculino, 'F' para femenino).
     */
    public Persona(String nombre, String dni, char sexo) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
    }

    /**
     * Constructor para crear una persona con información básica (sin DNI).
     *
     * @param nombre El nombre de la persona.
     * @param sexo   El sexo de la persona. 'M' para masculino, 'F' para femenino.
     */
    public Persona(String nombre, char sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    /**
     * Establece el número de DNI de la persona.
     *
     * @param dni El nuevo número de DNI de la persona.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Imprime los detalles de la persona, incluyendo su nombre y DNI.
     */
    public void Imprime() {
        System.out.println("Nombre: " + this.nombre);
        if (this.dni != null) {
            System.out.println("DNI: " + this.dni);
        }
    }
}
