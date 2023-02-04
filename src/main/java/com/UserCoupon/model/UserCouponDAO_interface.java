package com.UserCoupon.model;
import java.util.*;
public interface UserCouponDAO_interface {
	  public void insert(UserCouponVO userCoupon);
      public void update(UserCouponVO userCoupon);
      public void delete(Integer COUPON_NO,Integer USER_ID);
      public UserCouponVO findByPrimaryKey(Integer COUPON_NO,Integer USER_ID);
      public List<UserCouponVO> getAll();
      //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//    public List<userCoupon> getAll(Map<String, String[]> map); 

	
}
