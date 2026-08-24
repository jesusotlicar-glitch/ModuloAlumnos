package com.mx.Alumnos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Alumnos.entidad.Alumno;

public interface AlumnoDao extends JpaRepository<Alumno, Long> {
	List<Alumno> findByNumeroCarrera(int numeroCarrera);
}
