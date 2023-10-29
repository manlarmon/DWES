package Laboral.Clases;

import Laboral.Excepciones.DatosNoCorrectosException;

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
	public Empleado(String nombre, String dni, char sexo, int categoria, double anyos)
			throws DatosNoCorrectosException {
		super(nombre, dni, sexo);

		if (categoria <= 0 || categoria > 10) {
			throw new DatosNoCorrectosException("Categoría fuera del rango establecido (1-10)");
		}
		if (anyos < 0) {
			throw new DatosNoCorrectosException("Año introducido erróneo, no puede ser negativo");
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

	public double getAnyos() {
		return anyos;
	}

	public void setAnyos(double anyos) throws DatosNoCorrectosException {
		if (anyos<0) {
			throw new DatosNoCorrectosException("Los anyos no pueden ser negativos");
		}else{
			this.anyos = anyos;
		}
	}

	/**
	 * Establece la categoría laboral del empleado si es válida (entre 1 y 9).
	 *
	 * @param categoria La nueva categoría laboral del empleado.
	 */
	public void setCategoria(int categoria) throws DatosNoCorrectosException {
		if (categoria >= 1 && categoria <= 10) {
			this.categoria = categoria;
		} else {
			throw new DatosNoCorrectosException("Categoría fuera del rango establecido (1-10)");

		}
	}

	/**
	 * Incrementa en uno los años de servicio del empleado.
	 */
	public void incrAnyo() {
		this.anyos++;
	}

	/**
	 * Imprime los detalles del empleado, incluyendo su información personal y
	 * laboral.
	 */
	@Override
	public void Imprime() {
		System.out.println("Nombre: " + this.getNombre() + ", DNI: " + this.getDni() + ", Sexo: " + this.getSexo() + ", Categoria: " + this.getCategoria() +
				", Años de servicio: " + this.getAnyos());
	}



	
}
