<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>
    <title>All divers </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/daginn.css"/>"/>
</head>
<body>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
      <form class="form-inline my-2 my-lg-0" method="GET" action="/showDiver">
          <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Home</button>
        </form>
      <ul class="navbar-nav mr-auto">
      </ul>
      <form class="form-inline my-2 my-lg-0" method="GET" action="/logOut">
        <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Log out</button>
      </form>
    </nav>
        <div class="container">
      <div class="jumbotron">
          <h1>All divers</h1>
    <c:choose>
        <c:when test="${not empty divers}">
            <table class="divers table table-striped table-bordered table-hover table-responsive">
                <thead class="thead-inverse">
                    <tr>
                        <th>Name</th>
                        <th>Password</th>
                    </tr>
                </thead>
                <c:forEach var ="diver" items="${divers}">
                    <tr>
                        <td>${diver.name} </td>
                        <td>${diver.password}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <h3> No divers</h3>
        </c:otherwise>
    </c:choose>


      </div>
    </div>
        



</body>
<!-- method="POST" action="/demo/listiKennara" -->
</html>