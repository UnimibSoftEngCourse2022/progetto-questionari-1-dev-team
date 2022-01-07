package it.unimib.unimibmodules.repository;

import java.util.List;
import java.util.Optional;

public interface Repository <T>{

    public  void add(T entity);

    public void addall(List<T> entities);

    public Optional<T> get(int id);

    public Iterable<T> getAll();

    public void remove(int id);

    public void removeAll();

    public void modify(T entity);
}