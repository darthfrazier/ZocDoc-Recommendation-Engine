package com.ibm.devworks.examples.java.lll;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weka.core.Instance;

/**
 * Servlet implementation class DispatchServlet
 */
@WebServlet({  "/home"})
public class DispatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final WebsiteTitle myapp = new WebsiteTitle();
	private static final WekaTest kmeans = new WekaTest();
	private static final String[] jsonKeys = {"Id", "Gender", "Language", "Latitude", "Longitude", "Race", 
											"Income", "Psy_Symptoms", "Phy_Symptoms", "Other_Symptoms",
											"Doctor", "Rating"};

    /**
     * Default constructor. 
     */
    public DispatchServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			kmeans.generateModel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = request.getRequestURI().substring(request.getContextPath().length());
		request.setAttribute("myapp", myapp);
		RequestDispatcher view = request.getRequestDispatcher(path + ".jsp");
        view.forward(request, response); 		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Instance newInst = null;
		int cluster = -1;
		String doctor = null;
		String[] instanceData = new String[jsonKeys.length];
		for (int i = 0; i < jsonKeys.length; i++) {
			instanceData[i] = request.getParameter(jsonKeys[i]);
		}
		try {
			newInst = ClustererClient.createInstance(instanceData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cluster = kmeans.clusterInstance(newInst);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doctor = kmeans.getRecommendedDoctor(kmeans.getInstancesInCluster(cluster));
		PrintWriter out = response.getWriter();
		out.write(doctor);
		out.flush();
		out.close();
	}

}
