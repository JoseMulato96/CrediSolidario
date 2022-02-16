package com.coomeva.credisolidario.service;

import java.util.List;

import com.coomeva.credisolidario.dto.ParametrosDestinoEconomicoDTO;

public interface ParametroDestinoEconomicoService {
	List<ParametrosDestinoEconomicoDTO> getAll();
	ParametrosDestinoEconomicoDTO getById(Long id);
	List<ParametrosDestinoEconomicoDTO> add(List<ParametrosDestinoEconomicoDTO> item);
	List<ParametrosDestinoEconomicoDTO> update(List<ParametrosDestinoEconomicoDTO> item);
	void delete(Long id);
}
