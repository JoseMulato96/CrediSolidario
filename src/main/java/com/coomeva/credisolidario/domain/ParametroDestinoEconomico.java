package com.coomeva.credisolidario.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="PARAMETROS_DESTINO_ECONOMICO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParametroDestinoEconomico {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable=false, nullable=false)
	Long id;
	
	@Column
	int antiguedadMinima;
	
	@Column
	int antiguedadMaxima;
	
	@Column
	int destinoEconomico;
	
	@Column
	boolean activo;
	
	@Column(updatable = false)
	String usuarioCreacion;
	
	@Column
	String usuarioModificacion;
	
	@CreationTimestamp
	@Column(updatable = false)
	Timestamp fechaCreacion;
	
	@UpdateTimestamp
	@Column
	Timestamp fechaModificacion;
}
