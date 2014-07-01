<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../../assets/ico/favicon.ico">

<title>your madnotes</title>

<!-- Bootstrap core CSS -->
<link href="static/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="dynamic/css/signin.css" rel="stylesheet">
<link href="dynamic/css/footer.css" rel="stylesheet">

</head>

<body>

	<%@include file="menubar.jsp"%>

	<h1 style="text-align: center;">edit your note</h1>
	<div
		style="border: 3px solid; border-radius: 6px; width: 1000px; margin: 0 auto;">
			<table class="table" style="width: 1000px;" align="center">
				<tbody>
					<tr style="margin: 5px;">
							<td>posted on</td>
							<td>original post</td>
					</tr>
					
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;<%=request.getParameter("post_date")%></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;<%=request.getParameter("content")%></td>
					</tr>
				</tbody>
			</table>
		<form action="editnote" method="post">
			<table class="table" style="width: 1000px;" align="center">
				<tr style="margin: 5px; text-align: center;">
					<td><input type="hidden" name="post_id" value='<%=request.getParameter("post_id")%>'></td>
					<td style="vertical-align: middle;">
						<textarea type="text" name="editnote" cols="100" rows="3" style="padding: 6px;"><%=request.getParameter("content")%></textarea>
					</td>
					<td style="vertical-align: middle;">
						<p><button class="btn btn-md btn-primary" type="submit" style="background: rgb(155, 207, 151); border-color: gray;">UPDATE</button></p>
						<p><a href="notes.jsp"><button class="btn btn-md btn-primary" type="button" style="background: rgb(255, 187, 187); border-color: gray;">CANCEL</button></a></p>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<br>
	<br>
	<!-- CONSIDER PLACING AS CANCEL BUTTON IN THE FORM -->
	<p style="text-align: center;">
		<a href=notes.jsp>go to your notes</a>
	</p>
	
	<p style="text-align: center;">
		<a href=profile.jsp>go to your profile</a>
	</p>

	<p style="text-align: center;">
		<a href="logout">logout</a>
	</p>

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
	/* // TESTING IF STATEMENT IN SCRIPTLET CODE ABOVE
	if (session.getAttribute("email") == null) {
		response.sendRedirect("/madnotes/index.jsp");
	} */
%>