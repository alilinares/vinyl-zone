<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>

<h1>User Profile</h1>

<form action="UserControllerServlet" method="GET">

  <input type="hidden" name="userId" value="${USER.userId}" />

  <label for="username">Username: 
  <input type="text" name="username" id="username" value="${USER.firstName}"/>
  </label>  
  <br />
  <br />


  <label for="firstName">First Name:
  <input type="text" name="firstName" id="firstName" value="${USER.firstName}"/>
  </label>
  
  <br />
  <br />
  
  <label for="lastName">Last Name:
  <input type="text" name="lastName" id="lastName" value="${USER.lastName}"/>
  </label>
  
  <br />
  <br />
  
  <label for="email">Email Address:
  <input type="text" name="email" id="email" value="${USER.email}" />
  </label>
  
  <br />
  <br />
  
  <label for="password">Password: 
  <input type="password" name="password" id="password" value="${USER.password}" />
  </label>
  
  <br />
  <br />
  <input type="hidden" name="command" value="UPDATE_USER" />
  <input type="submit" name="updateBtn" value="Update" />
  
</form>

<jsp:include page="footer.jsp"/> 