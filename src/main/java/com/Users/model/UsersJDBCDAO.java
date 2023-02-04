package com.Users.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersJDBCDAO implements UsersDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7";
	String userid = "root";
	String passwd = "Cephas1981";

	private static final String INSERT_STMT = "INSERT INTO USERS (USER_ACCOUNT,USER_PASSWORD,USER_NAME,USER_NICKNAME,USER_ID_NO,USER_BIRTHDAY,USER_PHONE,USER_ADDRESS,USER_CERTIFICATION_STATUS,USER_STATUS,USER_GENDER,USER_FORUM_LEVEL,USER_PIC,USER_SHOP_LEVEL,BONUS_POINTS,ALLTOGETHER_SCORE,ALLTOGETHER_SCORE_PEOPLE,ART_COUNT,LIKE_TOTAL_COUNT,PURCHASE_TOTAL,REPORT_TOTAL_COUNT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT USER_ID ,USER_ACCOUNT,USER_PASSWORD,USER_NAME,USER_NICKNAME,USER_ID_NO,USER_BIRTHDAY,USER_PHONE,USER_ADDRESS,USER_CERTIFICATION_STATUS,USER_STATUS,USER_GENDER,USER_REGISTRATION_DATE,USER_FORUM_LEVEL,USER_PIC,USER_SHOP_LEVEL,BONUS_POINTS,ALLTOGETHER_SCORE,ALLTOGETHER_SCORE_PEOPLE,ART_COUNT,LIKE_TOTAL_COUNT,PURCHASE_TOTAL,REPORT_TOTAL_COUNT FROM USERS order by USER_ID  ";
	private static final String GET_ONE_STMT = "SELECT USER_ID ,USER_ACCOUNT,USER_PASSWORD,USER_NAME,USER_NICKNAME,USER_ID_NO,USER_BIRTHDAY,USER_PHONE,USER_ADDRESS,USER_CERTIFICATION_STATUS,USER_STATUS,USER_GENDER,USER_REGISTRATION_DATE,USER_FORUM_LEVEL,USER_PIC,USER_SHOP_LEVEL,BONUS_POINTS,ALLTOGETHER_SCORE,ALLTOGETHER_SCORE_PEOPLE,ART_COUNT,LIKE_TOTAL_COUNT,PURCHASE_TOTAL,REPORT_TOTAL_COUNT FROM USERS where USER_ID = ? ";
	private static final String DELETE = "DELETE FROM USERS where USER_ID = ?";
	private static final String UPDATE = "UPDATE USERS set USER_ACCOUNT= ?,USER_PASSWORD= ?,USER_NAME= ?,USER_NICKNAME= ?,USER_ID_NO= ?,USER_BIRTHDAY= ?,USER_PHONE= ?,USER_ADDRESS= ?,USER_CERTIFICATION_STATUS= ?,USER_STATUS= ?,USER_GENDER= ?,USER_FORUM_LEVEL= ?,USER_PIC= ?,USER_SHOP_LEVEL= ?,BONUS_POINTS= ?,ALLTOGETHER_SCORE= ?,ALLTOGETHER_SCORE_PEOPLE= ?,ART_COUNT= ?,LIKE_TOTAL_COUNT= ?,PURCHASE_TOTAL= ?,REPORT_TOTAL_COUNT = ? where USER_ID = ? ";
	private static final String RESETPASSWORD = "UPDATE USERS SET USER_PASSWORD = ?  WHERE  USER_ACCOUNT= ? " ;
	private static final String UPDATEPASSWORD = "UPDATE USERS SET USER_PASSWORD = ?  WHERE USER_PASSWORD = ? AND USER_ACCOUNT= ? " ;
	private static final String UPDATEREPORTTOTALCOUNTBYUSERACCOUNT = "UPDATE USERS set USER_STATUS = '1' where USER_ACCOUNT = ? ";
	private static final String RESTORATIONREPORTTOTALCOUNTBYUSERACCOUNT = "UPDATE USERS set USER_STATUS = '0' where USER_ACCOUNT = ? ";
	private static final String UPDATEINFO_USER_ACCOUNT = "UPDATE USERS set USER_NICKNAME= ?,USER_PHONE= ? , USER_PIC= ?  where USER_ACCOUNT = ? ";
	private static final String LOGIN = "select USER_ACCOUNT , USER_PASSWORD from USERS where USER_ACCOUNT = ? and USER_PASSWORD = ? ; ";
	private static final String GET_ONE_BY_USER_ACCOUNT = "SELECT USER_ID ,USER_ACCOUNT,USER_PASSWORD,USER_NAME,USER_NICKNAME,USER_ID_NO,USER_BIRTHDAY,USER_PHONE,USER_ADDRESS,USER_CERTIFICATION_STATUS,USER_STATUS,USER_GENDER,USER_REGISTRATION_DATE,USER_FORUM_LEVEL,USER_PIC,USER_SHOP_LEVEL,BONUS_POINTS,ALLTOGETHER_SCORE,ALLTOGETHER_SCORE_PEOPLE,ART_COUNT,LIKE_TOTAL_COUNT,PURCHASE_TOTAL,REPORT_TOTAL_COUNT FROM USERS where USER_ACCOUNT = ?  ";
	private static final String GET_ONE_BY_USER_NAME = "SELECT USER_ID ,USER_ACCOUNT,USER_PASSWORD,USER_NAME,USER_NICKNAME,USER_ID_NO,USER_BIRTHDAY,USER_PHONE,USER_ADDRESS,USER_CERTIFICATION_STATUS,USER_STATUS,USER_GENDER,USER_REGISTRATION_DATE,USER_FORUM_LEVEL,USER_PIC,USER_SHOP_LEVEL,BONUS_POINTS,ALLTOGETHER_SCORE,ALLTOGETHER_SCORE_PEOPLE,ART_COUNT,LIKE_TOTAL_COUNT,PURCHASE_TOTAL,REPORT_TOTAL_COUNT FROM USERS where USER_NAME = ?  ";
	private static final String GET_ALL_BY_REPORT_TOTAL_COUNT = "SELECT USER_ID ,USER_ACCOUNT,USER_PASSWORD,USER_NAME,USER_NICKNAME,USER_ID_NO,USER_BIRTHDAY,USER_PHONE,USER_ADDRESS,USER_CERTIFICATION_STATUS,USER_STATUS,USER_GENDER,USER_REGISTRATION_DATE,USER_FORUM_LEVEL,USER_PIC,USER_SHOP_LEVEL,BONUS_POINTS,ALLTOGETHER_SCORE,ALLTOGETHER_SCORE_PEOPLE,ART_COUNT,LIKE_TOTAL_COUNT,PURCHASE_TOTAL,REPORT_TOTAL_COUNT FROM USERS where REPORT_TOTAL_COUNT >= ?  ";
	private static final String FIND_COUNT_BY_USER_ACCOUNT = "SELECT USER_STATUS FROM USERS where USER_ACCOUNT = ?  ";
	private static final String FIND_ID_BY_USER_ACCOUNT = "SELECT USER_ID FROM USERS where USER_ACCOUNT = ?  ";
	
	@Override
	public void insert(UsersVO usersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			InputStream in;
//
//			in = Files.newInputStream(Path.of("images/3.jpg"));

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, usersVO.getUserAccount());
			pstmt.setString(2, usersVO.getUserPassword());
			pstmt.setString(3, usersVO.getUserName());
			pstmt.setString(4, usersVO.getUserNickname());
			pstmt.setString(5, usersVO.getUserIdNo());
			pstmt.setDate(6, usersVO.getUserBrthday());
			pstmt.setString(7, usersVO.getUserPhone());
			pstmt.setString(8, usersVO.getUserAddress());
			pstmt.setByte(9, usersVO.getUserCertificationStatus());
			pstmt.setByte(10, usersVO.getUserStatus());
			pstmt.setString(11, usersVO.getUserGender());
			pstmt.setByte(12, usersVO.getUserForumLevel());
			pstmt.setBytes(13, usersVO.getUserPic());
//			pstmt.setBinaryStream(13, in, in.available());
			pstmt.setByte(14, usersVO.getUserShopLevel());
			pstmt.setInt(15, usersVO.getBonusPoints());
			pstmt.setFloat(16, usersVO.getAlltogetherScore());
			pstmt.setInt(17, usersVO.getAlltogetherScorePeople());
			pstmt.setInt(18, usersVO.getArtCount());
			pstmt.setInt(19, usersVO.getLikeTotalCount());
			pstmt.setInt(20, usersVO.getPurchaseTotal());
			pstmt.setByte(21, usersVO.getReportTotalCount());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(UsersVO usersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, usersVO.getUserAccount());
			pstmt.setString(2, usersVO.getUserPassword());
			pstmt.setString(3, usersVO.getUserName());
			pstmt.setString(4, usersVO.getUserNickname());
			pstmt.setString(5, usersVO.getUserIdNo());
			pstmt.setDate(6, usersVO.getUserBrthday());
			pstmt.setString(7, usersVO.getUserPhone());
			pstmt.setString(8, usersVO.getUserAddress());
			pstmt.setByte(9, usersVO.getUserCertificationStatus());
			pstmt.setByte(10, usersVO.getUserStatus());
			pstmt.setString(11, usersVO.getUserGender());
			pstmt.setByte(12, usersVO.getUserForumLevel());
			pstmt.setBytes(13, usersVO.getUserPic());
//			InputStream in;
//			in = Files.newInputStream(Path.of("images/3.jpg"));
//			pstmt.setBinaryStream(13, in, in.available());
			pstmt.setByte(14, usersVO.getUserShopLevel());
			pstmt.setInt(15, usersVO.getBonusPoints());
			pstmt.setFloat(16, usersVO.getAlltogetherScore());
			pstmt.setInt(17, usersVO.getAlltogetherScorePeople());
			pstmt.setInt(18, usersVO.getArtCount());
			pstmt.setInt(19, usersVO.getLikeTotalCount());
			pstmt.setInt(20, usersVO.getPurchaseTotal());
			pstmt.setByte(21, usersVO.getReportTotalCount());
			pstmt.setInt(22, usersVO.getUserId());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	@Override
	public void resetPassword(UsersVO usersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(RESETPASSWORD);
			pstmt.setString(1, usersVO.getUserPassword());
			pstmt.setString(2, usersVO.getUserAccount());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		}
	@Override
	public void updatePassword(UsersVO usersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEPASSWORD);
			pstmt.setString(1, usersVO.getUserPassword());
			pstmt.setString(2, usersVO.getUserPasswordW());
			pstmt.setString(3, usersVO.getUserAccount());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		}
	@Override
	public void updateUserStatusByUserAccount(UsersVO usersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEREPORTTOTALCOUNTBYUSERACCOUNT);
			pstmt.setString(1, usersVO.getUserAccount());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	@Override
	public void restorationUserStatusByUserAccount(UsersVO usersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(RESTORATIONREPORTTOTALCOUNTBYUSERACCOUNT);
			pstmt.setString(1, usersVO.getUserAccount());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	@Override
	public void updateinfo(UsersVO usersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEINFO_USER_ACCOUNT);
			pstmt.setString(1, usersVO.getUserNickname());
			pstmt.setString(2, usersVO.getUserPhone());
			pstmt.setBytes(3, usersVO.getUserPic());
			pstmt.setString(4, usersVO.getUserAccount());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer USER_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, USER_ID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public UsersVO findByPrimaryKey(Integer USER_ID) {
//		File dir = new File("Pictures");
//		if (!dir.exists()) {
//			dir.mkdir();
//		}
		UsersVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, USER_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				userVO = new UsersVO();
//				userVO.setUserId(rs.getInt("USER_ID"));
				userVO.setUserAccount(rs.getString("USER_ACCOUNT"));
				userVO.setUserPassword(rs.getString("USER_PASSWORD"));
				userVO.setUserName(rs.getString("USER_NAME"));
				userVO.setUserNickname(rs.getString("USER_NICKNAME"));
				userVO.setUserIdNo(rs.getString("USER_ID_NO"));
				userVO.setUserBrthday(rs.getDate("USER_BIRTHDAY"));
				userVO.setUserPhone(rs.getString("USER_PHONE"));
				userVO.setUserAddress(rs.getString("USER_ADDRESS"));
				userVO.setUserCertificationStatus(rs.getByte("USER_CERTIFICATION_STATUS"));
				userVO.setUserStatus(rs.getByte("USER_STATUS"));
				userVO.setUserGender(rs.getString("USER_GENDER"));
				userVO.setUserRegistrationDate(rs.getTimestamp("USER_REGISTRATION_DATE"));
				userVO.setUserForumLevel(rs.getByte("USER_FORUM_LEVEL"));
//				InputStream in = rs.getBinaryStream(15);
//				try {
//					Files.copy(in, Path.of("Pictures/imageTest.jpg"), StandardCopyOption.REPLACE_EXISTING);
//				in.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}

//				userVO.setUserPic(rs.getByte("USER_PIC"));
				userVO.setUserShopLevel(rs.getByte("USER_SHOP_LEVEL"));
				userVO.setBonusPoints(rs.getInt("BONUS_POINTS"));
				userVO.setAlltogetherScore(rs.getFloat("ALLTOGETHER_SCORE"));
				userVO.setAlltogetherScorePeople(rs.getInt("ALLTOGETHER_SCORE_PEOPLE"));
				userVO.setArtCount(rs.getInt("ART_COUNT"));
				userVO.setLikeTotalCount(rs.getInt("LIKE_TOTAL_COUNT"));
				userVO.setPurchaseTotal(rs.getInt("PURCHASE_TOTAL"));
				userVO.setReportTotalCount(rs.getByte("REPORT_TOTAL_COUNT"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return userVO;
	}

	@Override
	public List<UsersVO> getAll() {
		List<UsersVO> list = new ArrayList<UsersVO>();
		UsersVO userVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				userVO = new UsersVO();
				userVO.setUserId(rs.getInt("USER_ID"));
				userVO.setUserAccount(rs.getString("USER_ACCOUNT"));
				userVO.setUserPassword(rs.getString("USER_PASSWORD"));
				userVO.setUserName(rs.getString("USER_NAME"));
				userVO.setUserNickname(rs.getString("USER_NICKNAME"));
				userVO.setUserIdNo(rs.getString("USER_ID_NO"));
				userVO.setUserBrthday(rs.getDate("USER_BIRTHDAY"));
				userVO.setUserPhone(rs.getString("USER_PHONE"));
				userVO.setUserAddress(rs.getString("USER_ADDRESS"));
				userVO.setUserCertificationStatus(rs.getByte("USER_CERTIFICATION_STATUS"));
				userVO.setUserStatus(rs.getByte("USER_STATUS"));
				userVO.setUserGender(rs.getString("USER_GENDER"));
				userVO.setUserRegistrationDate(rs.getTimestamp("USER_REGISTRATION_DATE"));
				userVO.setUserForumLevel(rs.getByte("USER_FORUM_LEVEL"));
				userVO.setUserPic(rs.getBytes("USER_PIC"));
				userVO.setUserShopLevel(rs.getByte("USER_SHOP_LEVEL"));
				userVO.setBonusPoints(rs.getInt("BONUS_POINTS"));
				userVO.setAlltogetherScore(rs.getFloat("ALLTOGETHER_SCORE"));
				userVO.setAlltogetherScorePeople(rs.getInt("ALLTOGETHER_SCORE_PEOPLE"));
				userVO.setArtCount(rs.getInt("ART_COUNT"));
				userVO.setLikeTotalCount(rs.getInt("LIKE_TOTAL_COUNT"));
				userVO.setPurchaseTotal(rs.getInt("PURCHASE_TOTAL"));
				userVO.setReportTotalCount(rs.getByte("REPORT_TOTAL_COUNT"));

				list.add(userVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public boolean login(String USER_ACCOUNT, String USER_PASSWORD) {
		boolean list = true;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LOGIN);
			pstmt.setString(1, USER_ACCOUNT);
			pstmt.setString(2, USER_PASSWORD);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = true;
			} else {
				list = false;
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		}
		return list;
	}

	public byte[] getPic(String path) {
		File file = new File(path);
		try (FileInputStream fis = new FileInputStream(file); BufferedInputStream bis = new BufferedInputStream(fis);) {
			byte[] data = new byte[bis.available()];
			data = bis.readAllBytes();
			return data;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new byte[0];
		} catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
		}

	}

	@Override
	public UsersVO findByUserAccount(String USER_ACCOUNT) {
		UsersVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BY_USER_ACCOUNT);

			pstmt.setString(1, USER_ACCOUNT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				userVO = new UsersVO();
				userVO.setUserId(rs.getInt("USER_ID"));
				userVO.setUserAccount(rs.getString("USER_ACCOUNT"));
				userVO.setUserPassword(rs.getString("USER_PASSWORD"));
				userVO.setUserName(rs.getString("USER_NAME"));
				userVO.setUserNickname(rs.getString("USER_NICKNAME"));
				userVO.setUserIdNo(rs.getString("USER_ID_NO"));
				userVO.setUserBrthday(rs.getDate("USER_BIRTHDAY"));
				userVO.setUserPhone(rs.getString("USER_PHONE"));
				userVO.setUserAddress(rs.getString("USER_ADDRESS"));
				userVO.setUserCertificationStatus(rs.getByte("USER_CERTIFICATION_STATUS"));
				userVO.setUserStatus(rs.getByte("USER_STATUS"));
				userVO.setUserGender(rs.getString("USER_GENDER"));
				userVO.setUserRegistrationDate(rs.getTimestamp("USER_REGISTRATION_DATE"));
				userVO.setUserForumLevel(rs.getByte("USER_FORUM_LEVEL"));
//				InputStream in = rs.getBinaryStream(15);
//				try {
//					Files.copy(in, Path.of("Pictures/imageTest.jpg"), StandardCopyOption.REPLACE_EXISTING);
//				in.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}

				userVO.setUserPic(rs.getBytes("USER_PIC"));
				userVO.setUserShopLevel(rs.getByte("USER_SHOP_LEVEL"));
				userVO.setBonusPoints(rs.getInt("BONUS_POINTS"));
				userVO.setAlltogetherScore(rs.getFloat("ALLTOGETHER_SCORE"));
				userVO.setAlltogetherScorePeople(rs.getInt("ALLTOGETHER_SCORE_PEOPLE"));
				userVO.setArtCount(rs.getInt("ART_COUNT"));
				userVO.setLikeTotalCount(rs.getInt("LIKE_TOTAL_COUNT"));
				userVO.setPurchaseTotal(rs.getInt("PURCHASE_TOTAL"));
				userVO.setReportTotalCount(rs.getByte("REPORT_TOTAL_COUNT"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return userVO;
	}
	@Override
	public UsersVO findByUserName(String USER_NAME) {
		UsersVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BY_USER_NAME);

			pstmt.setString(1, USER_NAME);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				userVO = new UsersVO();
				userVO.setUserId(rs.getInt("USER_ID"));
				userVO.setUserAccount(rs.getString("USER_ACCOUNT"));
				userVO.setUserPassword(rs.getString("USER_PASSWORD"));
				userVO.setUserName(rs.getString("USER_NAME"));
				userVO.setUserNickname(rs.getString("USER_NICKNAME"));
				userVO.setUserIdNo(rs.getString("USER_ID_NO"));
				userVO.setUserBrthday(rs.getDate("USER_BIRTHDAY"));
				userVO.setUserPhone(rs.getString("USER_PHONE"));
				userVO.setUserAddress(rs.getString("USER_ADDRESS"));
				userVO.setUserCertificationStatus(rs.getByte("USER_CERTIFICATION_STATUS"));
				userVO.setUserStatus(rs.getByte("USER_STATUS"));
				userVO.setUserGender(rs.getString("USER_GENDER"));
				userVO.setUserRegistrationDate(rs.getTimestamp("USER_REGISTRATION_DATE"));
				userVO.setUserForumLevel(rs.getByte("USER_FORUM_LEVEL"));
//				InputStream in = rs.getBinaryStream(15);
//				try {
//					Files.copy(in, Path.of("Pictures/imageTest.jpg"), StandardCopyOption.REPLACE_EXISTING);
//				in.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}

				userVO.setUserPic(rs.getBytes("USER_PIC"));
				userVO.setUserShopLevel(rs.getByte("USER_SHOP_LEVEL"));
				userVO.setBonusPoints(rs.getInt("BONUS_POINTS"));
				userVO.setAlltogetherScore(rs.getFloat("ALLTOGETHER_SCORE"));
				userVO.setAlltogetherScorePeople(rs.getInt("ALLTOGETHER_SCORE_PEOPLE"));
				userVO.setArtCount(rs.getInt("ART_COUNT"));
				userVO.setLikeTotalCount(rs.getInt("LIKE_TOTAL_COUNT"));
				userVO.setPurchaseTotal(rs.getInt("PURCHASE_TOTAL"));
				userVO.setReportTotalCount(rs.getByte("REPORT_TOTAL_COUNT"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return userVO;
	}
	@Override
	public List<UsersVO> getAllByReportTotalCount(Integer REPORT_TOTAL_COUNT) {
		List<UsersVO> list = new ArrayList<UsersVO>();
		UsersVO userVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_REPORT_TOTAL_COUNT);
			pstmt.setInt(1, REPORT_TOTAL_COUNT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				userVO = new UsersVO();
				userVO.setUserId(rs.getInt("USER_ID"));
				userVO.setUserAccount(rs.getString("USER_ACCOUNT"));
				userVO.setUserPassword(rs.getString("USER_PASSWORD"));
				userVO.setUserName(rs.getString("USER_NAME"));
				userVO.setUserNickname(rs.getString("USER_NICKNAME"));
				userVO.setUserIdNo(rs.getString("USER_ID_NO"));
				userVO.setUserBrthday(rs.getDate("USER_BIRTHDAY"));
				userVO.setUserPhone(rs.getString("USER_PHONE"));
				userVO.setUserAddress(rs.getString("USER_ADDRESS"));
				userVO.setUserCertificationStatus(rs.getByte("USER_CERTIFICATION_STATUS"));
				userVO.setUserStatus(rs.getByte("USER_STATUS"));
				userVO.setUserGender(rs.getString("USER_GENDER"));
				userVO.setUserRegistrationDate(rs.getTimestamp("USER_REGISTRATION_DATE"));
				userVO.setUserForumLevel(rs.getByte("USER_FORUM_LEVEL"));
				userVO.setUserPic(rs.getBytes("USER_PIC"));
				userVO.setUserShopLevel(rs.getByte("USER_SHOP_LEVEL"));
				userVO.setBonusPoints(rs.getInt("BONUS_POINTS"));
				userVO.setAlltogetherScore(rs.getFloat("ALLTOGETHER_SCORE"));
				userVO.setAlltogetherScorePeople(rs.getInt("ALLTOGETHER_SCORE_PEOPLE"));
				userVO.setArtCount(rs.getInt("ART_COUNT"));
				userVO.setLikeTotalCount(rs.getInt("LIKE_TOTAL_COUNT"));
				userVO.setPurchaseTotal(rs.getInt("PURCHASE_TOTAL"));
				userVO.setReportTotalCount(rs.getByte("REPORT_TOTAL_COUNT"));

				list.add(userVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	@Override
	public Integer findStatusByUserAccount(String USER_ACCOUNT) {
		Integer list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_COUNT_BY_USER_ACCOUNT);

			pstmt.setString(1, USER_ACCOUNT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				list=(int) rs.getByte("USER_STATUS");
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	public Integer findIdByUserAccount(String USER_ACCOUNT) {
		Integer list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_ID_BY_USER_ACCOUNT);

			pstmt.setString(1, USER_ACCOUNT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				list=(int) rs.getInt("USER_ID");
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	public static void main(String[] args) {

		UsersJDBCDAO dao = new UsersJDBCDAO();
		java.util.Date utilDate = new java.util.Date();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
//		try (InputStream in = Files.newInputStream(Path.of("images/1.png"))){
//			byte[] buf = new byte[in.available()]; 
//			in.read(buf);	
//		UsersVO userVO1 = new UsersVO();
//		userVO1.setUserAccount("111111");
//		userVO1.setUserPassword("222222");
//		userVO1.setUserName("john");
//		userVO1.setUserNickname("www");
//		userVO1.setUserIdNo("a223456789");
//		userVO1.setUserBrthday(java.sql.Date.valueOf("1991-09-22"));
//		userVO1.setUserPhone("0912222222");
//		userVO1.setUserAddress("桃園縣中壢市");
//		userVO1.setUserCertificationStatus((byte)0);
//		userVO1.setUserStatus((byte)0);
//		userVO1.setUserGender("女性");
//		userVO1.setUserRegistrationDate(sqlTimestamp);
//		userVO1.setUserForumLevel((byte)0);
//		userVO1.setUserPic(buf);
//		userVO1.setUserShopLevel((byte)0);
//		userVO1.setBonusPoints(0);
//		userVO1.setAlltogetherScore((float)0);
//		userVO1.setAlltogetherScorePeople(0);
//		userVO1.setArtCount(0);
//		userVO1.setLikeTotalCount(0);
//		userVO1.setPurchaseTotal(0);
//		userVO1.setReportTotalCount((byte)0);
//		dao.insert(userVO1);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		try (InputStream in = Files.newInputStream(Path.of("images/5.png"))){
//			byte[] buf = new byte[in.available()]; 
//			in.read(buf);
//		UsersVO userVO2 = new UsersVO();
//		userVO2.setUserId(40);
//		userVO2.setUserAccount("root");
//		userVO2.setUserPassword("123456");
//		userVO2.setUserName("peter");
//		userVO2.setUserNickname("qqq");
//		userVO2.setUserIdNo("a123456789");
//		userVO2.setUserBrthday(java.sql.Date.valueOf("1981-09-22"));
//		userVO2.setUserPhone("0912111111");
//		userVO2.setUserAddress("台北市南港區");
//		userVO2.setUserCertificationStatus((byte)0);
//		userVO2.setUserStatus((byte)0);
//		userVO2.setUserGender("男性");
//		userVO2.setUserRegistrationDate(sqlTimestamp);//current timestamp
//		userVO2.setUserForumLevel((byte)0);
//		userVO2.setUserPic(buf);
//		userVO2.setUserShopLevel((byte)0);
//		userVO2.setBonusPoints(0);
//		userVO2.setAlltogetherScore((float)0);
//		userVO2.setAlltogetherScorePeople(0);
//		userVO2.setArtCount(0);
//		userVO2.setLikeTotalCount(0);
//		userVO2.setPurchaseTotal(0);
//		userVO2.setReportTotalCount((byte)0);
//		dao.update(userVO2);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

//		UsersVO userVO2 = new UsersVO();
//		userVO2.setUserAccount("q111111111p@yahoo.com.tw");
//		dao.updateUserStatusByUserAccount(userVO2);
//		UsersVO userVO2 = new UsersVO();
//		userVO2.setUserAccount("q111111111p@yahoo.com.tw");
//		dao.restorationUserStatusByUserAccount(userVO2);	 
		
		
//		try (InputStream in = Files.newInputStream(Path.of("images/5.png"))){
//			byte[] buf = new byte[in.available()]; 
//			in.read(buf);
//		UsersVO userVO5 = new UsersVO();
//		userVO5.setUserNickname("qqq");
//		userVO5.setUserPhone("0912111111");
//		userVO5.setUserPic(buf);
//		userVO5.setUserAccount("qp@yahoo.com.tw");
//		dao.updateinfo(userVO5);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
			
			
			
		// �R��
//		dao.delete(41);

		// �d��
//		UsersVO userVO3 = dao.findByPrimaryKey(3);
//		System.out.println(userVO3.getUserId() + ",");
//		System.out.println(userVO3.getUserAccount() + ",");
//		System.out.println(userVO3.getUserPassword() + ",");
//		System.out.println(userVO3.getUserName() + ",");
//		System.out.println(userVO3.getUserNickname() + ",");
//		System.out.println(userVO3.getUserIdNo() + ",");
//		System.out.println(userVO3.getUserBrthday());
//		System.out.println(userVO3.getUserPhone());
//		System.out.println(userVO3.getUserAddress());
//		System.out.println(userVO3.getUserCertificationStatus());
//		System.out.println(userVO3.getUserStatus() + ",");
//		System.out.println(userVO3.getUserGender());
//		System.out.println(userVO3.getUserRegistrationDate());
//		System.out.println(userVO3.getUserForumLevel());
//		System.out.println(userVO3.getUserPic());
//		System.out.println(userVO3.getBonusPoints());
//		System.out.println(userVO3.getAlltogetherScore());
//		System.out.println(userVO3.getAlltogetherScorePeople());
//		System.out.println(userVO3.getArtCount());
//		System.out.println(userVO3.getLikeTotalCount());
//		System.out.println(userVO3.getPurchaseTotal());
//		System.out.println(userVO3.getReportTotalCount());
//
//		System.out.println("---------------------");

//		List<UsersVO> list = dao.getAll();
//		for (UsersVO User : list) {
//			System.out.print(User.getUserId() + ",");
//			System.out.print(User.getUserAccount() + ",");
//			System.out.print(User.getUserPassword() + ",");
//			System.out.print(User.getUserName() + ",");
//			System.out.print(User.getUserNickname() + ",");
//			System.out.print(User.getUserIdNo() + ",");
//			System.out.print(User.getUserBrthday() + ",");
//			System.out.print(User.getUserPhone() + ",");
//			System.out.print(User.getUserAddress() + ",");
//			System.out.print(User.getUserCertificationStatus() + ",");
//			System.out.print(User.getUserStatus() + ",");
//			System.out.print(User.getUserGender() + ",");
//			System.out.print(User.getUserRegistrationDate() + ",");
//			System.out.print(User.getUserForumLevel() + ",");
//			System.out.print(User.getUserPic());
//			System.out.print(User.getUserShopLevel() + ",");
//			System.out.print(User.getBonusPoints() + ",");
//			System.out.print(User.getAlltogetherScore() + ",");
//			System.out.print(User.getAlltogetherScorePeople() + ",");
//			System.out.print(User.getArtCount() + ",");
//			System.out.print(User.getLikeTotalCount() + ",");
//			System.out.print(User.getPurchaseTotal() + ",");
//			System.out.print(User.getReportTotalCount() + ",");
//			System.out.println();
//		}
//	System.out.println(dao.login("localhost@pchome.com.tw", "admin"));
//		UsersVO usersVO4= dao.findByUserAccount("q111111111p@yahoo.com.tw");
//		System.out.println(usersVO4.getUserId() + ",");
//		System.out.println(usersVO4.getUserAccount() + ",");
//		System.out.println(usersVO4.getUserPassword() + ",");
//		System.out.println(usersVO4.getUserName() + ",");
//		System.out.println(usersVO4.getUserNickname() + ",");
//		System.out.println(usersVO4.getUserIdNo() + ",");
//		System.out.println(usersVO4.getUserBrthday());
//		System.out.println(usersVO4.getUserPhone());
//		System.out.println(usersVO4.getUserAddress());
//		System.out.println(usersVO4.getUserCertificationStatus());
//		System.out.println(usersVO4.getUserStatus() + ",");
//		System.out.println(usersVO4.getUserGender());
//		System.out.println(usersVO4.getUserRegistrationDate());
//		System.out.println(usersVO4.getUserForumLevel());
//		System.out.println(usersVO4.getUserPic());
//		System.out.println(usersVO4.getBonusPoints());
//		System.out.println(usersVO4.getAlltogetherScore());
//		System.out.println(usersVO4.getAlltogetherScorePeople());
//		System.out.println(usersVO4.getArtCount());
//		System.out.println(usersVO4.getLikeTotalCount());
//		System.out.println(usersVO4.getPurchaseTotal());
//		System.out.println(usersVO4.getReportTotalCount());
//
//		System.out.println("---------------------");
		
//		UsersVO usersVO4= dao.findByUserName("王大明");
//		System.out.println(usersVO4.getUserId() + ",");
//		System.out.println(usersVO4.getUserAccount() + ",");
//		System.out.println(usersVO4.getUserPassword() + ",");
//		System.out.println(usersVO4.getUserName() + ",");
//		System.out.println(usersVO4.getUserNickname() + ",");
//		System.out.println(usersVO4.getUserIdNo() + ",");
//		System.out.println(usersVO4.getUserBrthday());
//		System.out.println(usersVO4.getUserPhone());
//		System.out.println(usersVO4.getUserAddress());
//		System.out.println(usersVO4.getUserCertificationStatus());
//		System.out.println(usersVO4.getUserStatus() + ",");
//		System.out.println(usersVO4.getUserGender());
//		System.out.println(usersVO4.getUserRegistrationDate());
//		System.out.println(usersVO4.getUserForumLevel());
//		System.out.println(usersVO4.getUserPic());
//		System.out.println(usersVO4.getBonusPoints());
//		System.out.println(usersVO4.getAlltogetherScore());
//		System.out.println(usersVO4.getAlltogetherScorePeople());
//		System.out.println(usersVO4.getArtCount());
//		System.out.println(usersVO4.getLikeTotalCount());
//		System.out.println(usersVO4.getPurchaseTotal());
//		System.out.println(usersVO4.getReportTotalCount());
//
//		System.out.println("---------------------");
//		List<UsersVO> list = dao.getAllByReportTotalCountT(9);
//		for (UsersVO User : list) {
//			System.out.print(User.getUserId() + ",");
//			System.out.print(User.getUserAccount() + ",");
//			System.out.print(User.getUserPassword() + ",");
//			System.out.print(User.getUserName() + ",");
//			System.out.print(User.getUserNickname() + ",");
//			System.out.print(User.getUserIdNo() + ",");
//			System.out.print(User.getUserBrthday() + ",");
//			System.out.print(User.getUserPhone() + ",");
//			System.out.print(User.getUserAddress() + ",");
//			System.out.print(User.getUserCertificationStatus() + ",");
//			System.out.print(User.getUserStatus() + ",");
//			System.out.print(User.getUserGender() + ",");
//			System.out.print(User.getUserRegistrationDate() + ",");
//			System.out.print(User.getUserForumLevel() + ",");
//			System.out.print(User.getUserPic());
//			System.out.print(User.getUserShopLevel() + ",");
//			System.out.print(User.getBonusPoints() + ",");
//			System.out.print(User.getAlltogetherScore() + ",");
//			System.out.print(User.getAlltogetherScorePeople() + ",");
//			System.out.print(User.getArtCount() + ",");
//			System.out.print(User.getLikeTotalCount() + ",");
//			System.out.print(User.getPurchaseTotal() + ",");
//			System.out.print(User.getReportTotalCount() + ",");
//			System.out.println();
//		}
//		System.out.println(dao.findStatusByUserAccount("q111111111p@yahoo.com.tw"));
//		UsersVO userVO6 = new UsersVO();
//		userVO6.setUserPassword("654321");
//		userVO6.setUserPasswordW("123456");
//		userVO6.setUserAccount("q111111111p@yahoo.com.tw");
//		dao.updatePassword(userVO6);
//		System.out.println(dao.findIdByUserAccount("q2@yahoo.com.tw"));
		
//		UsersVO userVO7 = new UsersVO();
//		userVO7.setUserPassword("654321");
//		userVO7.setUserAccount("aqwaqwaqw.roc@gmail.com");
//		dao.resetPassword(userVO7);
	}
}
