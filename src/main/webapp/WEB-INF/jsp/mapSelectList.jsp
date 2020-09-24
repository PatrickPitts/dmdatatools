<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Available Maps List</title>


</head>
<body>

<table>
    <tr th:each="mapName : ${mapNames}">
        <td>
            <a th:href="${mapRequestUrl}+${mapName}" th:text="${mapName}"/>
        </td>
    </tr>
</table>


</body>


</html>