package com.coomeva.credisolidario.service;

import java.util.List;

import com.coomeva.credisolidario.dto.ParametrosBaseDTO;

public interface ParametrosBaseService {
	List<ParametrosBaseDTO> getAll();
	ParametrosBaseDTO getById(Long id);
	List<ParametrosBaseDTO> add(List<ParametrosBaseDTO> item);
	List<ParametrosBaseDTO> update(List<ParametrosBaseDTO> item);
	void delete(Long id);
}
