<%--
  Created by IntelliJ IDEA.
  User: a18013526
  Date: 06/11/2022
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="home" value="/aaa" />
<c:url var="login" value="/login.js" />

<div id="login">
    <nav class="navbar navbar-dark bg-dark">
        <a class="btn btn-outline-light" href="/app">home</a>
        <a class="btn btn-outline-success" href="/login">Sign In</a>
    </nav>
    <br>
    <div class="d-flex justify-content-center" >
        <form method="post" @submit.prevent="authUser(user)">
            <h1 class="h3 mb-3 fw-normal">Sign in</h1>
            <div class="form-group row">
                <div class="col-xs-4">
                    <input type="text" class="form-control" id="floatingInput" placeholder="Username" v-model="user.username"  name="username" required>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-xs-4">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password" v-model="user.password" name="password" required>
                </div>
            </div>
            <div class="form-group row">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Sign in</button>
            </div>


        </form>
        <div v-if="(isLoggedIn)">
            <h3>User Authenticated</h3>
        </div>
    </div>


</div>
<script src="${login}"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>