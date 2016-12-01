<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>All Posts</title>
	<meta name="author" content="me dante">
	<link href="bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>

<body style="font-family: century; color:#7f7f7f; font-size:18px;">

<div class="page-header text-center">
  <h1 >Posts <small style="color:#23d0d8">&nbsp;&nbsp;&nbsp;&nbsp;all is running, all is curving</small></h1>
</div>
<div class="row" style="margin-top:4%;">
	<form action="/post" method="post">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-8">
				<div class="input-group">
				  <span class="input-group-addon">Author</span>
				  <input type="text" name="author" class="form-control" placeholder="">
				</div>
				<br/>
				<div class="input-group">
				  <textarea rows="5" name="body" cols="100" class="form-control" placeholder="Input your post body" style="border-radius:4px;"></textarea>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-8"></div>
			<div class="col-md-3">
				<br/>
				<input type="submit" value="Submit!" class="btn btn-info" style="padding:10px 40px"></input>
			</div>
		</div>
	</form>
</div>
<div class="row" style="margin-top:4%">
	<div class="col-md-1"></div>

	<div class="col-md-10" style="padding:0">
		<%@ page import="java.util.List" %>
		<%@ page import="me.dante.adventure.models.Post" %>
		<%
			List<Post> posts = (List<Post>)request.getAttribute("data");
			for(Post post : posts){
		%>
		<div class="panel panel-default">
		  <div class="panel-heading"><b><%=post.getAuthor()%></b> <i style="font-size:10px">wrote at <%=post.getCreatedAt().toString().replaceAll("\\.\\d+", "")%></i></div>
		  <div class="panel-body">
		    <%=post.getBody()%>
		  </div>
		</div>
		<%
		}
		%>
	</div>

	<div class="col-md-1"></div>
</div>

</body>
</html>
