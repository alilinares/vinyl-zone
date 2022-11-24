<jsp:include page="header.jsp"/>

<h2 class="display-2 mb-5" >Verification</h2>

<form action="UserControllerServlet" method="POST" >

<div class="form-group">
	<label for="email">Email</label>
	<input type="email" class="form-control  w-50 mt-2 mb-2" name="email" id="email" required/>
</div>


<button type="submit" class="btn bg-secondary text-light mt-3 mb-3" name="command" id="resetPass" value="FORGOT_PASSWORD">Reset Password</button>

</form>


<jsp:include page="footer.jsp"/>