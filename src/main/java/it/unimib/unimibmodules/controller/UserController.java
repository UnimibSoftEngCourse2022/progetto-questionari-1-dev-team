package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.dto.SurveyDTO;
import it.unimib.unimibmodules.dto.UserDTO;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.factory.UserFactory;
import it.unimib.unimibmodules.model.Survey;
import it.unimib.unimibmodules.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Controller handling HTTP requests from User
 * @author Gianlorenzo Martini
 * @version 0.1.0
 */

@RestController
@RequestMapping("/api")
public class UserController extends DTOMapping<User, UserDTO> {

    private static final Logger logger = LogManager.getLogger(User.class);

    /**
     * Instance of UserRepository that will be used to access the db.
     */
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository, ModelMapper modelMapper) {
    	super(modelMapper);
        this.userRepository = userRepository;

        modelMapper.createTypeMap(User.class, UserDTO.class)
                .addMappings(mapper -> {
                    mapper.map(User::getId, UserDTO::setId);
                    mapper.map(User::getUsername, UserDTO::setUsername);
                    mapper.map(User::getEmail, UserDTO::setEmail);
                });

        modelMapper.createTypeMap(UserDTO.class, User.class)
                .addMappings(mapper -> {
                    mapper.map(UserDTO::getId, User::setId);
                    mapper.map(UserDTO::getUsername, User::setUsername);
                    mapper.map(UserDTO::getPassword, User::setPassword);
                    mapper.map(UserDTO::getEmail, User::setEmail);
                    mapper.map(UserDTO::getName, User::setName);
                    mapper.map(UserDTO::getSurname, User::setSurname);
                });
    }

    /**
     * Gets the User associated with the given id.
     * @param       id      the id of the user
     * @return              an HTTP response with status 200 and the UserDTO if the user has been found, 500 otherwise
     */
    @GetMapping(path = "/getUser/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable int id) throws NotFoundException {

        User user = userRepository.get(id);
        logger.debug("Retrieved User with id " + id + ".");
        return new ResponseEntity<>(convertToDTO(user), HttpStatus.OK);
    }

    /**
     * Gets the surveys created by the user identified by the username
     * @param   username    the username of a user
     * @return              a list of surveys created by the user identified with username
     * @throws NotFoundException
     */
    @GetMapping("/getSurveysCreated")
    public ResponseEntity<List<SurveyDTO>> getSurveysCreated(@RequestParam (name = "username") String username) throws NotFoundException {

        User user = userRepository.getByUsername(username);
        Set<Survey> surveys  = user.getSurveysCreated();

        List<SurveyDTO> surveysDTO = new ArrayList<>();

        for (Survey survey : surveys) {
            SurveyDTO surveyDTO = new SurveyDTO();
            surveyDTO.setId(survey.getId());
            surveyDTO.setSurveyName(survey.getName());
            surveyDTO.setUserDTO(convertToDTO(survey.getUser()));
            surveysDTO.add(surveyDTO);
        }

        logger.debug("Retrieved surveys created by user: " + username + ".");
        return new ResponseEntity<>(surveysDTO, HttpStatus.OK);
    }

    /**
     * Logs the User into the website if the combination of username and password match.
     * @param       userDTO        Representation of a user from the body of the http post
     * @return                     an HTTP response with status 200 and the UserDTO if the user has been auth, 500 otherwise
     */
    @PostMapping(path = "/logInUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> logInUser(@RequestBody UserDTO userDTO) throws NotFoundException {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.getByUsername(userDTO.getUsername());

        if (bCryptPasswordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            logger.debug("Successful sign in of user: " + userDTO.getUsername() + ".");
            return new ResponseEntity<>("{\"response\":\"Login Successful.\"}", HttpStatus.OK);
        } else {
            logger.debug("Failed sign in of user: " + userDTO.getUsername() + ".");
            return new ResponseEntity<>("{\"response\":\"Login fAILED.\"}", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Create a new User.
     * @param   userDTO         Representation of a user from the body of the http post
     * @return                  an HTTP response with status 200 and the UserDTO if the user has been created, 500 otherwise
     */
    @PostMapping(path = "/signUpUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> signUpUser(@RequestBody UserDTO userDTO) {

        try {
            System.out.println(userDTO.getUsername());
            User user = userRepository.getByUsername(userDTO.getUsername());
            logger.debug("Failed creation of user " + userDTO.getUsername() + ": user already exist.");
            return new ResponseEntity<>("{\"response\":\"Username already existing.\"}", HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            User user = convertToEntity(userDTO);
            user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

            userRepository.add(user);
            logger.debug("Successful creation of user " + userDTO.getUsername() + ".");
            return new ResponseEntity<>("{\"response\":\"User created.\"}", HttpStatus.CREATED);
        }
    }

    /**
	 * Converts an instance of User to an instance of UserDTO
	 * @param       user	    an instance of user
	 * @return			        an instance of UserDTO, containing the serialized data of user
	 * @see DTOMapping#convertToDTO
	 */
	@Override
	public UserDTO convertToDTO(User user) {
        return modelMapper.getTypeMap(User.class, UserDTO.class).map(user);
	}

	/**
	 * Converts an instance of UserDTO to an instance of User
	 * @param       userDTO	    an instance of UserDTO
	 * @return				    an instance of User, containing the deserialized data of UserDTO
	 * @see DTOMapping#convertToEntity
	 */
	@Override
	public User convertToEntity(UserDTO userDTO) {
        return modelMapper.getTypeMap(UserDTO.class, User.class).map(userDTO);
	}
}