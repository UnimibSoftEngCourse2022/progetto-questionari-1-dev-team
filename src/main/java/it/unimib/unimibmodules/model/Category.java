package it.unimib.unimibmodules.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents a category for a question.
 * @author Lorenzo Occhipinti
 * @version 1.0.0
 */
@Entity
@Table(name = "Category")
public class Category {

    /**
     * The id of the Category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter	@Setter private int id;

    /**
     * The name of the Category.
     */
    @Getter	@Setter private String name;

    /**
     * Questions that are in this category
     */
    @OneToMany(mappedBy="category")
    @Getter	@Setter private Set<Question> questions;

    /**
     * Creates an empty Category.
     */
    public Category() {
        // Do nothing because it only creates an empty category
    }
}
