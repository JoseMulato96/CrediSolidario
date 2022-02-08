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
@Table(name="PARAMETROS_BASE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParametrosBase {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable=false, nullable=false)
	Long id;
	
	@Column
	String campo;
	
	@Column
	String valor;
	
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
