package com.external.course.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.course.entity.Course;
import com.example.course.entity.controller.CourseManager;

/**
 * Servlet implementation class CourseControllerServlet
 */
@WebServlet("/CourseControllerServlet")
public class CourseControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseControllerServlet() {
    	super();
    	CourseManager courseManager = CourseManager.getCourseManager();
        // TODO Auto-generated constructor stub
    	Course course = new Course();
    	course.setCode("E650");
    	course.setDescription("Enterprise Java Development");
    	course.setDuration(2);
    	course.setFee(1000);
    	try {
			courseManager.createCourse(course);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	CourseManager courseManager = CourseManager.getCourseManager();
		String create = request.getParameter("create");
		String read = request.getParameter("read");
		String update = request.getParameter("update");
		String delete = request.getParameter("delete");
		String code = request.getParameter("code");
			Course course = null;
		String message = "";
		boolean foundCourse = false;
		try {
			List<Course> courses = courseManager.getCourseByCode(code);
			if (courses.size()>0) {
				course = courses.get(0);
			}
			if (course != null) {
				foundCourse = true;
			}
			else {
				course = new Course();
				course.setCode(code);
			}
			if (create != null) {
				if (foundCourse) {
					message = "Course already exists";
				}
				else {
					course = getCourse(request, course);		
					message = "Course created";
					courseManager.createCourse(course);
				}
			}
			else if (read != null) {
				if (foundCourse) {
					message = "Course found";
				}
				else {
					message = "Course not found";
				}
			}
			else if (update != null) {
				if (foundCourse) {
					course = getCourse(request, course);
					message = "Course updated";
				}
				else {
					message = "Course not found";
				}
			}
			else if (delete != null) {
				if (foundCourse) {
					courseManager.deleteCourse(course);
					message = "Course deleted";
				}
				else {
					message = "Course not found";
				}
			}
		} catch (Exception e) {
			message = e.getMessage();
		}
		request.setAttribute("course", course);
		request.setAttribute("message", message);
		request.getRequestDispatcher("courseDisplay.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	private Course getCourse(HttpServletRequest request, Course course) {
		course.setDescription(request.getParameter("description"));
		course.setDuration(Integer.parseInt(request.getParameter("duration")));
		course.setFee(Integer.parseInt(request.getParameter("fee")));
		return course;
	}

}
