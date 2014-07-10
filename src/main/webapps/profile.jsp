<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- Unknown effectiveness.  Might delete later. -->
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../../assets/ico/favicon.ico">

<title>your profile</title>

<!-- Bootstrap core CSS -->
<link href="static/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="dynamic/css/profile.css" rel="stylesheet">
<link href="dynamic/css/footer.css" rel="stylesheet">

</head>

<body>

	<%@include file="menubar.jsp"%>

	<div class="profile">

		<table class="table" style="width: 1000px;" align="center">
			<tbody>
				<tr>
					<td>Hello, <%=session.getAttribute("firstname")%>!
					</td>
					<td><div
							style="margin: 10px; width: 200px; height: 200px; text-align: center;">
							Profile Picture (placeholder)
							<p>
								<img src="${root}dynamic/images/anonymous_avatar.png" />
							</p>
						</div></td>
				</tr>
				<tr>
					<td>First Name:</td>
					<td><%=session.getAttribute("firstname")%></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><%=session.getAttribute("lastname")%></td>
				</tr>
				<tr>
					<td>About:</td>
					<td><%=session.getAttribute("about")%></td>
				</tr>
			</tbody>
		</table>
		<br> <br>
		<p style="text-align: center;">
			<a href=profileupdate.jsp>update your profile</a>
		</p>

		<p style="text-align: center;">
			<a href="notes.jsp">go to your notes</a>
		</p>

		<p style="text-align: center;">
			<a href="logout">logout</a>
		</p>

	</div>

	<%@include file="footer.jsp"%>
	
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>

</body>
</html>

<%
	if (session.getAttribute("email") == null) {
		response.sendRedirect("index.jsp");
	}
%>