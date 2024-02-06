package com.code.conversion;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.code.Entity.DhabaModel;
import com.code.payload.DhabaModelDto;

@Component
public class Converion {
	public DhabaModel dtoToEntity(DhabaModelDto dhabaModelDto) {
		DhabaModel model = new DhabaModel();
		BeanUtils.copyProperties(dhabaModelDto, model);
		return model;
	}

	public DhabaModelDto entityToDto(DhabaModel dhabaModel) {
		DhabaModelDto dhabamodel = new DhabaModelDto();
		BeanUtils.copyProperties(dhabaModel, dhabamodel);
		return dhabamodel;

	}
}
