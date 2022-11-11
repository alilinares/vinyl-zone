<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Vinyl Zone</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" href="css/main.css" />
  </head>
<body>   
<header>
<nav class="navbar navbar-light bg-light">
  <div class="container justify-content-between">
        <a class="navbar-brand ml-4" href="IndexServlet">
    	<img src="images/vinyl.png" alt="vinyl record graphic" width="30" height="30" class="align-middle" /> 
    	${fn:toUpperCase("Vinyl Zone")}</a>
    <div class="nav">
        <a class="nav-item nav-link" href="login.jsp">Login</a>
 		<a class="nav-item nav-link" href="register.jsp">Register</a>
    </div>
  </div>
</nav>
</header>
<main class='container'>
