package Laboral.Clases;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import Laboral.DBUtils.EmpleadosController;

public class MenuCliente {
	EmpleadosController controller = new EmpleadosController();
	AltaEmpleadoBackup backup = new AltaEmpleadoBackup();
	Scanner sc = new Scanner(System.in);
	int n = 0;
	int n2 = 0;

	public void menuCliente(Connection conn) {
		controller.setConn(conn);
		backup.setConn(conn);

		do {
			System.out.println("--------------MENU--------------");
			System.out.println("OPCION 0: Salir del programa.   ");
			System.out.println("OPCION 1: Información de todos los empleados.");
			System.out.println("OPCION 2: Introduce un dni para ver el salario del empleado.");
			System.out.println("OPCION 3: Submenú para modificar datos de un empleado.");
			System.out.println("OPCION 4: Actualizar el sueldo de un empleado");
			System.out.println("OPCION 5: Actualizar el sueldo de todos los empleados");
			System.out.println("OPCION 6: Realizar copia de seguridad");
			System.out.println("--------------------------------");

			System.out.println("Intoduce una opción del 0 al 6");

			n = sc.nextInt();
			sc.nextLine();

			switch (n) {
			// Caso 0: sale de la aplicación
			case 0:
				System.out.println("Saliendo de la aplicion...");
				break;
			case 1:
				System.out.println("Información de todos los empleados:");
				List<Empleado> listaEmpleados = controller.findAllEmpleados();
				for (Empleado empleado : listaEmpleados) {
					empleado.Imprime();
				}
				break;

			case 2:
				System.out.println("Introduce un dni para ver el salario del empleado");
				String dniBuscado1 = sc.next();
				sc.nextLine();

				System.out.println("Salario del empleado con dni: " + dniBuscado1);
				String nominaEncontrada = controller.findNominaByDni(dniBuscado1);
				if (nominaEncontrada != null) {
					System.out.println(nominaEncontrada);
				} else {
					System.out.println("Empleado no encontrado.");
				}
				break;

			case 3:

				do {
					System.out.println("---------SUBMENU---------");

					System.out.println("OPCION 0: Salir al menu principal.");

					System.out.println("OPCION 1: Actualizar nombre.");

					System.out.println("OPCION 2: Actualizar sexo");

					System.out.println("OPCION 3: Actualizar categoria");

					System.out.println("OPCION 4: Actualizar años:");

					System.out.println("--------------------------------");

					System.out.println("Introduce una opción del submenú:");
					n2 = sc.nextInt();
					sc.nextLine();

					String dniBuscado2 = "";
					if (n2 != 0) {
						System.out.println("Introduce el dni del empleado a actualizar:");
						dniBuscado2 = sc.next();
						sc.nextLine();
					}

					switch (n2) {
					case 0:
						System.out.println("Saliendo al menu principal...");
						break;
					case 1:
						System.out.println("Introduce el nuevo nombre: ");
						String nuevoNombre = sc.next();
						sc.nextLine();

						controller.updateEmpleadoNombre(dniBuscado2, nuevoNombre);
						break;
					case 2:
						System.out.println("Introduce el nuevo sexo (F/M):");
						char nuevoSexo = sc.next().charAt(0);
						sc.nextLine();

						controller.updateEmpleadoSexo(dniBuscado2, nuevoSexo);
						break;
					case 3:
						System.out.println("Introduce la nueva categoria (1-9)");
						int nuevaCategoria = sc.nextInt();
						sc.nextLine();

						controller.updateEmpleadoCategoria(dniBuscado2, nuevaCategoria);
						break;
					case 4:

						double nuevosAnyos = 0;

						System.out.println("Introduce los años actualizados:");
						String Anyos = sc.next();
						nuevosAnyos = Double.valueOf(Anyos);

						controller.updateEmpleadoAnyos(dniBuscado2, nuevosAnyos);
						break;

					default:
						System.out.println("Numero introducido incorrecto.");
						break;
					}
				} while (n2 != 0);

				break;

			case 4:
				System.out.println("Introduce el dni del empleado para actualizar su nomina");
				String dniBuscado = sc.next();
				sc.nextLine();

				System.out.println("Introduce la categoria nueva (1-9):");
				int categoriaNueva = sc.nextInt();
				sc.nextLine();

				System.out.println("introduce los años nuevos:");
				double anyosNuevos = sc.nextDouble();
				sc.nextLine();

				controller.updateEmpleadoCategoria(dniBuscado, categoriaNueva);
				controller.updateEmpleadoAnyos(dniBuscado, anyosNuevos);

				break;
			case 5:

				System.out.println("Introduce la categoria nueva para todos los empleados:");
				int categoriaNuevaAll = sc.nextInt();
				System.out.println("introduce los años nuevos:");
				double anyosNuevosAll = sc.nextDouble();

				controller.updateAllEmpleados(anyosNuevosAll, categoriaNuevaAll);

				break;
			case 6:
				backup.backup();
				break;

			default:
				System.out.println("Numero introducido incorrecto (0-6)");
				break;
			}
		} while (n != 0);
	}
}
