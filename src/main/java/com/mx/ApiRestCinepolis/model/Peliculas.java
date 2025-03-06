package com.mx.ApiRestCinepolis.model;

import java.sql.Date;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;





//INICIAMOS EL MAPEO DE NUESTRA TABLA

@Entity
@Table (name = "CINEPOLIS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Peliculas {
	
	
	@Id //INICIALIZAMOS NUESTRO ID
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	
	@Column (name = "IDPELI",columnDefinition = " NUMBER", nullable = false)
	private Integer idpeli;
	
	
	@Column (name = "NOMBRE_P",columnDefinition = "VARCHAR2(50)", nullable = false)
	private String nombrePeli;
	
	@Column (name = "DURACION",columnDefinition = "DATE", nullable = false)
	private Date  duracion;
	
	@Column (name = "FECHA_LANZ",columnDefinition = "DATE", nullable = false)
	private Date lanza;
	
	/// COMO PARSEAMOS NUESTRO DATOS PRIMITIVOS PARA NUESTRAS COMPARACIONES
	// USAREMOS EL EQUALS 


}
