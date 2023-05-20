<%@ page language="java" contentType="text/html"%>
<html lang="en">
  <head>
    <meta charset="utf-8" />

    <title>Filemei</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/assets/style.css" rel="stylesheet" />
    <script src="/assets/script.js"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Bungee&display=swap" rel="stylesheet">
    
    <link rel="shortcut icon" href="/assets/filemei.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
<div class="wrapper">
  <div class="container">
    <a href="http://localhost:8080/filemei-1.0/home/" style="text-decoration: none;">
      <h1>filemei</h1>
    </a>
    <p>The link will be timeout in <strong>1 hour</strong> so take your time&#9203;</p>
    <div>
       <!-- The text field -->
       <div class="copy-text">    
        <input disabled="disabled" type="text" class="textbox" value="http://localhost:8080/filemei-1.0/download?filename=${requestScope.filename}" id="myInput">
        <button onclick="copy()" ><i class="fa fa-clone" ></i></button>
      </div>
    </div>
    <form id="FilemeiDownload" action="http://localhost:8080/filemei-1.0/download" method="get">
      <input type="hidden" name="filename" value="${requestScope.filename}" >
      <button class="button-three" type="submit" form="FilemeiDownload" >Download</button>
    </form>
    
  </div>
</div>
<footer>
    <p style="color:#eae7e7;">Copyright by <a href="https://github.com/suppi147/" style="color:#eae7e7;">suppi147</a> | All right reserved</p>
</footer>
</body>
</html>