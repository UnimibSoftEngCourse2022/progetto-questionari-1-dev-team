package it.unimib.unimibmodules.model;

import java.util.Set;
import javax.persistence.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Rapresentation of a user in a general website way. 
 * @author Gianlorenzo Martini
 * @version 0.0.1
 */

@Entity
@Table(name = "user")
public class User {

    /**
     * The id of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * The email of the user.
     */
    private String email;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The surname of the user.
     */
    private String surname;

    /**
     * The list of the surveys created by the user.
     */
    @OneToMany(mappedBy = "user")
    private Set<Survey> surveysCreated;

    /**
     * The list of the surveys compiled by the user.
     */
    @ManyToMany
    @JoinTable(
        name = "survey_user", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "survey_id"))
    private Set<Survey> surveysCompiled;

    /**
     * The list of the questions created by the user.
     */
    @OneToMany(mappedBy = "user")
    private Set<Question> questionsCreated;

    @OneToMany(mappedBy = "user")
    private Set<Answer> answers;

    public User() {}
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Survey> getSurveysCreated() {
        return surveysCreated;
    }

    public void setSurveysCreated(Set<Survey> surveysCreated) {
        this.surveysCreated = surveysCreated;
    }

    public Set<Survey> getSurveysCompiled() {
        return surveysCompiled;
    }

    public void setSurveysCompiled(Set<Survey> surveysCompiled) {
        this.surveysCompiled = surveysCompiled;
    }

    public Set<Question> getQuestionsCreated() {
        return questionsCreated;
    }

    public void setQuestionsCreated(Set<Question> questionsCreated) {
        this.questionsCreated = questionsCreated;
    }
}