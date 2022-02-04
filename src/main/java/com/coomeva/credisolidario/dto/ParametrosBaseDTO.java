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
	
	String estadosPermitidos;
	
	Integer minAntiguedad;
	
	Long valorMinimoCredisolidario;
	
	Long valorMaximoCredisolidario;
	
	Long valorSMMV;
	
	String correoLog;
	
	Long intervaloValorMonto;
	
	Integer intervaloPlazo;
	
	String linkTyC;
	
	String mensajeInformativoTasa;
	
	/*
	 * @Column String productosValidar;
	 * 
	 * @Column String fechaPerseverancia;
	 */
	boolean activo;
	
	String usuarioCreacion;
	
	String usuarioModificacion;
	
	Timestamp fechaCreacion;
	
	Timestamp fechaModificacion;
}
