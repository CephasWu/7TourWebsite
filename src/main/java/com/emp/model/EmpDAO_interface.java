package com.emp.model;
import java.util.*;
public interface EmpDAO_interface {
	  public void insert(EmpVO empVO);
      public void update(EmpVO empVO);
      public void delete(Integer empNo);
      public EmpVO findByEmpName(String empName);
      public List<EmpVO> getAll();
      public boolean login(String USER_ACCOUNT,String USER_PASSWORD);
//    public List<UsersVO> getAll(Map<String, String[]> map); 
      public EmpVO findByEmpEmail(String empEmail);
	
}
