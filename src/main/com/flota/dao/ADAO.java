package main.com.flota.dao;

import java.util.List;

public interface ADAO<T> {

   public T registrarAvion( T t);

   public T buscarAvion(Long id);

   public void  eliminarAvion(Long id);

   public List<T> buscarAviones();





}
