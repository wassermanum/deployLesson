package exercise.servlet;

import exercise.Data;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

@WebServlet("/companies")
public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException {

        // BEGIN
        String param = request.getParameter("search");

        List<String> companies = Data
                .getCompanies()
                .stream()
                .filter(x -> x.contains(Objects.nonNull(param) ? param : ""))
                .toList();

        if (companies.isEmpty()) {
            response.getWriter().write("Companies not found");
        } else {
            companies.forEach(x -> {
                try {
                    response.getWriter().println(x);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        // END
    }
}
