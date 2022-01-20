package it.unimib.unimibmodules.controller;


import it.unimib.unimibmodules.exception.FormatException;
import it.unimib.unimibmodules.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Defines the method that will be used by controllers to convert an instance of M (the model) to an instance of T (the
 * DTO) and vice versa.
 * @author Davide Costantini
 * @author Luca Milazzo
 * @version 0.1.0
 */
public abstract class DTOMapping<M, T> {
	
	/**
	 * The instance of modelMapper that will be used to convert Question to QuestionDTO and vice versa.
	 */
	protected final ModelMapper modelMapper;
	
	@Autowired
	protected DTOMapping(ModelMapper modelMapper) {

		this.modelMapper = modelMapper;
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		modelMapper.getConfiguration().setImplicitMappingEnabled(false);
	}

	/**
	 * Converts an instance of M to an instance of T
	 * @param	value	an instance of M
	 * @return			an instance of T, containing the serialized data of value
	 */
	public abstract T convertToDTO(M value);

	/**
	 * Converts an instance of T to an instance of M
	 * @param   dto	an instance of T
	 * @return		an instance of M, containing the deserialized data of T
	 * @throws FormatException 
	 */
	public abstract M convertToEntity(T dto) throws FormatException, NotFoundException;
}
