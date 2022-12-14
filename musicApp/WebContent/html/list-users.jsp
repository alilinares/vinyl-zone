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
  <c:forEach var="u" items="${USER_LIST}">
	<c:url var="tempLink" value="UserControllerServlet">
	  	<c:param name="command" value="LOAD_USER"/>
	  	<c:param name="userId" value="${u.userId}"/>
	  </c:url>
	  
	   <c:url var="deleteLink" value="UserControllerServlet">
	  	<c:param name="command" value="DELETE_USER"/>
	  	<c:param name="userId" value="${u.userId}"/>
	 </c:url>
     <tr>
     <td>${u.username}</td>
     <td>${u.firstName}</td>
     <td>${u.lastName}</td>
     <td>${u.email}</td>
     <td><a href="${tempLink}">Update</a></td>
     <td><a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete user?'))){return false;}">Delete</a></td>
     </tr>
  </c:forEach>
  </tbody>
</table>


<jsp:include page="footer.jsp"/> 