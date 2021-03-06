package servlet;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Address;
import entity.User;
import exception.NoSuchRowException;
import manager.AddressManager;
import manager.UserManager;

@WebServlet("/changeAddress.html")
public class ChangeAddress extends HttpServlet{

	private static final long serialVersionUID = 3L;
	
	public ChangeAddress(){
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		EntityManagerFactory factory = (EntityManagerFactory) context.getAttribute("factory");
		
		AddressManager addressManager = new AddressManager(factory);
		UserManager userManager = new UserManager(factory);
		System.out.println("doGet test");

		try {
			int UID = Integer.parseInt(request.getParameter("id"));
		 	String street_name = request.getParameter("street_name");
			String housenumber = request.getParameter("housenumber");
			String city = request.getParameter("city");
			String postalcode = request.getParameter("postalcode");
			User user = userManager.findByID(UID);
			int AID = user.getAddress().getID();
			Address address = new Address(AID,street_name,housenumber," ",postalcode,city);
			System.out.println("test2: "+address.toString());
			addressManager.save(address);
			
			
			
			response.setContentType("text/html");
			response.getWriter().append("");
		} catch (NumberFormatException | NoSuchRowException e) {
			System.out.println(e.getMessage());
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			addressManager.close();
			userManager.close();
		}
		
		addressManager.close();
		userManager.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
