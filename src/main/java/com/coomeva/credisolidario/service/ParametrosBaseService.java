package com.coomeva.credisolidario.service;

import java.util.List;

import com.coomeva.credisolidario.dto.ParametrosBaseDTO;

public interface ParametrosBaseService {
	List<ParametrosBaseDTO> getAll();
	ParametrosBaseDTO getById(Long id);
	ParametrosBaseDTO add(ParametrosBaseDTO item);
	void update(Long id, ParametrosBaseDTO item);
	void delete(Long id);
}
