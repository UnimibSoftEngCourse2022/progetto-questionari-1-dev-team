package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface Repository <T>{
	
	void add(T entity);
	
	void addAll(List<T> entities);
	
	Optional<T> get(int id) throws NotFoundException;
	
	Iterable<T> getAll();
	
	void remove(int id) throws NotFoundException;
	
	void removeAll();
	
	void modify(T entity);
}