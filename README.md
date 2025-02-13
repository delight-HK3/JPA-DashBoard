# Spring Data JPA 게시판
```
개발환경

Framework : Spring boot 3.3.6
Language : Java 17
Database : MariaDB 10.7 
Server : localhost
Tool : Visual Studio Code
```
### JPA-DashBoard 에서 사용한 JPA 기술
- JPQL (EntityManager)
- QueryDSL
- Spring Data JPA (JPARepository)

<br>

(만약 프로젝트를 실행시키고 싶으면 하단의 application.properties를 생성해주시기 바랍니다.)
```

```

### 📔 구현한 기능
- 유저등록 (2025.01.28 완료)
- 로그인기능 (2025.01.28 완료)
- 세션 등록 및 구분 (2025.01.28 완료)
- 게시글 조회(페이지네이션) (2025.02.04 완료)
- 게시글 등록 (2025.01.30 완료)
- 게시글 상세보기 (2025.01.31 완료)
- 게시글 삭제 (2025.02.06 완료)
<br>

### 📔 앞으로 구현할 기능
- 유저등록시 아이디, 닉네임 중복체크
- 게시글 수정
- 게시글 검색기능
- 게시글 수정일 추가
- 게시글 좋아요 기능

<br>

### 📰 개발노트
---
#### 동시성 문제 해결방안 고민 (2025.02.13)
게시글 조회를 구현하고 결과를 보니 동시성 문제가 생길 가능성을 확인, 그래서 문제해결 및 신규기술 사용목적으로 메모리 데이터베이스인 Redis도입 결정, Redis를 통한 게시글 조회수 동시성 문제를 완화한다면
좋아요 기능에도 도입할계획
<br><br>


---
#### 게시글 좋아요 및 좋아요 체크 방식 고민 (2025.02.11)
게시글에 좋아요기능 구현 목적을 위해 Likes엔티티를 추가하여 게시글 비즈니스로직 구현은 완료했으나 UI설계 및 계정마다 어떤 게시글에 좋아요를 클릭했는지 확인하는 기능이 필요
그래서 다음과 같은 방법을 생각했다.

> 참고 게시글 : https://velog.io/@korea3611/Spring-Boot게시글-좋아요-기능-만들기

<br>

**1번 방법**

![image](https://github.com/user-attachments/assets/f0155e57-b98d-4b89-b879-a2a05e84d360)

요청은 많아지지만 기존에 코드수정양이 적다는 장점이 있다.

**2번 방법**

![image](https://github.com/user-attachments/assets/9ba38cf7-2efd-48c0-b610-e30e65ad5013)

테이블 2개를 Join함으로써 요청 1번으로 작업을 끝낼 수 있지만 코드수정이 많아진다.

<br>

**선택한 방법**

결국 1번 방법을 선택하게 되었다 그이유는 2번 방법을 시도한 결과 게시글 조회, 
좋아요 여부 및 로그인을 안한 유저의 경우를 생각하게 되었고 QueryDSL의 코드가 복잡해지는 결과가 나왔다. 그래서 1번 방법을 해본결과 예상대로 DB호출은 많아졌지만 로그인 여부에 따른 구분을 하기가 쉬워지는 장점도 얻을 수 있었다.

<br><br>


--- 
#### 세션 여부에 따른 화면처리 (2025.01.27)
로그인 여부에 따라 페이지 분할이 필요함을 느껴 페이지 마다 세션을 체크할 수 없기에 filter를 도입 세션이 없거나 세션의 특정 속성값이 null이면 필터적용 대상인데 만약 필터 조건에 해당되면 메인페이지로 이동
<br><br>

**화이트 리스트**
```java
// 화이트 리스트에 등록된 URL은 세션 필터대상에서 제외
private final String[] whitelist = {"/", "/user/*", "/login", "/board/list"
                                          , "/board/detail", "/js/*","/css/*"};

```

**filter 처리코드**
```java
if (isLoginCheckPath(requestURI)) {
    //log.info("인증 체크 로직 실행{}", requestURI);
    HttpSession session = httpRequest.getSession(false); // false로 해도 상관 없고 true도 상관이 없다.
                
    // 세션이 없거나 세션 속성중 id가 없는 경우
    if (session == null || session.getAttribute("nickName") == null) {
        // 로그인이 필요하다는 alert메세지를 만드는 부분
        response.setContentType("text/html; charset=utf-8");
        PrintWriter w = response.getWriter();
        w.write("<script>alert('로그인이 필요합니다.');location.href='/board/list';</script>");
        w.flush();
        w.close();

        return; //여기가 중요, 미인증 사용자는 다음으로 진행하지 않고 끝!
    }
}

chain.doFilter(request, response); // 다음 필터 진행. 없다면 서블릿 띄우기

```

**입력된 URL체크 메서드**
```java
private boolean isLoginCheckPath(String requestURI) {
    // 입력된 url이 whiteList에 포함되어있는지 확인
    return !PatternMatchUtils.simpleMatch(whitelist, requestURI); 
}
```



