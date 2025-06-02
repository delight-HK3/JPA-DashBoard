# Spring Data JPA 게시판
```
기술 스택

Framework : Spring boot 3.3.6
Language : Java 17
Database : MariaDB 10.7
ORM : Spring Data JPA, QueryDSL
Tool : Visual Studio Code
```

<br>

(만약 프로젝트를 실행시키고 싶으면 properties.txt의 내용을 src/main/resources 위치에 application.properties파일을 생성하고 copy & paste 해주시기 바랍니다.)

### 📔 구현한 기능들
- 유저등록 (2025.01.28 완료)
- 로그인기능 (2025.01.28 완료)
- 세션 등록 및 구분 (2025.01.28 완료)
- 게시글 조회(페이지네이션) (2025.02.04 완료)
- 게시글 등록 (2025.01.30 완료)
- 게시글 상세보기 (2025.01.31 완료)
- 게시글 삭제 (2025.02.06 완료)
- 게시글 수정 (2025.02.06 완료)
  
<br>

### 📔 앞으로 구현 및 추가예정 기능들
- 유저등록시 아이디, 닉네임 중복체크
- 게시글 검색기능
- 게시글 수정일 컬럼추가
- 게시글 좋아요 기능

<br>

### 📰 개발노트
---
#### Redis, cache 도입 고민 (2025.06.02)
이것과 별개의 프로젝트를 진행하면서 Redis를 도입해야할거 같은 상황은 여러번 있었던거 같다. 이전에도 여러번 시도를 해봤지만 잘되지 않았다.
하지만 이번에는 새로운 방법을 찾았다. Redis 뿐만 아니라 cache를 도입하는 방법인데 찾아본 결과 복잡한 작업이 아닌 단순 반복작업이라면 Redis와 cache를 사용하는 방법을
추천받았고 그래서 시도해볼 생각이다.
<br><br>

---
#### 동시성 문제 해결방안 고민 (2025.04.16)
4월 7일에 동시성문제를 해결하고자 낙관적 락을 도입했고 테스트결과 성공은 했으나 만약 트래픽이 많아지는 상황이라면 다음과 같이 변경할 거 같다.
1. 큐(Queue) 기반 처리
2. Kafka 혹은 Redis도입 (트래픽이 많아지는 경우)

#### 동시성 문제 해결방안 고민 (2025.04.07)
게시글 조회 및 좋아요 기능을 제작함에 있어서 2월 13일에는 Redis를 도입하고자 했고 실제로 Redis를 사용해보았으나 현재로써는 기술적 한계로인해 원래 계획한 낙관적 락 처리 방식으로 변경

#### 동시성 문제 해결방안 고민 (2025.02.13)
게시글 조회를 구현하고 결과를 보니 동시성 문제가 생길 가능성을 확인, 그래서 문제해결 목적으로 메모리 데이터베이스인 Redis도입 결정, Redis를 통한 게시글 조회수 동시성 문제를 완화한다면
좋아요 기능에도 도입할 계획이다.
(솔직히 대용량 트래픽 처리를 해야하는 상황이 아니면 현재 프로젝트기준으로 비관적 Lock을 걸어주어 해결하는 방법을 사용하면 되지만 Redis의 우수성을 확인해보고자 Redis를 선택했습니다.)
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



