package com.Nomina.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "empleados")
public class Empleado {

	@Id
	@Column(name = "dni", nullable = false, length = 50)
	private String dni;

	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;

	@Column(name = "sexo", nullable = false, length = 1)
	private char sexo;

	@Column(name = "categoria", nullable = false, length = 50)
	private int categoria;

	@Column(name = "anyos", nullable = false, length = 50)
	private double anyos;

	@Column(name = "nomina", nullable = false, length = 255)
	private double nomina;

	private static final int SUELDO_BASE[] = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000,
			230000 };

	public Empleado() {

	}

	public Empleado(String DNI, String nombre, char sexo, int categoria, double anyos) {
		super();
		dni = DNI;
		this.nombre = nombre;
		this.sexo = sexo;
		this.categoria = categoria;
		this.anyos = anyos;
		this.nomina = calculaNomina(categoria, anyos);
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String id) {
		this.dni = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public double getAnyos() {
		return anyos;
	}

	public void setAnyos(double anyos) {
		this.anyos = anyos;
	}

	public double getNomina() {
		return nomina;
	}

	public void setNomina(int categoria, double anyos) {
		this.nomina = calculaNomina(categoria, anyos);
	}

	public static int[] getSueldoBase() {
		return SUELDO_BASE;
	}

	public double calculaNomina(int categoria, double anyos) {
		int sueldoBase = SUELDO_BASE[categoria - 1];
		double sueldo = sueldoBase + 5000 * anyos;
		return sueldo;
	}

	@Override
	public String toString() {
		return "Empleado [getDni()=" + getDni() + ", getNombre()=" + getNombre() + ", getSexo()=" + getSexo()
				+ ", getCategoria()=" + getCategoria() + ", getAnyos()=" + getAnyos() + "]";
	}

}
