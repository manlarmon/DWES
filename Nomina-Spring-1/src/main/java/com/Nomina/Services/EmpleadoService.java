package com.Nomina.Services;

import java.util.List;

import com.Nomina.Entities.Empleado;

public interface EmpleadoService {

	public List<Empleado> listAllEmpleados();

	public Empleado createEmpleado(Empleado empleado);

	public Empleado findEmpleadoByDni(String dni);

	public List<Empleado> findEmpleadoLikeDni(String dni);

	public List<Empleado> findEmpleadoLikeNombre(String nombre);

	public List<Empleado> findEmpleadoBySexo(char sexo);

	public List<Empleado> findEmpleadoByCategoria(int categoria);

	public List<Empleado> findEmpleadoByAnyos(double anyos);

	public List<Empleado> findEmpleadoLikeAnyos(double anyos);

	public Empleado updateEmpleado(Empleado empleado);

	public void deleteEmpleadoByDni(String dni);
}
