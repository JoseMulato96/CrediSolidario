package com.coomeva.credisolidario.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coomeva.credisolidario.domain.ParametroDestinoEconomico;
import com.coomeva.credisolidario.dto.ParametrosDestinoEconomicoDTO;
import com.coomeva.credisolidario.repo.ParametroDestinoEconomicoRepository;
import com.coomeva.credisolidario.service.ParametroDestinoEconomicoService;

@Service
public class ParametroDestinoEconomicoServiceImpl implements ParametroDestinoEconomicoService {

	@Autowired
	ParametroDestinoEconomicoRepository destinoEconomicoRepository;
	
	private ParametrosDestinoEconomicoDTO convertDTO(ModelMapper mapper, ParametroDestinoEconomico destinoEconomico) {
		return mapper.map(destinoEconomico, ParametrosDestinoEconomicoDTO.class);
	}

	@Override
	public List<ParametrosDestinoEconomicoDTO> getAll() {
		ModelMapper modelMapper = new ModelMapper();
		List<ParametrosDestinoEconomicoDTO> dtos = new ArrayList<>();
		Consumer<ParametroDestinoEconomico> lambda = x-> dtos.add(convertDTO(modelMapper, x));
		destinoEconomicoRepository.findAll().forEach(lambda);
		return dtos;
	}

	@Override
	public ParametrosDestinoEconomicoDTO getById(Long id) {
		ModelMapper modelMapper = new ModelMapper();		
		ParametroDestinoEconomico destinoEconomico = destinoEconomicoRepository.findById(id).get();
		ParametrosDestinoEconomicoDTO destinoEconomicoDTO = modelMapper.map(destinoEconomico, ParametrosDestinoEconomicoDTO.class);
		return destinoEconomicoDTO;
	}

	@Override
	public List<ParametrosDestinoEconomicoDTO> add(List<ParametrosDestinoEconomicoDTO> item) {
		return addOrUpdateEntity(item);
	}

	@Override
	public List<ParametrosDestinoEconomicoDTO> update(List<ParametrosDestinoEconomicoDTO> item) {
		return addOrUpdateEntity(item);
	}

	@Override
	public void delete(Long id) {
		if(id != null && id > 0) {
			destinoEconomicoRepository.deleteById(id);
		}
	}
	
	private List<ParametrosDestinoEconomicoDTO> addOrUpdateEntity(List<ParametrosDestinoEconomicoDTO> item){
		ModelMapper modelMapper = new ModelMapper();
		List<ParametroDestinoEconomico> listDestinoEconomico = new ArrayList<>();
		List<ParametrosDestinoEconomicoDTO> listDestinoEconomicoDto = new ArrayList<>();
		
		for (ParametrosDestinoEconomicoDTO paramDto : item) {
			ParametroDestinoEconomico destinoEconomico = modelMapper.map(paramDto, ParametroDestinoEconomico.class);
			destinoEconomico.setActivo(true);
			if(destinoEconomico.getId() == null || !(destinoEconomico.getId() > 0)) {
				destinoEconomico.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				destinoEconomico.setUsuarioCreacion("User");	
			}			
			destinoEconomico.setFechaModificacion(new Timestamp(System.currentTimeMillis()));			
			destinoEconomico.setUsuarioModificacion("User");
			listDestinoEconomico.add(destinoEconomico);
		}		
		
		listDestinoEconomico = destinoEconomicoRepository.saveAll(listDestinoEconomico);
		
		for (ParametroDestinoEconomico itemBase : listDestinoEconomico) {
			listDestinoEconomicoDto.add( modelMapper.map(itemBase, ParametrosDestinoEconomicoDTO.class));
		}
		
		return listDestinoEconomicoDto;
	}
	}
