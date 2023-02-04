package com.emp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.plaf.synth.Region;

import org.apache.catalina.connector.Response;

import com.ColIti.model.ColItiService;
import com.Mes.model.MesService;
import com.Mes.model.MesVO;
import com.Users.model.UsersService;
import com.Users.model.UsersVO;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;

@MultipartConfig
@WebServlet("/empServlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
//====================================================================================
		
		if ("backEndlogin".equals(action)) {
			String empEmail = req.getParameter("empEmail");// 來自backpage.jsp的請求
			String empPw = req.getParameter("empPw");
			EmpService empSvc = new EmpService();
			if (empSvc.login(empEmail, empPw) == true) {
				EmpService empSvc1 = new EmpService();
				EmpVO empVO = empSvc1.getOneEmpEmail(empEmail);
				req.getSession().setAttribute("empVO", empVO);
				RequestDispatcher success = req.getRequestDispatcher("/back-end/emp/backpage.jsp");
				success.forward(req, res);
			} else {
				req.getRequestDispatcher("/front-end/member/loginFail.html").forward(req, res);
			}
		}
//====================================================================================
		if ("loginOut".equals(action)) {// 來自backpage.jsp的請求
			req.getSession().invalidate(); 
			res.sendRedirect(req.getContextPath()+"/back-end/backEndLogin.jsp");
		}
//====================================================================================
		if ("usercontrol".equals(action)) {// 來自backpage.jsp的請求
			RequestDispatcher success = req.getRequestDispatcher("/back-end/emp/systemSelect.jsp");
			success.forward(req, res);
		}
//====================================================================================
		if ("userMes".equals(action)) {// 來自backpage.jsp的請求
			RequestDispatcher success = req.getRequestDispatcher("/back-end/emp/userMes.jsp");
			success.forward(req, res);
		}
//====================================================================================
		if ("userStatus".equals(action)) {// 來自backpage.jsp的請求
			RequestDispatcher success = req.getRequestDispatcher("/back-end/emp/userStatus.jsp");
			success.forward(req, res);
		}
//====================================================================================
		
		if ("getOne_For_userMail".equals(action)) { // 來自userMes.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	/***************************1.接收請求參數 - 輸入格式的錯誤處裡**********************/
			String userMail = req.getParameter("userMail");
				if (userMail == null || (userMail.trim()).length() == 0) {
					errorMsgs.add("請輸入使用者Email");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/userMes.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料*****************************************/
				UsersService usersSvc = new UsersService();
				UsersVO usersVO = usersSvc.getOneUserEmail(userMail);
				if (usersVO == null) {
					errorMsgs.add("沒有找到這筆資料，請重新輸入");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/userMes.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成，準備轉交(Send the Success view)*************/
				req.getSession().setAttribute("usersVO", usersVO); // 資料庫取出的UsersVO物件,存入req
				String url = "/back-end/emp/sendUserMes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交sendUserMes.jsp
				successView.forward(req, res);
		}
//====================================================================================
				if ("getOne_For_userName".equals(action)) { // 來自userMes.jsp的請求
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
				/***************************1.接收請求參數 - 輸入格式的錯誤處裡**********************/
				String userName = req.getParameter("userName");
				if (userName == null || (userName.trim()).length() == 0) {
					errorMsgs.add("請輸入使用者姓名");
				}
						// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/userMes.jsp");
					failureView.forward(req, res);
					return;
				}
						
				/***************************2.開始新增資料*****************************************/
				UsersService usersSvc = new UsersService();
				UsersVO usersVO = usersSvc.getOneUserName(userName);
				if (usersVO == null) {
					errorMsgs.add("沒有找到這筆資料，請重新輸入");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/userMes.jsp");
							failureView.forward(req, res);
							return;
						}
						
				/***************************3.查詢完成，準備轉交(Send the Success view)*************/
				req.getSession().setAttribute("usersVO", usersVO); // 資料庫取出的UsersVO物件,存入req
				String url = "/back-end/emp/sendUserMes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交sendUserMes.jsp
				successView.forward(req, res);
				return;
		}
//====================================================================================
				
				if ("mesSend".equals(action)) { // 來自sendUserMes.jsp的請求
					String sendContent = req.getParameter("sendContent");
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處裡**********************/
						if (sendContent == null || (sendContent.trim()).length() == 0) {
							errorMsgs.add("請輸入留言內容");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/emp/sendUserMes.jsp");
							failureView.forward(req, res);
							return;
						}
						

						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/emp/sendUserMes.jsp");
							failureView.forward(req, res);
							return;
						}

						String str = req.getParameter("userId");
						if (str == null || (str.trim()).length() == 0) {
							errorMsgs.add("請輸入使用者ID");
						}
						Integer userId = null;
						try {
							userId = Integer.valueOf(str);
						} catch (Exception e) {
							errorMsgs.add("請輸入正確格式");
						}
				/***************************2.開始新增資料*****************************************/
						MesService mesSvc = new MesService();
						String Time= req.getParameter("sendTime");
						String sendTitle = req.getParameter("sendTitle");
						java.sql.Timestamp sendTime = java.sql.Timestamp.valueOf(Time);
						String str1=req.getParameter("readMesseng");
						Integer int1= Integer.valueOf(str1);
						byte readMesseng = int1.byteValue();
						Part part = req.getPart("sendPic");
						InputStream in = part.getInputStream();
							byte[] sendPic = new byte[in.available()];
							in.read(sendPic);
							in.close();
							try {
								if (part == null||part.getSize() == 0) {
							errorMsgs.add("請選擇上傳照片");
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/emp/sendUserMes.jsp");
							failureView.forward(req, res);
							return;
								
							} 
							}catch (Exception e) {
							e.printStackTrace();
							}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/emp/sendUserMes.jsp");
							failureView.forward(req, res);
							return;
						}
						
				/***************************3.查詢完成，準備轉交(Send the Success view)*************/
						mesSvc.addMesVO(userId, sendTitle, sendContent, sendPic, sendTime, readMesseng);
						errorMsgs.add("上傳成功");
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/emp/sendUserMes.jsp");
						failureView.forward(req, res);
						return;
				}
				//====================================================================================
				
				if ("getAll_Total_Count_userstatus".equals(action)) { // 來自userMes.jsp的請求
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
			/***************************1.接收請求參數 - 輸入格式的錯誤處裡**********************/
					String userStatus = req.getParameter("userStatus");
						if (userStatus == null || (userStatus.trim()).length() == 0) {
							errorMsgs.add("請輸入檢舉次數");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/emp/userStatus.jsp");
							failureView.forward(req, res);
							return;
						}
						Integer reportTotalCount = null;
						try {
							reportTotalCount = Integer.valueOf(userStatus);
						} catch (Exception e) {
							errorMsgs.add("請輸入正確格式");
						}
						/***************************2.開始新增資料*****************************************/
						UsersService usersSvc = new UsersService();
						List<UsersVO> usersVO = usersSvc.getAllByReportTotalCount(reportTotalCount);
						if (usersVO == null) {
							errorMsgs.add("沒有找到這筆資料，請重新輸入");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/emp/userStatus.jsp");
							failureView.forward(req, res);
							return;
						}
						
						/***************************3.查詢完成，準備轉交(Send the Success view)*************/
						req.getSession().setAttribute("reportTotalCount", reportTotalCount); // 資料庫取出的reportTotalCount,存入req
						String url = "/back-end/emp/sendUserReportTotalCount.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交sendUserReportTotalCount.jsp
						successView.forward(req, res);
				}
				//====================================================================================
				
				if ("getOne_For_userMail_userstatus".equals(action)) { // 來自userStatus.jsp的請求
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
			/***************************1.接收請求參數 - 輸入格式的錯誤處裡**********************/
					String userMail = req.getParameter("userMail");
						if (userMail == null || (userMail.trim()).length() == 0) {
							errorMsgs.add("請輸入使用者Email");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/emp/userStatus.jsp");
							failureView.forward(req, res);
							return;
						}
						
						/***************************2.開始新增資料*****************************************/
						UsersService usersSvc = new UsersService();
						UsersVO usersVO = usersSvc.getOneUserEmail(userMail);
						if (usersVO == null) {
							errorMsgs.add("沒有找到這筆資料，請重新輸入");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/emp/userStatus.jsp");
							failureView.forward(req, res);
							return;
						}
						
						/***************************3.查詢完成，準備轉交(Send the Success view)*************/
						req.getSession().setAttribute("usersVO", usersVO); // 資料庫取出的UsersVO物件,存入req
						String url = "/back-end/emp/sendUserReportTotalCountOne.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交sendUserReportTotalCountOne.jsp
						successView.forward(req, res);
				}
				//====================================================================================
				if ("getOne_For_userName_userstatus".equals(action)) { // 來自userStatus.jsp的請求
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
				/***************************1.接收請求參數 - 輸入格式的錯誤處裡**********************/
				String userName = req.getParameter("userName");
				if (userName == null || (userName.trim()).length() == 0) {
					errorMsgs.add("請輸入使用者姓名");
				}
						// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/userStatus.jsp");
					failureView.forward(req, res);
					return;
				}
						
				/***************************2.開始新增資料*****************************************/
				UsersService usersSvc = new UsersService();
				UsersVO usersVO = usersSvc.getOneUserName(userName);
				if (usersVO == null) {
					errorMsgs.add("沒有找到這筆資料，請重新輸入");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/userStatus.jsp");
							failureView.forward(req, res);
							return;
						}
						
				/***************************3.查詢完成，準備轉交(Send the Success view)*************/
				req.getSession().setAttribute("usersVO", usersVO); // 資料庫取出的UsersVO物件,存入req
				String url = "/back-end/emp/sendUserReportTotalCountOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交sendUserReportTotalCountOne.jsp
				successView.forward(req, res);
				return;
		}
				if ("suspension".equals(action)) { // 來自sendUserReportTotalCountOne.jsp
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					/***************************1.接收請求參數***************************************/
					String userAccount = req.getParameter("userAccount");
					/***************************2.開始刪除資料***************************************/
					UsersService usersSvc = new UsersService();
					usersSvc.updateUserStatus(userAccount);
					/***************************3.更新完成,準備轉交(Send the Success view)***********/	
					errorMsgs.add("停權成功");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/sendUserReportTotalCountOne.jsp");
					failureView.forward(req, res);
					return;
				}
				if ("disSuspensionOneForone".equals(action)) { // 來自sendUserReportTotalCount.jsp
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					/***************************1.接收請求參數***************************************/
					String userAccount = req.getParameter("userAccount");
					/***************************2.開始刪除資料***************************************/
					UsersService usersSvc = new UsersService();
					usersSvc.restorationUserStatusByUserAccount(userAccount);
					/***************************3.更新完成,準備轉交(Send the Success view)***********/	
					errorMsgs.add("復權成功");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/sendUserReportTotalCountOne.jsp");
					failureView.forward(req, res);
					return;
				}
				if ("suspensionOne".equals(action)) { // 來自sendUserReportTotalCount.jsp
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					/***************************1.接收請求參數***************************************/
					String userAccount = req.getParameter("userAccount");
					/***************************2.開始刪除資料***************************************/
					UsersService usersSvc = new UsersService();
					usersSvc.updateUserStatus(userAccount);
					/***************************3.更新完成,準備轉交(Send the Success view)***********/	
					errorMsgs.add("停權成功");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/sendUserReportTotalCount.jsp");
					failureView.forward(req, res);
					return;
				}
				if ("suspensionAll".equals(action)) { // 來自sendUserReportTotalCount.jsp
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					/***************************1.接收請求參數***************************************/
					String []userAccountAll = req.getParameterValues("userAccountAll");
					/***************************2.開始刪除資料***************************************/
					UsersService usersSvc = new UsersService();
					if(userAccountAll!=null) {
						for(int i=0; i<userAccountAll.length;i++)
						{
					 	usersSvc.updateUserStatus(userAccountAll[i]);
						}
					}
					/***************************3.更新完成,準備轉交(Send the Success view)***********/	
					errorMsgs.add("停權成功");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/sendUserReportTotalCount.jsp");
					failureView.forward(req, res);
					return;
				}
				if ("disSuspensionOne".equals(action)) { // 來自sendUserReportTotalCount.jsp
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					/***************************1.接收請求參數***************************************/
					String userAccount = req.getParameter("userAccount");
					/***************************2.開始刪除資料***************************************/
					UsersService usersSvc = new UsersService();
					usersSvc.restorationUserStatusByUserAccount(userAccount);
					/***************************3.更新完成,準備轉交(Send the Success view)***********/	
					errorMsgs.add("復權成功");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/sendUserReportTotalCount.jsp");
					failureView.forward(req, res);
					return;
				}
	}
	
}
