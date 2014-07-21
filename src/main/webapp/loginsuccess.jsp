<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../../assets/ico/favicon.ico">

<title>login succeeded!</title>

<!-- Bootstrap core CSS -->
<link href="static/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="dynamic/css/signin.css" rel="stylesheet">
<link href="dynamic/css/footer.css" rel="stylesheet">

</head>

<body>

	<%@include file="menubar.jsp"%>

	<div class="container">

		<h2 class="form-signin-heading" style="text-align: center;">

			<p>welcome!</p>
			<p>this is a debug view and will be removed later.</p>
			<%@ page import="java.util.*"%>

			<p>
				<br>
			<div
				style="border: 3px solid; border-radius: 6px; width: 1000px; margin: 0 auto;">
				<table align="center"
					style="width: 100%; text-align: left; margin: 15px;">
					<tbody>
						<tr>
							<td>DEBUG: Server Time:</td>
							<td><%=new Date()%></td>
						</tr>
						<tr>
							<td>DEBUG: Your user_id:</td>
							<td><%=session.getAttribute("user_id")%></td>
						</tr>
						<tr>
							<td>DEBUG: Your email:</td>
							<td><%=session.getAttribute("email")%></td>
						</tr>
						<tr>
							<!-- password should NOT be stored in session for obvious reasons -->
							<td>DEBUG: Your password:</td>
							<td><%=session.getAttribute("password")%> (THIS SHOULD BE
								null!)</td>
						</tr>
						<tr>
							<td>DEBUG: Your first name:</td>
							<td><%=session.getAttribute("firstname")%></td>
						</tr>
						<tr>
							<td>DEBUG: Your last name:</td>
							<td><%=session.getAttribute("lastname")%></td>
						</tr>
					</tbody>
				</table>
			</div>
			<br>
			</p>

			<p>
				hello,
				<%=session.getAttribute("firstname") + " "
					+ session.getAttribute("lastname")%>!
			</p>
			<br>

			<p>
				<a href="profile.jsp">go to your profile</a>
			</p>

			<p>
				<a href="notes.jsp">go to your notes</a>
			</p>

			<p>
				<a href="logout">logout</a>
			</p>

		</h2>

	</div>

	<%@include file="footer.jsp"%>

</body>
</html>

<%
	if (session.getAttribute("email") == null) {
		response.sendRedirect("index.jsp");
	}
%>