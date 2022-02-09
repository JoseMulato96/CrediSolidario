package com.coomeva.credisolidario.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "SC_USUARIO_LOGIN")
@Data
public class ScUsuarioLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "CONS_LOGIN")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SQUSUARIOLOGIN")
	@SequenceGenerator(name = "SQUSUARIOLOGIN", sequenceName = "FCP_USUARIO_LOGIN_SEQ")
	private Long consLogin;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "USUARIO_INGRESO")
	private String usuarioIngreso = "";
	@Basic(optional = false)
	@NotNull
	@Column(name = "FECHA_INGRESO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaIngreso = new Date();
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "IP_EQUIPO")
	private String ipEquipo = "";
}
