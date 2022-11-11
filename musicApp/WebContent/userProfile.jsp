
<jsp:include page="header.jsp"/>

<h2 class='display-2 mb-5'>Public Profile</h2>

<form action="UserControllerServlet" method="GET">

  <input type="hidden" name="userId" value="${USER.userId}" />

<div class="form-group">
  <label for="username">Username</label> 
  <input type="text" name="username" id="username" value="${USER.username}" class="form-control  w-50 mt-2 mb-2" />
</div>

<div class="form-group">
  <label for="firstName">First Name</label>
  <input type="text" name="firstName" id="firstName" value="${USER.firstName}" class="form-control  w-50 mt-2 mb-2" />
</div>
  
<div class="form-group">
  <label for="lastName">Last Name</label>
  <input type="text" name="lastName" id="lastName" value="${USER.lastName}" class="form-control  w-50 mt-2 mb-2"/>
</div>

<div class="form-group">
  <label for="email">Email Address</label>
  <input type="text" name="email" id="email" value="${USER.email}" class="form-control  w-50 mt-2 mb-2" />
</div>
  
<div class="form-group">  
  <label for="bio">Bio</label>
  <textarea  name="bio" id="bio" value="" class="form-control  w-50 mt-2 mb-2"> </textarea>
  <small>Limit 250 characters</small>
</div>

  <button type="submit" name="command" value="UPDATE_USER" class="btn bg-secondary text-light mt-3 mb-3">Update</button>
  
</form>

<jsp:include page="footer.jsp"/> 