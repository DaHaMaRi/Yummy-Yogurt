package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Ingredient;
import entity.User;
import entity.Yogurt;
import exception.NoSuchRowException;
import manager.IngredientManager;
import manager.UserManager;
import manager.YogurtManager;


@WebServlet("/Mixer")
public final class Mixer extends HttpServlet {
	
	private static final long serialVersionUID = -1033141283317370043L;

	
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) 
			throws ServletException, IOException 
	{
		response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
	}

	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) 
			throws ServletException, IOException 
	{
		final String payload = request.getReader().readLine();
		final ServletContext context = request.getServletContext();
		final EntityManagerFactory factory = (EntityManagerFactory) context.getAttribute("factory");
		
		final UserManager userManager = new UserManager(factory);
		final YogurtManager yogurtManager = new YogurtManager(factory);
		final IngredientManager ingredientManager = new IngredientManager(factory);
		
		try {
			final String yogurtname = this.parseYogurtname(payload);
			final User owner = userManager.findByID(1);
			
			final Yogurt yogurt = new Yogurt(yogurtname, owner, true);
			
			final List<String> nameOfIngredients = this.parseIngredientnames(payload);
			for(String ingredientname : nameOfIngredients) {
				Ingredient ingredient = ingredientManager.findByName(ingredientname);
				yogurt.addToRecipe(ingredient);
			}
			
			yogurtManager.save(yogurt);
			
			response.setContentType("application/json");
			response.getWriter().append(String.valueOf(yogurt.getID()));
			
		} catch (NoSuchRowException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
		} finally {
			userManager.close();
			yogurtManager.close();
			ingredientManager.close();
		}
	}
	
	
	private String parseYogurtname(final String payload) 
	{
		final String yogurtname;
		String[] formValues = payload.split("&");
		String[] values = formValues[0].split("=");
		
		if(values[1].contains("+"))
			values[1] = values[1].replace("+", " ");
		
		yogurtname = values[1];
		return yogurtname;
	}
	
	private List<String> parseIngredientnames(final String payload) 
	{
		final List<String> nameOfIngredients = new ArrayList<>();
		
		String[] formValues = payload.split("&");
		for(int i = 1; i < formValues.length; i++) {
			String[] values = formValues[i].split("=");
			
			if(values[0].contains("%C3%9F"))
				values[0] = values[0].replace("%C3%9F", "�");
			
			nameOfIngredients.add(values[0]);
		}
		
		return nameOfIngredients;
	}

}
