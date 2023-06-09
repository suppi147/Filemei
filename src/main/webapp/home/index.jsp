<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
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
    <a href="#" style="text-decoration: none;">
      <h1>filemei</h1>
    </a>
    <p>Upload as many files as you like up to <strong>1 GB</strong> and get a link to share&#128230;</p>
    <div class="upload-container">
      <div class="border-container">
        <div class="icons fa-4x">
          <i class="fas fa-file-image" data-fa-transform="shrink-3 down-2 left-6 rotate--45"></i>
          <i class="fas fa-file-alt" data-fa-transform="shrink-2 up-4"></i>
          <i class="fas fa-file-pdf" data-fa-transform="shrink-3 down-2 right-6 rotate-45"></i>
        </div>
          
          <form id="FilemeiUpload" action="http://localhost/upload" method="post" enctype="multipart/form-data" class="hidden">
            <input type="file" name="fileToUpload" id="fileToUpload" multiple>
            
            <script>
              
              $('#fileToUpload').on('change', function() {
                const fileInput = document.getElementById('fileToUpload');
                
                
                var filename = this.value;
                var lastIndex = filename.lastIndexOf("");
                if (lastIndex >= 0) {
                  filename = filename.substring(lastIndex + 1);
                }
                var files = $('#fileToUpload')[0].files;
                for (var i = 0; i < files.length; i++) {
                  var fileSize = (files[i].size / 1024 / 1024).toFixed(2);
                  if (files[i].size > 1073741824) {
                    fileInput.value='';
                    if(i==0 && files.length==1){
                      emptyAlertdanger("The file is larger than 1 GB &#10060",2000);
                    }
                    else{
                      emptyAlertdanger("The files are larger than 1 GB &#10060",2000);
                    }
                  }
                  
                }
                fileCount += files.length;
                showFileCount();
              });
              /*$('#fileToUpload').on('change', function() {
                const fileInput = document.getElementById('fileToUpload');
                var numb = $(this)[0].files[0].size / 1024 / 1024 / 1024;
                numb = numb.toFixed(1);
                if (numb > 1) {
                  fileInput.value='';
                  emptyAlertdanger("The file is Larger than 1 GB &#10060",2000);
                }
              });*/
              </script>
          </form>
      </div>
    </div>
    <button id="submit" class="button-two" type="submit" form="FilemeiUpload">Upload</button>
    <script>
      function emptyAlertdanger(msg,duration)
        {
        var el = document.createElement("div");
        el.setAttribute("class","alert alert-danger");
        el.setAttribute("style","position:fixed;top:80%;left:1%;");
        el.innerHTML = msg;
        setTimeout(function(){
          el.parentNode.removeChild(el);
        },duration);
        document.body.appendChild(el);
        }
      function emptyAlert(msg,duration)
      {
      var el = document.createElement("div");
      el.setAttribute("class","alert alert-warning");
      el.setAttribute("style","position:fixed;top:80%;left:1%;");
      el.innerHTML = msg;
      setTimeout(function(){
      el.parentNode.removeChild(el);
      },duration);
      document.body.appendChild(el);
      }
            document.getElementById("submit").onclick = function(e) {
      if (document.getElementById("fileToUpload").value == "") {
        e.preventDefault();
        emptyAlert("No files uploaded &#10060",1000);
      }
      }
    </script>
  </div>
</div>
<footer>
  <p style="color:#eae7e7;">Copyright by <a href="https://github.com/suppi147/" style="color:#eae7e7;">suppi147</a> | All right reserved</p>
</footer>
</body>
</html>
    