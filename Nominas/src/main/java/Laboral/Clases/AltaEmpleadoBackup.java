package Laboral.Clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import Laboral.DBUtils.EmpleadosController;

public class AltaEmpleadoBackup {
    EmpleadosController tablaEmpleado = null;
    Connection conn = null;
    List<Empleado> empleados = new ArrayList<>();
    List<Empleado> empleadosFichero = new ArrayList<>();
    File archivoTexto = null;
    File archivoBinario = null;
    FileReader fr = null;
    BufferedReader br = null;
    FileWriter fw = null;
    BufferedWriter bw = null;
    ObjectOutputStream objectOutputStream = null;

    // se llama en el main
    public Connection setConn(Connection conexionDB) {
        return conn = conexionDB;
    }

    public void backup() {

        try {
            tablaEmpleado = new EmpleadosController();

            // Establezco conexion en el controlador.
            tablaEmpleado.setConn(conn);
            escrituraFicheros();

            // empleados = lecturaFicheroEmpleados();
            // for (Empleado empleado : empleados) {
            // tablaEmpleado.createEmpleado(empleado.getNombre(), empleado.getDni(),
            // empleado.getSexo(),empleado.getCategoria(), empleado.getAnyos());
            // }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    private void escrituraFicheros() {
        empleadosFichero = tablaEmpleado.findAllEmpleados();
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            archivoTexto = new File(
                    "G:\\Mi unidad\\2ºDAW\\DWES\\Proyectos\\Proyectos DWES\\Nominas\\src\\main\\java\\Laboral\\Recursos\\empleadosDesdeDB.txt");
            fw = new FileWriter(archivoTexto);
            bw = new BufferedWriter(fw);

            // Abre el archivo binario para escritura
            archivoBinario = new File(
                    "G:\\Mi unidad\\2ºDAW\\DWES\\Proyectos\\Proyectos DWES\\Nominas\\src\\main\\java\\Laboral\\Recursos\\empleadosDesdeDB.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(archivoBinario);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Creo un objeto nomina para poder calcular el sueldo de los empleados.
            Nomina n = new Nomina();

            for (Empleado empleado : empleadosFichero) {
                bw.write(empleado.getNombre() + "," + empleado.getDni() + "," + empleado.getSexo() + ","
                        + empleado.getCategoria() + "," + empleado.anyos + "\n");
                objectOutputStream.writeUTF(empleado.getDni());
                objectOutputStream.writeDouble(n.sueldo(empleado));
            }
            System.out.println("Empleados registrados en los ficheros correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cierra el archivo binario y el resto de recursos
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (bw != null) {
                    bw.flush();
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }

                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private List<Empleado> lecturaFicheroEmpleados() {
        List<Empleado> empleadosFichero = new ArrayList<>();

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            archivoTexto = new File("src\\main\\Recursos\\empleadosNuevos.txt");
            fr = new FileReader(archivoTexto);
            br = new BufferedReader(fr);

            // Abre el archivo binario para escritura
            archivoBinario = new File("src\\main\\Recursos\\salarios.dat");
            FileOutputStream fileOutputStream = new FileOutputStream(archivoBinario);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Creo un objeto nomina para poder calcular el sueldo de los empleados.
            Nomina n = new Nomina();

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length > 0 && datos.length <= 5) {

                    String nombre = datos[0];
                    String dni = datos[1];
                    char sexo = (datos[2].charAt(0));
                    int categoria = 1;
                    double años = 0;

                    if (datos.length > 3) {
                        // Si hay al menos 3 elementos en datos, entonces los campos de categoría y años
                        // están presentes
                        if (!datos[3].isEmpty()) {
                            categoria = Integer.parseInt(datos[3]);
                        }
                        if (datos.length > 4 && !datos[4].isEmpty()) {
                            años = Double.parseDouble(datos[4]);
                        }
                    }
                    Empleado empleado = new Empleado(nombre, dni, sexo, categoria, años);
                    empleadosFichero.add(empleado);

                    objectOutputStream.writeUTF(dni);
                    objectOutputStream.writeDouble(n.sueldo(empleado));

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cierra el archivo binario y el resto de recursos
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return empleadosFichero;
    }
}
