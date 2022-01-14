package it.unimib.unimibmodules.repository;

import java.util.List;
import java.util.Optional;

import it.unimib.unimibmodules.exception.NotFoundException;
/**
 * @version 0.1.0
 */
public interface Repository <T>{
	
	void add(T entity);
	
	void addall(List<T> entities);
	
	T get(int id) throws NotFoundException;
	
	Iterable<T> getAll();
	
	void remove(int id);
	
	void removeAll();
	
	void modify(T entity);
}