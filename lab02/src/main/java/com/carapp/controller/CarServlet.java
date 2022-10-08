package com.carapp.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carapp.model.Car;
import com.carapp.model.CarBean;

@WebServlet("/car")
public class CarServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
     
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String category = request.getParameter("category");
            String make = request.getParameter("make");
            int year = Integer.parseInt(request.getParameter("year"));

            Car car = new Car();
            CarBean carBean = new CarBean();
            ServletContext ctx = request.getServletContext();
            
            if(category.equals("")) {
            	// if the category is empty, read the "defaultCategory" context attribute
            	car.setCategory((String) ctx.getAttribute("defaultCategory"));
            } else {
            	car.setCategory(category);	
            }
            car.setMake(make);
            car.setYear(year);

            carBean.addCar(car);

            // programmatically navigate to "result.jsp" to show new car table
            request.getRequestDispatcher("result.jsp").forward(request, response);
        }
}
