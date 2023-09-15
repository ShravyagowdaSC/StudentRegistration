package std.application;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import std.DAO.StudentDA;
import std.DAO.StudentDAOImpli;
import std.module.Student;

@WebServlet("/student")
public class Userservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StudentDA studentDAO;

    public void init() {
        // Initialize the StudentDAO
        studentDAO = new StudentDAOImpli();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list"; // Default action is to list all students
        }

        switch (action) {
            case "list":
                listStudents(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteStudent(request, response);
                break;
            default:
                listStudents(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list"; // Default action is to list all students
        }

        switch (action) {
            case "create":
                createStudent(request, response);
                break;
            case "update":
                updateStudent(request, response);
                break;
            default:
                listStudents(request, response);
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> students = studentDAO.getAll();
        request.setAttribute("students", students);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDAO.get(id);
        request.setAttribute("student", student);
        request.getRequestDispatcher("edit.jsp").forward(request, response);
    }

    private void createStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        long phone = Long.parseLong(request.getParameter("phone"));
        String branch = request.getParameter("branch");
        Date dob = Date.valueOf(request.getParameter("dob"));
        float percentage = Float.parseFloat(request.getParameter("percentage"));

        Student student = new Student(0, name, gender, email, phone, branch, dob, percentage);
        studentDAO.save(student);

        response.sendRedirect("student");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        long phone = Long.parseLong(request.getParameter("phone"));
        String branch = request.getParameter("branch");
        Date dob = Date.valueOf(request.getParameter("dob"));
        float percentage = Float.parseFloat(request.getParameter("percentage"));

        Student student = new Student(id, name, gender, email, phone, branch, dob, percentage);
        studentDAO.update(student);

        response.sendRedirect("student");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.delete(id);

        response.sendRedirect("student");
    }
}