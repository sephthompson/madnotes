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

<title>profile update</title>

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
						<td>DEBUG: Hello, <%=session.getAttribute("firstname")%>!
						</td>
						<td><div style="margin: 10px; width: 200px; height: 200px;">
								<center>
									<p>
										<img src="${root}dynamic/images/anonymous_avatar.png" />
									</p>
									<p>
									<form action="uploadimage" method="post" enctype="multipart/form-data"><br>
										<input type="file" name="uploadimage" />
										<input type="submit" /><br>
									</form>
									</p>
								</center>
							</div></td>
					</tr>
					<form action=updateprofile method="post">
					<tr>
						<td>First Name:</td>
						<td><input type="text" name="firstname"
							value='<%=session.getAttribute("firstname")%>'></td>
					</tr>
					<tr>
						<td>Last Name:</td>
						<td><input type="text" name="lastname"
							value='<%=session.getAttribute("lastname")%>'></td>
					</tr>
					<tr>
						<td>About:</td>
						<td><textarea type="text" name="about" cols="30" rows="3"><%=session.getAttribute("about")%></textarea></td>
					</tr>
				</tbody>
			</table>
			<p>
				<button class="btn btn-lg btn-primary btn-block" type="submit">
					<strong>Update Profile</strong>
				</button>
			</p>
		</form>
		<br> <br>
		<p style="text-align: center;">
			<a href=profile.jsp>go back without updating</a>
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