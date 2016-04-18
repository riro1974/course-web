package com.external.course.web;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import com.example.course.entity.Participant;
import com.example.course.entity.controller.ParticipantManager;

/**
 * Servlet implementation class ParticipantTestServlet
 */
@WebServlet("/ParticipantTestServlet")
public class ParticipantTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@PersistenceUnit
	protected EntityManagerFactory emf;
	@Resource
	protected UserTransaction utx;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParticipantTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ParticipantManager manager = new ParticipantManager();
		manager.setEmf(emf);
		Writer writer = response.getWriter();
		String name = request.getParameter("name");
		if (name == null) {
			writer.write("Enter a class participant last name");
			return;
		}
		List<Participant> list = manager.getParticipantByLastName(name);
		if (list.size() == 0) {
			writer.write("Class participant last name not found");
		}
		else {
			Participant whom = list.get(0);
			try {
	writer.write("The class fee for " + name + " is " +    whom.getSignedFee());
			} catch (Exception e) {
				writer.write("The class fee for " + name + " is " + e.getMessage());
			}	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
