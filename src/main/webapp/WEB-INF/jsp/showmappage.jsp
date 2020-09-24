<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Basic Map Display</title>


</head>
<body>

<img th:src="${mapurl}" width="200" height="200"/>
<table>
    <tr th:each="descriptor : ${mapDescriptors}">

    </tr>
</table>


</body>


</html>