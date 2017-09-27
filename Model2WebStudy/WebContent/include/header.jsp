<%@page import="java.net.URLDecoder"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" uri="/WEB-INF/util-functions.tld" %>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
        data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
          class="icon-bar"></span> <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand glyphicon glyphicon-education" href="/" style="color: yellow;">BANGEY
        BLOG</a>
    </div>

    <div id="navbar" class="navbar-collapse collapse text-uppercase">
      <ul class="nav navbar-nav">
        <li><a href="/">HOME</a></li>
        <li><a href="#">GUESTBOOK</a></li>
        <li class="dropdown"><a href="#" data-toggle="dropdown" role="button"
          aria-expanded="false">BOARD <span class="caret"></span>
        </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">FREE BOARD</a></li>
            <li><a href="#">JOB BOARD</a></li>
            <li class="divider"></li>
            <li><a href="#">Q&A</a></li>
            <li><a href="#">NOTICE</a></li>
          </ul></li>
        <li><a href="#">RESOURCE ROOM</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/user/list.mall">MEMBER</a></li>
      </ul>
      
      <c:choose>
        <c:when test="${empty cookie.user }">
          <form class="navbar-form navbar-right" action="${pageContext.servletContext.contextPath}/user/certify.mall"" method="post">
            <div class="form-group">
              <input type="text" name="id" placeholder="ID" class="form-control" size="10">
            </div>
            <div class="form-group">
              <input type="password" name="passwd" placeholder="PASSWORD" class="form-control" size="10">
            </div>
            <button type="submit" class="btn btn-success">LOGIN</button>
            <a class="glyphicon glyphicon-log-in" href="${pageContext.servletContext.contextPath}/user/regist.mall">JOIN</a>
          </form>
        </c:when>
        <c:otherwise>
          <div class="navbar-form navbar-right">
            <div class="form-group">
             <span class="glyphicon glyphicon-user"  style="color: white;"><strong>${u:decode(cookie.user.value) }</strong>님 행복하세요^^</span>
              <a class="glyphicon glyphicon-log-out" href="${pageContext.servletContext.contextPath}/user/certify.mall"">LOGOUT</a>
            </div>
          </div>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</nav>
