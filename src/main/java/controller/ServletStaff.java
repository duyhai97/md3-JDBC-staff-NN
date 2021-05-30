package controller;

import jdk.nashorn.internal.ir.RuntimeNode;
import model.Category;
import model.Staff;
import service.jdbccategory.CategoryJDBC;
import service.jdbccategory.CategoryJDBCInterface;
import service.jdbcstaff.StaffJDBC;
import service.jdbcstaff.StaffJDBCInterface;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletStaff" ,urlPatterns = "/staff")
public class ServletStaff extends HttpServlet {
    StaffJDBCInterface staffer = new StaffJDBC();
    CategoryJDBCInterface categories = new CategoryJDBC();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action){
            case "":
                showListStaff(request,response);
                break;
            case "createStaff":
                showFormCreate(request,response);
                break;
            case "updateStaff":
                showFormEditStaff(request,response);
                break;
            case "deleteStaff":
                showFormDelete(request,response);
                break;
        }
    }

    private void showFormDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("staff/deleteStaff.jsp");
        int id =Integer.parseInt(request.getParameter("id"));
        Staff staff = this.staffer.selectById(id);
        request.setAttribute("staff", staff);
        dispatcher.forward(request,response);

    }

    private void showFormEditStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("staff/updateStaff.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        Staff staff = this.staffer.selectById(id);
        List<Category> categoryList = this.categories.selectAll();
        request.setAttribute("staff",staff);
        request.setAttribute("categoryList",categoryList);

        dispatcher.forward(request,response);
    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("staff/createStaff.jsp");
        List<Category> categoryList = this.categories.selectAll();
        request.setAttribute("categoryList",categoryList);
        dispatcher.forward(request,response);
    }

    private void showListStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Staff> staffList = this.staffer.selectAll();
        request.setAttribute("staffList", staffList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("staff/listStaff.jsp");
        dispatcher.forward(request,response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action){
            case "createStaff":
                createNewStaff(request,response);
                break;
            case "updateStaff":
                updateStaff(request,response);
                break;
            case "deleteStaff":
                deleteStaff(request,response);
                break;
        }
    }

    private void deleteStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.staffer.delete(id);
        request.setAttribute("message", "delete success");
        RequestDispatcher dispatcher = request.getRequestDispatcher("staff/deleteStaff.jsp");
        dispatcher.forward(request,response);

    }

    private void updateStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        Staff staff = new Staff(id,name,address);
        String [] categoryString = request.getParameterValues("categoryList");
        int[] categoryInt = new int[categoryString.length];
        for (int i = 0; i < categoryString.length; i++) {
            categoryInt[i] = Integer.parseInt(categoryString[i]);
        }
        this.staffer.update(id,staff,categoryInt);
        RequestDispatcher dispatcher = request.getRequestDispatcher("staff/updateStaff.jsp");
        request.setAttribute("message","update success");
        dispatcher.forward(request,response);
    }

    private void createNewStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        Staff staff = new Staff(name,address);
        String[] categoryString = request.getParameterValues("categoryList");
        int[] categoryInt = new int[categoryString.length];
        for (int i = 0; i < categoryString.length; i++) {
            categoryInt[i] = Integer.parseInt(categoryString[i]);
        }
        this.staffer.create(staff,categoryInt);
        RequestDispatcher dispatcher = request.getRequestDispatcher("staff/createStaff.jsp");
        request.setAttribute("message","create success");
        dispatcher.forward(request,response);

    }
}
