function loginCheck() {
    // 1. 아이디 입력란이 비어 있는지 확인
	if (document.frm.userid.value.length == 0) {
		alert("아이디를 입력해주세요.");   // 경고창 표시
		frm.userid.focus();               // 아이디 입력란에 커서 이동
		return false;                     // submit 진행 막기
	}
	
    // 2. 비밀번호 입력란이 비어 있는지 확인
	if (document.frm.pwd.value == "") {
		alert("비밀번호를 입력해주세요."); // 경고창 표시
		frm.pwd.focus();                 // 비밀번호 입력란에 커서 이동
		return false;                    // submit 진행 막기
	}

    // 3. 아이디/비번 모두 입력되었으면 true 반환 → submit 진행
	return true;
}

function idCheck(){
	
	if(document.frm.userid.value == ""){
		alert("아이디를 입력해주세요.");   // 경고창 표시
		frm.userid.focus();               // 아이디 입력란에 커서 이동
		return ;                     // submit 진행 막기
	}
	
	//idCheck.do?userid='somi'
	let url="idCheck.do?userid=" +document.frm.userid.value;
	
	// 새로운 브라우저 창(팝업창)을 연다.
	window.open(url, "_blank", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200"); 
	
	/*
	window.open(
	    url,               // 열고 싶은 페이지의 주소(URL)
	    "_blank_1",        // 새 창의 이름(식별자). 같은 이름이면 같은 창을 재사용함
	    "toolbar=no,       // 브라우저 도구모음(툴바) 숨기기
	     menubar=no,       // 메뉴바 숨기기
	     scrollbars=yes,   // 스크롤바는 보이도록 설정
	     resizeable=no,    // 창 크기 조절 불가능
	     width=450,        // 새 창의 너비 450px
	     height=200"         // 새 창의 높이 200px (오타: height가 맞음!)
	);
	*/
}

