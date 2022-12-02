<%--
  Created by IntelliJ IDEA.
  User: abdes
  Date: 02/12/2022
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:url var="createPerson" value="/createPerson.js" />
<c:url var="show-person" value="/api/persons/" />
<div id="createPerson">
<form id="add-person" method="post" novalidate="true" @submit.prevent="addPerson(newUser)">
  <h1 class="mt-4">Add New Person</h1>
  <div class="form-group">
    <label>Firstname :</label>
    <input v-model="newUser.firstName" class="form-control" name="firstName"
           v-bind:class="{'is-invalid':errors.firstname}"  required/>
    <div v-if="(errors.firstName)" class="alert alert-warning">
      {{errors.firstName}}
    </div>
  </div>
  <div class="form-group">
    <label>Lastname :</label>
    <input v-model="newUser.lastName" class="form-control" name="lastName"
           v-bind:class="{'is-invalid':errors.lastName}" required />
    <div v-if="(errors.lastName)" class="alert alert-warning">
      {{errors.lastName}}
    </div>
  </div>
  <div class="form-group">
    <label>Birthdate :</label>
    <input v-model="newUser.birthDate" class="form-control" name="birthDate"
           v-bind:class="{'is-invalid':errors.birthDate}" number required />
    <div v-if="(errors.birthDate)" class="alert alert-warning">
      {{errors.birthDate}}
    </div>
  </div>
  <div class="form-group">
    <label> Adress :</label>
    <input v-model="newUser.adress" class="form-control" name="adress"
           v-bind:class="{'is-invalid':errors.adress}" number required/>
    <div v-if="(errors.adress)" class="alert alert-warning">
      {{errors.adress}}
    </div>
    <label>Email adress :</label>
    <input v-model="newUser.email" class="form-control" name="email"
           v-bind:class="{'is-invalid':errors.email}" number required/>
    <div v-if="(errors.email)" class="alert alert-warning">
      {{errors.email}}
    </div>
  </div>
  <div class="form-group">
    <label>Website :</label>
    <input v-model="newUser.website" class="form-control" name="website"
           v-bind:class="{'is-invalid':errors.website}" number required/>
    <div v-if="(errors.website)" class="alert alert-warning">
      {{errors.website}}
    </div>
  </div>
  <div class="form-group">
    <label>Password :</label>
    <input v-model="newUser.password" class="form-control" name="password"
           v-bind:class="{'is-invalid':errors.password}" number required/>
    <div v-if="(errors.password)" class="alert alert-warning">
      {{errors.password}}
    </div>
  </div>
  <div class="form-group">
    <button class="btn btn-primary mr-2" type="submit" >
      submit</button>
    <div>
      <a class="btn btn-primary btn-sm" href="/profile">annuler</a>
    </div>
  </div>
</form>
</div>
<script src="${createPerson}"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
