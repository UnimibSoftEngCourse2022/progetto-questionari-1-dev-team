package it.unimib.unimibmodules.repository;

import java.util.List;

public interface Repository <T>{
	
	public  void add(T entity);
	
	public void addall(List<T> entities);
	
	public T get(int id);
	
	public List<T> getAll();
	
	public void remove(int id);
	
	public void removeAll();
	
	public void modify(T entity);
}
