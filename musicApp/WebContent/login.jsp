
<jsp:include page="header.jsp"/>

<h2> login </h2>

<form action="UserControllerServlet" method="GET" >
<label for="username">
Username:
<br/>
<input type="text" name="username" id="username"required />
</label>
<br/>
<label for="password">
Password (8 characters minimum):
<br/>
<input type="password" name="password" id="password" minlength="8" required/>
</label>
<br/>
<br/>
<input type="submit" name="loginBtn" id="loginBtn" value="Login"/>

</form>





<jsp:include page="footer.jsp"/>