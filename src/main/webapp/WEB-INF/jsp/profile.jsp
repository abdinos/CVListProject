<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="home" value="/aaa" />
<c:url var="login" value="/login.js" />

<div id="login">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="">Modifier CV</a>
            <a class="navbar-brand" href="">Créer une Personne</a>
            <a class="navbar-brand" href="">Déconnexion</a>
        </nav>

        <h1>bbb abababa
            {{username}}</h1>

    </div>
</div>
<script src="${login}"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>