package it.unimib.unimibmodules.controller;

import java.text.ParseException;
import java.util.Optional;
import java.util.TimeZone;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unimib.unimibmodules.dao.SurveyDAO;
import it.unimib.unimibmodules.dto.SurveyDTO;
import it.unimib.unimibmodules.exception.FormatException;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Survey;
import it.unimib.unimibmodules.repository.SurveyRepository;

/**
 * Controller handling HTTP requests related to Survey.
 * @author Luca Milazzo
 */
@RestController
@RequestMapping("/api")
public class SurveyController extends DTOMapping<Survey, SurveyDTO>{
	
	/**
	 * Instance of SurveyRepository that will be used to access Repository layer.
	 */
	private final SurveyRepository surveyRepository;
	
	@Autowired
	public SurveyController(SurveyRepository surveyRepository, ModelMapper modelMapper) {
		super(modelMapper);
		this.surveyRepository = surveyRepository;
	}
	
	
	/**
	 * Finds the survey associated with the given id.
	 * @param	id	the id of the Survey
	 * @return		an HTTP response with status 200 and the SurveyDTO if the survey has been found
	 * @throws SurveyNotFoundException 
	 */
	@GetMapping("/findSurvey")
	public ResponseEntity<Survey> findSurvey(@RequestParam(name = "id") int id) throws NotFoundException {
		Survey survey = surveyRepository.get(id);
		return new ResponseEntity<Survey>(survey, HttpStatus.OK);
	}
	
	/**
	 * Creates a survey, with the given name and request's date-time, that is associated to the given user id
	 * @param	surveyDTO	the serialized version of a Survey object
	 * @return			an HTTP response with status 200 if the survey has been added
	 * @throws FormatException 
	 */
	@PostMapping("/createSurvey")
	public ResponseEntity<Survey> postSurvey(@RequestParam SurveyDTO surveyDTO) throws FormatException {
		
		Survey survey = convertToEntity(surveyDTO);
		surveyRepository.add(survey);
		return new ResponseEntity<Survey>(survey, HttpStatus.OK);
	}
	
	/**
	 * Modifies the survey's name associated with the given surveyDTO.
	 * @param	surveyDTO	the serialized version of a Survey object
	 * @return			an HTTP response with status 200 if the survey has been modified, 500 otherwise
	 */
	@PatchMapping(path = "/updateSurvey")
	public ResponseEntity<Survey> patchSurvey(@RequestParam SurveyDTO surveyDTO) throws FormatException {

		Survey survey = convertToEntity(surveyDTO);
		surveyRepository.modify(survey);
		return new ResponseEntity<Survey>(survey, HttpStatus.OK);
	}
	
	
	/**
	 * Deletes the survey associated with the given id.
	 * @param   id	the id of the survey that will be deleted
	 * @return		an HTTP Response with status 200 if the survey has been deleted, 500 otherwise
	 */
	@DeleteMapping(path = "/deleteSurvey/{id}")
	public ResponseEntity<Survey> deleteSurvey(@PathVariable int id) {
		
		surveyRepository.remove(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	/**
	 * Converts an instance of Survey to an instance of SurveyDTO
	 * @param   survey	an instance of Survey
	 * @return			an instance of SurveyDTO, containing the serialized data of survey
	 * @see DTOMapping#convertToDTO
	 */
	@Override
	public SurveyDTO convertToDTO(Survey survey) {
		SurveyDTO surveyDTO = modelMapper.map(survey, SurveyDTO.class);
		surveyDTO.setCreationDate(survey.getCreationDate(), TimeZone.getDefault().toString());
	    return surveyDTO;
	}

	/**
	 * Converts an instance of SurveyDTO to an instance of Survey
	 * @param   surveyDTO	an instance of SurveyDTO
	 * @return				an instance of Survey, containing the deserialized data of surveyDTO
	 * @throws FormatException
	 * @see DTOMapping#convertToEntity
	 */
	@Override
	public Survey convertToEntity(SurveyDTO surveyDTO) throws FormatException {
		
		Survey survey = modelMapper.map(surveyDTO, Survey.class);
		survey.setCreationDate(surveyDTO.getCreationDateConverted(TimeZone.getDefault().toString()));
	    return survey;
	}

}