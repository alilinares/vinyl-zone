
<jsp:include page="header.jsp"/>


<h2 class="display-2 mb-5"> Register </h2>

<form action="UserControllerServlet" method="GET">

<div class="form-group">
	<label for="username">Username</label>
	<input type="text" class="form-control  w-50 mt-2 mb-2" name="username" id="username" required />
</div>

<div class="form-group">
	<label for="firstName">First Name</label>
	<input type="text" class="form-control  w-50 mt-2 mb-2" name="firstName" id="firstName" required />
</div>

  <div class="form-group">
  	<label for="lastName">Last Name</label>
  	<input type="text" class="form-control  w-50 mt-2 mb-2"name="lastName" id="lastName"/>
  </div>

  <div class="form-group">
  	<label for="email">Email Address</label>
  	<input type="email" class="form-control  w-50 mt-2 mb-2"name="email" id="email"/>
  </div>
  
  <div class="form-group">
  	<label for="password">Password</label>
  	<input type="text" class="form-control  w-50 mt-2 mb-2" name="password" id="password" />
  </div>
  
    <div class="form-group">
    <label for="fileUpload">Upload Photo</label>
    <input type="file" class="form-control-file w-50 mt-3 mb-3" id="fileUpload">
  </div>
  
  <button type="submit" class="btn bg-secondary text-light mt-3 mb-3" name="command" id="loginBtn" value="REGISTER_USER">Sign Up</button>
  
</form>


<jsp:include page="footer.jsp"/>