<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.Users.model.*"%>
<%@ page import="com.Mes.model.*"%>
<%@ page import="com.Collection.model.*"%>
<%@ page import="java.util.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% 
try{
UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
	if(usersVO!=null){
	Integer user=usersVO.getUserId();
	String userAcount=usersVO.getUserAccount();
	MesService MesSvc = new MesService();
	    List<MesVO> list = MesSvc.getAll(user);
	    pageContext.setAttribute("list",list);
	    byte UserShopLevel=usersVO.getUserShopLevel();
	    String str="";
	    if(UserShopLevel==1){
	    	str="VIP";
	    }
	    if(UserShopLevel==0){
	    	str="一般";
	    }
	    }}catch (NullPointerException e) {
			e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/fontpage.css">
  <link rel="website icon" href="<%=request.getContextPath()%>/front-end/images/bgremove_Logo.jpg">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css"
    integrity="sha384-b6lVK+yci+bfDmaY1u0zE8YYJt0TZxLEAFyYSLHId4xoVvsrQu3INevFKo+Xir8e" crossorigin="anonymous">
  <title>7 Tour | 旅遊規劃</title>
</head>
<body>

    <!-- nav start -->
  <nav class="navbar navbar-expand-lg navbar-light bg-cblue fixed-top">
    <div class="container-fluid">
      <a class="navbar-brand" href="<%=request.getContextPath()%>/front-end/index.html">
        <img src="<%=request.getContextPath()%>/front-end/images/bgremove_Logo.jpg" id="logo">
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item hover-down">
            <a class="nav-link fs-5" href="#">論壇</a>
            <ul class="bg-cblue hover-list">
              <li><a class="hover-item fs-6" href="#">論壇首頁</a></li>
              <li><a class="hover-item fs-6" href="#">文章</a></li>
            </ul>
          </li>
          <li class="nav-item hover-down">
            <a class="nav-link fs-5" href="#">訂房</a>
            <ul class="bg-cblue hover-list">
              <li><a class="hover-item fs-6" href="#">訂房首頁</a></li>
              <li><a class="hover-item fs-6" href="#">我的訂單</a></li>
              <li><a class="hover-item fs-6" href="#">購物車</a></li>
            </ul>
          </li>
          <li class="nav-item hover-down">
            <a class="nav-link fs-5" href="#">揪團</a>
            <ul class="bg-cblue hover-list">
              <li><a class="hover-item fs-6" href="#">揪團首頁</a></li>
              <li><a class="hover-item fs-6" href="#">建立揪團</a></li>
            </ul>
          </li>
        </ul>
        <a href="<%=request.getContextPath()%>/front-end/member/login.jsp"  id="login">登入/註冊</a>
        <ul class="navbar-nav mb-2 mb-lg-0">
          <li class="nav-item bg-dblue rounded mx-3 ">
              <a class="nav-link fs-5 text-white fw-bold" href="#">開始規劃</a>
          </li>

          <li class="nav-item user-msg">
            <div id="bi"><i  class="bi bi-bell-fill fa-2x msg-icon"></i></div>
            <ul class="bg-cblue msg-list">
            <table>
            <c:forEach var="MesVO" items="${list}" begin="<%=0%>" end="<%=10%>">
            
     			<FORM id="color" name="color" METHOD="post" ACTION="<%=request.getContextPath()%>/usersServlet" style="margin-bottom: 0px;">
              <li id="msg-item" class="msg-item">
              <img src="<%=request.getContextPath()%>/front-end/getMesPic.jsp?mesId='${MesVO.mesId}'" id="mesPic" style="width:30px;float:right">
            	<input type="hidden" name="userId"  value="${MesVO.userId}">
    			<input type="hidden" name="action" value="changeColor"></FORM>
             
                <h5>${MesVO.sendTitle}</h5>
                <p class="text-truncate" style="white-space: normal;width:200px">${MesVO.sendContent}</p>
               <p>發送時間：<fmt:formatDate value="${MesVO.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
               <FORM id="delete_mes" name="delete_mes" METHOD="post" ACTION="<%=request.getContextPath()%>/usersServlet" style="margin-bottom: 0px;">
              <div id="bell"><i class="bi-bell" style="font-size:24px;position:relative;z-index:1"></i></div>
                 <input type="hidden" name="mesId"  value="${MesVO.mesId}">
    			<input type="hidden" name="action" value="deleteMes"></FORM>
				</div>
               <li  class="bgcolor" ><div class="text" style="color:transparent;font-size:0px">${MesVO.readMesseng}</div></li>
               </li>
              <hr class="m-1">
              </c:forEach>
			</div>
			</table>
            </ul>
          </li>

          <li class="nav-item mx-3 user-info">
            <img src="<%=request.getContextPath()%>/front-end/images/user.png" alt="會員照片" class="user-pic" id="userPic">
            <ul class="bg-cblue info-list">

              <li id="info"><a href=" #" class="d-block info-item">會員基本資料</a></li>
              <form id="myTrip" METHOD="post" ACTION="<%=request.getContextPath()%>/usersServlet">
              <li id="trip"><a href="#" class="d-block info-item">我的行程</a></li>
              </form>
			 <form id="Collection" name="Collection" METHOD="post" ACTION="<%=request.getContextPath()%>/collectionServlet"><input type="hidden" name="action" value="collection">
			 <li id="collection" name="collection"><a href="#" class="d-block info-item">我的收藏</a></li>
			 </form>
              
              <li><a href="#" class="d-block info-item">我的訂單</a></li>
              <form id="login_out" name="login_out" ACTION="<%=request.getContextPath()%>/usersServlet">
              <input type="hidden" name="action" value="loginout">
              <li id="loginout" style="display:none" name="loginout"><a href="#" class="d-block info-item">登出</a></li>
              </form>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- nav end -->

  <!-- slide pic start -->
  <div class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="<%=request.getContextPath()%>/front-end/images/view1.jpeg" class="d-block w-100 slide-pic">
      </div>
      <div class="carousel-item">
        <img src="<%=request.getContextPath()%>/front-end/images/view2.jpeg" class="d-block w-100 slide-pic">
      </div>
      <div class="carousel-item">
        <img src="<%=request.getContextPath()%>/front-end/images/view3.jpeg" class="d-block w-100 slide-pic">
      </div>
    </div>
    <div class="container pic-searchbar">
      <div class="row justify-content-center">
        <div class="col-8">
          <form class="d-flex">
            <input class="form-control" type="search" placeholder="輸入你想去的地方吧!!~~" aria-label="Search">
            <button class="btn btn-primary" type="submit">
              <i class="bi bi-search"></i>
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
  <!-- slide pic end -->

  <!-- service start -->

  <!-- service image start -->

  <div class="btn chat-icon" data-bs-toggle="collapse" href="#service" role="button" aria-expanded="false"
    aria-controls="service">
    <i class="bi bi-robot fa-5x text-danger"></i>
  </div>

  <!-- service image end  -->

  <div class="container p-0 m-0 fixed-bottom collapse" id="service">
    <div class="row">
      <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4 ms-auto">
        <div class="card">
          <div class="card-header bg-cblue d-flex overflow-hidden">
            <h5 class="fw-bold m-0 p-0 d-inline flex-fill">客服聊天室</h5>
            <div class="bg-cblue p-0 text-end flex-fill">

              <i class="bi bi-x-lg"></i>

            </div>
          </div>
          <div class="coontainer p-3 overflow-auto" style="height: 300px;">
            <div class="row">
              <div class="col-2 p-2 pb-0">
                <img src="<%=request.getContextPath()%>/front-end/images/dog.jpeg" alt="會員照片" class="user-pic">
              </div>
              <div class="col-7 p-1">
                <p class="border border-dark rounded p-2">
                  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXsomething
                </p>
              </div>
              <div class="col-3 p-0 ct-text align-self-end">
                <p class="mb-4">下午 12:00</p>
              </div>
            </div>

            <div class="row">
              <div class="col-2 p-2 pb-0">
                <img src="<%=request.getContextPath()%>/front-end/images/dog.jpeg" alt="會員照片" class="user-pic">
              </div>
              <div class="col-7 p-1">
                <p class="border border-dark rounded p-2">
                  something
                </p>
              </div>
              <div class="col-3 p-0 ct-text align-self-end">
                <p class="mb-4">下午 12:00</p>
              </div>
            </div>

            <div class="row flex-row-reverse">
              <div class="col-7 p-1">
                <p class="border border-dark rounded p-2">
                  something
                </p>
              </div>
              <div class="col-3 p-0 ct-text align-self-end d-inline">
                <p class="mb-4 text-end">下午 12:00</p>
              </div>
            </div>

          </div>

          <div class="card-footer">
            <div class="d-flex">
              <div class="form-outline w-100">
                <input type="text" id="form1" class="form-control">
              </div>
              <button type="button" class="btn btn-primary">
                <i class="bi bi-send-fill"></i>
              </button>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>



  <!-- service end -->

  <!-- area start -->
  <article class="container-md">
    <div class="row justify-content-md-evenly text-center area-content">
      <a href="" class="col-2 fixed-w-h fade-area">
        <img src="<%=request.getContextPath()%>/front-end/images/north.jpeg" alt="北部" class="w-100 h-100 rounded">
        <div class="w-100 h-100 pic-text">北部</div>
      </a>
      <a href="" class="col-2 fixed-w-h fade-area">
        <img src="<%=request.getContextPath()%>/front-end/images/middle.jpg" alt="中部" class="w-100 h-100 rounded">
        <div class="w-100 h-100 pic-text">中部</div>
      </a>
      <a href="" class="col-2 fixed-w-h fade-area">
        <img src="<%=request.getContextPath()%>/front-end/images/south.jpg" alt="南部" class="w-100 h-100 rounded">
        <div class="w-100 h-100 pic-text">南部</div>
      </a>
      <a href="" class="col-2 fixed-w-h fade-area">
        <img src="<%=request.getContextPath()%>/front-end/images/east.jpg" alt="東部" class="w-100 h-100 rounded">
        <div class="w-100 h-100 pic-text">東部</div>
      </a>
    </div>
  </article>
  <!-- area end -->

  <!-- hotel card start -->
  <div class="container">
    <div class="row mt-4 pt-4">
      <div class="col">
        <h2 class="fw-bold">熱門住宿</h2>
      </div>
      <div class="col text-end">
        <a href="#" class="a-style">更多</a>
      </div>
    </div>

    <div class="row row-cols-1 row-cols-xs-2 row-cols-sm-2 row-cols-lg-4 g-3">
      <div class="col">
        <a href="#" class="card h-100 shadow-sm fade-area">
          <img src="<%=request.getContextPath()%>/front-end/images/hotel1.jpeg" class="card-img-top" alt="飯店圖片">
          <div class="card-body">
            <h5 class="card-title fw-bold text-truncate">XXX飯店</h5>
            <p class="card-text m-1">台北</p>
            <p class="card-text m-1">$ 10,000元起</p>
            <span class="card-text bg-success rounded p-1">評價</span>
            <span class="card-text">
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
            </span>
          </div>
        </a>
      </div>

      <div class="col">
        <a href="#" class="card h-100 shadow-sm fade-area">
          <img src="<%=request.getContextPath()%>/front-end/images/hotel2.jpeg" class="card-img-top" alt="飯店圖片">
          <div class="card-body">
            <h5 class="card-title fw-bold text-truncate">XXX飯店</h5>
            <p class="card-text m-1">台北</p>
            <p class="card-text m-1">$ 10,000元起</p>
            <span class="card-text bg-success rounded p-1">評價</span>
            <span class="card-text">
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
            </span>
          </div>
        </a>
      </div>

      <div class="col">
        <a href="#" class="card h-100 shadow-sm fade-area">
          <img src="<%=request.getContextPath()%>/front-end/images/hotel3.jpeg" class="card-img-top" alt="飯店圖片">
          <div class="card-body">
            <h5 class="card-title fw-bold text-truncate">XXX飯店</h5>
            <p class="card-text m-1">台北</p>
            <p class="card-text m-1">$ 10,000元起</p>
            <span class="card-text bg-success rounded p-1">評價</span>
            <span class="card-text">
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
            </span>
          </div>
        </a>
      </div>

      <div class="col">
        <a href="#" class="card h-100 shadow-sm fade-area">
          <img src="<%=request.getContextPath()%>/front-end/images/hotel4.jpeg" class="card-img-top" alt="飯店圖片">
          <div class="card-body">
            <h5 class="card-title fw-bold text-truncate">XXX飯店</h5>
            <p class="card-text m-1">台北</p>
            <p class="card-text m-1">$ 10,000元起</p>
            <span class="card-text bg-success rounded p-1">評價</span>
            <span class="card-text">
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
            </span>
          </div>
        </a>
      </div>


    </div>
  </div>
  <!-- hotel card end -->

  <!-- article start -->
  <div class="container">
    <div class="row mt-4 pt-4">
      <div class="col">
        <h2 class="fw-bold">旅遊回憶</h2>
      </div>
      <div class="col text-end">
        <a href="#" class="a-style">更多</a>
      </div>
    </div>

    <div class="row row-cols-1 row-cols-xs-2 row-cols-sm-2 row-cols-lg-4 g-3">
      <div class="col">
        <div class="card h-100 shadow-sm">
          <a href="#" class="fade-area">
            <img src="<%=request.getContextPath()%>/front-end/images/view1.jpeg" class="card-img-top" alt="飯店圖片">
          </a>
          <div class="card-body">
            <a href="#" class="a-style text-dark">
              <h5 class="card-title fw-bold text-truncate">
                文章名稱XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXSXXXXXXX文章</h5>
            </a>
            <div class="card-text m-1">
              <a href="" class="a-style text-dark">
                <img src="<%=request.getContextPath()%>/front-end/images/dog.jpeg" alt="會員照片" class="user-pic">
                <span class="fw-bold text-truncate">XXX會員名稱</span>
              </a>
            </div>
            <span class="card-text m-1">2023年1月1日</span>
            <span class="card-text m-1 border-start border-dark ps-2">
              <i class="bi bi-hand-thumbs-up-fill fa-2x like-icon"></i>
            </span>
            <span>223</span>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="card h-100 shadow-sm">
          <a href="#" class="fade-area">
            <img src="<%=request.getContextPath()%>/front-end/images/view2.jpeg" class="card-img-top" alt="飯店圖片">
          </a>
          <div class="card-body">
            <a href="#" class="a-style text-dark">
              <h5 class="card-title fw-bold text-truncate">
                文章名稱XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXSXXXXXXX文章</h5>
            </a>
            <div class="card-text m-1">
              <a href="" class="a-style text-dark">
                <img src="<%=request.getContextPath()%>/front-end/images/dog.jpeg" alt="會員照片" class="user-pic">
                <span class="fw-bold text-truncate">XXX會員名稱</span>
              </a>
            </div>
            <span class="card-text m-1">2023年1月1日</span>
            <span class="card-text m-1 border-start border-dark ps-2">
              <i class="bi bi-hand-thumbs-up-fill fa-2x like-icon"></i>
            </span>
            <span>223</span>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="card h-100 shadow-sm">
          <a href="#" class="fade-area">
            <img src="<%=request.getContextPath()%>/front-end/images/view3.jpeg" class="card-img-top" alt="飯店圖片">
          </a>
          <div class="card-body">
            <a href="#" class="a-style text-dark">
              <h5 class="card-title fw-bold text-truncate">
                文章名稱XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXSXXXXXXX文章</h5>
            </a>
            <div class="card-text m-1">
              <a href="" class="a-style text-dark">
                <img src="<%=request.getContextPath()%>/front-end/images/dog.jpeg" alt="會員照片" class="user-pic">
                <span class="fw-bold text-truncate">XXX會員名稱</span>
              </a>
            </div>
            <span class="card-text m-1">2023年1月1日</span>
            <span class="card-text m-1 border-start border-dark ps-2">
              <i class="bi bi-hand-thumbs-up-fill fa-2x like-icon"></i>
            </span>
            <span>223</span>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="card h-100 shadow-sm">
          <a href="#" class="fade-area">
            <img src="<%=request.getContextPath()%>/front-end/images/view3.jpeg" class="card-img-top" alt="飯店圖片">
          </a>
          <div class="card-body">
            <a href="#" class="a-style text-dark">
              <h5 class="card-title fw-bold text-truncate">
                文章名稱XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXSXXXXXXX文章</h5>
            </a>
            <div class="card-text m-1">
              <a href="" class="a-style text-dark">
                <img src="<%=request.getContextPath()%>/front-end/images/dog.jpeg" alt="會員照片" class="user-pic">
                <span class="fw-bold text-truncate">XXX會員名稱</span>
              </a>
            </div>
            <span class="card-text m-1">2023年1月1日</span>
            <span class="card-text m-1 border-start border-dark ps-2">
              <i class="bi bi-hand-thumbs-up-fill fa-2x like-icon"></i>
            </span>
            <span>223</span>
          </div>
        </div>
      </div>

    </div>
  </div>
  <!-- article end -->

  <!-- group start -->
  <div class="container">
    <div class="row mt-4 pt-4">
      <div class="col">
        <h2 class="fw-bold">熱門揪團</h2>
      </div>
      <div class="col text-end">
        <a href="#" class="a-style">更多</a>
      </div>
    </div>

    <div class="row row-cols-1 row-cols-xs-2 row-cols-sm-2 row-cols-lg-4 g-3">
      <div class="col">
        <div class="card h-100 shadow-sm">
          <a href="#" class="fade-area">
            <img src="<%=request.getContextPath()%>/front-end/images/Logo.jpg" class="card-img-top" alt="飯店圖片">
          </a>
          <div class="card-body">
            <a href="#" class="a-style text-dark">
              <h5 class="card-title fw-bold text-truncate">揪團名稱</h5>
            </a>
            <div class="card-text m-1">
              <a href="" class="a-style text-dark">
                <img src="<%=request.getContextPath()%>/front-end/images/dog.jpeg" alt="會員照片" class="user-pic">
                <span class="fw-bold text-truncate">XXX會員名稱</span>
              </a>
            </div>
            <span class="card-text m-1">參加人數 : 10</span>
            <span class="card-text m-1 border-start border-dark ps-2">人數上限 : 100</span>
            <div class="card-text">報名截止 : 2023/5/5 13:00</div>
            <span class="card-text bg-success rounded p-1">團主評價</span>
            <span class="card-text">
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
            </span>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="card h-100 shadow-sm">
          <a href="#" class="fade-area">
            <img src="<%=request.getContextPath()%>/front-end/images/Logo.jpg" class="card-img-top" alt="飯店圖片">
          </a>
          <div class="card-body">
            <a href="#" class="a-style text-dark">
              <h5 class="card-title fw-bold text-truncate">揪團名稱</h5>
            </a>
            <div class="card-text m-1">
              <a href="" class="a-style text-dark">
                <img src="<%=request.getContextPath()%>/front-end/images/dog.jpeg" alt="會員照片" class="user-pic">
                <span class="fw-bold text-truncate">XXX會員名稱</span>
              </a>
            </div>
            <span class="card-text m-1">參加人數 : 10</span>
            <span class="card-text m-1 border-start border-dark ps-2">人數上限 : 100</span>
            <div class="card-text">報名截止 : 2023/5/5 13:00</div>
            <span class="card-text bg-success rounded p-1">團主評價</span>
            <span class="card-text">
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
            </span>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="card h-100 shadow-sm">
          <a href="#" class="fade-area">
            <img src="<%=request.getContextPath()%>/front-end/images/Logo.jpg" class="card-img-top" alt="飯店圖片">
          </a>
          <div class="card-body">
            <a href="#" class="a-style text-dark">
              <h5 class="card-title fw-bold text-truncate">揪團名稱</h5>
            </a>
            <div class="card-text m-1">
              <a href="" class="a-style text-dark">
                <img src="<%=request.getContextPath()%>/front-end/images/dog.jpeg" alt="會員照片" class="user-pic">
                <span class="fw-bold text-truncate">XXX會員名稱</span>
              </a>
            </div>
            <span class="card-text m-1">參加人數 : 10</span>
            <span class="card-text m-1 border-start border-dark ps-2">人數上限 : 100</span>
            <div class="card-text">報名截止 : 2023/5/5 13:00</div>
            <span class="card-text bg-success rounded p-1">團主評價</span>
            <span class="card-text">
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
            </span>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="card h-100 shadow-sm">
          <a href="#" class="fade-area">
            <img src="<%=request.getContextPath()%>/front-end/images/Logo.jpg" class="card-img-top" alt="飯店圖片">
          </a>
          <div class="card-body">
            <a href="#" class="a-style text-dark">
              <h5 class="card-title fw-bold text-truncate">揪團名稱1</h5>
            </a>
            <div class="card-text m-1">
              <a href="" class="a-style text-dark">
                <img src="<%=request.getContextPath()%>/front-end/images/dog.jpeg" alt="會員照片" class="user-pic">
                <span class="fw-bold text-truncate">XXX會員名稱</span>
              </a>
            </div>
            <span class="card-text m-1">參加人數 : 10</span>
            <span class="card-text m-1 border-start border-dark ps-2">人數上限 : 100</span>
            <div class="card-text">報名截止 : 2023/5/5 13:00</div>
            <span class="card-text bg-success rounded p-1">團主評價</span>
            <span class="card-text">
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
            </span>
          </div>
        </div>
      </div>

    </div>
  </div>
  <!-- group end -->

  <!-- footer start -->

  <footer class="text-center text-lg-start text-dark bg-cblue mt-5">
    <div class="container p-4 pb-0">
      <div class="row">
        <div class="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
          <h6 class="mb-2 font-weight-bold">TibaMe CGA105 JAVA班 第七組</h6>
          <p class="mb-2">組員 : </p>
          <p>
            XXXXXXX
          </p>
        </div>

        <hr class="w-100 clearfix d-md-none" />

        <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mt-3">
          <h6 class="text-uppercase mb-4 font-weight-bold">聯絡資訊</h6>
          <p><i class="fas fa-envelope mr-3"></i>組長的email</p>
          <p><i class="fas fa-phone mr-3"></i>組長的電話</p>
        </div>
      </div>

      <hr class="my-3">

      <section class="p-3 pt-0">
        <div class="row d-flex align-items-center">
          <div class="col-md-7 col-lg-8 text-center text-md-start">
            <div class="p-3">© 2020 Copyright</div>
          </div>

          <div class="col-md-5 col-lg-4 ml-lg-0 text-center text-md-end">
            <!-- Facebook -->
            <a class="btn btn-outline-light btn-floating m-1" role="button">
              <i class="fab fa-facebook-f"></i></a>

            <!-- Google -->
            <a class="btn btn-outline-light btn-floating m-1" role="button">
              <i class="fab fa-google"></i></a>

            <!-- Instagram -->
            <a class="btn btn-outline-light btn-floating m-1" role="button">
              <i class="fab fa-instagram"></i></a>
          </div>
        </div>
      </section>
    </div>
  </footer>
  <!-- footer end -->
   <script> 
<%
 UsersVO usersVO2 = (UsersVO) session.getAttribute("usersVO");
 if(usersVO2!=null){
	out.println("let position = document.getElementById('mesPic').addEventListener('click', function () {"+
	"document.getElementById('color').submit();"
	+"});");
	out.println("login.style.visibility = 'hidden' ; ");
 	out.println("loginout.style.display='block' ; ");
 }
%>
<%  
UsersVO usersVO1 = (UsersVO)session.getAttribute("usersVO");
if(usersVO1!=null){
	out.println("let userPic = document.getElementById('userPic').src="+"'"+request.getContextPath()+"/front-end/member/getUserPic.jsp?UserAccount="+"\""+usersVO1.getUserAccount()+"\""+"'"+";");
	};
%>
<%-- let userPic = document.getElementById('userPic').src="request.getContextPath()%>/front-end/member/getUserPic.jsp?UserAccount='<%=usersVO1.getUserAccount()%>'"; --%>
 </script> 
  <script src="https://kit.fontawesome.com/616f19a0b0.js" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
 <script> let collection=document.getElementById("collection").addEventListener("click", function () {
	   document.Collection.submit();
 });     
 let bgcolor = document.getElementsByClassName("msg-item");
	for(let i = 0;i<bgcolor.length;i++){
  	if(document.getElementsByClassName('bgcolor')[i].innerText==0){
  	document.getElementsByClassName('msg-item')[i].style.backgroundColor='gray';}
 	}
 let login=document.getElementById("login");
 let loginout=document.getElementById("loginout");

 let info = document.getElementById("info").addEventListener("click", function () {
	      window.open("<%=request.getContextPath()%>/front-end/member/userInfo.jsp ", "userInfo", config = "height=540,width=445, top = 100, left = 700"); 
	     });
 if(document.getElementById('msg-item').style.backgroundColor!='gray'){document.getElementById("bi").className = "change";}
	loginout.addEventListener("click", function () {document.login_out.submit();});   
	document.getElementById('bell').addEventListener("click", function () {document.getElementById('delete_mes').submit();});
</script>
<style>
.change {
   color: #FF3B3B; 
}
</style>
</body>

</html>