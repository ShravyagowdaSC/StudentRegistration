<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Student</title>
</head>
<body>
    <h2>Edit Student</h2>
    <form action="student?action=update" method="post">
        <input type="hidden" name="id" value="${student.id}" />
        <label for="name">Name:</label>
        <input type="text" name="name" value="${student.name}" /><br>
        <label for="gender">Gender:</label>
        <input type="text" name="gender" value="${student.gender}" /><br>
        <label for="email">Email:</label>
        <input type="email" name="email" value="${student.email}" /><br>
        <label for="phone">Phone:</label>
        <input type="text" name="phone" value="${student.phone}" /><br>
        <label for="branch">Branch:</label>
        <input type="text" name="branch" value="${student.branch}" /><br>
        <label for="dob">Date of Birth:</label>
        <input type="date" name="dob" value="${student.dob}" /><br>
        <label for="percentage">Percentage:</label>
        <input type="text" name="percentage" value="${student.percentage}" /><br>
        <input type="submit" value="Save" />
    </form>
    <br>
    <a href="student">Back to List</a>
</body>
</html>