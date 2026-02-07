package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // Predefined user (UC2)
    private static final String PREDEFINED_USERNAME = "Lishitha";
    private static final String PREDEFINED_PASSWORD = "Password@1";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        out.println("<h2>Login Result</h2>");

        // UC3: Validate Username
        if (!isValidName(username)) {
            out.println("<p style='color:red;'>Invalid Name </p>");
            out.println("<p>Name must start with Capital letter and have minimum 3 characters.</p>");
            return;
        }

        // UC4: Validate Password
        String passwordError = validatePassword(password);
        if (passwordError != null) {
            out.println("<p style='color:red;'>Invalid Password </p>");
            out.println("<p>" + passwordError + "</p>");
            return;
        }

        // UC2: Check predefined user login
        if (username.equals(PREDEFINED_USERNAME) && password.equals(PREDEFINED_PASSWORD)) {
            out.println("<p style='color:green;'>Login Successful </p>");
            out.println("<h3>Welcome " + username + "!</h3>");
        } else {
            out.println("<p style='color:red;'>Login Failed </p>");
            out.println("<p>Username or Password is incorrect.</p>");
        }
    }

    // UC3 Name rule
    private boolean isValidName(String name) {
        if (name == null) return false;
        return name.matches("^[A-Z][a-zA-Z]{2,}$");
    }

    // UC4 Password rule
    private String validatePassword(String password) {

        if (password == null) return "Password cannot be empty.";

        // Rule 1: minimum 8 chars
        if (password.length() < 8) {
            return "Rule1: Password must be minimum 8 characters.";
        }

        // Rule 2: at least 1 uppercase
        if (!password.matches(".*[A-Z].*")) {
            return "Rule2: Password must contain at least 1 uppercase letter.";
        }

        // Rule 3: at least 1 numeric
        if (!password.matches(".*[0-9].*")) {
            return "Rule3: Password must contain at least 1 number.";
        }

        // Rule 4: exactly 1 special character
        int specialCount = password.replaceAll("[a-zA-Z0-9]", "").length();
        if (specialCount != 1) {
            return "Rule4: Password must contain exactly 1 special character.";
        }

        return null; // valid
    }
}
