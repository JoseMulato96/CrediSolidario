package com.coomeva.credisolidario.dto;

import java.sql.Timestamp;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParametrosDestinoEconomicoDTO {

	Long id;
		
	int antiguedadMinima;
	
	int antiguedadMaxima;
	
	int destinoEconomico;
	
	boolean activo;
	
	String usuarioCreacion;
	
	String usuarioModificacion;
	
	Timestamp fechaCreacion;
	
	Timestamp fechaModificacion;
}
