package com.UserCoupon.model;
import java.sql.Date;
import java.sql.Timestamp;
public class UserCouponVO implements java.io.Serializable{
	private Integer couponNo  ;
	private Integer userId	;
	private Integer couponQnt	;
	private Integer couponNoW  ;
	private Integer userIdW	;
	
	public Integer getCouponNoW() {
		return couponNoW;
	}
	public void setCouponNoW(Integer couponNoW) {
		this.couponNoW = couponNoW;
	}
	public Integer getUserIdW() {
		return userIdW;
	}
	public void setUserIdW(Integer userIdW) {
		this.userIdW = userIdW;
	}
	
	public Integer getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(Integer couponNo) {
		this.couponNo = couponNo;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCouponQnt() {
		return couponQnt;
	}
	public void setCouponQnt(Integer couponQnt) {
		this.couponQnt = couponQnt;
	}
	
}
	

