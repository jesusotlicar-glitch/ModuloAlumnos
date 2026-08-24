package com.mx.Alumnos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Alumnos.dto.Respuesta;
import com.mx.Alumnos.entidad.Alumno;
import com.mx.Alumnos.service.AlumnoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("alumnos")
@CrossOrigin
public class AlumnoController {
	final AlumnoService service;
	
	public AlumnoController(AlumnoService service) {
		this.service=service;
	}
	
	@GetMapping("listar")
	public ResponseEntity<?> listar(){
		return service.mostrar();
	}
	
	@PostMapping("guardar")
	public Respuesta guardar (@RequestBody Alumno alumno) {
		return service.guardar(alumno);
	}
	
	@PostMapping("editar")
	public Respuesta editar(@RequestBody Alumno alumno) {
		return service.editar(alumno);
	}
	
	@GetMapping("eliminar/{matricula}")
	public Respuesta eliminar(@PathVariable long matricula) {
		return service.eliminar(matricula);
	}
	
	@GetMapping("buscar/{maricula}")
	public ResponseEntity<?> buscar (@PathVariable long matricula){
		return service.buscar(matricula);
	}
	
	@GetMapping("buscarPorCarrera/{numero}")
	public ResponseEntity<?> buscarPorCarrera(@PathVariable int numero){
		return service.buscarAlumnosPorCarrera(numero);
	}
}
