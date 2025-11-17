<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container mt-3">
  <h2>로그인 화면</h2>
  <form action="10_testLogin.jsp" method="post">
    <div class="mb-3 mt-3">
      <label for="userid">아이디</label>
      <input type="text" class="form-control" id="userid" placeholder="Enter id..." name="id">
    </div>
    <div class="mb-3">
      <label for="userpwd">암&nbsp;호</label>
      <input type="password" class="form-control" id="userpwd" placeholder="Enter password" name="pwd">
    </div>
    
    <button type="submit" class="btn btn-success">로그인</button>
  </form>
</div>

</body>
</html>
    