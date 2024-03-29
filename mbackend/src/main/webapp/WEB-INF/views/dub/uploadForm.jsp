<html xmlns:th="http://www.thymeleaf.org">
<body>

<div th:if="${message}">
  <h2 th:text="${message}"/>
</div>

<div>
  <form method="POST" enctype="multipart/form-data" action="/dub/upload">
    <table>
      <tr><td>File to upload:</td><td><input type="file" name="uploaded_file" /></td></tr>
      <tr><td>Name:</td><td><input type="text" name="name" /></td></tr>
      <tr><td></td><td><input type="submit" value="Upload" /></td></tr>
    </table>
  </form>
</div>

<div>
  <ul>
    <li th:each="file : ${files}" th:text="${file}"></li>
  </ul>
</div>

</body>
</html>