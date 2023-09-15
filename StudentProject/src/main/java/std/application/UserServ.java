package std.application;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import std.DAO.StudentDA;
import std.DAO.StudentDAOImpli;
import std.module.Student;


@WebServlet("/")
public class UserServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDA studentDAO;
	
	public void init() throws ServletException {
		studentDAO = new StudentDAOImpli();
		
	}	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		switch (action) 
		{
		case "/new":
			showNewForm(request, response);
			break;
	
		case "/insert":
			insertStudent(request, response);
			break;
			
		case "/delete":
			deleteStudent(request,response);
			break;
			
		case "/edit":
			showEditStudent(request, response);
			break;
			
		case "/update":
			updateStudent(request, response);
			break;
			
		default:
			listStudent(request, response);
			break;
							
		}
	
	}
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void insertStudent(HttpServletRequest request, HttpServletResponse response) {
		 String name = request.getParameter("name");
	        String gender = request.getParameter("gender");
	        String email = request.getParameter("email");
	        long phone = Long.parseLong(request.getParameter("phone"));
	        String branch = request.getParameter("branch");
	        Date dob = Date.valueOf(request.getParameter("dob"));
	        float percentage = Float.parseFloat(request.getParameter("percentage"));

	        Student student = new Student(0, name, gender, email, phone, branch, dob, percentage);
	        studentDAO.save(student);

	        try {
				response.sendRedirect("student");
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.delete(id);

        try {
			response.sendRedirect("student");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void showEditStudent(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Student student;
        try {
        	student = studentDAO.get(id);
        	request.setAttribute("student", student);
			RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) {
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
        try {
			response.sendRedirect("student");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void listStudent(HttpServletRequest request, HttpServletResponse response) {
		List<Student> students = studentDAO.getAll();
        request.setAttribute("students", students);
        try {
			request.getRequestDispatcher("list.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
