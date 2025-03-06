package com.mx.ApiRestCinepolis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestCinepolis.dao.Pelisdao;
import com.mx.ApiRestCinepolis.model.Peliculas;
@Service
public class PelisServImp {
	
	//REALIZAMOS LA INYECCION DE NUESTRA DEPENDIENCIAS
	
	@Autowired
	Pelisdao dao;
	
	
//-------------METODO LISTAR (MOSTRAR) --------------------	
	
	@Transactional (readOnly = true) //SOLO REALIZAMOS LECTURA
	
	public List<Peliculas> mostrar () {
		return dao.findAll();
		}
	
	
	
//-------------METODO GUARDAR CON VALIDACION DE NOMBRE  --------------------	
	
	@Transactional
	public boolean  guardar (Peliculas peli) {
		boolean bandera = false; //INICIALIZAMO LA BANDERA
		for (Peliculas p: mostrar ()) { //RECORREMOS LA LISTA
			
			if (p.getNombrePeli().equals(peli.getNombrePeli())){//COMPARAMOS EL CONTENDIO 
				bandera = true; // ENCONTRAMOS LA COINCIDENCIA Y CAMBIAMOS EL VALOR DE LA BANDERA
				break;    // ROMPEMOS EL CICLO
			}
				
		}
		dao.save(peli); //COMO NO HAY COINCIDENCIA PODEMOS ALMACENAR LA PELICULA
		return bandera; //PERMANECE CON EL MISMO VALOR LA BANDERA, NO HAY COINCIDENCIAS 
	}
	
	
	//-------------METODO mostrarxindice --------------------
	@Transactional(readOnly = true)
	public Peliculas buscarXid(Integer idPeli) {
	    Peliculas coincidencia = dao.findById(idPeli).orElse(null); ///REALIZAMOS LA BUSQUEDA
	    if (coincidencia == null) { //NO HAY COINCIDENCIA 
	        return new Peliculas(); 
	    }
	    return coincidencia; // REGRESA LA PELICULA
	}

	
//-------------METODO EDITAR CON VALIDACION EXISTENCIA  --------------------	
	
	@Transactional
	public boolean editar(Peliculas pelicula) {
		boolean bandera = false;
		for (Peliculas p: dao.findAll()) {
			if(p.getIdpeli().equals(pelicula.getIdpeli())) {
				dao.save(pelicula);//dao.deleteById(idropa);
				bandera =  true;
				break;
			}
		}
		return bandera;
	}
	
	
//-------------METODO ELIMINAR CON VALIDACION EXISTENCIA  --------------------	
	

	@Transactional
	public boolean eliminar(Peliculas pelicula) {
	    List<Peliculas> lista = dao.findAll();
	    boolean bandera = false;

	    for (Peliculas p : lista) {
	        if (p.getIdpeli().equals(pelicula.getIdpeli())) { // Buscamos la coincidencia
	            dao.delete(p); // Eliminamos la película encontrada
	            bandera = true;
	            break;
	        }
	    }

	    return bandera;
	}

	
	
}
