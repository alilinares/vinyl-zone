
<jsp:include page="header.jsp"/>
  <div class="wrapper">
    <form class="form-signin" action="UserControllerServlet" method="POST">       
      <h2 class="form-signin-heading">Register</h2>
      <input type="text" class="form-control" name="username" placeholder="UserName" required="" autofocus="" />
      <input type="text" class="form-control" name="firstName" placeholder="FirstName" required=""/>      
      <input type="text" class="form-control" name="LastName" placeholder="LastName" required=""/>      
      <input type="text" class="form-control" name="email" placeholder="email@email.com" required=""/>      
      <input type="password" class="form-control" name="password" placeholder="Password" required=""/>      
      <button type="submit" class="btn bg-secondary text-light mt-3 mb-3" name="command" id="loginBtn" value="REGISTER_USER">Sign Up</button>  
    </form>
  </div>

<jsp:include page="footer.jsp"/>
