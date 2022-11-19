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



<br>



### 강의 참고사항(17강)
* [Get vs Post 차이점 참고 링크](https://velog.io/@songyouhyun/Get%EA%B3%BC-Post%EC%9D%98-%EC%B0%A8%EC%9D%B4%EB%A5%BC-%EC%95%84%EC%8B%9C%EB%82%98%EC%9A%94)



<br>




### 강의 참고사항(25강)
* 상태 유지를 위한 5가지 방법
  * application
  * session
  * cookie
  * hidden input
  * querystring

<br>



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



### 강의 참고사항(32강. Application/Session/Cookie 정리)

###### application

* 사용범위: ***전역 범위*** 에서 사용하는 저장 공간
* 생명주기: ***WAS(Web Application Service)*** 가 시작해서 종료할 때 까지
* 저장위치: WAS 서버의 메모리

###### session

* 사용범위: ***세션 범위*** 에서 사용하는 저장 공간
* 생명주기: ***세션*** 이 시작해서 종료할 때 까지
* 저장위치: WAS 서버의 메모리

###### cookie

* 사용범위: ***Web Browser*** 별 지정한 ***path 범주*** 공간
* 생명주기: ***Browser에 전달한 시간*** 부터 ***만료시간*** 까지
* 저장위치: Web Browser의 메모리 또는 파일



저장 기간이 긴 경우, 특정 url(범위) 에서만 사용하는 경우는 cookie를 사용한다.



### 강의 참고사항(57강). EL의 키워드에 관해

```jsp
<%
// list라는 녀석을 사용할수 없다(지역변수가 없기때문에). request라는 녀석에게서 꺼내온다(getAttribute). Object로 뽑아지는 녀석을 list로 형변환해준다.
List<Notice> list = (List<Notice>)request.getAttribute("list");					
for(Notice n : list) {
  	pageContext.setAttribute("n", n);
%>


<!-- 
${n.id}: EL에서 사용하는 n은 저장소에 담겨져 있는 키워드이다. 지역변수 n이 아니다. 
따라서 위에서 pageContext.setAttribute("n", n)을 통해 page 범위 저장소에 담아준다. 
-->
<tr>													
	<td>${n.id}</td>										
	<td class="title indent text-align-left"><a href="detail?id=${n.id}">${n.title}</a></td>
	<td>${n.writerId}</td>											
	<td>${n.regdate}</td>									
	<td>${n.hit}</td>											
</tr>
```



### 강의 참고사항(58강). View page 은닉하기

* WEB-INF 하위에 .jsp 파일을 둔다. 



### 강의 참고사항(61강). 중간 정리

* 이미지: 유튜브 강의 참고

<img width="889" alt="Screenshot 2022-11-14 at 2 17 34 PM" src="https://user-images.githubusercontent.com/80478750/201817381-01f8d034-1e26-45db-a1fc-fdc1961a8e38.png">
