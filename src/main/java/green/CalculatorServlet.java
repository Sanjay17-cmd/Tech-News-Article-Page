package green;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        
        try {
            String number1Str = request.getParameter("num1");
            String number2Str = request.getParameter("num2");
            String operation = request.getParameter("operation");
            
            double num1 = Double.parseDouble(number1Str);
            double num2 = Double.parseDouble(number2Str);
            double result = 0;
            String operationName = "";
            boolean validOperation = true;
            
            switch (operation) {
                case "add":
                    result = num1 + num2;
                    operationName = "Addition";
                    break;
                case "subtract":
                    result = num1 - num2;
                    operationName = "Subtraction";
                    break;
                case "multiply":
                    result = num1 * num2;
                    operationName = "Multiplication";
                    break;
                case "divide":
                    if (num2 != 0) {
                        result = num1 / num2;
                        operationName = "Division";
                    } else {
                        validOperation = false;
                    }
                    break;
                default:
                    validOperation = false;
            }
            
            out.print("<!DOCTYPE html>");
            out.print("<html>");
            out.print("<head><title>Calculator Result</title></head>");
            out.print("<body>");
            out.print("<h2>Calculation Result</h2>");
            
            if (validOperation) {
                out.print("<p><b>First Number:</b> " + num1 + "</p>");
                out.print("<p><b>Second Number:</b> " + num2 + "</p>");
                out.print("<p><b>Operation:</b> " + operationName + "</p>");
                out.print("<h3><b>Final Result:</b> " + result + "</h3>");
            } else {
                out.print("<p style='color:red;'><b>Error:</b> Cannot divide by zero or invalid operation!</p>");
            }
            
            out.print("<br><a href='index.html'>Go Back to Calculator</a>");
            out.print("</body>");
            out.print("</html>");
            
        } catch (NumberFormatException e) {
            out.print("<p style='color:red;'><b>Error:</b> Please enter valid numeric inputs.</p>");
        } finally {
            out.close();
        }
    }
}
