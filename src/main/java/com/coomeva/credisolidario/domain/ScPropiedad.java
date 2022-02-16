package com.coomeva.credisolidario.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Entity
@Table(name = "SC_PROPIEDAD")
@XmlRootElement
@Data
@NamedQueries({ @NamedQuery(name = "ScPropiedad.findAll", query = "SELECT f FROM ScPropiedad f"),
		@NamedQuery(name = "ScPropiedad.findByConsPropiedad", query = "SELECT f FROM ScPropiedad f WHERE f.consPropiedad = :consPropiedad"),
		@NamedQuery(name = "ScPropiedad.findByDescripcion", query = "SELECT f FROM ScPropiedad f WHERE f.descripcion = :descripcion"),
		@NamedQuery(name = "ScPropiedad.findByValor", query = "SELECT f FROM ScPropiedad f WHERE f.valor = :valor") })
public class ScPropiedad implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "CONS_PROPIEDAD")
	private Long consPropiedad;
	@Size(max = 50)
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Size(max = 200)
	@Column(name = "VALOR")
	private String valor;

	public ScPropiedad() {
		super();
	}

	public ScPropiedad(@NotNull Long consPropiedad) {
		super();
		this.consPropiedad = consPropiedad;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (consPropiedad != null ? consPropiedad.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ScPropiedad)) {
			return false;
		}
		ScPropiedad other = (ScPropiedad) object;
		if ((this.consPropiedad == null && other.consPropiedad != null)
				|| (this.consPropiedad != null && !this.consPropiedad.equals(other.consPropiedad))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.coomeva.credisolidario.domain.ScPropiedad[ consPropiedad=" + consPropiedad + " ]";
	}

}
