package Manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.Bank;
import com.jsp.dto.Manager;
import com.jsp.service.ManagerService;

@WebServlet("/SaveManager")
public class SaveManager extends HttpServlet{
ManagerService managerService=new ManagerService();
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Manager manager= new Manager();
	Bank bank= new Bank();
	String name=req.getParameter("name");
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	
	manager.setManager_name(name);
	manager.setEmail(email);
	manager.setBank(bank);
	manager.setM_password(password);
	Manager manager2= managerService.saveManager(manager);
	ArrayList<Manager> managers=new ArrayList<>();
	managers.add(manager);
	if(manager2!=null) {
		System.out.println("================================================");
		System.out.println(manager2.getManager_name() + " " + manager2.getEmail() );
		System.out.println("============================");
		PrintWriter printWriter= resp.getWriter();
		printWriter.write("<html><body><h1>"+"Details of Manager are saved "+"</h1></body></html>");
		printWriter.write("<br><br>");
		printWriter.write("<html><body><h1>"+"Name of Manager is :  "+manager2.getManager_name()+"</h1></body></html>");
		printWriter.write("<br><br>");
		printWriter.write("<html><body><h1>"+"Password of Manager is :  "+manager2.getM_password()+"</h1></body></html>");
		printWriter.write("<br><br>");
		printWriter.write("<a href='BankController.jsp'>Main Menu</a>");
		printWriter.write("<br><br>");
		printWriter.write("<a href='ManagerController.jsp'>Back</a>");
//		RequestDispatcher requestDispatcher=req.getRequestDispatcher("ManagerController.jsp");
//		requestDispatcher.forward(req, resp);
	}else {
		System.out.println("============================");
		System.out.println("Bank not saved");
		System.out.println("============================");
		PrintWriter printWriter= resp.getWriter();
		printWriter.write("<html><body><h1>"+"Details of Manager are Not saved "+"</h1></body></html>");
//		printWriter.write("<br><br>");
//		printWriter.write("<html><body><h1>"+"Name of Manager is :  "+"name"+"</h1></body></html>");
		printWriter.write("<br><br>");
		printWriter.write("<a href='BankController.jsp'>Main Menu</a>");
		printWriter.write("<br><br>");
		printWriter.write("<a href='ManagerController.jsp'>Back</a>");
//		RequestDispatcher requestDispatcher=req.getRequestDispatcher("ManagerController.jsp");
//		requestDispatcher.forward(req, resp);
	}
	}
}
