<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>


<table>
  <thead>
    <tr>
      <th>Username </th>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Email</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="u" items="${USER_LIST}">
     <tr>
     <td>${u.username}</td>
     <td>${u.firstName}</td>
     <td>${u.lastName}</td>
     <td>${u.email}</td>
     </tr>
  </c:forEach>
  </tbody>
</table>


<jsp:include page="footer.jsp"/> 