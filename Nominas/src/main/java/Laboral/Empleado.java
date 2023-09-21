package Laboral;

/**
 * La clase Empleado representa a un empleado con información personal y
 * laboral. Extiende la clase Persona.
 */
public class Empleado extends Persona {

	/** Propiedad de empleado que indica su categoría laboral **/
	private int categoria;
	/** Propiedad de empleado que indica los años que lleva trabajados **/
	public double anyos;

	/**
	 * Constructor para crear un empleado con información completa.
	 *
	 * @param nombre    El nombre del empleado.
	 * @param dni       El número de DNI del empleado.
	 * @param sexo      El sexo del empleado ('M' para masculino, 'F' para
	 *                  femenino).
	 * @param categoria La categoría laboral del empleado.
	 * @param anyos     Los años trabajados del empleado.
	 * @throws DatosNoCorrectosException Si los datos del empleado no son correctos.
	 */
	public Empleado(String nombre, String dni, char sexo, int categoria, int anyos) throws DatosNoCorrectosException {
		super(nombre, dni, sexo);

		if (categoria <= 0 || categoria > 10 || anyos < 0) {
			throw new DatosNoCorrectosException("Los datos del empleado no son correctos");
		}

		this.categoria = categoria;
		this.anyos = anyos;
	}

	/**
	 * Constructor para crear un empleado con información básica.
	 *
	 * @param nombre El nombre del empleado.
	 * @param dni    El número de DNI del empleado.
	 * @param sexo   El sexo del empleado ('M' para masculino, 'F' para femenino).
	 */
	public Empleado(String nombre, String dni, char sexo) {
		super(nombre, dni, sexo);
		this.categoria = 1;
		this.anyos = 0;
	}

	/**
	 * Obtiene la categoría laboral del empleado.
	 *
	 * @return La categoría laboral del empleado.
	 */
	public int getCategoria() {
		return categoria;
	}

	/**
	 * Establece la categoría laboral del empleado si es válida (entre 1 y 9).
	 *
	 * @param categoria La nueva categoría laboral del empleado.
	 */
	public void setCategoria(int categoria) {
		if (categoria >= 1 && categoria <= 9) {
			this.categoria = categoria;
		}
	}

    /**
     * Incrementa en uno los años de servicio del empleado.
     */
    public void incrAnyo() {
        this.anyos++;
    }

    /**
     * Imprime los detalles del empleado, incluyendo su información personal y laboral.
     */
    @Override
    public void Imprime() {
        super.Imprime();
        System.out.println("Sexo: " + this.sexo + "\nCategoria: " + this.categoria +
                "\nAños de servicio: " + this.anyos);
    }
}
