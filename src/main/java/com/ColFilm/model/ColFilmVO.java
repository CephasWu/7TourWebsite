package com.ColFilm.model;
import java.sql.Date;
import java.sql.Timestamp;
public class ColFilmVO implements java.io.Serializable{
	private Integer filmId  ;
	private Integer userId	;
	private Integer filmIdW  ;
	private Integer userIdW	;
	public Integer getFilmIdW() {
		return filmIdW;
	}
	public void setFilmIdW(Integer filmIdW) {
		this.filmIdW = filmIdW;
	}
	public Integer getUserIdW() {
		return userIdW;
	}
	public void setUserIdW(Integer userIdW) {
		this.userIdW = userIdW;
	}
	
	public Integer getFilmId() {
		return filmId;
	}
	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	

}
	

