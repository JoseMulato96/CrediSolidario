package com.coomeva.credisolidario.dto;

import java.sql.Timestamp;

import javax.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParametrosBaseDTO {
	Long Id;
	
	String campo;
	
	String valor;
		
	boolean activo;
	
	String usuarioCreacion;
	
	String usuarioModificacion;
	
	Timestamp fechaCreacion;
	
	Timestamp fechaModificacion;
}
