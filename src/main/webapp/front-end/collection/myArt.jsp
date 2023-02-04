<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.Users.model.*"%>
<%@ page import="com.ColArt.model.*"%>
<%@ page import="java.util.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
UsersVO usersVO = (UsersVO) session.getAttribute("usersVO"); 
Integer user=usersVO.getUserId();
String userAcount=usersVO.getUserAccount();
ColArtService ColArtSvc = new ColArtService();
    List<ColArtVO> list = ColArtSvc.getAll(user);
    pageContext.setAttribute("list",list);
    byte UserShopLevel=usersVO.getUserShopLevel();
    String str="";
    if(UserShopLevel==1){
    	str="VIP";
    }
    if(UserShopLevel==0){
    	str="一般";
    }
%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/collection/css/header.css">
        <link rel="website icon" href="<%=request.getContextPath()%>/front-end/member/images/bgremove_Logo.jpg">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css"
          integrity="sha384-b6lVK+yci+bfDmaY1u0zE8YYJt0TZxLEAFyYSLHId4xoVvsrQu3INevFKo+Xir8e" crossorigin="anonymous">
          <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/collection/css/sidebar.css">
          <script src="https://kit.fontawesome.com/616f19a0b0.js" crossorigin="anonymous"></script>
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
        
        <title>7 Tour | 旅遊規劃</title>
      </head>
      
      <body>	<form id="index" name="index" method="post" ACTION="<%=request.getContextPath()%>/collectionServlet">
           <!-- nav start -->
        <nav class="navbar navbar-expand-lg navbar-light bg-cblue sticky-top">
          <div class="container-fluid">
            <a class="navbar-brand" href="<%=request.getContextPath()%>/front-end/index.jsp">
              <img src="<%=request.getContextPath()%>/front-end/member/images/bgremove_Logo.jpg" id="logo">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item hover-down">
                  <a class="nav-link fs-5 fw-bold" href="#">論壇</a>
                  <ul class="bg-cblue hover-list">
                    <li><a class="hover-item fs-5 fw-bold" href="#">論壇首頁</a></li>
                    <li><a class="hover-item fs-5 fw-bold" href="#">文章</a></li>
                  </ul>
                </li>
                <li class="nav-item hover-down">
                  <a class="nav-link fs-5 fw-bold" href="#">訂房</a>
                  <ul class="bg-cblue hover-list">
                    <li><a class="hover-item fs-5 fw-bold" href="#">訂房首頁</a></li>
                    <li><a class="hover-item fs-5 fw-bold" href="#">我的訂單</a></li>
                    <li><a class="hover-item fs-5 fw-bold" href="#">購物車</a></li>
                  </ul>
                </li>
                <li class="nav-item hover-down">
                  <a class="nav-link fs-5 fw-bold" href="#">揪團</a>
                  <ul class="bg-cblue hover-list">
                    <li><a class="hover-item fs-5 fw-bold" href="#">揪團首頁</a></li>
                    <li><a class="hover-item fs-5 fw-bold" href="#">建立揪團</a></li>
                  </ul>
                </li>
              </ul>
            <ul class="navbar-nav mb-2 mb-lg-0">
                <li class="nav-item bg-dblue rounded mx-3 ">
                    <a class="nav-link fs-5 text-white fw-bold" href="#">開始規劃</a>
                </li>
      
                <li class="nav-item user-msg">
                  <i class="bi bi-bell-fill fa-2x msg-icon"></i>
                  <ul class="bg-cblue msg-list">
                    <li class="msg-item">
                      <h5>title</h5>
                      <p class="text-truncate">訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容</p>
                    </li>
                    <hr class="m-1">
      
                    <li class="msg-item">
                      <h5>title</h5>
                      <p class="text-truncate">訊息內容</p>
                    </li>
                    <hr class="m-1">
                    
                    <li class="msg-item">
                      <form action="">
                        <h5>XXX邀請你，共同規畫行程</h5>
                        <button class="btn btn-primary rounded p-1">確認</button>
                        <button class="btn btn-danger rounded p-1">拒絕</button>
                      </form>
                    </li>
                    <hr class="m-1">
      
                    <li class="msg-item">
                      <h5>title</h5>
                      <p class="text-truncate">訊息內容</p>
                    </li>
                    <hr class="m-1">
                  </ul>
                </li>
      
                <li class="nav-item mx-3 user-info">
                  <img src="<%=request.getContextPath()%>/front-end/images/user.png" alt="會員照片" class="user-pic"id="userPic">
                  <ul class="bg-cblue info-list">
                    <li id="info"><a href=" #" class="d-block info-item">會員基本資料</a></li>
                    <li><a href="#" class="d-block info-item">我的行程</a></li>
                    <li><a href="#" class="d-block info-item">我的收藏</a></li>
                    <li><a href="#" class="d-block info-item">我的訂單</a></li>
                  <form  id="login_out" name="login_out" ACTION="<%=request.getContextPath()%>/collectionServlet"METHOD="post">
              		<input type="hidden" name="action" value="loginout">
              		<li id="loginout"  name="loginout"><a href="#" class="d-block info-item">登出</a></li>
              </form>
              </ul>
                </li>
               </ul>
            </div>
          </div>
        </nav>
        
       </form>   

        <body>
 
          <div id="wrapper" style="left:0;">

             <div class="sidebar">
                <div class="headSculpture">
                   <img src="<%=request.getContextPath()%>/front-end/member/getUserPic.jsp?UserAccount='<%=usersVO.getUserAccount()%>'" id="userPic">
                   <p>CephasWu</p>
                </div>
                <p id="vip">論壇等級：達人</p>
                <div class="option">
                   <ul>
                      <li><i class="bi bi-award"></i>
                         <p>購買等級：</p><p id="point"><%=str%></p>
                      </li>
                      <li><i class="bi bi-gem"></i>
                         <p>紅利積點</p><p id="point"><%=usersVO.getBonusPoints()%></p>
                      </li>
                      <li><i class="bi bi-people-fill"></i>
                         <p>揪團評分</p><p id="point"><%=usersVO.getAlltogetherScore()%></p>
                      </li>
                      <li><i class="bi bi-bag"></i>
                        <p>購買累計金額</p><p id="point"><%=usersVO.getPurchaseTotal()%></p>
                     </li>
                     <li><i class="bi bi-book"></i>
                      <p>我的文章</p>
                   </li>
                      <li><i class="bi bi-calendar2"></i>
                         <p><a href="<%=request.getContextPath()%>/front-end/collection/myTrip.jsp">我的行程</a></p>
                      </li>
                      <li><i class="bi bi-file-text"></i>
                         <p><a href="<%=request.getContextPath()%>/front-end/collection/collection.jsp">店家收藏</a></p>
                      </li>
                   </ul>
                </div>
             </div>
             <!-- 侧边栏按钮 -->
             <button></button>
             <!-- 内容区域 -->
            <div id="banner">
               <h2>我的文章</h2>
             </div>
          </div>
          <script src="<%=request.getContextPath()%>/front-end/member/js/jquery.min.js"></script>
          <script src="<%=request.getContextPath()%>/front-end/member/js/sidebar.js"></script>
       </body>
<!--================================================================================================================== 表格部分-->
<table>
<div id="position">
	<p><%@ include file="page1.file"%> </p>
	
<c:forEach var="ColArtVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		      
<div id="article"> <img src="<%=request.getContextPath()%>/front-end/collection/getArtPic.jsp?artId='${ColArtVO.artId}'"  alt="" id="foodPic">
  <div id="font"> <h5 style="color:#00B2B2">發文會員：${ColArtVO.ususerAccount}</h5>
  <div>文章標題：${ColArtVO.artTitle}</div>
  <div>文章發表時間：<fmt:formatDate value="${ColArtVO.artTime}" pattern="yyyy-MM-dd HH:mm:ss "/></div>
    <div id="artContent">
    ${ColArtVO.artContent}
  </div>
    <div style="color:#FF2626">評分：${ColArtVO.artShowCount}</div><div style="color:#FF2626">文章讚數：${ColArtVO.artLikesCount}</div>
  <div><FORM id="Delete" name="Delete" METHOD="post" ACTION="<%=request.getContextPath()%>/collectionServlet" style="margin-bottom: 0px;">
    <img src="<%=request.getContextPath()%>/front-end/collection/images/heart.png"  alt="" id="heartPic">
    <input type="hidden" name="userId"  value="${ColArtVO.userId}">
    <input type="hidden" name="artId"  value="${ColArtVO.artId}">
    <input type="hidden" name="action" value="deleteArt"></FORM></div>
</div>
	</c:forEach>
	</table>
</div>


<!--================================================================================================================== 表格底部-->
<!--================================================================================================================== 表格css-->

   <style>
   body {
      overflow: scroll;
    font-family: "微軟正黑體";
    
}
#banner{
position: absolute;
left:100px;
}
#foodPic{
position: absolute;

width:230px;
height:170px;

}
  #position{  
    position: absolute;
    top:100px;
    right:250px;
   
  }   
    #position>p{  
    width:150px;
    position: absolute;
    top:-20px;
    left:50px;
   
  }
#position #article{
  
float: middle;
  width:800px;
  height: 180px;
  background-color: white;
  border-radius: 10px;
 margin: 15px 0 0 0px;
 padding-top:5px;
}

#position #article #artContent{
border-radius:10px;
  background-color:#8CFFFF;
 overflow: hidden;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  white-space: normal;
}
#position #article #font{

top:-20px;
left:230px;
word-break: break-all;


width:550px;
  position: relative;
 margin: 10px 0 0 10px;
 padding: 10px 0 0 0;
 
}
#position #article #heartPic{
  position: absolute;
  top:10px;
  right:5px;
  width: 30px;
 margin: 0px 0 0 540px;
 }
  </style>
        <script>
        <!-- 控制delete 表單發送                                                                                   -->
        let heart = document.getElementById("heartPic").addEventListener("click", function () {
        	document.getElementById("Delete").submit();
        	});
        <!-- 控制登出發送                                                                                   -->
   		loginout.addEventListener("click", function () {
        	document.login_out.submit();
        });
   		<!-- 控制userInfo 發送                                                                                   -->
        let info = document.getElementById("info").addEventListener("click", function () {
        	window.open("<%=request.getContextPath()%>/front-end/member/userInfo.jsp ", "userInfo", config = "height=540,width=445, top = 100, left = 700"); 
        });
        <%  
        UsersVO usersVO1 = (UsersVO)session.getAttribute("usersVO");
        if(usersVO1!=null){
        	out.println("let userPic = document.getElementById('userPic').src="+"'"+request.getContextPath()+"/front-end/member/getUserPic.jsp?UserAccount="+"\""+usersVO1.getUserAccount()+"\""+"'"+";");
        	};
        %>
        </script> 
         </html>