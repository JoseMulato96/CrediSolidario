package com.coomeva.credisolidario.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coomeva.credisolidario.domain.ScPropiedad;

@Repository
public interface ScRepositoryPropiedad extends JpaRepository<ScPropiedad, Long> {

	@Query("SELECT f FROM ScPropiedad f WHERE f.consPropiedad = ?1")
	public ScPropiedad find(Long id);

	public ScPropiedad findByDescripcionIgnoreCase(String desc);

}
