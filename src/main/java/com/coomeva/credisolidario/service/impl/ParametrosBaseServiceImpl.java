package com.coomeva.credisolidario.service.impl;

import java.sql.Timestamp;
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
	public List<ParametrosBaseDTO> add(List<ParametrosBaseDTO> item) {
		return addOrUpdateEntity(item);
	}

	@Override
	public List<ParametrosBaseDTO> update(List<ParametrosBaseDTO> item) {
		return addOrUpdateEntity(item);
	}

	@Override
	public void delete(Long id) {
		if(id != null && id > 0) {
			parametrosBaseRepository.deleteById(id);	
		}
	}
	
	private List<ParametrosBaseDTO> addOrUpdateEntity(List<ParametrosBaseDTO> item){
		ModelMapper modelMapper = new ModelMapper();
		List<ParametrosBase> lstParamsBase = new ArrayList<>();
		List<ParametrosBaseDTO> lstParamsBaseDto = new ArrayList<>();
		
		for (ParametrosBaseDTO paramBaseDto : item) {
			ParametrosBase parametrosBase = modelMapper.map(paramBaseDto, ParametrosBase.class);
			parametrosBase.setActivo(true);
			if(parametrosBase.getId() == null || !(parametrosBase.getId() > 0)) {
				parametrosBase.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				parametrosBase.setUsuarioCreacion("User");	
			}			
			parametrosBase.setFechaModificacion(new Timestamp(System.currentTimeMillis()));			
			parametrosBase.setUsuarioModificacion("User");
			lstParamsBase.add(parametrosBase);
		}		
		
		lstParamsBase = parametrosBaseRepository.saveAll(lstParamsBase);
		
		for (ParametrosBase itemBase : lstParamsBase) {
			lstParamsBaseDto.add( modelMapper.map(itemBase, ParametrosBaseDTO.class));
		}
		
		return lstParamsBaseDto;
	}

}
