package com.coomeva.credisolidario.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coomeva.credisolidario.domain.ParametroDestinoEconomico;

@Repository
public interface ParametroDestinoEconomicoRepository  extends JpaRepository<ParametroDestinoEconomico, Long>  {

}
