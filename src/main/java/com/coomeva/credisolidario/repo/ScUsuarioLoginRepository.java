package com.coomeva.credisolidario.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coomeva.credisolidario.domain.ScUsuarioLogin;

@Repository
public interface ScUsuarioLoginRepository extends JpaRepository<ScUsuarioLogin, Long> {

}
