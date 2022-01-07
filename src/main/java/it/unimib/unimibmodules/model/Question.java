package it.unimib.unimibmodules.model;

public class Question {
	
	private int id;
	private String urlImage;
	private String text;
	private Answer answer;
	private Category category;
	
	public Question(int id, String urlImage, String text, Answer answer, Category category) {
		this.id = id;
		this.urlImage = urlImage;
		this.text = text;
		this.answer = answer;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
	
	
	
	
	

}
