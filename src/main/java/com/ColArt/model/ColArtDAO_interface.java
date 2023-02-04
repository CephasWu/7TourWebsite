package com.ColArt.model;
import java.util.*;
public interface ColArtDAO_interface {
	  public void insert(ColArtVO colArtVO);
      public void update(ColArtVO colArtVO);
      public void delete(Integer ART_ID,Integer USER_ID);
      public ColArtVO findByPrimaryKey(Integer USER_ID);
      public List<ColArtVO> getAll(Integer USER_ID);
      //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//    public List<ColArtVO> getAll(Map<String, String[]> map); 

	
}
