<%@page contentType="text/html; charset=utf-8"%>

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
        <h2>회원 가입 화면</h2>
      </div>
    </div>
    
    <div class="row">
      <div class="col-md-5">

        <form class="form-horizontal" role="form" action="${pageContext.servletContext.contextPath}/user/join.mall" method="post">
          
          <div class="form-group">
            <label class="control-label col-sm-3">아이디:</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" name="id">
            </div>
          </div>
          
          <div class="form-group">
            <label class="control-label col-sm-3">비밀번호:</label>
            <div class="col-sm-9">
              <input type="password" class="form-control" name="passwd">
            </div>
          </div>

          <div class="form-group">
            <label class="control-label col-sm-3">이름:</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" name="name">
            </div>
          </div>

          <div class="form-group">
            <label class="control-label col-sm-3">이메일:</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" name="email">
            </div>
          </div>

          <div class="form-group">
            <label class="control-label col-sm-3">전화번호:</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" name="telephone">
            </div>
          </div>

          <div class="form-group">
            <label class="control-label col-sm-3">직업:</label>
            <div class="col-sm-9">
              <select class="form-control" name="job">
                <option value="학생">학생</option>
                <option value="강사">강사</option>
                <option value="기타">기타</option>
              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="control-label col-sm-3">가입인사:</label>
            <div class="col-sm-9">
              <textarea class="form-control" rows="3" name="message"></textarea>
            </div>
          </div>
          
          <div class="form-group">
            <div class="col-sm-12" style="text-align: right;">
              <button class="btn btn-default" type="submit">가입하기</button>
              <button class="btn btn-default"  type="reset">취소하기</button>
            </div>
          </div>
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
