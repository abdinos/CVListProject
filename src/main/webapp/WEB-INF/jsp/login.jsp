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
    <main class="form-signin w-100 m-auto">
        <form method="post" @submit.prevent="authUser(user)">
            <h1 class="h3 mb-3 fw-normal">Please sign in XXXXXXXXXX</h1>
            <div class="form-floating">
<%--                <label for="floatingInput">Username</label>--%>
                <input type="text" class="form-control" id="floatingInput" placeholder="Username" v-model="user.username"  name="username" required>
            </div>
            <div class="form-floating">
<%--                <label for="floatingPassword">Password</label>--%>
                <input type="password" class="form-control" id="floatingPassword" placeholder="Password" v-model="user.password" name="password" required>
            </div>

            <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
        </form>
        <div v-if="(isLoggedIn)">
            <h3>User Authenticated</h3>
        </div>
    </main>


</div>
<script src="${login}"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>