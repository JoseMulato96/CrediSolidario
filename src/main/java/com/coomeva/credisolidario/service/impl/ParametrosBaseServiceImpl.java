package com.coomeva.credisolidario.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coomeva.credisolidario.domain.ParametrosBase;
import com.coomeva.credisolidario.dto.ParametrosBaseDTO;
import com.coomeva.credisolidario.repo.ParametrosBaseRepository;
import com.coomeva.credisolidario.service.ParametrosBaseService;

@Service
public class ParametrosBaseServiceImpl implements ParametrosBaseService {

	@Autowired
	ParametrosBaseRepository parametrosBaseRepository;
			
	private ParametrosBaseDTO convertDTO(ModelMapper mapper, ParametrosBase parametrosBase) {
		return mapper.map(parametrosBase, ParametrosBaseDTO.class);
	}
	
	@Override
	public List<ParametrosBaseDTO> getAll() {
		ModelMapper modelMapper = new ModelMapper();
		List<ParametrosBaseDTO> dtos = new ArrayList<>();
		Consumer<ParametrosBase> lambda = x-> dtos.add(convertDTO(modelMapper, x));
		parametrosBaseRepository.findAll().forEach(lambda);
		return dtos;
	}

	@Override
	public ParametrosBaseDTO getById(Long id) {		
		ModelMapper modelMapper = new ModelMapper();		
		ParametrosBase parametrosBase = parametrosBaseRepository.findById(id).get();
		ParametrosBaseDTO parametrosBaseDTO = modelMapper.map(parametrosBase, ParametrosBaseDTO.class);
		return parametrosBaseDTO;
	}

	@Override
	public ParametrosBaseDTO add(ParametrosBaseDTO item) {
		ModelMapper modelMapper = new ModelMapper();
		ParametrosBase parametrosBase = modelMapper.map(item, ParametrosBase.class);		
		parametrosBase = parametrosBaseRepository.save(parametrosBase);
		ParametrosBaseDTO parametrosBaseDTO = modelMapper.map(parametrosBase, ParametrosBaseDTO.class);
		return parametrosBaseDTO;
	}

	@Override
	public void update(Long id, ParametrosBaseDTO item) {
		ParametrosBase baseDb = parametrosBaseRepository.findById(id).get();
		
		baseDb.setCampo(item.getCampo());
		///TODO: Implementar logica
		parametrosBaseRepository.save(baseDb);
	}

	@Override
	public void delete(Long id) {
		parametrosBaseRepository.deleteById(id);

	}

}
