<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Welcome to My Blog</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery/Bootstrap JS -->
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".clickid").click(function(event){
		var userId=$(event.target).attr("href");
		alert(userId);
		/* var url="${pageContext.servletContext.contextPath}/user/read.mall?id="+userId; */
		/* alert(url); */
		$.ajax({
			url : "read.mall",
			data : "id="+userId,
			dataType : "text", //응답결과로 반환되는 데이터타입(text, html, xml, html, json)
			contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
			success : function(data) {
				var user=jQuery.parseJSON(data);
				$("#id").val(user.id);
				$("#name").val(user.name);
				$("#email").val(user.email);
				$("#telephone").val(user.telephone);
				$("#job").val(user.job);
				$("#message").val(user.message);
			}
		});
	});
	
	
	$(".removeid").click(function(event){
		var userId=$(event.target).attr("value");
		alert(userId);
		var url="${pageContext.servletContext.contextPath}/user/delete.mall?id="+userId;
		alert(url);
		$.ajax({
			url : url,
			dataType : "text", //응답결과로 반환되는 데이터타입(text, html, xml, html, json)
			contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
			success : function(data) {
				if(data=="true"){
					$("#remove").val("삭제 되었습니다.");
					
					$(event.target).parent().parent().css("display", "none");
					
				}else{
					$("#remove").val("삭제 실패!!!!!");
				}
		}
	});
});
});
</script>
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
          회원 목록 <span class="badge">회원수 :
            ${pageBuilder.totalRowCount}명</span>
        </h2>
      </div>
    </div>
    <div class="row">
      <div>
        <table
          class="table table-striped table-bordered table-condensed table-hover">
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
            <c:choose>
              <c:when test="${empty list}">
                <tr>
                  <td colspan="7"
                    style="text-align: center; color: red;">검색된 회원이
                    존재하지 않습니다.</td>
                </tr>
              </c:when>
              <c:otherwise>
                <c:forEach items="${list}" var="user" varStatus="status">
                  <tr class="targetid">
                    <td>${(pageBuilder.totalRowCount - ((params.page - 1) * params.pageSize)) - status.index}</td>
                    <td><a class="clickid" href="${user.id }"data-toggle="modal" data-target="#myModal">${user.name }</a></td>
                    <td>${user.id }</td>
                    <td><a href="mailto:${user.email }">${user.email }</a></td>
                    <td>${user.telephone }</td>
                    <td>${user.job }</td>
                    <td>${user.regdate }</td>
                    <td><a class="removeid" value="${user.id }" id="read" data-toggle="modal"
                      data-target="#deleteModal">탈퇴</a></td>
                  </tr>
                </c:forEach>

              </c:otherwise>
            </c:choose>
          </tbody>
        </table>
      </div>
    </div>

    <div class="row">
      <div style="text-align: center;">
        <ul class="pagination">

          <c:if test="${pageBuilder.showFirst }">
            <li><a href="${pageBuilder.getQueryString(1)}">처음으로</a></li>
          </c:if>

          <c:if test="${pageBuilder.showPrevious }">
            <li class="prev"><a
              href="${pageBuilder.getQueryString(pageBuilder.previousStartPage)}">이전목록</a></li>
          </c:if>

          <c:forEach var="i" begin="${pageBuilder.currentStartPage}"
            end="${pageBuilder.currentEndPage }">
            <c:choose>
              <c:when test="${i == params.page }">
                <li class="active"><a>${i }</a></li>
              </c:when>
              <c:otherwise>
                <li><a href="${pageBuilder.getQueryString(i)}">${i }</a></li>
              </c:otherwise>
            </c:choose>
          </c:forEach>

          <c:if test="${pageBuilder.showNext }">
            <li class="next"><a href="${pageBuilder.getQueryString(pageBuilder.nextStartPage)}">다음목록</a></li>
          </c:if>

          <c:if test="${pageBuilder.showLast }">
            <li><a
              href="${pageBuilder.getQueryString(pageBuilder.totalPageCount)}">끝으로</a></li>
          </c:if>
        </ul>

        <%-- 검색 --%>
        <form name="search" class="form-inline" role="form" method="get">
          <div class="form-group">
            <select class="form-control" id="type" name="type">
              <option value="id">아이디</option>
              <option value="name">이름</option>
              <option value="job">직업</option>
            </select> <input type="text" class="form-control" id="value"
              name="value" required>
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
    <div class="modal fade" id="myModal" role="dialog">
      <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">회원정보</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" role="form"
              action="${pageContext.servletContext.contextPath}/user/join.mall"
              method="post">

              <div class="form-group">
                <label class="control-label col-sm-3">아이디:</label>
                <div class="col-sm-9">
                  <input type="text" id="id" class="form-control"
                    name="id">
                </div>
              </div>

              <div class="form-group">
                <label class="control-label col-sm-3">이름:</label>
                <div class="col-sm-9">
                  <input type="text" id="name" class="form-control"
                    name="name">
                </div>
              </div>

              <div class="form-group">
                <label class="control-label col-sm-3">이메일:</label>
                <div class="col-sm-9">
                  <input type="text" id="email" class="form-control"
                    name="email">
                </div>
              </div>

              <div class="form-group">
                <label class="control-label col-sm-3">전화번호:</label>
                <div class="col-sm-9">
                  <input type="text" id="telephone" class="form-control"
                    name="telephone">
                </div>
              </div>

              <div class="form-group">
                <label class="control-label col-sm-3">직업:</label> <input
                  type="text" id="job" class="form-control"
                  name="telephone">
              </div>

              <div class="form-group">
                <label class="control-label col-sm-3">가입인사:</label>
                <div class="col-sm-9">
                  <textarea class="form-control" id="message" rows="3"
                    name="message"></textarea>
                </div>
              </div>

            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default"
              data-dismiss="modal">Close</button>
          </div>
        </div>

      </div>
    </div>


    <div class="modal fade" id="deleteModal" role="dialog">
      <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">회원정보</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" role="form"
              action="${pageContext.servletContext.contextPath}/user/join.mall"
              method="post">

              <div class="form-group">
                <label class="control-label col-sm-3">아이디:</label>
                <div class="col-sm-9">
                  <input type="text" id="remove" class="form-control" name="id">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div>

      </div>
    </div>
  </div>
</body>
</html>
