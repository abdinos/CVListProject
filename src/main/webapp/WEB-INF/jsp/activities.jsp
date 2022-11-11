<%--
  Created by IntelliJ IDEA.
  User: a18013526
  Date: 11/11/2022
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="home" value="/aaa" />
<c:url var="app" value="/app.js" />

<div id="myApp">
    <div class="container">
        <h1> My activities </h1>
        <h1>Liste des activities</h1>
        <table class="table">
            <tr>
                <th>Titre</th>
                <th>Description</th>
                <th>Site</th>
                <th>Annee</th>
            </tr>
            <tr v-for="activity in activities">
                <td>{{activity.title}}</td>
                <td>{{activity.description}}</td>
                <td>{{activity.website}}</td>
                <td>{{activity.year}}</td>
            </tr>
        </table>
    </div>
</div>
<script src="${app}"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
