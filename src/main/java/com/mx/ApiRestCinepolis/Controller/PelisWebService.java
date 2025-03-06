package com.mx.ApiRestCinepolis.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ApiRestCinepolis.model.Peliculas;
import com.mx.ApiRestCinepolis.services.PelisServImp;
import com.mx.TiendaRopa.model.Ropas;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping (path = "PelisWebService")
@CrossOrigin

public class PelisWebService {
	
	
    @Autowired
	
	PelisServImp impelis;
    
  //---------------------PETICION GET PARA MORSTRAR REGISTRO---
    //http://localhost:9000/PelisWebService/mostrar
    
	@GetMapping(path = "mostrar")
	public List<Peliculas> mostrar () {
		return impelis.mostrar();
	}
	
    
  //---------------------PETICION PUT PARA GUARDAR---  
	//http://localhost:9000/PelisWebService/guardar
	@PutMapping(path = "guardar")
	
	public ResponseEntity<?> guardar(@RequestBody Peliculas pelicula){ /// RECIBE CUALQUIER DATO Y TRANSFORMA JSON
		boolean response = impelis.guardar(pelicula); // VERIFICAMOS SI SE GUARDO LA PELICULA
		if (response == false) 
			return new ResponseEntity<> ("TUVISTE EXITO AL GUARDAR TU PELICULA",HttpStatus.OK); // NO HAY COINCIDENCIAS SE GUARDA
		else 
			return new ResponseEntity<> ("ERROR AL GUARDAR",HttpStatus.OK);
		
	}
	
	
	
	//---------------------PETICION PUT PARA BUSCAR---  
	
	 //http://localhost:9000/PelisWebService/buscar
	@PostMapping (path = "buscar")
	
	public Peliculas buscar(@RequestBody Peliculas pelicula) {
	    Peliculas peli = impelis.buscarXid(pelicula.getIdpeli());
	    if (peli.getIdpeli()== null) {
	        return new Peliculas(); 
	    }
	    return peli;
	}
	

	//---------------------PETICION PUT PARA EDITAR--- 	
	
	
	//http://localhost:9000/PelisWebService/editar
		@PutMapping(path = "editar")
		public ResponseEntity<?> editar(@RequestBody Peliculas pelicula){
			boolean response = impelis.editar(pelicula);
			
			if (response == false)
				return new ResponseEntity<>("NO SE PUEDE EDITAR, NO EXITE", HttpStatus.OK);
			else
			
				return new ResponseEntity<>(pelicula, HttpStatus.CREATED);
		}
		
		
		
		
		///---------------ELIMINACION -----------------
		
		
		//http://localhost:9000/RopaWebService/eliminar
	

		@PostMapping(path = "eliminar")
		
		public ResponseEntity<String> eliminar(@RequestBody Peliculas pelicula) {
		    

		    boolean bandera = impelis.eliminar(pelicula);

		    if (!bandera) {
		    	return new ResponseEntity<>("NO SE PUEDE ELIMINAR, NO EXITE", HttpStatus.OK);
		    }

		    return new ResponseEntity<>("ELIMINADO", HttpStatus.OK);
		}



    

}
