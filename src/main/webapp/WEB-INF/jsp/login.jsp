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
        <form @submit.prevent="login">

            <div class="container">
                <div>
                    <label for="username"><b>Username</b></label>
                    <input type="text" placeholder="Enter Username"  v-model="user.username" name="username" required>
                </div>
                <div>
                    <label for="password"><b>Password</b></label>
                    <input type="password" placeholder="Enter Password" v-model="user.password" name="password" required>
                </div>
                <button type="submit" >Login</button>
            </div>
        </form>
    </div>
</div>
<script src="${app}"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>