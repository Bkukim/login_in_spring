<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2024-03-22
  Time: 오전 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>

</head>
<body>
<jsp:include page="common/header.jsp"/>
<h2>따균이 더 바보</h2>
<br>
<br>
<h2>기타 JSP 사용법 소개</h2>
<h4>1) el & jstl 표현식 :</h4>
<h5>(1) 변수에 값을 저장하는 방법</h5>
<%--    jsp 범위(scope) 객체 : 변수의 사용 범위를 정하는 객체
        예) 다른 페이지에서도 현재 변수를 사용할 수 있는가?
        변수 저장소(사용범위:scope) :
            page(현재페이지) < request(모든페이지) < session(모든페이지) < application(전체프로그램)
 --%>
<%--   TODO: 사용법 : <c:set var="변수명" value="값" scope="jsp객체" />--%>
<p><c:set var="num" value="10" scope="page" /></p>
<p>${num}</p>

<br>
<h5>(2) 문자열 구분자로 화면 출력하기</h5>
<%--    예) 1/2/3 => / 구분자로 출력 --%>
<%--  TODO: 사용법 : 구분자로 잘려서 반복 출력됨
        <c:forTokens items="문자열" delims="구분자" var="변수명">
           화면출력 태그(반복됨)
        </c:forTokens>
--%>
<c:forTokens items="1/2/3" delims="/" var="number">
    <p>토큰 : ${number}</p>
</c:forTokens>
<h5>(3) 리다이렉트(강제 이동) : jsp 사용법 </h5>
<%--    if문(조건문)과 함께 사용 --%>
<%--   TODO: 사용법 : <c:redirect url="이동url" />  --%>
<%--    <c:redirect url="/basic/dept" />--%>

<%--    TODO: 2. fn(function) 태그 라이브러리 사용 --%>
<c:set var="chars" value="안녕하세요 홍길동입니다." />
<c:set var="name" value="홍길동" />
<h5>(1) chars 에 name 의 값이 있으면 true , 없으면 false</h5>
<%--    TODO: 사용법 : ${fn:contains(문자열, 문자열2)}  --%>
<h5>contains : ${fn:contains(chars, name)}</h5>

<h5>(2) 전체 길이를 리턴</h5>
<%--    TODO: 사용법 : ${fn:length(문자열)} --%>
<h5>length   : ${fn:length(chars)}</h5>

<h5>(3) chars 문자열에 "홍길동" 값이 있는 index 번호 리턴</h5>
<%--    TODO: 사용법 : ${fn:indexOf(문자열, 찾을문자열)} --%>
<h5>indexOf   : ${fn:indexOf(chars, "홍길동")}</h5>

<%--    TODO: 3) 태그 fmt(format) 라이브러리 --%>
<c:set var="numtest" value="1234567890" />
<h5>(1) 숫자 찍기 : 3자리마다 쉼표 추가 </h5>
<h5><fmt:formatNumber value="${numtest}" /> </h5>

<%--    TODO: jsp 과거 코딩 : 스크립틀릿 (가독성 떨어짐)
                        -> el & jstl 태그 사용(권고)
--%>
<h2> jSP 과거 코딩 : 스크립틀릿 (참고) </h2>
<%--    1) 변수 저장 --%>
<%
    String str = "abcde";
    int num2   = 10;
%>
<%--    2) 화면 출력--%>
<h5>화면출력</h5>
<h5><%=str%></h5>
<h5><%=num2%></h5>

<%--    3) 조건문 --%>
<%--    예제: 값이 10보다 크면 큽니다 , 아니면 아닙니다 --%>
<%
    int num3 = 20;

    if(num3 > 10) {
%>
<p>10 보다 큽니다.</p>
<%
} else {
%>
<p>10 보다 작습니다.</p>
<%
    }
%>
<%--    4) 반복문 --%>
<%--    예제 : 0 ~ 3까지 반복 출력 --%>
<%
    for(int i=0; i< 3; i++) {
%>
<p><%=i%></p>
<%
    }
%>

</div>
<%--    꼬리말--%>
<jsp:include page="common/footer.jsp" />

</body>
</html>
