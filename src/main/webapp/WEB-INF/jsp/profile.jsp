<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="home" value="/aaa" />
<c:url var="profile" value="/profile.js" />

<div id="profile">
    <div class="container" v-if="(isLoggedIn)">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="">Edit CV</a>
            <a class="navbar-brand" href="">Create a person</a>
            <button v-on:click="logoutUser()">Logout</button>
<%--            <a class="navbar-brand" href="/logout">Logout</a>--%>
        </nav>
        <h1>{{username}}</h1>
        <div v-if="(cv != null)">
            <h1>CV</h1>
            <table class="table">
                <tr>
                    <th>{{cv.cvName}}</th>
                    <th>voir CV </th>
                </tr>
                <tr>
                    <td></td>
                    <td><button v-on:click="getCvActivities(1)">Montrer</button></td>
                </tr>
            </table>
            <h1>Liste des activities</h1>
            <table class="table">
                <tr>
                    <th>Titre</th>
                    <th>Description</th>
                    <th>Site</th>
                    <th>Annee</th>
                </tr>
                <tr v-for="activity in cv.activities">
                    <td>{{activity.title}}</td>
                    <td>{{activity.description}}</td>
                    <td>{{activity.website}}</td>
                    <td>{{activity.year}}</td>
                </tr>
            </table>
        </div>

    </div>
    <div class="container" v-if="(!isLoggedIn)">
        <h3>You're not authenticated, please sign in</h3><br>
        <button class="w-100 btn btn-sm btn-primary" type="submit"><a href="/login" style="color: white">Sign in</a></button>
    </div>



</div>
<script src="${profile}"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>