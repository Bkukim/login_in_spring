<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-13
  Time: 오후 5:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!--    bootstrap css cdn -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <%--    main.css import --%>
    <link href="/resources/css/main.css" rel="stylesheet">
    <%--    main.css import --%>
    <link href="/resources/css/login.css" rel="stylesheet">
</head>
<body>
<%-- 부트스트랩 메뉴 --%>
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <%--     1번째 메뉴 : Navbar -> Simple   --%>
        <a class="navbar-brand" href="/">
            <%--     TODO: 이미지 넣기 --%>
            <img src="/resources/img/simple-coding.png" width="20" height="20"/>
            Home
        </a>
        <%-- 부트스트랩 메뉴 : 화면이 작아지면 단축메뉴로 바뀜 : 아래 태그 --%>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <%--  메뉴 시작  --%>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <%--좌측메뉴 : me-auto(오른쪽에 자동으로 마진 부여 : 좌측에 붙임--%>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <%--            부서 #1 --%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Dept(부서)
                    </a>
                    <%--                   소메뉴 --%>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/basic/dept">Dept</a></li>
                        <li><a class="dropdown-item" href="/basic/dept/addition">Add Dept</a></li>

                    </ul>
                </li>
                <%--            사원 #2 --%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Emp(사원)
                    </a>
                    <%--                   소메뉴 --%>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/basic/emp">Emp</a></li>
                        <li><a class="dropdown-item" href="/basic/emp/addition">Add Emp</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Faq
                    </a>
                    <%--                   소메뉴 --%>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/basic/faq">Faq</a></li>
                        <li><a class="dropdown-item" href="/basic/faq/addition">Add Faq</a></li>

                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        File Db
                    </a>
                    <%--                   소메뉴 --%>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/advanced/fileDb">File Db</a></li>
                        <li><a class="dropdown-item" href="/advanced/fileDb/addition">File Db add</a></li>

                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Gallery
                    </a>
                    <%--                   소메뉴 --%>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/advanced/gallery">Gallery</a></li>
                        <li><a class="dropdown-item" href="/advanced/gallery/addition">Gallery add</a></li>

                    </ul>
                </li>
            </ul>
            <%--우측메뉴 : ms-auto(왼쪽에 자동으로 마진 부여 : 우측에 붙임--%>
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <%--소메뉴 : Admin 관리자면 이 메뉴 보이고 사용자는 안 보임--%>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Admin</a>
                </li>
                <%--회원가입 #2 : register--%>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/auth/register">회원가입</a>
                </li>
                    <%-- Login #3 : Login--%>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/auth/customLogin">Login</a>
                </li>
                    <%-- Logout #4 : Logout : 이동하는 페이지가 없고 바로 실행 되야 하므로 action이 필요 form으로 해야한다.--%>
                <li class="nav-item">
                <form method="post" action="/auth/customLogout" >
                    <button class="btn" aria-current="page" type="submit">Logout</button>
                </form>
                </li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
