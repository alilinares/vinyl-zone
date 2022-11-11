
<jsp:include page="header.jsp"/>


<h2> Register </h2>

<form action="UserControllerServlet" method="GET">

  <label for="username">Username: 
  <input type="text" name="username" id="username"/>
  </label>
  
  <br />
  <br />

  <label for="firstName">First Name:
  <input type="text" name="firstName" id="firstName"/>
  </label>
  
  <br />
  <br />
  
  <label for="lastName">Last Name:
  <input type="text" name="lastName" id="lastName"/>
  </label>
  
  <br />
  <br />
  
  <label for="email">Email Address:
  <input type="text" name="email" id="email"/>
  </label>
  
  <br />
  <br />
  
  <label for="password">Password: 
  <input type="text" name="password" id="password" />
  </label>
  
  <br />
  <br />
  <input type="hidden" name="command" value="REGISTER_USER" />
  <input type="submit" name="registerBtn" value="Sign Up" />
  
</form>


<jsp:include page="footer.jsp"/>