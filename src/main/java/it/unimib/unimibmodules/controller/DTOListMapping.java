package it.unimib.unimibmodules.controller;

import org.modelmapper.ModelMapper;

import java.util.List;

public abstract class DTOListMapping<M, T> extends DTOMapping<M, T> {

	protected DTOListMapping(ModelMapper modelMapper) {

		super(modelMapper);
	}

	public abstract List<T> convertListToDTO(Iterable<M> values);
}
