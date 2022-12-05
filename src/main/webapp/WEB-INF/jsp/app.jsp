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
    <div class="bg-image" style="background-image: url('https://wallpaper.dog/large/20555764.jpg') ;background-position: center;background-repeat: no-repeat;">
        <nav class="navbar navbar-dark bg-dark">
            <a class="btn btn-outline-light" href="/cvList">Resumes</a>
            <a class="btn btn-outline-success" href="/login">Sign In</a>
        </nav>
    </div><br>

    <div class="d-flex justify-content-center">
        <div>
            <h3 style="align-content: center">CV Manager</h3>
            <div class="input-group">
                <form action="/result/find" id="searchForm" >
                    <%--                    <button class="btnSearchInput" type="button"><i class="fas fa-search"></i></button>--%>
                    <input  class="form-control" type="search" placeholder="Rechercher..." aria-label="Search" name="name">
                </form>
                <button type="button" class="btn btn-primary">
                    <i class="fas fa-search"></i>
                </button>
            </div>
        </div>
    </div>
</div>
<script src="${app}"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>