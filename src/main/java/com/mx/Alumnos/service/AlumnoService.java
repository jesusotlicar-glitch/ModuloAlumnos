package com.mx.Alumnos.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mx.Alumnos.dao.AlumnoDao;
import com.mx.Alumnos.dto.Respuesta;
import com.mx.Alumnos.entidad.Alumno;

@Service

public class AlumnoService {
	final AlumnoDao alumnoDao;
	
	public AlumnoService(AlumnoDao alumnoDao) {
		this.alumnoDao=alumnoDao;
	}
	
	public ResponseEntity<?> mostrar(){
		if(alumnoDao.findAll().isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(alumnoDao.findAll());
	}
	
	public Respuesta guardar(Alumno alumno) {
		if(alumnoDao.existsById(alumno.getMatricula()))
			return new Respuesta("El alumno no ha sido agregado porque su amtricula ya existe",false,alumno.getMatricula());
		alumnoDao.save(alumno);
		return new Respuesta("El alumno ha sido agregado",true,alumno);
	}
	
	public Respuesta editar(Alumno alumno) {
		if(alumnoDao.existsById(alumno.getMatricula())) {
			alumnoDao.save(alumno);
			return new  Respuesta("El alumno ha sido editado",true,alumno);
		}
		return new Respuesta("El alumno que tratas de editar no existe",false,alumno.getMatricula());
	}
	
	public Respuesta eliminar(long matricula) {
		Alumno alumno = alumnoDao.findById(matricula).orElse(null);
		if(alumno==null) {
			return new Respuesta("El alumno que tratas de eliminar no existe",false,matricula);
		}
		Respuesta rs = new Respuesta("El alumno ha sido dado de baja",true,alumno);
		alumnoDao.delete(alumno);
		return rs;
	}
	
	public ResponseEntity<?> buscar(long matricula){
		Alumno alumno = alumnoDao.findById(matricula).orElse(null);
		if(alumno == null)
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(alumno);
	}

	public ResponseEntity<?> buscarAlumnosPorCarrera(int numero) {
		List<Alumno> alumnos = alumnoDao.findByNumeroCarrera(numero);
		if(alumnos.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(alumnos);
	}
}
