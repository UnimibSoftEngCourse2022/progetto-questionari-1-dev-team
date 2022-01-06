package it.unimib.unimibmodules.model;

public class Question {
	
	private int id;
	private String urlImmagine;
	private String testo;
	private Answer answer;
	private Category category;
	
	public Question(int id, String urlImmagine, String testo, Answer answer, Category category) {
		this.id = id;
		this.urlImmagine = urlImmagine;
		this.testo = testo;
		this.answer = answer;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrlImmagine() {
		return urlImmagine;
	}

	public void setUrlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
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
