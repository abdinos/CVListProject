<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="home" value="/aaa" />
<c:url var="profile" value="/profile.js" />

<div id="profile">
    <div class="container" v-if="(isLoggedIn)">
        <nav class="navbar navbar-dark bg-dark">
            <a class="btn btn-outline-light" href="/cvList">Parcours des CV</a>
            <a class="btn btn-outline-warning" href="#" v-on:click="setAddActivity(true)">Edit CV</a>
            <a class="btn btn-outline-success" href="/createPerson">Create a person</a>
            <a class="btn btn-outline-info" href="#" v-on:click="setShowUserInfo(true)">Show user info</a>
            <button class="btn btn-outline-danger" v-on:click="logoutUser()">Logout</button>
<%--            <a class="navbar-brand" href="/logout">Logout</a>--%>
        </nav>

        <h3>{{username}}</h3>

        <div v-if="(showInfo)">
            <h3>User Info</h3>
            <table class="table">
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>BirthDate</th>
                    <th>Address</th>
                    <th>Email</th>
                    <th>Website</th>
                </tr>
                <tr>
                    <td>{{userInfo[0]}}</td>
                    <td>{{userInfo[1]}}</td>
                    <td>{{userInfo[2]}}</td>
                    <td>{{userInfo[3]}}</td>
                    <td>{{userInfo[4]}}</td>
                    <td>{{userInfo[5]}}</td>
                </tr>
            </table>
            <button v-on:click="setShowUserInfo(false)" class="btn btn-outline-primary">
                Close</button>
        </div>
        <div v-if="(cv != null)">
            <h3>CV</h3>
            <table class="table">
                <tr>
                    <th>{{cv.cvName}}</th>
                    <th>voir CV </th>
                </tr>
            </table>
            <h3>Liste des activities</h3>
            <table class="table">
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Nature</th>
                    <th>Website</th>
                    <th>Year</th>
                    <th>Action</th>
                </tr>
                <tr v-for="activity in activities">
                    <td>{{activity.title}}</td>
                    <td>{{activity.description}}</td>
                    <td>{{activity.nature}}</td>
                    <td>{{activity.website}}</td>
                    <td>{{activity.year}}</td>
                    <td><button class="btn btn-success btn-sm"
                                v-on:click="editCV(activity)">Edit</button></td>
                </tr>
            </table>

            <form v-if="(editable != null)" id="app" method="post" novalidate="true">
                <h3 class="mt-4">Edit activity</h3>
                <div class="form-group">
<%--                    <label>Titre :</label>--%>
                    <input placeholder="Title" v-model="editable.title" class="form-control"
                           v-bind:class="{'is-invalid':errors.title}" />
                    <div v-if="(errors.title)" class="alert alert-warning">
                        {{errors.title}}
                    </div>
                </div>
                <div class="form-group">
<%--                    <label>Description :</label>--%>
                    <input placeholder="Description" v-model="editable.description" class="form-control"
                           v-bind:class="{'is-invalid':errors.description}"  />
                    <div v-if="(errors.description)" class="alert alert-warning">
                        {{errors.description}}
                    </div>
                </div>

                <div class="form-group">
<%--                    <label>Description :</label>--%>
                    <input placeholder="Nature" v-model="editable.nature" class="form-control"
                           v-bind:class="{'is-invalid':errors.nature}"  />
                    <div v-if="(errors.nature)" class="alert alert-warning">
                        {{errors.nature}}
                    </div>
                </div>
                <div class="form-group">
<%--                    <label>Site :</label>--%>
                    <input placeholder="Website" v-model="editable.website" class="form-control"
                           v-bind:class="{'is-invalid':errors.website}"  />
                    <div v-if="(errors.website)" class="alert alert-warning">
                        {{errors.website}}
                    </div>
                </div>
                <div class="form-group">
<%--                    <label>Annee :</label>--%>
                    <input placeholder="Year" v-model="editable.year" class="form-control"
                           v-bind:class="{'is-invalid':errors.year}"  />
                    <div v-if="(errors.year)" class="alert alert-warning">
                        {{errors.year}}
                    </div>
                </div>
                <div class="form-group">
                    <button v-on:click="submitCV(editable)" class="btn btn-primary mr-2">
                        Save</button>
                    <button v-on:click="clearEditable()" class="btn btn-outline-primary">
                        Clear</button>
                </div>
            </form>

        <%--            Ajout d'une activit--%>
            <form v-if="(isAddActivity)" id="add-activity" novalidate="true" @submit.prevent="addActivity(newActivity)">
                <h3 class="mt-4">Add New Activity</h3>
                <div class="form-group">
<%--                    <label>Title :</label>--%>
                    <input placeholder="Title" v-model="newActivity.title" class="form-control" name="name"
                           v-bind:class="{'is-invalid':errors.title}" />
                    <div v-if="(errors.title)" class="alert alert-warning">
                        {{errors.title}}
                    </div>
                </div>
                <div class="form-group">
<%--                    <label>Description :</label>--%>
                    <input placeholder="Description" v-model="newActivity.description" class="form-control" name="description"
                           v-bind:class="{'is-invalid':errors.description}"  />
                    <div v-if="(errors.website)" class="alert alert-warning">
                        {{errors.description}}
                    </div>
                </div>
                <div class="form-group">
<%--                    <label>Nature :</label>--%>
                    <input placeholder="Nature (en Maj)" v-model="newActivity.nature" class="form-control" name="nature"
                           v-bind:class="{'is-invalid':errors.nature}"  />
                    <div v-if="(errors.nature)" class="alert alert-warning">
                        {{errors.nature}}
                    </div>
                </div>
                <div class="form-group">
<%--                    <label>website :</label>--%>
                    <input placeholder="Website" v-model="newActivity.website" class="form-control" name="website"
                           v-bind:class="{'is-invalid':errors.website}"  />
                    <div v-if="(errors.website)" class="alert alert-warning">
                        {{errors.website}}
                    </div>
                </div>
                <div class="form-group">
<%--                    <label>Year :</label>--%>
                    <input placeholder="Year" v-model="newActivity.year" class="form-control" name="year"
                           v-bind:class="{'is-invalid':errors.year}" number />
                    <div v-if="(errors.year)" class="alert alert-warning">
                        {{errors.year}}
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit"  class="btn btn-primary mr-2">
                        Add Activity</button>
                    <button v-on:click="setAddActivity(false)" class="btn btn-outline-primary">
                        Clear</button>

                </div>
            </form>
        </div>

    </div>
    <div class="container" v-if="(!isLoggedIn)">
        <h3>You're not authenticated, please sign in</h3><br>
        <a class="btn btn-primary btn-sm" href="/login">Sign in</a>
    </div>



</div>
<script src="${profile}"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>