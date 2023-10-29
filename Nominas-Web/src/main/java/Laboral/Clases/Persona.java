package Laboral.Clases;

/**
 * La clase Persona representa a una persona con información personal básica.
 */
public class Persona {

    private String nombre;
    private String dni;
    private char sexo;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    /**
     * Establece el número de DNI de la persona.
     *
     * @param dni El nuevo número de DNI de la persona.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    /**
     * Imprime los detalles de la persona, incluyendo su nombre y DNI.
     */
    public void Imprime() {
        System.out.println("Nombre: " + this.nombre + ", DNI: " + this.dni);
    }


    
}
