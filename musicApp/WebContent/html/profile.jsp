
<jsp:include page="header.jsp"/>

<h2 class='display-2 mb-5'>Public Profile</h2>

<form action="UserProfileServlet" method="POST" enctype="multipart/form-data">

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

 <div class="form-group mt-3">
    <label for="password">Password</label>
    <input type="password" class="form-control w-50 mt-3 mb-3" id="password" name="password" value="${USER.password}"/>
  </div>
  
<div class="form-group">  
  <label for="bio">Bio</label>
  <textarea  name="bio" id="bio" class="form-control  w-50 mt-2 mb-2">${USER.bio}</textarea>
  <small>Limit 250 characters</small>
</div>

 <div class="form-group mt-3">
    <label for="profilePhoto">Upload Profile Photo</label>
    <br/>
    <input type="file" class="form-control-file w-50 mt-3 mb-3" id="profilePhoto" name="profilePhoto" value="${USER.profilePhoto}">
  </div>

  <button type="submit" name="command" value="UPDATE_PROFILE" class="btn bg-secondary text-light mt-3 mb-3">Update</button>
</form>

  <form action="UserProfileServlet" method="POST">
   <input type="hidden" name="userId" value="${USER.userId}" />
   <button type="submit" name="command" value="DELETE_ACCOUNT" class="btn bg-secondary text-light mt-3 mb-3">Delete Account</button>
  </form>

<jsp:include page="footer.jsp"/> 