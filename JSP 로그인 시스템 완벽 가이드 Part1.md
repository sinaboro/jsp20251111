# 🎓 JSP 로그인 시스템 완벽 가이드 (초보용)

> "복잡해 보이는 프로그램도, 하나씩 뜯어보면 이해할 수 있습니다!"

---

## 📖 목차

1. [시작하기 전에 - 전체 그림 이해하기](#1-시작하기-전에---전체-그림-이해하기)
2. [시스템 구조 - 5가지 핵심 구성요소](#2-시스템-구조---5가지-핵심-구성요소)
3. [파일 구조 - 어떤 파일이 무슨 일을 하나요?](#3-파일-구조---어떤-파일이-무슨-일을-하나요)
4. [로그인 과정 따라가기 - 12단계 완벽 분석](#4-로그인-과정-따라가기---12단계-완벽-분석)
5. [회원가입 과정 - 아이디 중복 체크까지](#5-회원가입-과정---아이디-중복-체크까지)
6. [회원정보 수정 과정](#6-회원정보-수정-과정)
7. [로그아웃 과정](#7-로그아웃-과정)
8. [코드 한 줄씩 이해하기](#8-코드-한-줄씩-이해하기)
9. [프로그래밍 용어 사전](#9-프로그래밍-용어-사전)
10. [자주 묻는 질문 (Q&A)](#10-자주-묻는-질문-qa)

---

## 1. 시작하기 전에 - 전체 그림 이해하기

### 🏫 학교 도서관으로 비유하면?

이 프로그램을 학교 도서관 시스템으로 생각해봅시다.

```
학생(여러분) → 출입구 →  담당 선생님   →  사서 선생님 →  회원 명부 → 도서관 안
    ↓        ↓          ↓            ↓           ↓
  브라우저     JSP      Servlet       DAO     데이터베이스     세션
```

**실제 도서관에서 일어나는 일:**
1. 여러분이 도서관에 들어가고 싶어합니다
2. 출입구에 **신청서(JSP)** 가 있어요 - 학번과 이름을 쓰라고 되어있죠
3. 신청서를 작성하면 **담당 선생님(Servlet)** 이 받습니다
4. 선생님은 **사서 선생님(DAO)** 에게 "이 학생 맞나요?"라고 확인합니다
5. 사서 선생님은 **회원 명부(데이터베이스)** 를 뒤져서 학생 정보를 찾습니다
6. 맞으면 여러분에게 **임시 출입증(세션)** 을 줍니다
7. 이제 출입증으로 도서관 안에서 자유롭게 활동할 수 있어요!

**웹사이트도 똑같습니다!**
- 로그인 = 도서관 입장
- 아이디/비밀번호 = 학번/이름
- 세션 = 임시 출입증
- 로그아웃 = 도서관 퇴장

### 🎯 핵심 요약
- 웹 로그인 시스템은 도서관 출입 시스템과 비슷합니다
- 신분을 확인하고, 확인되면 임시 출입증(세션)을 줍니다
- 이 출입증으로 여러 페이지를 자유롭게 돌아다닐 수 있습니다

---

## 2. 시스템 구조 - 5가지 핵심 구성요소

### 📦 1) 브라우저 (Browser) - "학생"

**역할**: 사용자가 보고 조작하는 프로그램

**예시**:
- 크롬, 파이어폭스, 사파리 등
- 여러분이 네이버나 유튜브를 볼 때 사용하는 그 프로그램!

**하는 일**:
```
1. 웹사이트 주소를 입력하면 → 서버에 "페이지 주세요!" 요청
2. 서버가 보낸 HTML, CSS, JavaScript를 받아서 → 화면에 예쁘게 보여줌
3. 버튼을 클릭하거나 폼을 작성하면 → 서버에 데이터 전송
```

### 📄 2) JSP (JavaServer Pages) - "신청서/안내판"

**역할**: 사용자가 보는 웹 페이지를 만듭니다

**예시**: 
- `login.jsp` - 로그인 페이지 (아이디/비밀번호 입력하는 곳)
- `main.jsp` - 메인 페이지 ("○○○님 환영합니다" 보여주는 곳)
- `join.jsp` - 회원가입 페이지

**특징**:
```html
<!-- 일반 HTML -->
<p>안녕하세요!</p>

<!-- JSP (동적 HTML) -->
<p>안녕하세요, ${loginUser.name}님!</p>
```
- HTML은 항상 "안녕하세요!"만 보여줌
- JSP는 로그인한 사람 이름에 따라 "안녕하세요, 철수님!" 또는 "안녕하세요, 영희님!" 다르게 보여줌

**비유**: 
- HTML = 인쇄된 책 (내용이 고정됨)
- JSP = 전광판 (내용이 계속 바뀜)

### ☕ 3) Servlet - "담당 선생님/교통 경찰"

**역할**: 사용자의 요청을 받아서 처리하고, 결과를 돌려줍니다

**예시**:
- `LoginServlet` - 로그인 처리
- `JoinServlet` - 회원가입 처리
- `UpdateServlet` - 회원정보 수정 처리
- `LogoutServlet` - 로그아웃 처리
- `IdCheckServlet` - 아이디 중복 체크

**두 가지 메서드**:
```java
doGet()  → "보여주기" - 페이지를 달라고 할 때
doPost() → "처리하기" - 데이터를 제출할 때
```

**비유**:
- 여러분이 선생님께 "이거 해주세요"라고 부탁하면
- 선생님이 알아서 필요한 일을 하고
- 결과를 돌려줍니다

### 🗄️ 4) DAO (Data Access Object) - "사서 선생님"

**역할**: 데이터베이스와 소통하는 전문가

**예시**: `MemberDAO`

**하는 일**:
```java
userCheck()    → 아이디/비밀번호 맞는지 확인
getMember()    → 회원 정보 가져오기
insertMember() → 새 회원 등록하기
updateMember() → 회원 정보 수정하기
confirmID()    → 아이디 중복 확인하기
```

**비유**:
- 도서관 사서 선생님처럼 책(데이터)을 찾고, 등록하고, 수정하는 일을 합니다
- 다른 사람들은 사서 선생님에게만 부탁하면 됩니다

**왜 DAO가 필요한가요?**
- 데이터베이스 작업은 복잡해요
- 한 곳(DAO)에 모아두면 관리하기 편해요
- 여러 Servlet이 같은 DAO를 사용할 수 있어요

### 📦 5) VO (Value Object) - "택배 상자"

**역할**: 데이터를 담아서 옮기는 상자

**예시**: `MemberVO`

**담는 데이터**:
```java
name   → 이름
userid → 아이디
pwd    → 비밀번호
email  → 이메일
phone  → 전화번호
admin  → 등급 (0=일반회원, 1=관리자)
```

**비유**:
- 택배 상자처럼 여러 물건(데이터)을 담아서 이곳저곳 옮깁니다
- 상자를 열고(get) 물건을 꺼내거나, 상자에 넣을(set) 수 있어요

```java
// 상자에 넣기 (setter)
mvo.setName("철수");

// 상자에서 꺼내기 (getter)
String name = mvo.getName();
```

### 🎯 핵심 요약

```
브라우저  → 사용자가 보는 화면 (크롬, 파이어폭스)
JSP      → 동적인 웹 페이지 (사용자마다 다르게 보임)
Servlet  → 요청 처리하는 프로그램 (선생님 역할)
DAO      → 데이터베이스 전문가 (사서 선생님)
VO       → 데이터 담는 상자 (택배 상자)
```

---

## 3. 파일 구조 - 어떤 파일이 무슨 일을 하나요?

### 📁 전체 폴더 구조

```
web-study-09/
├── src/
│   └── main/
│       ├── java/               ← Java 코드 (로직)
│       │   └── com/saeyan/
│       │       ├── controller/ ← Servlet들 (요청 처리)
│       │       │   ├── LoginServlet.java
│       │       │   ├── JoinServlet.java
│       │       │   ├── UpdateServlet.java
│       │       │   ├── LogoutServlet.java
│       │       │   └── IdCheckServlet.java
│       │       └── dto/        ← DAO와 VO (데이터 관련)
│       │           ├── MemberDAO.java
│       │           └── MemberVO.java
│       └── webapp/             ← 웹 페이지 (화면)
│           ├── member/         ← 회원 관련 JSP
│           │   ├── login.jsp
│           │   ├── main.jsp
│           │   ├── join.jsp
│           │   ├── memberUpdate.jsp
│           │   └── idCheck.jsp
│           ├── js/             ← JavaScript 파일
│           │   └── member.js
│           └── WEB-INF/
│               └── web.xml     ← 설정 파일
```

### 🔍 각 파일의 역할 상세 설명

#### 📄 MemberVO.java - "회원 정보 상자"

**위치**: `src/main/java/com/saeyan/dto/MemberVO.java`

**역할**: 회원 정보를 담는 상자

**왜 필요한가요?**
- 회원 정보(이름, 아이디, 비밀번호 등)를 하나로 묶어서 전달하기 위해
- 여러 개의 변수를 따로따로 전달하면 복잡하니까 하나의 객체로 묶음

**들어있는 내용**:
```java
private String name;    // 이름
private String userid;  // 아이디
private String pwd;     // 비밀번호
private String email;   // 이메일
private String phone;   // 전화번호
private int admin;      // 등급 (0 또는 1)
```

**사용 예시**:
```java
// 상자 만들기
MemberVO mvo = new MemberVO();

// 상자에 데이터 넣기
mvo.setName("철수");
mvo.setUserid("chulsoo");
mvo.setPwd("1234");

// 상자에서 데이터 꺼내기
String name = mvo.getName();  // "철수"
```

---

#### 🗄️ MemberDAO.java - "데이터베이스 전문가"

**위치**: `src/main/java/com/saeyan/dto/MemberDAO.java`

**역할**: 데이터베이스에서 회원 정보를 찾고, 추가하고, 수정하는 일

**싱글톤 패턴이란?**
```java
private static MemberDAO instance = new MemberDAO();

public static MemberDAO getInstance() {
    return instance;
}
```
- **의미**: 사서 선생님은 한 분만 계십니다
- **이유**: 여러 명이 있으면 혼란스러우니까 한 분만!
- **사용법**: `MemberDAO dao = MemberDAO.getInstance();`

**주요 메서드**:

1. **getConnection()** - 데이터베이스 연결하기
```java
// 이렇게 쓰면 안 돼요 (직접 접근)
// Database db = new Database();

// 이렇게 써야 해요 (DAO 통해서)
Connection con = dao.getConnection();
```

2. **userCheck()** - 로그인 확인하기
```java
int result = dao.userCheck("철수", "1234");
// result = 1  → 로그인 성공!
// result = 0  → 아이디나 비밀번호 틀림
// result = -1 → 데이터베이스 오류
```

3. **getMember()** - 회원 정보 가져오기
```java
MemberVO mvo = dao.getMember("chulsoo");
// mvo에 철수의 모든 정보가 담겨있어요
```

4. **insertMember()** - 새 회원 등록하기
```java
MemberVO mvo = new MemberVO();
mvo.setName("영희");
mvo.setUserid("younghee");
// ... 다른 정보 설정 ...

int result = dao.insertMember(mvo);
// result = 1  → 가입 성공!
// result = -2 → 아이디 중복
```

5. **updateMember()** - 회원 정보 수정하기
```java
MemberVO mvo = new MemberVO();
mvo.setUserid("chulsoo");
mvo.setPwd("새비밀번호");
// ... 다른 정보 설정 ...

int result = dao.updateMember(mvo);
// result = 1 → 수정 성공!
```

6. **confirmID()** - 아이디 중복 확인하기
```java
int result = dao.confirmID("chulsoo");
// result = 1 → 이미 있는 아이디 (사용 불가)
// result = 0 → 없는 아이디 (사용 가능)
```

---

#### ☕ LoginServlet.java - "로그인 담당 선생님"

**위치**: `src/main/java/com/saeyan/controller/LoginServlet.java`

**URL**: `/login.do`로 접속하면 이 Servlet이 처리합니다

**두 가지 메서드**:

1. **doGet()** - "로그인 페이지 보여주세요"
```java
protected void doGet(...) {
    // 1. 이전 값들 지우기 (깨끗한 로그인 페이지)
    request.removeAttribute("userid");
    request.removeAttribute("message");
    
    // 2. login.jsp 페이지로 이동
    request.getRequestDispatcher("/member/login.jsp")
           .forward(request, response);
}
```

**언제 실행되나요?**
- 브라우저 주소창에 `http://localhost:8080/web-study-09/login.do` 입력할 때
- 다른 페이지에서 `<a href="login.do">` 링크 클릭할 때

2. **doPost()** - "로그인 처리해주세요"
```java
protected void doPost(...) {
    // 1. 사용자가 입력한 데이터 받기
    String userid = request.getParameter("userid");
    String pwd = request.getParameter("pwd");
    
    // 2. DAO에게 확인 요청
    MemberDAO dao = MemberDAO.getInstance();
    int result = dao.userCheck(userid, pwd);
    
    // 3. 결과에 따라 처리
    if(result == 1) {
        // 성공! 세션에 저장하고 메인 페이지로
        HttpSession session = request.getSession();
        session.setAttribute("userid", userid);
        
        // 전체 회원 정보도 가져오기
        MemberVO mvo = dao.getMember(userid);
        session.setAttribute("loginUser", mvo);
        
        url = "/member/main.jsp";
    } else {
        // 실패! 에러 메시지와 함께 다시 로그인 페이지로
        request.setAttribute("message", "아이디 또는 비밀번호가 틀립니다");
        request.setAttribute("userid", userid);  // 아이디는 유지
        url = "/member/login.jsp";
    }
    
    // 4. 페이지로 이동
    request.getRequestDispatcher(url).forward(request, response);
}
```

**언제 실행되나요?**
- login.jsp에서 아이디/비밀번호 입력하고 "로그인" 버튼 클릭할 때

---

#### ☕ JoinServlet.java - "회원가입 담당 선생님"

**위치**: `src/main/java/com/saeyan/controller/JoinServlet.java`

**URL**: `/join.do`

**doGet()** - 회원가입 페이지 보여주기
```java
protected void doGet(...) {
    // join.jsp 페이지로 이동
    request.getRequestDispatcher("/member/join.jsp")
           .forward(request, response);
}
```

**doPost()** - 회원가입 처리하기
```java
protected void doPost(...) {
    // 1. 입력된 데이터 받기
    String name = request.getParameter("name");
    String userid = request.getParameter("userid");
    String pwd = request.getParameter("pwd");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String adminStr = request.getParameter("admin");
    String reid = request.getParameter("reid");  // 중복 체크 했는지 확인용
    
    // 2. 중복 체크 확인
    if(reid가 없거나 userid와 다르면) {
        "아이디 중복 체크를 해주세요" 메시지;
        return;
    }
    
    // 3. VO에 담기
    MemberVO mvo = new MemberVO();
    mvo.setName(name);
    mvo.setUserid(userid);
    // ... 나머지 정보 설정 ...
    
    // 4. DAO를 통해 DB에 저장
    MemberDAO dao = MemberDAO.getInstance();
    int result = dao.insertMember(mvo);
    
    // 5. 결과에 따라 처리
    if(result == 1) {
        // 성공! 로그인 페이지로
        "회원 가입이 완료되었습니다" 메시지;
        url = "/login.do";
    } else {
        // 실패! 다시 회원가입 페이지로
        "가입 실패" 메시지;
        url = "/member/join.jsp";
    }
}
```

---

#### ☕ UpdateServlet.java - "회원정보 수정 담당 선생님"

**위치**: `src/main/java/com/saeyan/controller/UpdateServlet.java`

**URL**: `/memberUpdate.do`

**doGet()** - 수정 페이지 보여주기 (기존 정보 불러오기)
```java
protected void doGet(...) {
    // 1. 로그인했는지 확인
    HttpSession session = request.getSession();
    MemberVO loginUser = session.getAttribute("loginUser");
    
    if(loginUser == null) {
        // 로그인 안 했으면 로그인 페이지로
        response.sendRedirect("/login.do");
        return;
    }
    
    // 2. 데이터베이스에서 최신 정보 가져오기
    String userid = loginUser.getUserid();
    MemberDAO dao = MemberDAO.getInstance();
    MemberVO mvo = dao.getMember(userid);
    
    // 3. 정보를 request에 담아서 JSP로
    request.setAttribute("mvo", mvo);
    request.getRequestDispatcher("/member/memberUpdate.jsp")
           .forward(request, response);
}
```

**doPost()** - 수정 내용 저장하기
```java
protected void doPost(...) {
    // 1. 수정된 데이터 받기
    String userid = request.getParameter("userid");
    String pwd = request.getParameter("pwd");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    
    // 2. VO에 담기
    MemberVO mvo = new MemberVO();
    mvo.setUserid(userid);
    mvo.setPwd(pwd);
    // ... 나머지 설정 ...
    
    // 3. DAO를 통해 DB 업데이트
    MemberDAO dao = MemberDAO.getInstance();
    int result = dao.updateMember(mvo);
    
    // 4. 성공하면 세션도 업데이트
    if(result == 1) {
        HttpSession session = request.getSession();
        MemberVO updatedMvo = dao.getMember(userid);
        session.setAttribute("loginUser", updatedMvo);
        
        url = "/member/main.jsp";
    }
}
```

---

#### ☕ LogoutServlet.java - "로그아웃 담당 선생님"

**위치**: `src/main/java/com/saeyan/controller/LogoutServlet.java`

**URL**: `/logout.do`

**역할**: 세션을 무효화하고 로그인 페이지로 돌아가기

```java
protected void doGet(...) {
    // 1. 세션 가져오기
    HttpSession session = request.getSession();
    
    // 2. 세션 무효화 (출입증 회수)
    session.invalidate();
    
    // 3. 로그인 페이지로 이동
    response.sendRedirect(request.getContextPath() + "/login.do");
}
```

**비유**:
- 도서관 나갈 때 출입증을 반납하는 것과 같아요
- 세션이 사라지면 로그인 정보도 모두 사라집니다

---

#### ☕ IdCheckServlet.java - "아이디 중복 체크 담당 선생님"

**위치**: `src/main/java/com/saeyan/controller/IdCheckServlet.java`

**URL**: `/idCheck.do`

**역할**: 회원가입할 때 아이디가 이미 있는지 확인

```java
protected void doGet(...) {
    // 1. 확인할 아이디 받기
    String userid = request.getParameter("userid");
    
    // 2. 아이디 입력했는지 확인
    if(userid가 비어있으면) {
        "아이디를 입력해주세요" 메시지;
        return;
    }
    
    // 3. DAO에게 중복 확인 요청
    MemberDAO dao = MemberDAO.getInstance();
    int result = dao.confirmID(userid);
    
    // 4. 결과를 idCheck.jsp로 전달
    request.setAttribute("userid", userid);
    request.setAttribute("result", result);
    request.getRequestDispatcher("/member/idCheck.jsp")
           .forward(request, response);
}
```

**사용 시나리오**:
1. 회원가입 페이지에서 아이디 입력
2. "중복 체크" 버튼 클릭
3. 팝업 창이 열림 (idCheck.jsp)
4. 결과 표시: "사용 가능" 또는 "이미 있는 아이디"

---

### 📄 JSP 파일들

#### 🖥️ login.jsp - "로그인 화면"

**위치**: `src/main/webapp/member/login.jsp`

**보여주는 것**:
- 아이디 입력 칸
- 비밀번호 입력 칸
- 로그인 버튼
- 회원가입 버튼
- 에러 메시지 (로그인 실패 시)

**주요 코드 설명**:

1. **폼 태그** - 데이터를 서버로 보내는 부분
```jsp
<form name="frm" action="</login.do" method="post">
```
- `action`: 데이터를 어디로 보낼지 (LoginServlet으로)
- `method="post"`: POST 방식으로 전송 (비밀번호는 안전하게!)

2. **아이디 입력** - 로그인 실패 시 아이디 유지
```jsp
<%
   String userid = (String) request.getAttribute("userid");
   if(userid == null) userid = "";
%>
<input type="text" name="userid" value="<%= userid %>" autocomplete="off">
```
- 로그인 실패해도 아이디는 그대로 남아있어요

3. **JavaScript 검증** - 빈 칸 체크
```javascript
function loginCheck() {
    if(document.frm.userid.value == "") {
        alert("아이디를 입력해주세요.");
        return false;  // 폼 제출 중단!
    }
    if(document.frm.pwd.value == "") {
        alert("비밀번호를 입력해주세요.");
        return false;
    }
    return true;  // 폼 제출 진행!
}
```

4. **에러 메시지 표시**
```jsp
<% if(request.getAttribute("message") != null) { %>
    <div><%=request.getAttribute("message")%></div>
<% } %>
```
- LoginServlet이 보낸 메시지를 화면에 표시

---

#### 🖥️ join.jsp - "회원가입 화면"

**위치**: `src/main/webapp/member/join.jsp`

**보여주는 것**:
- 이름, 아이디, 비밀번호, 이메일, 전화번호 입력 칸
- 아이디 중복 체크 버튼
- 확인/취소 버튼

**중요한 부분**:

1. **아이디 중복 체크**
```jsp
<input type="text" name="userid" id="userid" />
<input type="hidden" name="reid" />
<input type="button" value="중복 체크" onclick="idCheck()" />
```
- `userid`: 사용자가 입력한 아이디
- `reid`: 중복 체크를 했는지 확인하는 숨겨진 필드
- "중복 체크" 버튼을 누르면 `idCheck()` 함수 실행

2. **비밀번호 확인**
```jsp
<input type="password" name="pwd" />
<input type="password" name="pwd_check" />
```
- 두 비밀번호가 일치하는지 JavaScript에서 확인

3. **등급 선택**
```jsp
<input type="radio" name="admin" value="0" checked /> 일반회원
<input type="radio" name="admin" value="1" /> 관리자
```
- 라디오 버튼으로 하나만 선택 가능

4. **취소 버튼**
```jsp
<input type="button" value="취소" onclick="cancelJoin()" />

<script>
function cancelJoin() {
    location.href = contextPath + "/login.do";
}
</script>
```
- 취소하면 로그인 페이지로 이동

---

#### 🖥️ main.jsp - "메인 화면"

**위치**: `src/main/webapp/member/main.jsp`

**보여주는 것**:
- 환영 메시지 ("○○○님 환영합니다")
- 회원 정보 (아이디, 이메일, 전화번호, 등급)
- 로그아웃 버튼
- 회원정보변경 버튼

**중요한 부분**:

1. **로그인 체크** - 로그인 안 하면 못 들어옴!
```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty loginUser}">
    <jsp:forward page="/login.do" />
</c:if>
```
- 세션에 `loginUser`가 없으면 자동으로 로그인 페이지로

2. **EL 표현식으로 데이터 표시**
```jsp
<span>${loginUser.name}</span>님
<p>ID : <strong>${loginUser.userid}</strong></p>
<p>Email : <strong>${loginUser.email}</strong></p>
```
- `${loginUser.name}`: 세션에 저장된 회원 이름 가져오기
- Java 코드 없이 깔끔하게 데이터 표시!

3. **삼항 연산자**
```jsp
<p>Admin : <strong>${loginUser.admin == 1 ? '관리자' : '일반회원'}</strong></p>
```
- admin이 1이면 "관리자", 아니면 "일반회원" 표시

---

#### 🖥️ memberUpdate.jsp - "회원정보 수정 화면"

**위치**: `src/main/webapp/member/memberUpdate.jsp`

**보여주는 것**:
- 이름 (수정 불가)
- 아이디 (수정 불가)
- 비밀번호 (새 비밀번호 입력)
- 이메일, 전화번호 (수정 가능)
- 확인/취소 버튼

**중요한 부분**:

1. **기존 데이터 불러오기**
```jsp
<%
   MemberVO mvo = (MemberVO) request.getAttribute("mvo");
   String nameValue = mvo != null ? mvo.getName() : "";
%>
<input type="text" name="name" value="<%= nameValue %>" readonly>
```
- UpdateServlet이 보낸 `mvo` 데이터를 받아서 표시
- `readonly`: 읽기 전용 (수정 불가)

2. **아이디 처리** - 수정 못 하게!
```jsp
<input type="hidden" name="userid" value="<%= useridValue %>">
<input type="text" value="<%= useridValue %>" readonly disabled>
```
- 숨겨진 필드로 서버에 전송은 하지만
- 화면에는 읽기 전용으로 표시만

3. **취소 버튼**
```jsp
<input type="button" value="취소" onclick="cancelUpdate()">

<script>
function cancelUpdate() {
    location.href = contextPath + '/member/main.jsp';
}
</script>
```
- 수정 취소하고 메인 페이지로

---

#### 🖥️ idCheck.jsp - "아이디 중복 체크 팝업"

**위치**: `src/main/webapp/member/idCheck.jsp`

**보여주는 것**:
- 아이디 입력 칸
- 중복 체크 버튼
- 결과 메시지
- "사용" 버튼 (사용 가능할 때만)

**결과에 따른 표시**:

1. **사용 가능한 경우** (result == 0)
```jsp
<c:if test="${result == 0}">
    <div>
        <strong>${userid}</strong>는 사용 가능한 아이디입니다.
        <input type="button" value="사용" onclick="idok('${userid}')">
    </div>
</c:if>
```

2. **이미 있는 경우** (result == 1)
```jsp
<c:if test="${result == 1}">
    <div>
        <strong>${userid}</strong>는 이미 사용 중인 아이디입니다.
    </div>
</c:if>
```

3. **사용 버튼 클릭 시** - 부모 창에 전달
```javascript
function idok(userid) {
    // 부모 창(회원가입 페이지)의 폼에 아이디 설정
    window.opener.document.frm.userid.value = userid;
    window.opener.document.frm.reid.value = userid;
    window.opener.document.frm.userid.readOnly = true;
    
    // 팝업 닫기
    window.close();
}
```
- `window.opener`: 팝업을 연 부모 창
- 부모 창의 폼에 아이디를 설정하고
- 아이디를 수정 못 하게 만들고
- 팝업을 닫습니다

---

### 📜 member.js - "JavaScript 함수 모음"

**위치**: `src/main/webapp/js/member.js`

**포함된 함수들**:

1. **loginCheck()** - 로그인 폼 검증
2. **joinCheck()** - 회원가입 폼 검증
3. **idCheck()** - 아이디 중복 체크 팝업 열기
4. **idok()** - 아이디 사용 확인

**자세한 설명은 섹션 8에서!**


---
### 🎯 핵심 요약

```
Java 파일 (로직)
├── MemberVO      - 데이터 상자
├── MemberDAO     - DB 전문가
└── Servlet들     - 요청 처리

JSP 파일 (화면)
├── login.jsp     - 로그인
├── main.jsp      - 메인
├── join.jsp      - 회원가입
├── memberUpdate.jsp - 정보 수정
└── idCheck.jsp   - 중복 체크

JavaScript
└── member.js     - 유효성 검증
```

---

## 4. 로그인 과정 따라가기 - 12단계 완벽 분석

이제 실제로 "철수"라는 학생이 로그인하는 과정을 단계별로 따라가봅시다!

**철수의 정보**:
- 아이디: `chulsoo`
- 비밀번호: `1234`
- 이름: 김철수
- 이메일: chulsoo@example.com

### 📍 1단계: 로그인 페이지 요청

**철수가 하는 일**:
```
브라우저 주소창에 입력:
http://localhost:8080/web-study-09/login.do
```

**일어나는 일**:
- 브라우저가 서버에게 "login.do 페이지 주세요!" 요청을 보냄
- 이것은 **GET 요청**입니다 (페이지를 달라는 요청)

**비유**: 
- 도서관 입구에 도착해서 "입장 신청서 주세요" 하는 것

---

### 📍 2단계: LoginServlet의 doGet() 실행

**서버에서 일어나는 일**:
```java
// LoginServlet.java
protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    // 1. 이전 값 지우기
    request.removeAttribute("userid");
    request.removeAttribute("message");
    
    // 2. login.jsp로 이동
    request.getRequestDispatcher("/member/login.jsp")
           .forward(request, response);
}
```

**해설**:
1. `removeAttribute()`: 이전에 로그인 실패했을 때 남은 데이터 삭제
2. `getRequestDispatcher()`: login.jsp 파일 찾기
3. `forward()`: 그 파일로 이동하기

**비유**:
- 담당 선생님이 깨끗한 신청서를 철수에게 건네주는 것

---

### 📍 3단계: login.jsp 화면 표시

**브라우저에 표시되는 것**:
```
┌─────────────────────────┐
│      로그인             │
├─────────────────────────┤
│ 아이디: [          ]   │
│ 비밀번호: [          ]   │
│                         │
│  [로그인] [취소] [회원가입]│
└─────────────────────────┘
```

**철수가 입력**:
- 아이디 칸에 `chulsoo`
- 비밀번호 칸에 `1234`

---

### 📍 4단계: JavaScript 검증

**철수가 "로그인" 버튼 클릭!**

**login.jsp의 버튼 코드**:
```html
<input type="submit" value="로그인" onclick="return loginCheck();">
```

**member.js의 loginCheck() 함수 실행**:
```javascript
function loginCheck() {
    // 아이디 입력했나요?
    if(document.frm.userid.value == "") {
        alert("아이디를 입력해주세요.");
        return false;  // 폼 제출 중단!
    }
    
    // 비밀번호 입력했나요?
    if(document.frm.pwd.value == "") {
        alert("비밀번호를 입력해주세요.");
        return false;  // 폼 제출 중단!
    }
    
    return true;  // 모두 OK! 폼 제출 진행!
}
```

**결과**:
- 아이디 `chulsoo` 입력됨 ✓
- 비밀번호 `1234` 입력됨 ✓
- `return true` → 폼 제출 진행!

**비유**:
- 신청서를 제출하기 전에 빈 칸이 없는지 확인하는 것

---

### 📍 5단계: 서버로 데이터 전송

**폼 제출!**
```html
<form name="frm" action="/web-study-09/login.do" method="post">
    <input type="text" name="userid" value="chulsoo">
    <input type="password" name="pwd" value="1234">
</form>
```

**서버로 전송되는 데이터**:
```
POST /web-study-09/login.do
userid=chulsoo&pwd=1234
```

**비유**:
- 작성한 신청서를 담당 선생님께 제출하는 것

---

### 📍 6단계: LoginServlet의 doPost() 실행

**서버에서 일어나는 일**:
```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    // 1. 한글 인코딩
    request.setCharacterEncoding("UTF-8");
    
    // 2. 데이터 받기
    String userid = request.getParameter("userid");  // "chulsoo"
    String pwd = request.getParameter("pwd");        // "1234"
    
    // 다음 단계로 계속...
}
```

**해설**:
- `request.getParameter("userid")`: 폼에서 `name="userid"`인 값 가져오기
- 결과: `userid` 변수에 "chulsoo" 저장됨

**비유**:
- 담당 선생님이 신청서를 받아서 내용을 확인하는 것

---

### 📍 7단계: MemberDAO에 확인 요청

**LoginServlet 계속**:
```java
// 3. DAO 객체 가져오기
MemberDAO dao = MemberDAO.getInstance();

// 4. 로그인 확인 요청
int result = dao.userCheck("chulsoo", "1234");
```

**해설**:
- `getInstance()`: 싱글톤 패턴으로 하나뿐인 DAO 객체 가져오기
- `userCheck()`: "이 아이디와 비밀번호가 맞나요?" 확인 요청

**비유**:
- 담당 선생님이 사서 선생님께 "철수라는 학생 있나요?" 물어보는 것

---

### 📍 8단계: 데이터베이스에서 확인

**MemberDAO의 userCheck() 메서드**:
```java
public int userCheck(String userid, String pwd) {
    int result = -1;  // 기본값: 오류
    
    try {
        // 1. DB 연결
        Connection con = getConnection();
        
        // 2. SQL 쿼리 준비
        String sql = "select pwd from member where userid = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, userid);  // ?에 "chulsoo" 대입
        
        // 3. 쿼리 실행
        ResultSet rs = pstmt.executeQuery();
        
        // 4. 결과 확인
        if(rs.next()) {  // 데이터가 있으면
            String dbPwd = rs.getString("pwd");  // DB의 비밀번호
            
            if(dbPwd.equals(pwd)) {  // 비밀번호 비교
                result = 1;  // 성공!
            } else {
                result = 0;  // 비밀번호 틀림
            }
        } else {
            result = 0;  // 아이디 없음
        }
    } catch(Exception e) {
        result = -1;  // 오류 발생
    }
    
    return result;
}
```

**단계별 설명**:

1. **DB 연결**
```java
Connection con = getConnection();
```
- 데이터베이스와 연결
- 비유: 회원 명부가 있는 창고 문 열기

2. **SQL 쿼리 작성**
```sql
select pwd from member where userid = 'chulsoo'
```
- 의미: member 테이블에서 userid가 'chulsoo'인 행의 pwd(비밀번호) 가져오기
- 비유: 명부에서 철수 찾기

3. **결과 확인**
```
member 테이블:
┌────────┬────────┬──────────┬──────┐
│ userid │  pwd   │   name   │ ...  │
├────────┼────────┼──────────┼──────┤
│chulsoo │  1234  │  김철수   │ ...  │
│younghee│  5678  │  이영희   │ ...  │
└────────┴────────┴──────────┴──────┘
```
- DB에서 찾은 비밀번호: "1234"
- 입력한 비밀번호: "1234"
- 같다! → `result = 1` (성공)

**비유**:
- 사서 선생님이 명부를 뒤져서 "철수 있고, 비밀번호도 맞네요!" 확인하는 것

---

### 📍 9단계: 결과 반환

**MemberDAO → LoginServlet**:
```java
int result = dao.userCheck("chulsoo", "1234");
// result = 1 (성공!)
```

**비유**:
- 사서 선생님이 담당 선생님께 "네, 확인됐습니다!" 보고하는 것

---

### 📍 10단계: 결과에 따라 처리

**LoginServlet 계속**:
```java
if(result == 1) {
    // ★★★ 로그인 성공! ★★★
    
    // 5. 세션 객체 가져오기
    HttpSession session = request.getSession();
    
    // 6. 세션에 아이디 저장
    session.setAttribute("userid", "chulsoo");
    
    // 7. 전체 회원 정보 가져오기
    MemberVO mvo = dao.getMember("chulsoo");
    
    // 8. 세션에 전체 정보 저장
    session.setAttribute("loginUser", mvo);
    
    // 9. 이동할 페이지 설정
    url = "/member/main.jsp";
    
} else if(result == 0) {
    // ✗ 로그인 실패
    request.setAttribute("message", "아이디 또는 비밀번호가 맞지 않습니다.");
    request.setAttribute("userid", "chulsoo");  // 아이디는 유지
    url = "/member/login.jsp";
    
} else {
    // ✗ DB 오류
    request.setAttribute("message", "데이터베이스 오류가 발생했습니다.");
    url = "/member/login.jsp";
}
```

**세션에 저장되는 내용**:
```java
session {
    userid: "chulsoo",
    loginUser: MemberVO {
        name: "김철수",
        userid: "chulsoo",
        email: "chulsoo@example.com",
        phone: "010-1234-5678",
        admin: 0
    }
}
```

**해설**:
- `session.setAttribute()`: 세션에 데이터 저장
- `dao.getMember()`: DB에서 철수의 전체 정보 가져오기
- 이제 세션에 철수의 모든 정보가 저장됨!

**비유**:
- 담당 선생님이 철수에게 임시 출입증(세션)을 발급해주는 것
- 출입증에는 철수의 모든 정보가 적혀있어요

---

### 📍 11단계: 페이지로 이동

**LoginServlet 마지막**:
```java
// 설정한 페이지로 이동
RequestDispatcher dis = request.getRequestDispatcher(url);
dis.forward(request, response);
```

**철수는 성공했으므로**:
```java
url = "/member/main.jsp"
```

**비유**:
- 담당 선생님이 철수를 메인 페이지(도서관 안)로 안내하는 것

---

### 📍 12단계: main.jsp 화면 표시

**main.jsp에서 일어나는 일**:

1. **로그인 체크**
```jsp
<c:if test="${empty loginUser}">
    <jsp:forward page="/login.do" />
</c:if>
```
- 세션에 `loginUser`가 있나요? → 있어요! (11단계에서 저장함)
- 그럼 계속 진행!

2. **환영 메시지 표시**
```jsp
<span>${loginUser.name}</span>님
```
- `${loginUser.name}`: 세션에서 이름 가져오기
- 결과: "김철수님"

3. **회원 정보 표시**
```jsp
<p>ID : <strong>${loginUser.userid}</strong></p>
<p>Email : <strong>${loginUser.email}</strong></p>
```
- 결과:
```
ID : chulsoo
Email : chulsoo@example.com
```

**최종 화면**:
```
┌─────────────────────────────┐
│   반갑습니다!               │
│   김철수님                  │
│   오늘도 멋진 하루 보내세요. │
├─────────────────────────────┤
│ ID : chulsoo                │
│ Email : chulsoo@example.com │
│ Phone : 010-1234-5678       │
│ Admin : 일반회원            │
├─────────────────────────────┤
│ [로그아웃] [회원정보변경]   │
└─────────────────────────────┘
```

**비유**:
- 철수가 도서관 안으로 들어와서 편안하게 책을 읽을 수 있게 됨
- 임시 출입증(세션) 덕분에 다른 페이지도 자유롭게 이동 가능!

---

### 🎯 12단계 요약

```
1. 철수가 login.do 접속
   ↓
2. LoginServlet doGet() → login.jsp 보여주기
   ↓
3. 철수가 아이디/비밀번호 입력
   ↓
4. JavaScript로 빈 칸 체크
   ↓
5. 서버로 데이터 전송 (POST)
   ↓
6. LoginServlet doPost() → 데이터 받기
   ↓
7. MemberDAO에게 확인 요청
   ↓
8. 데이터베이스에서 조회
   ↓
9. 결과 반환 (1=성공)
   ↓
10. 세션에 회원 정보 저장
    ↓
11. main.jsp로 이동
    ↓
12. "김철수님 환영합니다" 표시
```

---

### 💡 로그인 실패 시나리오

만약 철수가 비밀번호를 틀렸다면?

**8단계에서 달라지는 부분**:
```java
String dbPwd = rs.getString("pwd");  // "1234"
if(dbPwd.equals(pwd)) {  // pwd = "0000" (틀림!)
    result = 1;
} else {
    result = 0;  // ✗ 비밀번호 틀림!
}
```

**10단계에서 달라지는 부분**:
```java
if(result == 0) {  // 실패한 경우
    // 에러 메시지 설정
    request.setAttribute("message", "아이디 또는 비밀번호가 맞지 않습니다.");
    
    // 아이디는 유지 (다시 입력 안 해도 됨)
    request.setAttribute("userid", "chulsoo");
    
    // 다시 로그인 페이지로
    url = "/member/login.jsp";
}
```

**12단계에서 달라지는 부분**:
```
┌─────────────────────────────┐
│      로그인                 │
├─────────────────────────────┤
│ 아이디: [chulsoo      ]     │  ← 아이디는 그대로!
│ 비밀번호: [          ]      │  ← 비밀번호만 다시 입력
│                             │
│ ⚠️ 아이디 또는 비밀번호가    │  ← 에러 메시지!
│    맞지 않습니다.            │
│                             │
│  [로그인] [취소] [회원가입]  │
└─────────────────────────────┘
```

---

### 🔐 세션(Session)이란 무엇인가요?

**세션 = 임시 출입증**

**실생활 비유**:
- 놀이공원에 입장하면 손목에 밴드를 채워줍니다
- 이 밴드가 있으면 놀이공원 안의 모든 놀이기구를 탈 수 있어요
- 나갈 때 밴드를 떼어내면 다시 못 들어옵니다

**웹에서의 세션**:
- 로그인하면 서버가 세션을 만들어줍니다
- 세션에 회원 정보를 저장합니다
- 이제 다른 페이지로 이동해도 "이 사람이 누구인지" 알 수 있어요
- 로그아웃하거나 일정 시간이 지나면 세션이 사라집니다

**세션이 없다면?**
```
철수가 main.jsp 접속
→ "너 누구야?" (세션 없음)
→ "로그인부터 해!" (login.do로 강제 이동)
```

**세션이 있다면?**
```
철수가 main.jsp 접속
→ "아, 김철수님이네요!" (세션 확인)
→ "환영합니다!" (페이지 표시)
```

---

### 🎯 핵심 요약

**로그인 과정**:
1. 사용자가 아이디/비밀번호 입력
2. JavaScript로 빈 칸 체크
3. 서버(Servlet)가 데이터 받음
4. DAO가 데이터베이스에서 확인
5. 성공하면 세션에 저장
6. 메인 페이지로 이동

**중요한 개념**:
- **세션**: 로그인 상태를 유지하는 임시 출입증
- **forward**: 서버 내부에서 페이지 이동 (URL 안 바뀜)
- **redirect**: 브라우저에게 "다른 페이지로 가라" 지시 (URL 바뀜)

---

## 5. 회원가입 과정 - 아이디 중복 체크까지

영희가 회원가입을 하는 과정을 따라가봅시다!

**영희의 정보**:
- 이름: 이영희
- 원하는 아이디: `younghee`
- 비밀번호: `5678`
- 이메일: younghee@example.com
- 전화번호: 010-9876-5432

### 📍 1단계: 회원가입 페이지 접속

**영희가 하는 일**:
- login.jsp에서 "회원가입" 버튼 클릭
```jsp
<input type="button" value="회원가입" 
       onclick="location.href='<%=request.getContextPath()%>/join.do'">
```

**JoinServlet doGet() 실행**:
```java
protected void doGet(...) {
    request.getRequestDispatcher("/member/join.jsp")
           .forward(request, response);
}
```

**결과**: join.jsp 화면 표시

---

### 📍 2단계: 정보 입력

**영희가 폼에 입력**:
```
이름: 이영희
아이디: younghee
비밀번호: 5678
비밀번호 확인: 5678
이메일: younghee@example.com
전화번호: 010-9876-5432
등급: (•) 일반회원  ( ) 관리자
```

---

### 📍 3단계: 아이디 중복 체크

**영희가 "중복 체크" 버튼 클릭!**

**join.jsp의 버튼**:
```jsp
<input type="button" value="중복 체크" onclick="idCheck()" />
```

**member.js의 idCheck() 함수 실행**:
```javascript
function idCheck() {
    // 1. 아이디 입력했는지 확인
    var useridValue = document.frm.userid.value;
    if(useridValue == "") {
        alert("아이디를 입력해주세요.");
        return;
    }
    
    // 2. 팝업 URL 만들기
    var url = contextPath + "/idCheck.do?userid=" + useridValue;
    
    // 3. 팝업 창 열기
    window.open(url, "idCheckPopup", "width=450, height=200");
}
```

**결과**:
- 팝업 창이 열림
- URL: `/idCheck.do?userid=younghee`

---

### 📍 4단계: IdCheckServlet 실행

**IdCheckServlet doGet()**:
```java
protected void doGet(...) {
    // 1. 아이디 받기
    String userid = request.getParameter("userid");  // "younghee"
    
    // 2. DAO에게 중복 확인 요청
    MemberDAO dao = MemberDAO.getInstance();
    int result = dao.confirmID(userid);
    
    // 3. 결과를 idCheck.jsp로 전달
    request.setAttribute("userid", userid);
    request.setAttribute("result", result);
    request.getRequestDispatcher("/member/idCheck.jsp")
           .forward(request, response);
}
```

---

### 📍 5단계: 데이터베이스에서 중복 확인

**MemberDAO의 confirmID() 메서드**:
```java
public int confirmID(String userid) {
    try {
        Connection con = getConnection();
        
        // 이 아이디가 있는지 조회
        String sql = "select userid from member where userid = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, userid);  // "younghee"
        
        ResultSet rs = pstmt.executeQuery();
        
        if(rs.next()) {
            // 데이터가 있음 = 이미 있는 아이디
            return 1;  // 중복!
        } else {
            // 데이터가 없음 = 사용 가능한 아이디
            return 0;  // 사용 가능!
        }
    } catch(Exception e) {
        return -1;  // 오류
    }
}
```

**가정**: `younghee`는 아직 없는 아이디
**결과**: `return 0` (사용 가능)

---

### 📍 6단계: 중복 체크 결과 표시

**idCheck.jsp 팝업 창**:
```jsp
<%-- result == 0 (사용 가능) --%>
<c:if test="${result == 0}">
    <div>
        <strong>${userid}</strong>는 사용 가능한 아이디입니다.
        <br><br>
        <input type="button" value="사용" onclick="idok('${userid}')">
    </div>
</c:if>
```

**팝업 창 화면**:
```
┌────────────────────────────┐
│   아이디 중복확인           │
├────────────────────────────┤
│ 아이디: [younghee    ]     │
│         [중복 체크]        │
│                            │
│ ✓ younghee는 사용 가능한   │
│   아이디입니다.             │
│                            │
│         [사용]             │
└────────────────────────────┘
```

---

### 📍 7단계: "사용" 버튼 클릭

**영희가 "사용" 버튼 클릭!**

**member.js의 idok() 함수 실행**:
```javascript
function idok(userid) {
    // 부모 창(회원가입 페이지)의 폼에 아이디 설정
    window.opener.document.frm.userid.value = userid;
    
    // 중복 체크 확인용 hidden 필드에도 설정
    window.opener.document.frm.reid.value = userid;
    
    // 아이디 필드를 읽기 전용으로 변경
    window.opener.document.frm.userid.readOnly = true;
    
    // 팝업 닫기
    window.close();
}
```

**결과**:
- 회원가입 페이지의 아이디 칸이 읽기 전용으로 바뀜
- `reid` hidden 필드에 "younghee" 저장됨 (중복 체크 했다는 증거)
- 팝업 창이 닫힘

**비유**:
- 선생님께 "이 아이디 써도 되나요?" 허락받은 것
- 허락 도장(reid)이 찍혀서 아이디를 못 바꾸게 됨

---

### 📍 8단계: "확인" 버튼 클릭

**영희가 모든 정보를 입력하고 "확인" 버튼 클릭!**

**join.jsp의 버튼**:
```jsp
<input type="submit" value="확인" onclick="return joinCheck()" />
```

**member.js의 joinCheck() 함수 실행**:
```javascript
function joinCheck() {
    // 1. 이름 체크
    if (document.frm.name.value.length == 0) {
        alert("이름을 써주세요.");
        return false;
    }
    
    // 2. 아이디 체크
    if (document.frm.userid.value.length < 4) {
        alert("아이디는 4글자 이상이어야 합니다.");
        return false;
    }
    
    // 3. 비밀번호 체크
    if (document.frm.pwd.value == "") {
        alert("암호는 반드시 입력해야 합니다.");
        return false;
    }
    
    // 4. 비밀번호 일치 체크
    if (document.frm.pwd.value != document.frm.pwd_check.value) {
        alert("암호가 일치하지 않습니다.");
        return false;
    }
    
    // 5. 중복 체크 했는지 확인
    if (document.frm.reid.value.length == 0) {
        alert("중복 체크를 하지 않았습니다.");
        return false;
    }
    
    return true;  // 모두 OK!
}
```

**검증 결과**:
- 이름: "이영희" ✓
- 아이디: "younghee" (4글자 이상) ✓
- 비밀번호: "5678" ✓
- 비밀번호 확인: "5678" (일치) ✓
- reid: "younghee" (중복 체크 함) ✓

**모두 통과! → 폼 제출 진행**

---

### 📍 9단계: JoinServlet doPost() 실행

**서버로 전송되는 데이터**:
```
POST /web-study-09/join.do
name=이영희
userid=younghee
pwd=5678
pwd_check=5678
email=younghee@example.com
phone=010-9876-5432
admin=0
reid=younghee
```

**JoinServlet doPost()**:
```java
protected void doPost(...) {
    // 1. 데이터 받기
    String name = request.getParameter("name");
    String userid = request.getParameter("userid");
    String pwd = request.getParameter("pwd");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String adminStr = request.getParameter("admin");
    String reid = request.getParameter("reid");
    
    // 2. 중복 체크 확인 (한 번 더!)
    if(reid == null || !reid.equals(userid)) {
        request.setAttribute("message", "아이디 중복 체크를 해주세요.");
        // 다시 회원가입 페이지로
        return;
    }
    
    // 3. admin 문자열을 숫자로 변환
    int admin = Integer.parseInt(adminStr);  // "0" → 0
    
    // 4. VO 객체에 담기
    MemberVO mvo = new MemberVO();
    mvo.setName(name);
    mvo.setUserid(userid);
    mvo.setPwd(pwd);
    mvo.setEmail(email);
    mvo.setPhone(phone);
    mvo.setAdmin(admin);
    
    // 5. DAO를 통해 DB에 저장
    MemberDAO dao = MemberDAO.getInstance();
    int result = dao.insertMember(mvo);
    
    // 6. 결과에 따라 처리
    if(result == 1) {
        // 성공!
        request.setAttribute("message", "회원 가입이 완료되었습니다.");
        url = "/login.do";
    } else {
        // 실패!
        request.setAttribute("message", "가입 실패");
        url = "/member/join.jsp";
    }
    
    request.getRequestDispatcher(url).forward(request, response);
}
```

---

### 📍 10단계: 데이터베이스에 저장

**MemberDAO의 insertMember() 메서드**:
```java
public int insertMember(MemberVO mvo) {
    try {
        Connection con = getConnection();
        
        // INSERT 쿼리
        String sql = "insert into member values (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        
        // 값 설정
        pstmt.setString(1, mvo.getName());     // "이영희"
        pstmt.setString(2, mvo.getUserid());   // "younghee"
        pstmt.setString(3, mvo.getPwd());      // "5678"
        pstmt.setString(4, mvo.getEmail());    // "younghee@example.com"
        pstmt.setString(5, mvo.getPhone());    // "010-9876-5432"
        pstmt.setInt(6, mvo.getAdmin());       // 0
        
        // 쿼리 실행
        int result = pstmt.executeUpdate();
        
        return result;  // 1 (성공)
        
    } catch(Exception e) {
        e.printStackTrace();
        return -1;  // 실패
    }
}
```

**데이터베이스 테이블**:
```sql
member 테이블에 새 행 추가:
┌──────────┬────────┬────────┬───────────────────────┬──────────────┬───────┐
│   name   │ userid │  pwd   │        email          │    phone     │ admin │
├──────────┼────────┼────────┼───────────────────────┼──────────────┼───────┤
│  이영희    │younghee│  5678  │younghee@example.com   │010-9876-5432 │   0   │
└──────────┴────────┴────────┴───────────────────────┴──────────────┴───────┘
```

**비유**:
- 회원 명부에 영희의 이름이 등록됨!

---

### 📍 11단계: 로그인 페이지로 이동

**JoinServlet 마지막 부분**:
```java
if(result == 1) {
    request.setAttribute("message", "회원 가입이 완료되었습니다. 로그인해주세요.");
    url = "/login.do";
}
```

**결과**: LoginServlet doGet() → login.jsp 표시

---

### 📍 12단계: 완료 메시지 표시

**login.jsp 화면**:
```
┌─────────────────────────────────┐
│      로그인                     │
├─────────────────────────────────┤
│ 아이디: [              ]        │
│ 비밀번호: [              ]        │
│                                 │
│ ✓ 회원 가입이 완료되었습니다.    │
│   로그인해주세요.                │
│                                 │
│  [로그인] [취소] [회원가입]      │
└─────────────────────────────────┘
```

**영희는 이제 로그인할 수 있어요!**

---

### 🎯 회원가입 과정 요약

```
1. 회원가입 페이지 접속 (join.do)
   ↓
2. 정보 입력 (이름, 아이디, 비밀번호 등)
   ↓
3. "중복 체크" 버튼 클릭
   ↓
4. IdCheckServlet 실행
   ↓
5. DAO가 DB에서 아이디 확인
   ↓
6. "사용 가능" 결과 표시
   ↓
7. "사용" 버튼 → reid 설정, 팝업 닫기
   ↓
8. "확인" 버튼 → joinCheck() 검증
   ↓
9. JoinServlet doPost() 실행
   ↓
10. DAO가 DB에 저장
    ↓
11. 로그인 페이지로 이동
    ↓
12. "가입 완료" 메시지 표시
```

---

### 💡 왜 중복 체크가 필요한가요?

**중복 체크 안 하면?**
```
영희: 아이디 "chulsoo" 사용하고 싶어요
서버: (DB 저장 시도)
DB: "chulsoo는 이미 있는데요? 저장 불가!"
영희: "헉! 다시 처음부터..."
```

**중복 체크 하면?**
```
영희: 아이디 "chulsoo" 사용하고 싶어요
     (중복 체크 클릭)
서버: "chulsoo는 이미 있어요!"
영희: "그럼 다른 아이디로..." (바로 수정 가능)
```

---

### 🔐 보안: reid 필드의 역할

**왜 reid가 필요한가요?**

**없다면?**
```
1. 영희가 중복 체크 ("younghee" 사용 가능 확인)
2. 영희가 아이디를 "chulsoo"로 바꿈 (중복 체크 안 함)
3. 서버는 모름 → DB 저장 시도
4. DB: "chulsoo 이미 있음!" 오류 발생
```

**있다면?**
```
1. 영희가 중복 체크 ("younghee" 사용 가능 확인)
2. reid에 "younghee" 저장됨
3. 아이디 필드가 읽기 전용으로 바뀜 (수정 불가)
4. 서버가 reid와 userid 비교 → 일치해야 진행
```

**비유**:
- reid = 선생님의 허락 도장
- 도장 없으면 제출 안 받아줌!

---

(part 2 에서 계속)

