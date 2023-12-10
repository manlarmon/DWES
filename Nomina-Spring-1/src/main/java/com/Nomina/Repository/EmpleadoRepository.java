package com.Nomina.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Nomina.Entities.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, String> {

	// Los repositorios actúan como una capa de abstracción sobre la lógica de
	// almacenamiento
	// y recuperación de datos, permitiendo a las aplicaciones
	// acceder y manipular datos de manera más sencilla y eficiente.


    @Query("SELECT e FROM Empleado e WHERE e.dni LIKE %:dni%")
    List<Empleado> findLikeDni(@Param("dni") String dni);

    @Query("SELECT e FROM Empleado e WHERE e.nombre LIKE %:nombre%")
    List<Empleado> findLikeNombre(@Param("nombre") String nombre);

    @Query("SELECT e FROM Empleado e WHERE e.sexo = :sexo")
    List<Empleado> findBySexo(@Param("sexo") char sexo);

    @Query("SELECT e FROM Empleado e WHERE e.categoria = :categoria")
    List<Empleado> findByCategoria(@Param("categoria") int categoria);

    @Query("SELECT e FROM Empleado e WHERE e.anyos = :anyos")
	List<Empleado> findByAnyos(@Param("anyos") Double anyos);
	
	//Truncate para "redondear" hacia abajo y que me aparezcan todos los empleados
	//cuya parte entera de los anyos sea la introducida
	@Query("SELECT e FROM Empleado e WHERE TRUNCATE(e.anyos) = :anyos")
	List<Empleado> findLikeAnyos(@Param("anyos") Double anyos);

}
