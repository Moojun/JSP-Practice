# Youtube 뉴렉처 JSP 강의 내용
> reference link: [GitHub-1](https://github.com/tmedcount/newLecture-JSP), [GitHub-2](https://github.com/jihoa/newlecture_servlet/commits/main?after=19c172516102d88672b972c454e468379b4a7797+34&branch=main&qualified_name=refs%2Fheads%2Fmain)

<br>

### MacOS] JSP 실습환경 구축(tomcat, Intellij)

> [velog-moojun3](https://velog.io/@moojun3/MacOS-JSP-%EC%8B%A4%EC%8A%B5%ED%99%98%EA%B2%BD-%EA%B5%AC%EC%B6%95tomcat-Intellij)

<br>


### 강의 참고사항(11강)

* Java Resources  -> src/main/java, WebContent -> webapps 로 기본 설정이 변경 됨. 
* web.xml을 강의에서 처럼 복붙한 경우 web.xml에서 Nana 관련 내용을 삭제해야 한다. 
* 프로젝트 이름에 우클릭 해야 하며 source folder는 src/main/java이다. (강의: src 폴더에 우클릭) - 우클릭 후 New>Servlet으로 해야한다. (강의: New >. Class) 
* Nana Servlet을 호출하는 url 변경 시 web.xml에서 url-pattern 태그를 수정한다.



### 강의 참고사항(17강)
* [Get vs Post 차이점 참고 링크](https://velog.io/@songyouhyun/Get%EA%B3%BC-Post%EC%9D%98-%EC%B0%A8%EC%9D%B4%EB%A5%BC-%EC%95%84%EC%8B%9C%EB%82%98%EC%9A%94)


### 강의 참고사항(25강)
* 상태 유지를 위한 5가지 방법
  * application
  * session
  * cookie
  * hidden input
  * querystring

### 강의 참고사항(28강. WAS가 현재 사용자(Session)을 인식하는 방식)

* 같은 브라우저(Chrome, etc) 이면 동일한 Session 값을 가진다. 하지만 브라우저를 닫으면 값이 사라진다. 다시 열면 SID가 사라진다. 
* 다른 브라우저의 경우(ex. Chrome vs FireFox), SessionID 값이 다르다.



세션 메소드

```java
void setAttribute(String name, Object value) // 지정된 이름으로 객체를 설정
Object getAttribute(String name) // 지정된 이름의 객체를 반환
void invalidate() // 세션에서 사용되는 객체들을 바로 해제. 저장소를 비울 때 사용되는 메소드이다.
  
void setMaxInactiveInterval(int interval) // 세션 타임아웃을 정수(초)로 설정
boolean isNew() // 세션이 새로 생성되었는지를 확인
Long getCreationTime() // 세션이 시작된 시간을 반환. 1970년 1월 1일을 시작으로 하는 밀리초
long getLastAccessedTime() // 마지막 요청 시간. 1970년 1월 1일을 시작으로 하는 밀리초
  
  
```