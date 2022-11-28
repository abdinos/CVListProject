<%--
  Created by IntelliJ IDEA.
  User: a18013526
  Date: 20/11/2022
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="cv" value="/cvList" />

<div id="myApp">
  <div class="container">
    <h1>Creation d'un CV</h1>

    <form @submit.prevent="createCV" >

      <div class="form-group">
        <label>Nom :</label>
        <input path="name" cssClass="form-control"
                    cssErrorClass="form-control is-invalid" />
      </div>
      <button type="submit" class="btn btn-primary">Creer</button>
      <a class="btn btn-primary" href="${cv}">Annuler</a>
    </form>
  </div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
