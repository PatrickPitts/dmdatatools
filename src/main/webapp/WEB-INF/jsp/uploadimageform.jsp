<html xmlns:th="https://www.thymeleaf.org">
<body>

<div th:if="${message}">
    <h2 th:text="${message}"></h2>
</div>
<div>
    <form method="post" enctype="multipart/form-data" action="/uploadmap">
        <table>
            <tr>
                <td>File to upload:</td>
                <td><input type="file" name="file"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Upload"></td>
            </tr>
        </table>
    </form>
</div>
<div>
    <ul>
        <li th:each="file : ${files}">
            <a th:href="${file}" th:text="${file}"/>
        </li>
    </ul>
</div>
</body>


</html>