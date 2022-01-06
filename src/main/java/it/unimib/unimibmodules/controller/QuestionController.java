package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.dto.QuestionDTO;
import it.unimib.unimibmodules.factory.QuestionFactory;
import it.unimib.unimibmodules.repository.QuestionRepository;

public class QuestionController {

	private QuestionFactory questionFactory;
	private QuestionDTO questionDTO;
	private QuestionRepository questionRepository;
	
	public QuestionController(QuestionFactory questionFactory, QuestionDTO questionDTO,
			QuestionRepository questionRepository) {
		this.questionFactory = questionFactory;
		this.questionDTO = questionDTO;
		this.questionRepository = questionRepository;
	}
	
	
}
