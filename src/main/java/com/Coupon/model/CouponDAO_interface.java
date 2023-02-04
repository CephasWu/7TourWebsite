package com.Coupon.model;
import java.util.*;
public interface CouponDAO_interface {
	  public void insert(CouponVO couponVO);
      public void update(CouponVO couponVO);
      public void delete(Integer COUPON_NO);
      public CouponVO findByPrimaryKey(Integer COUPON_NO);
      public List<CouponVO> getAll();
      //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//    public List<CouponVO> getAll(Map<String, String[]> map); 

	
}
