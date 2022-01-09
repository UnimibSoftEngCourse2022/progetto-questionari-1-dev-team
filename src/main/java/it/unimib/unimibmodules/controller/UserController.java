package it.unimib.unimibmodules.controller;

import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.unimib.unimibmodules.dto.UserDTO;
import it.unimib.unimibmodules.model.User;

/**
 * Controller handling HTTP requests from User
 * @author Gianlorenzo Martini
 */

@RestController
public class UserController extends DTOMapping<User, UserDTO> {
    
    /**
     * Instance of UserRepository that will be used to access the db.
     */
    private final UserRepository userRepository;

    /**
     * The istance of modelMapper that will be used to convert User to UserDTO and vice versa.
     */
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Gets the User associated with the given id.
     * @param       id      the id of the user
     * @return              an HTTP response with status 200 and the UserDTO if the user has been found, 500 otherwise
     */
    @GetMapping(path = "/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {

        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Logs the User into the website if the combination of username and password match.
     * @param       username        the username insert by the user
     * @param       password        the password insert by the user
     * @return                      an HTTP response with status 200 and the UserDTO if the user has been auth, 500 otherwise
     */
    @PostMapping(path = "/logInUser")
    public ResponseEntity<User> logInUser(@RequestParam String username, @RequestParam String password) {

        // Checking if username-password match

        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Create a new User.
     * @param requestParams     Parameters insert in order to create a new User
     * @return                  an HTTP response with status 200 and the UserDTO if the user has been created, 500 otherwise
     */
    @PostMapping(path = "/signUpUser")
    public ResponseEntity<User> signUpUser(@RequestParam Map<String,String> requestParams) {

        String username = requestParams.get("username");
        String email = requestParams.get("email");
        String password = requestParams.get("password");
        String name = requestParams.get("name");
        String surname = requestParams.get("surname");

        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    /**
	 * Converts an instance of User to an instance of UserDTO
	 * @param       user	    an instance of user
	 * @return			        an instance of UserDTO, containing the serialized data of user
	 * @see DTOMapping#convertToDTO
	 */
	@Override
	public UserDTO convertToDTO(User user) {

		return modelMapper.map(user, UserDTO.class);
	}

	/**
	 * Converts an instance of UserDTO to an instance of User
	 * @param       UserDTO	    an instance of UserDTO
	 * @return				    an instance of User, containing the deserialized data of UserDTO
	 * @see DTOMapping#convertToEntity
	 */
	@Override
	public User convertToEntity(UserDTO userDTO) {

		return modelMapper.map(userDTO, User.class);
	}
}