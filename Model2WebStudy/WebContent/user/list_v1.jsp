<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Welcome to My Blog</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery/Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

  <%-- 머리글 영역 시작 --%>
  <jsp:include page="/include/header.jsp" />
  <%-- 머리글 영역 종료 --%>
  <%-- 본문 내용 시작 --%>
  <div class="container">
    <br>
    <div class="row">
      <div class="page-header">
        <h2>
          회원 목록<span class="badge">100</span>
        </h2>
      </div>
    </div>
    <div class="row">
      <div>
        <table class="table table-striped table-bordered table-condensed table-hover">
          <thead>
            <tr class="success">
              <th style="width: 50px">번호</th>
              <th>이름</th>
              <th>아이디</th>
              <th>이메일</th>
              <th>전화번호</th>
              <th>직업</th>
              <th>가입일자</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${list }" var="user" varStatus="status">
              <tr>
                <td>${status.count}</td>
                <td><a href="read.jsp?id=">${user.id }</a></td>
                <td>${user.name }</td>
                <td><a href="mailto:">${user.email }</a></td>
                <td>${user.telephone }</td>
                <td>${user.job }</td>
                <td>${user.regdate }</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>

    <div class="row">
      <div style="text-align: center;">
        <ul class="pagination">
          <li><a href="?page=1">처음으로</a></li>
          <li class="prev"><a href="?page=1">이전목록</a></li>
          <li class="active"><a>1</a></li>
          <li><a>2</a></li>
          <li><a>3</a></li>
          <li><a>4</a></li>
          <li><a>5</a></li>
          <li><a>6</a></li>
          <li><a>7</a></li>
          <li><a>8</a></li>
          <li><a>9</a></li>
          <li><a>10</a></li>
          <li class="next"><a href="?page=11">다음목록</a></li>
          <li><a href="?page=10">끝으로</a></li>
        </ul>

        <%-- 검색 --%>
        <form name="search" class="form-inline" role="form">
          <div class="form-group">
            <select class="form-control" id="type" name="type">
              <option value="id">아이디</option>
              <option value="name">이름</option>
              <option value="job">직업</option>
            </select> <input type="text" class="form-control" id="value" name="value" required>
          </div>
          <button type="submit" class="btn btn-default">검색</button>
        </form>

      </div>
    </div>
    <%-- 본문 내용 종료 --%>

    <hr>

    <%-- 바닥글 시작 --%>
    <jsp:include page="/include/footer.jsp" />
    <%-- 바닥글 종료 --%>

  </div>
</body>
</html>
