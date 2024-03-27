<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2024-03-27
  Time: 오전 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--todo 머리말--%>
<jsp:include page="../common/header.jsp"/>
<%--todo 본문 : 부트스트랩 테마에서 가져온것 (mit 라이센스는 무료이다.)--%>
<div content="container">
    <div class="row justify-content-center">
        <div class="col-xl-10 col-lg-12 col-md-9">
            <div class="card mt-5">
                <div class="card-body p-0">
                    <!-- {/* Nested Row within Card Body */} -->
                    <div class="row">
                        <div class="col-lg-6 bg-login-image"></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 mb-4">Welcome Back!</h1>
                                </div>
                                <%--todo : 백엔드 전송(id/pw)--%>
                                <form class="user" action="/auth/login" method="post">
                                    <%--todo : email입력--%>
                                    <div class="form-group">
                                        <input
                                                type="email" <%--type을 email로 해주면 email이 아니고 다른 것이 입력되면 경고가 뜬다.--%>
                                                class="form-control form-control-user mb-3"
                                                id="email"
                                                aria-describedby="emailHelp"
                                                placeholder="Enter Email Address..."
                                                name="username" <%--todo 우리는 email로 설정했지만 스프링 시큐리티에서 username으로 사용하기때문에 이렇게 적어주어야힌다.--%>
                                        />
                                    </div>
                                    <%--todo : pw입력--%>
                                    <div class="form-group">
                                        <input
                                                type="password"
                                                class="form-control form-control-user mb-3"
                                                id="password"
                                                placeholder="password"
                                                name="password"
                                        />
                                    </div>
                                    <%--todo : 로그인 버튼--%>
                                    <button class="btn btn-primary btn-user w-100 mb-3" type="submit">Login</button>

                                    <hr/>
                                    <a href="/" class="btn btn-google btn-user w-100 mb-2">
                                        <i class="fab fa-google fa-fw"></i>&nbsp;Login with
                                        Google
                                    </a>
                                    <a href="/" class="btn btn-naver btn-user w-100 mb-2">
                                        <i class="fa-solid fa-n"></i>&nbsp;Login with Naver
                                    </a>
                                    <a href="/" class="btn btn-kakao btn-user w-100 mb-3">
                                        <i class="fa-solid fa-k"></i>&nbsp;Login with Kakao
                                    </a>
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                </form>
                                <hr/>
                                <div class="text-center">
                                    <a class="small" href="">
                                        Forgot Password?
                                    </a>
                                </div>
                                <div class="text-center">
                                    <a class="small" href="">
                                        Create an Account!
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--todo 꼬리말--%>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>
