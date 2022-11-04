
<jsp:include page="header.jsp"/>


<h2> Register </h2>

<form action="process-user.jsp" method="">

  <label for="userName">Username: 
  <input type="text" name="userName" id="userName"/>
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
  <input type="submit" value="submit" />
  
</form>


<jsp:include page="footer.jsp"/>