package Manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.Manager;
import com.jsp.dto.Person;
import com.jsp.service.ManagerService;
import com.jsp.service.PersonService;

@WebServlet("/Approve")
public class Approve extends HttpServlet {
	ManagerService managerService = new ManagerService();
	PersonService personService = new PersonService();

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String Customerid= req.getParameter("CustomerId");;
		String Status= req.getParameter("Status");
		int c_id=Integer.parseInt(Customerid);
		HttpSession httpSession=req.getSession();
		int managerid=(int)httpSession.getAttribute("managerid");
		Person person=personService.getPersonById(c_id);
		Manager manager=managerService.approvePersonById(c_id, managerid);
		PrintWriter printWriter= resp.getWriter();
		printWriter.write("<html><body><h1>"+"Applicant Name : "+ person.getName()+"</h1></body></html>");
		printWriter.write("<html><body><h1>"+"Status Approved "+"</h1></body></html>");
		printWriter.write("<br><br>");
		
		printWriter.write("<a href='CustomerController.jsp'>Customer menu</a>");
		printWriter.write("<br><br>");
		printWriter.write("<a href='ManagerController.jsp'>Manager Menu</a>");
		if(manager!=null) {
			
//			RequestDispatcher requestDispatcher= req.getRequestDispatcher("GetAllCustomer.jsp");
//			requestDispatcher.forward(req, resp);
			
		}else {
			System.out.println("wrong input");
		}
		}
}
