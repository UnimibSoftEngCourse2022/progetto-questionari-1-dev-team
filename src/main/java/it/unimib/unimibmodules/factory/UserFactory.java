package it.unimib.unimibmodules.factory;

import it.unimib.unimibmodules.model.User;

/**
 * Factory class for User.
 * @author Gianlorenzo Martini
 * @version 0.3.0
 */

public class UserFactory {

    private UserFactory() {
        //private constructor in order to hide the implicit public one.
    }

    /**
	 * Creates a new instance of User.
	 * @param   email       the email chose by the user
     * @param   password    the password chose by the user
     * @param   username    the username of the user
     * @param   name        the name of the user
     * @param   surname     the surname of the user
	 * @return              the new instance of User
	 */
    
    public static User createUser(String email, String password, String username, String name, String surname) {
        
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);

        return user;
    }
}
