<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Dashboard</title>
    <link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <script src="webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="scripts/index.js"></script>
</head>
<body>
<h1>Welcome!</h1>
<div>You are logged in via Google OAuth</div>

<div id="pages">
    <div class="btn-group" role="group">
        <button data-toggle="collapse" data-target="#uploadFile" class="btn btn-primary">
            Simple Upload Demo
        </button>
    </div>
    <div class="collapse" id="uploadFile">
        <div class="card card-body" id="simpleUpload">
            <a href="/create">Click here to begin upload</a>
        </div>
    </div>
</div>
</body>
</html>