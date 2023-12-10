package com.Nomina.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Nomina.Entities.Empleado;
import com.Nomina.Repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	// Inyecta las dependencias de forma automática, los objetos no crean las
	// dependencias, se las proporcionan del exterior (contenedor de Spring)
	private EmpleadoRepository empleadoRepository;

	@Override
	public List<Empleado> listAllEmpleados() {
		return empleadoRepository.findAll();
	}

	@Override
	public Empleado createEmpleado(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	@Override
	public Empleado findEmpleadoByDni(String dni) {
		return empleadoRepository.findById(dni).get();
	}

	@Override
	public List<Empleado> findEmpleadoLikeDni(String dni) {
		return empleadoRepository.findLikeDni(dni);

	}

	@Override
	public List<Empleado> findEmpleadoLikeNombre(String nombre) {
		return empleadoRepository.findLikeNombre(nombre);
	}

	@Override
	public List<Empleado> findEmpleadoBySexo(char sexo) {
		return empleadoRepository.findBySexo(sexo);
	}

	@Override
	public List<Empleado> findEmpleadoByCategoria(int categoria) {
		return empleadoRepository.findByCategoria(categoria);
	}

	@Override
	public List<Empleado> findEmpleadoByAnyos(double anyos) {
		return empleadoRepository.findByAnyos(anyos);
	}

	@Override
	public List<Empleado> findEmpleadoLikeAnyos(double anyos) {
		return empleadoRepository.findLikeAnyos(anyos);
	}

	@Override
	public Empleado updateEmpleado(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	@Override
	public void deleteEmpleadoByDni(String dni) {
		empleadoRepository.deleteById(dni);
	}

}
