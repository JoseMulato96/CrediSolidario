package com.coomeva.credisolidario.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coomeva.credisolidario.domain.ParametrosBase;

@Repository
public interface ParametrosBaseRepository extends JpaRepository<ParametrosBase, Long> {

}
