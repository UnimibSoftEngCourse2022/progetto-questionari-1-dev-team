package it.unimib.unimibmodules.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents a category for a question.
 * @author Lorenzo Occhipinti
 * @version 0.3.0
 */
@Entity
@Table(name = "category")
public class Category {
    /**
     * The id of the Category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * The name of the Category.
     */
    private String name;

    /**
     * Questions that are in this category
     */
    @OneToMany(mappedBy="category")
    private Set<Question> questions;

    /**
     * Creates an empty Category.
     */
    public Category() {
        // Do nothing because it only creates an empty category
    }

    /**
     * Returns the id of the Category.
     * @return  the id of the Category
     */
    public int getId() {
        return id;
    }

    /**
     * Modifies the id of the Category, setting <code>id</code> as the new value.
     * @param   id  the new id Category
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the Category.
     * @return  the name of the Category
     */
    public String getName() {
        return name;
    }

    /**
     * Modifies the name of the Category, setting <code>text</code> as the new value.
     * @param   name    the new text value
     */
    public void setName(String name) {
        this.name = name;
    }
}
