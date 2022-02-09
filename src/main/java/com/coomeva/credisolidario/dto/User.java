package com.coomeva.credisolidario.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SQUSER")
	@SequenceGenerator(name = "SQUSER", sequenceName = "SQFVEUSER")
	private long idUsuario;
	@Column
	private String username;
	@Column
	@JsonIgnore
	private String password;
}
