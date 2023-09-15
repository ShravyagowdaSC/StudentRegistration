<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student List</title>
</head>
<body>
    <h2>Student List</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Branch</th>
            <th>Date of Birth</th>
            <th>Percentage</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <tr>
                <td><c:out value="${student.id}" /></td>
                <td><c:out value="${student.name}" /></td>
                <td><c:out value="${student.gender}" /></td>
                <td><c:out value="${student.email}" /></td>
                <td><c:out value="${student.phone}" /></td>
                <td><c:out value="${student.branch}" /></td>
                <td><c:out value="${student.dOB}" /></td>
                <td><c:out value="${student.percentage}" /></td>
                <td>
                    <a href="student?action=edit&id=${student.id}">Edit</a> |
                    <a href="student?action=delete&id=${student.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="student?action=create">Add Student</a>
</body>
</html>