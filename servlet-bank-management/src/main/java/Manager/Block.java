package Manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.Manager;
import com.jsp.dto.Person;
import com.jsp.service.ManagerService;
import com.jsp.service.PersonService;

@WebServlet("/Block")
public class Block extends HttpServlet{
	ManagerService managerService = new ManagerService();
	PersonService personService = new PersonService();

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String Customerid= req.getParameter("CustomerId");
		String Managerid= req.getParameter("ManagerId");
		String Status= req.getParameter("Status");
		int c_id=Integer.parseInt(Customerid);
		int m_id= Integer.parseInt(Managerid);
		Person person=personService.getPersonById(c_id);
	
		Manager manager=managerService.unApprovePersonById(c_id, m_id);
		PrintWriter printWriter= resp.getWriter();
		printWriter.write("<html><body><h1>"+"Applicant Name : "+ person.getName()+"</h1></body></html>");
		printWriter.write("<html><body><h1>"+"Status UnApproved "+"</h1></body></html>");
		printWriter.write("<br><br>");
		
		printWriter.write("<a href='CustomerController.jsp'>Customer menu</a>");
		printWriter.write("<br><br>");
		printWriter.write("<a href='ManagerController.jsp'>Manager Menu</a>");
		if(person!=null) {
			
//			RequestDispatcher requestDispatcher= req.getRequestDispatcher("GetAllCustomer.jsp");
//			requestDispatcher.forward(req, resp);
//			System.out.println("================================================");
//			System.out.println( person.getName()+ " " + person.getStatus() + " " + person.getPerson_id());
//			System.out.println("============================");
//			PrintWriter printWriter= resp.getWriter();
//			printWriter.write("<html><body><h1>"+"You have Selected : "+ bank1.getBank_name()+"</h1></body></html>");
//			printWriter.write("<html><body><h1>"+"Your Branch is : "+ bank1.getCity()+"</h1></body></html>");
//			RequestDispatcher requestDispatcher=req.getRequestDispatcher("Login.jsp");
//			requestDispatcher.forward(req, resp);
//			printWriter.write("<a href='Login.jsp'>Click here to Proceed further</a>");
//			printWriter.write("<br><br>");
//			
//		}else {
//			System.out.println("wrong input");
//		}
//}
}}}