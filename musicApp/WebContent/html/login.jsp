
<jsp:include page="header.jsp"/>

<h2 class="display-2 mb-5" >Login</h2>

<form action="LoginServlet" method="POST" >

<div class="form-group">
	<label for="username">Username</label>
	<input type="text" class="form-control  w-50 mt-2 mb-2" name="username" id="username" required />
</div>


<div class="form-group">
	<label for="password">Password</label>
	<input type="password" class="form-control  w-50 mt-2 mb-2" name="password" id="password" required/>
	<small id="passwordHelp" class="form-text text-muted"><a href="PasswordServlet">Forgot Password?</a></small>
</div>

<button type="submit" class="btn bg-secondary text-light mt-3 mb-3" name="command" id="loginBtn" value="LOGIN_USER">Login</button>

</form>





<jsp:include page="footer.jsp"/>