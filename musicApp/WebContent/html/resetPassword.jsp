<jsp:include page="header.jsp"/>

<h2 class="display-2 mb-5" >Reset Password</h2>

<form action="UserControllerServlet" method="POST" >

<div class="form-group">
	<label for="password">New Password</label>
	<input type="password" class="form-control  w-50 mt-2 mb-2" name="password" id="password" required/>
</div>

<button type="submit" class="btn bg-secondary text-light mt-3 mb-3" name="command" id="resetPass" value="RESET_PASSWORD">Reset Password</button>

</form>


<jsp:include page="footer.jsp"/>