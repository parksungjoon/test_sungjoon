<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>김기정 블로그</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="/css/signin.css" rel="stylesheet">
<!-- jQuery/Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>
  <div class="container">

    <form class="form-signin" action="${pageContext.servletContext.contextPath}/user/certify.mall" method="post">
      <h2 class="form-signin-heading">로그인</h2>
      <label for="inputEmail" class="sr-only">ID</label> <input type="text" id="id" name="id"
        class="form-control" placeholder="ID" required autofocus> <label for="passwd"
        class="sr-only">Password</label> <input type="password" id="passwd" name="passwd"
        class="form-control" placeholder="Password" required> <input type="hidden"
        name="referer" value="${referer }">
      <div>
        <p style="color: red;">${message }</p>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">LOGIN</button>
    </form>

  </div>

</body>
</html>
