<%@ page contentType="text/html; charset=utf-8"%>
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
    <div class="jumbotron">
      <div class="container">
        <h2 class="text-danger">${title}</h2>
        <p>이 블로그는 Servlet/JSP 트레이닝을 위한 파일럿 웹사이트입니다. It includes a large callout
          called a jumbotron and three supporting pieces of content. Use it as a starting point to
          create something more unique.</p>
        <p>
          <a class="btn btn-primary btn-lg" href="#" role="button">Learn more &raquo;</a>
        </p>
      </div>
    </div>
  </div>

  <div class="container">
    <div class="row">
      <div class="col-md-4">
        <h2>Subject1</h2>
        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus
          commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam
          porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
        <p>
          <a class="btn btn-info" href="#" role="button">View &raquo;</a>
        </p>
      </div>
      <div class="col-md-4">
        <h2>Subject2</h2>
        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus
          commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam
          porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
        <p>
          <a class="btn btn-info" href="#" role="button">View &raquo;</a>
        </p>
      </div>
      <div class="col-md-4">
        <h2>Subject3</h2>
        <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam.
          Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo,
          tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
        <p>
          <a class="btn btn-info" href="#" role="button">View &raquo;</a>
        </p>
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
