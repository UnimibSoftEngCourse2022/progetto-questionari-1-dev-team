package it.unimib.unimibmodules.repository;

import lombok.Getter;

public enum UnitOfWorkOperations {

	INSERT("INSERT"),
	DELETE("DELETE"),
	MODIFY("MODIFY");

	@Getter	private final String value;

	UnitOfWorkOperations(String value) {

		this.value = value;
	}
}
