package com.Users.model;
import java.util.*;
public interface UsersDAO_interface {
	  public void insert(UsersVO userVO);
      public void update(UsersVO userVO);
      public void updatePassword(UsersVO userVO);
      public void resetPassword(UsersVO userVO);
      public void updateUserStatusByUserAccount(UsersVO userVO);
      public void restorationUserStatusByUserAccount(UsersVO userVO);
      public void delete(Integer USER_ID);
      public UsersVO findByPrimaryKey(Integer USER_ID);
      public List<UsersVO> getAll();
      public boolean login(String USER_ACCOUNT,String USER_PASSWORD);
//    public List<UsersVO> getAll(Map<String, String[]> map); 
      public byte[] getPic (String USER_PIC) ;
      public UsersVO findByUserAccount(String USER_ACCOUNT);
      public UsersVO findByUserName(String USER_NAME);
      public List<UsersVO> getAllByReportTotalCount(Integer REPORT_TOTAL_COUNT);
      public void updateinfo(UsersVO userVO);
      public Integer findStatusByUserAccount(String USER_ACCOUNT);
      public Integer findIdByUserAccount(String USER_ACCOUNT);
  	
}
