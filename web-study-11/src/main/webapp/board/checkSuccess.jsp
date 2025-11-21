<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript">

// 현재 창의 이름이 'update'라면 (즉, 수정 버튼을 눌러서 열린 팝업창이라면)
if (window.name == 'update') {
	
    // 부모 창(opener)의 화면을 '게시글 수정 폼'으로 이동시킴
    // BoardServlet에서 board_update_form 명령을 실행하고 num 값 전달
	window.opener.parent.location.href = 
        "BoardServlet?command=board_update_form&num=${param.num}";
	
} else if (window.name == 'delete') {
	// 현재 창의 이름이 'delete'라면(삭제 팝업에서 열렸다면)

    // 삭제 완료 안내 메시지 출력
	alert('삭제되었습니다.');

    // 부모 창을 '게시글 삭제 처리' URL로 이동시켜 삭제 요청 실행
	window.opener.parent.location.href = 
        "BoardServlet?command=board_delete&num=${param.num}";
}

window.close();

</script>

</body>
</html>