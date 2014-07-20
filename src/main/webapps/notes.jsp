<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.sql.*"%>

<%!public Connection cOpen() throws SQLException {
		// This entire damn thing needs to be separated into its own class somehow...
		Connection con = null;
		try {
			con = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/madnotes?user=postgres&password=pass1234");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return con;
	}

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String SQL = "";%>


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

<body style="background-color: rgb(100, 100, 100);">

	<%@include file="menubar.jsp"%>

	<h1 style="text-align: center;">your madnotes</h1>
	<div
		style="border: 3px solid; border-radius: 6px; width: 1000px; margin: 0 auto; background-color: rgb(250, 250, 250);">
			<table class="table" style="width: 1000px;" align="center">
				<tbody>
					<tr style="margin: 5px;">
							<td>posted on</td>
							<td>title</td>
					</tr>
					<%
						// Replace email with the user_id stored in session.
						// Task COMPLETED!
						int user_id = 0;
						try {
							if (session.getAttribute("user_id") == null) {
								response.sendRedirect("index.jsp");
							} else {
								user_id = Integer.parseInt(session.getAttribute("user_id").toString());
								//session.setAttribute("user_id", user_id); // this may be an unnecessary step...
							}
						
							int postCount = 0;
							con = cOpen();
							// This should be a much simpler query WHERE notes.user_id = ? // <-- accounts.user_id stored in session
							SQL = "SELECT post_id, post_date, title FROM notes WHERE notes.user_id = ? ORDER BY post_date ASC";
							ps = con.prepareStatement(SQL);
							ps.setInt(1, user_id);
							rs = ps.executeQuery();
							while (rs.next()) {
								postCount++;
					%>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;<%=rs.getString("post_date")%></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;<%=rs.getString("title")%></td>
						<td>
							<form action="showcontent" method="post">
								<input type="hidden" name="post_id" value='<%=rs.getString("post_id")%>'>
								<input type="hidden" name="post_date" value='<%=rs.getString("post_date")%>'>
								<input type="hidden" name="title" value='<%=rs.getString("title")%>'>
								<button type="submit" style="border: 0; outline: 0; background: none;">
								<span class="glyphicon glyphicon-pencil"></span>
								</button>
							</form>
						</td>
						<td>
							<form action="deletenote" method="post">
								<input type="hidden" name="post_id" value='<%=rs.getString("post_id")%>'>
								<button type="submit" style="border: 0; outline: 0; background: none;">
								<span class="glyphicon glyphicon-remove"></span>
								</button>
							</form>
						</td>
					</tr>
					<%
						}
					%>

					<tr>
						<td><%="TOTAL POSTS: " + postCount%></td>
					</tr>

					<%
						} catch (SQLException e) {
							e.printStackTrace(System.out);
						} finally {
							try {
								ps.close();
							} catch (Exception e) {
							}
							try {
								rs.close();
							} catch (Exception e) {
							}
							try {
								if (null != con) {
									con.close();
								}
							} catch (SQLException ex) {
								ex.printStackTrace(System.out);
							}
						}
					%>
				</tbody>
			</table>
		<form action="addnote" method="post">
			<table class="table" style="width: 1000px;" align="center">
				<tr style="margin: 5px; text-align: center;">
					<td style="vertical-align: middle;"><textarea type="text" name="addnote" cols="100" rows="3" placeholder="add new note" style="padding: 6px;"></textarea></td>
					<td style="vertical-align: middle;"><button class="btn btn-md btn-primary" type="submit">SUBMIT</button></td>
				</tr>
			</table>
		</form>
	</div>
	<br>
	<br>
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