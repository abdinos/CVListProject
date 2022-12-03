<%--
  Created by IntelliJ IDEA.
  User: a18013526
  Date: 06/11/2022
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="home" value="/aaa" />
<c:url var="app" value="/app.js" />

<div id="myApp">
    <div >
        <nav class="navbar navbar-dark bg-dark">
            <a class="btn btn-outline-light" href="/cvList">Resumes</a>
            <a class="btn btn-outline-success" href="/login">Sign In</a>
        </nav>
    </div><br>

    <div class="d-flex justify-content-center">
        <div>
            <h3 style="align-content: center">Welcome to our CV Manager</h3>
            <img src="image.png" alt="image">

        </div>
    </div>
</div>
<script src="${app}"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>