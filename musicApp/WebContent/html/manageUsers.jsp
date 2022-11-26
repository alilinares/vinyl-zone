<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>


<table>
  <thead>
    <tr>
      <th>Username</th>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Email</th>
      <th colspan='2'>Operation</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="usr" items="${USER_LIST}">
	<c:url var="tempLink" value="UserProfileServlet">
	  	<c:param name="command" value="LOAD_USER"/>
	  	<c:param name="userId" value="${usr}"/>
	  </c:url>
	  
	   <c:url var="deleteLink" value="UserProfileServlet">
	  	<c:param name="command" value="DELETE_ACCOUNT"/>
	  	<c:param name="userId" value="${usr}"/>
	 </c:url>
     <tr>
     <td>${usr.username}</td>
     <td>${usr.firstName}</td>
     <td>${usr.lastName}</td>
     <td>${usr.email}</td>
     <td><a href="${tempLink}">Update</a></td>
     <td><a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete user?'))){return false;}">Delete</a></td>
     </tr>
  </c:forEach>
  </tbody>
</table>


<jsp:include page="footer.jsp"/> 