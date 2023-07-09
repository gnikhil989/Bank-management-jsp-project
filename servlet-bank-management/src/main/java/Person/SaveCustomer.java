package Person;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.Pan;
import com.jsp.dto.Person;
import com.jsp.service.PersonService;

@WebServlet("/SaveCustomer")
public class SaveCustomer extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	PersonService personService= new PersonService();
	String name=req.getParameter("name");
	String password=req.getParameter("password");
	String email=req.getParameter("email");
	String gender=req.getParameter("gender");
	String Pan_number=req.getParameter("Pan");
	
	Person person= new Person();
	person.setName(name);
	person.setC_password(password);
	person.setEmail(email);
	person.setGender(gender);
	Pan pan= new Pan();
	pan.setPan_number(Pan_number);
	pan.setPerson(person);
	person.setPan(pan);
	Person person2= personService.savePersonDetails(person);
	System.out.println(name);
	System.out.println(email);
	System.out.println(gender);
	System.out.println(password);
	List<Person> persons= new ArrayList<>();
	persons.add(person2);
	HttpSession  httpSession= req.getSession();
	httpSession.setAttribute("c_name", name);
//	
//	if(pan!=null) {
//		
//		PrintWriter printWriter= resp.getWriter();
//		printWriter.write("<html><body><h1>"+"Waiting for Verification By Manager "+"</h1></body></html>");
////		printWriter.write("<br><br>");
////		printWriter.write("<html><body><h1>"+"Name of Manager is :  "+"name"+"</h1></body></html>");
//		printWriter.write("<br><br>");
//		printWriter.write("<a href='BankController.jsp'>Main Menu</a>");
//		printWriter.write("<br><br>");
//		printWriter.write("<a href='ManagerController.jsp'>Back</a>");
//	}
	if(person2!=null) {
		System.out.println("================================================");
		System.out.println(person2.getName() + " " + person2.getEmail() + " " + person2.getPerson_id());
		System.out.println("============================");
		PrintWriter printWriter= resp.getWriter();
		printWriter.write("<html><body><h1>"+"You Details are Saved : "+ name+"</h1></body></html>");
		printWriter.write("<html><body><h1>"+"Your Id is : "+ person2.getPerson_id()+"</h1></body></html>");
		printWriter.write("<br><br>");
		printWriter.write("<html><body><h1>"+"Your Password is : "+ person2.getC_password()+"</h1></body></html>");
		printWriter.write("<br><br>");
		printWriter.write("<html><body><h1>"+"Waiting for Verification By Manager "+"</h1></body></html>");
	
		printWriter.write("<br><br>");
		printWriter.write("<br><br>");
		printWriter.write("<a href='CustomerController.jsp'>Customer menu</a>");
		printWriter.write("<br><br>");
		printWriter.write("<a href='ManagerController.jsp'>Manager Menu</a>");
//		RequestDispatcher requestDispatcher=req.getRequestDispatcher("CustomerController.jsp");
//		requestDispatcher.forward(req, resp);
	}else {
		PrintWriter printWriter= resp.getWriter();
		System.out.println("============================");
		System.out.println("Bank not saved");
		System.out.println("============================");
		printWriter.write("<html><body><h1>"+"Wrong Input "+"</h1></body></html>");
		printWriter.write("<a href='CustomerController.jsp'>Customer menu</a>");
	}
}
}
