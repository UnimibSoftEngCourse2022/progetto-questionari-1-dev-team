package it.unimib.unimibmodules.controller;

/**
 * Defines the method that will be used by controllers to convert an instance of M (the model) to an instance of T (the
 * DTO) and vice versa.
 * @author Gianlorenzo Martini
 */

public abstract class DTOMapping<M, T> {

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
	 */
	public abstract M convertToEntity(T dto);
}
