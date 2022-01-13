package it.unimib.unimibmodules.dto;

public class CategoryDTO {

    /**
     * Serialization of the id of the category.
     */
    int id;

    /**
     * Serialization of the name of the category.
     */
    private String name;

    /**
     * Returns the name of the category.
     * @return	the name of the category
     */
    public String getName() {

        return name;
    }

    /**
     * Modifies the name of the category, setting <code>name</code> as the new value.
     * @param	name	the new name value
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Returns the id of the category.
     * @return	the id of the category
     */
    public int getId() {

        return id;
    }

    /**
     * Modifies the id of the category, setting <code>id</code> as the new value.
     * @param	id	the new id value
     */
    public void setId(int id) {

        this.id = id;
    }
}
