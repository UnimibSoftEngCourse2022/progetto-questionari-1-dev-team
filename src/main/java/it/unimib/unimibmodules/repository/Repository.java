package it.unimib.unimibmodules.repository;

import java.util.List;
import java.util.Optional;

public interface Repository <T>{
	
	void add(T entity);
	
	void addAll(List<T> entities);
	
	Optional<T> get(int id);
	
	Iterable<T> getAll();
	
	void remove(int id);
	
	void removeAll();
	
	void modify(T entity);
}