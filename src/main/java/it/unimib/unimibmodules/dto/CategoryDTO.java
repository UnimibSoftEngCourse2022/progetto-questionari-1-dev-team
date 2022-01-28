package it.unimib.unimibmodules.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for the Category class.
 * @author Lorenzo Occhipinti
 * @version 0.4.0
 */
public class CategoryDTO {

    /**
     * Serialization of the id of the category.
     */
    @Getter	@Setter int id;

    /**
     * Serialization of the name of the category.
     */
    @Getter @Setter private String name;
}
