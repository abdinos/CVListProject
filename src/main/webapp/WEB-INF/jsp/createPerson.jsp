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
    <nav class="navbar navbar-dark bg-dark">
        <div class="d-flex justify-content-start">
            <a class="btn btn-outline-light" href="/profile">Profile</a>
        </div>
        <div class="d-flex justify-content-end">
            <button class="btn btn-outline-danger" v-on:click="logoutUser()">Logout</button>
        </div>
        <%--            <a class="navbar-brand" href="/logout">Logout</a>--%>
    </nav><br>
<form id="add-person" method="post" novalidate="true" @submit.prevent="addPerson(newUser)">
  <h1 class="mt-4">Add New Person</h1>
    <br>
    <div class="row">
    <div class="col">
<%--      <label>Firstname :</label>--%>
      <input placeholder="First name" v-model="newUser.firstName" class="form-control" name="firstName"
             v-bind:class="{'is-invalid':errors.firstname}"  required/>
      <div v-if="(errors.firstName)" class="alert alert-warning">
        {{errors.firstName}}
      </div>
    </div>
    <div class="col">
<%--      <label>Lastname :</label>--%>
      <input placeholder="Last name" v-model="newUser.lastName" class="form-control" name="lastName"
             v-bind:class="{'is-invalid':errors.lastName}" required />
      <div v-if="(errors.lastName)" class="alert alert-warning">
        {{errors.lastName}}
      </div>
    </div>
  </div>
    <br>

    <div class="row">
      <div class="col">
<%--        <label>Birthdate :</label>--%>
        <input placeholder="Birthdate" v-model="newUser.birthDate" class="form-control" name="birthDate"
               v-bind:class="{'is-invalid':errors.birthDate}" number required />
        <div v-if="(errors.birthDate)" class="alert alert-warning">
          {{errors.birthDate}}
        </div>
      </div>
      <div class="col">
<%--        <label> Adress :</label>--%>
        <input placeholder="Address" v-model="newUser.adress" class="form-control" name="adress"
               v-bind:class="{'is-invalid':errors.adress}" number required/>
        <div v-if="(errors.adress)" class="alert alert-warning">
          {{errors.adress}}
        </div>
       </div>
  </div>
    <br>

    <div class="row">
    <div class="col">
      <%--        <label>Email adress :</label>--%>
      <input placeholder="Email adress" v-model="newUser.email" class="form-control" name="email"
             v-bind:class="{'is-invalid':errors.email}" number required/>
      <div v-if="(errors.email)" class="alert alert-warning">
        {{errors.email}}
      </div>
    </div>
    <div class="col">
<%--      <label>Website :</label>--%>
      <input placeholder="Website" v-model="newUser.website" class="form-control" name="website"
             v-bind:class="{'is-invalid':errors.website}" number required/>
      <div v-if="(errors.website)" class="alert alert-warning">
        {{errors.website}}
      </div>
    </div>

  </div>
    <br>
  <div class="row">
      <div class="col">
          <%--      <label>Password :</label>--%>
          <input placeholder="Password" v-model="newUser.password" class="form-control" name="password"
                 v-bind:class="{'is-invalid':errors.password}" number required/>
          <div v-if="(errors.password)" class="alert alert-warning">
              {{errors.password}}
          </div>
      </div>
      <div class="col">
          <div>
            <button class="btn btn-outline-primary mr-3" type="submit" >
              Save</button>

              <a class="btn btn-outline-danger mr-3" href="/profile">Cancel</a>
          </div>
      </div>
  </div>
</form>
</div>
<script src="${createPerson}"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
