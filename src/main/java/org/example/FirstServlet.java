package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/FirstServlet") // URL mapping for this servlet
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Setting response type as HTML
        response.setContentType("text/html");

        // Getting PrintWriter to send output to browser
        PrintWriter out = response.getWriter();

        // Printing message on browser
        out.println("<h2>Hello World! My First Servlet is Working </h2>");

        // Closing writer
        out.close();
    }
}
