package com.Nomina.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Nomina.Entities.Empleado;
import com.Nomina.Services.EmpleadoService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	@GetMapping({ "empleados", "/" })
	// Model se utiliza para pasar atributos a la vista
	public String listAllEmpleados(Model model) {
		model.addAttribute("empleados", empleadoService.listAllEmpleados());
		return "empleados"; // archivo html de empleados
	}

	@GetMapping({ "/empleados/buscar" })
	// PathVariable para url y RequestParam para formularios
	public String findEmpleadosLike(Model model, @RequestParam String campo, @RequestParam String valor) {
		List<Empleado> empleados = new ArrayList<>();

		switch (campo) {
			case "dni":
				empleados = empleadoService.findEmpleadoLikeDni(valor);
				break;
			case "nombre":
				empleados = empleadoService.findEmpleadoLikeNombre(valor);
				break;
			case "sexo":
				char sexo = valor.charAt(0);
				empleados = empleadoService.findEmpleadoBySexo(sexo);
				break;
			case "categoria":
				int categoria = Integer.parseInt(valor);
				empleados = empleadoService.findEmpleadoByCategoria(categoria);
				break;
			case "anyos":
				double anyos = Double.parseDouble(valor);
				empleados = empleadoService.findEmpleadoLikeAnyos(anyos);
				break;
			default:
				empleados = empleadoService.listAllEmpleados();
				break;
		}
		model.addAttribute("empleados", empleados);
		return "empleados";
	}

	@GetMapping("/empleados/busca_nomina")
	public String findNomina(Model model) {
		return "find_nomina";
	}

	@GetMapping({ "/empleados/busca_nomina/buscar" })
	public String findNominaByDni(Model model, @RequestParam String dni) {
		Empleado empleado = new Empleado();
		empleado = empleadoService.findEmpleadoByDni(dni);
		model.addAttribute("empleado", empleado);
		return "find_nomina";
	}

	@GetMapping("/empleados/crear")
	public String createEmpleadoForm(Model model) {
		Empleado empleado = new Empleado();
		model.addAttribute("empleado", empleado);
		// Pasamos un objeto empleado para que en el formulario podamos asignarle los
		// atributos del objeto y retornarlo
		return "create_empleado";
	}

	@PostMapping("/empleados/crear")
	public String createEmpleado(@ModelAttribute("empleado") Empleado empleado) {
		empleado.setNomina(empleado.getCategoria(), empleado.getAnyos());
		empleadoService.createEmpleado(empleado);
		return "redirect:/empleados";
	}

	@GetMapping("/empleados/editar/{dni}")
	public String editEmpleadoForm(@PathVariable String dni, Model model) {
		Empleado empleadoEncontrado = empleadoService.findEmpleadoByDni(dni);
		model.addAttribute("empleado", empleadoEncontrado);
		return "edit_empleado";
	}

	@PostMapping("/empleados/editar/{dni}")
	public String editEmpleado(@PathVariable String dni, @ModelAttribute("empleado") Empleado empleado, Model model) {
		// Antes de actualizar, obtengo el empleado existente por su dni
		Empleado empleadoEncontrado = empleadoService.findEmpleadoByDni(dni);

		if (empleadoEncontrado != null) {
			// Actualizo los atributos del empleado existente con los del formulario
			empleadoEncontrado.setNombre(empleado.getNombre());
			empleadoEncontrado.setSexo(empleado.getSexo());
			empleadoEncontrado.setCategoria(empleado.getCategoria());
			empleadoEncontrado.setAnyos(empleado.getAnyos());
			empleadoEncontrado.setNomina(empleado.getCategoria(), empleado.getAnyos());

			// Ahora actualizo el empleado en la base de datos
			empleadoService.updateEmpleado(empleadoEncontrado);
		}

		return "redirect:/empleados";
	}

	@GetMapping("/empleados/eliminar/{dni}")
	public String deleteEmpleadoForm(@PathVariable String dni, Model model) {
		Empleado empleadoEncontrado = empleadoService.findEmpleadoByDni(dni);
		model.addAttribute("empleado", empleadoEncontrado);
		return "delete_empleado";
	}

	@PostMapping("/empleados/eliminar/{dni}")
	public String deleteEmpleado(@PathVariable String dni) {
		empleadoService.deleteEmpleadoByDni(dni);
		return "redirect:/empleados";
	}

}
