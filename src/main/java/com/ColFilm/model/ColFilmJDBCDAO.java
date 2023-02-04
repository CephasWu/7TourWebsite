package com.ColFilm.model;

import java.io.File;
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

public class ColFilmJDBCDAO implements ColFilmDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/test";
	String userid = "root";
	String passwd = "Cephas1981";

	private static final String INSERT_STMT = "INSERT INTO COL_FILM (FILM_ID,USER_ID) VALUES ( ?, ?)";
	private static final String GET_ALL_STMT = "SELECT FILM_ID,USER_ID FROM COL_FILM order by FILM_ID";
	private static final String GET_ONE_STMT = "SELECT FILM_ID,USER_ID FROM COL_FILM WHERE FILM_ID=? AND USER_ID=?";
	private static final String DELETE = "DELETE FROM COL_FILM where FILM_ID = ? AND USER_ID = ?";
	private static final String UPDATE = "UPDATE COL_FILM set FILM_ID = ?,USER_ID = ? where FILM_ID=? AND USER_ID=?";

	@Override
	public void insert(ColFilmVO colFilmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, colFilmVO.getFilmId());
			pstmt.setInt(2, colFilmVO.getUserId());
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
	public void update(ColFilmVO colFilmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, colFilmVO.getFilmId());
			pstmt.setInt(2, colFilmVO.getUserId());
			pstmt.setInt(3, colFilmVO.getFilmIdW());
			pstmt.setInt(4, colFilmVO.getUserIdW());
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
	public void delete(Integer FILM_ID,Integer USER_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, FILM_ID);
			pstmt.setInt(2, USER_ID);
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
	public ColFilmVO findByPrimaryKey(Integer FILM_ID,Integer USER_ID) {
		ColFilmVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, FILM_ID);
			pstmt.setInt(2, USER_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				couponVO = new ColFilmVO();
				couponVO.setFilmId(rs.getInt("FILM_ID"));
				couponVO.setUserId(rs.getInt("USER_ID"));
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
		return couponVO;
	}

	@Override
	public List<ColFilmVO> getAll() {
		List<ColFilmVO> list = new ArrayList<ColFilmVO>();
		ColFilmVO colFilmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				colFilmVO = new ColFilmVO();
				colFilmVO.setFilmId(rs.getInt("FILM_ID"));
				colFilmVO.setUserId(rs.getInt("USER_ID"));
				list.add(colFilmVO); // Store the row in the list
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

		ColFilmJDBCDAO dao = new ColFilmJDBCDAO();
		// 新增
//		ColFilmVO colFilmVO1 = new ColFilmVO();
//		colFilmVO1.setFilmId(1);
//		colFilmVO1.setUserId(3);
//		dao.insert(colFilmVO1);

//		//修改
//		ColFilmVO colFilmVO2 = new ColFilmVO();
//		colFilmVO2.setFilmId(2);
//		colFilmVO2.setUserId(4);
//		colFilmVO2.setFilmIdW(1);//設定where=?
//		colFilmVO2.setUserIdW(3);//設定where=?
//		dao.update(colFilmVO2);

//		// 刪除
//		dao.delete(2,4);

		// 查詢
//		ColFilmVO colFilmVO3 = dao.findByPrimaryKey(2,4);
//		System.out.print(colFilmVO3.getFilmId() + ",");
//		System.out.print(colFilmVO3.getUserId() + ",");
//		System.out.println("---------------------");

		// 查詢all
		List<ColFilmVO> list = dao.getAll();
		for (ColFilmVO colFilmVO : list) {
			System.out.print(colFilmVO.getFilmId() + ",");
			System.out.print(colFilmVO.getUserId() + ",");
		}
	}
}
