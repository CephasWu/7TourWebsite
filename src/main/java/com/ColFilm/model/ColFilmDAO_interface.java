package com.ColFilm.model;
import java.util.*;
public interface ColFilmDAO_interface {
	  public void insert(ColFilmVO colFilmVO);
      public void update(ColFilmVO colFilmVO);
      public void delete(Integer FILM_ID,Integer USER_ID);
      public ColFilmVO findByPrimaryKey(Integer FILM_ID,Integer USER_ID);
      public List<ColFilmVO> getAll();
      
//    public List<ColFilmVO> getAll(Map<String, String[]> map); 

	
}
