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
    <nav class="navbar navbar-dark bg-dark">
        <div class="d-flex justify-content-start">
            <a class="btn btn-outline-light" href="/app">home</a>
        </div>
        <div class="d-flex justify-content-center">
            <div class="input-group">
                <form action="/result/find" id="searchForm" >
<%--                    <button class="btnSearchInput" type="button"><i class="fas fa-search"></i></button>--%>
                    <input  class="form-control" type="search" placeholder="Rechercher..." aria-label="Search" name="name">
                </form>
                <button type="button" class="btn btn-primary">
                    <i class="fas fa-search"></i>
                </button>
            </div>

        </div>
        <div class="d-flex justify-content-end">
            <a class="btn btn-outline-light" href="/profile">Profile</a>
        </div>
        <%--            <a class="navbar-brand" href="/logout">Logout</a>--%>
    </nav><br>
    <div class="container">
        <h3>List of Curriculum Vitae</h3>

        <table class="table">
            <tr>
                <th>CV</th>
                <th>Personne</th>
                <th>voir CV </th>
            </tr>
            <c:forEach items="${cvList}" var="cv">
                        <tr>
                            <td> <c:out value="${cv.cvName}" /> </td>
                            <td> <c:out value="${cv.person.firstName}" /> </td>
                            <td><button v-on:click="getCvActivities(${cv.id})">Montrer</button></td>

                        </tr>
            </c:forEach>
        </table>
        <nav class="pagination is-centered" role="navigation" aria-label="pagination">
            <c:choose>
                <c:when test="${param.page != null && param.page != 0}">
                        <a href="/cvList?page=${param.page == null || param.page == 0 ? 0 : param.page-1}" class="pagination-previous"><i id="left-arrow" class="fas fa-arrow-left"></i></a>
                </c:when>

                <c:otherwise>
                         <a class="pagination-previous" disabled><i id="left-arr" class="fas fa-arrow-left"></i></a>
                </c:otherwise>
            </c:choose>

            <a href="/cvList?page=${param.page == null ? 1 : param.page+1}" class="pagination-next"><i id="right-arrow" class="fas fa-arrow-right" style="margin-left: 1000px" ></i></a>
        </nav>
        <div v-if="(cv != null)">
            <h1>CV</h1>
            <p>nom : {{cv.cvName}}</p>
            <h1>List of activities</h1>
            <table class="table">
                <tr>
                    <th>Titre</th>
                    <th>Description</th>
                    <th>Nature</th>
                    <th>Website</th>
                    <th>Year</th>
                </tr>
                <tr v-for="activity in cv.activities">
                    <td>{{activity.title}}</td>
                    <td>{{activity.description}}</td>
                    <td>{{activity.nature}}</td>
                    <td>{{activity.website}}</td>
                    <td>{{activity.year}}</td>
                </tr>
            </table>
        </div>
    </div>
</div>
<script src="${app}"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
