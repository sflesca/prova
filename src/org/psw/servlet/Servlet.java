package org.psw.servlet;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/ciccio")
public class Servlet extends HttpServlet {

    @Resource(lookup = "java:/PostgresDS")
    DataSource db;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection con = db.getConnection();

            Statement stmt = con.createStatement();
            String query = "select * from gruppo";
            ResultSet rs = stmt.executeQuery(query);

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.print("<center><h1>Group Details</h1></center>");
            out.print("<html><body>");
            out.print("<table border=\"1\" cellspacing=10 cellpadding=5>");
            out.print("<tr><th>Group ID</th>");
            out.print("<th>Group Name</th></tr>");

            while (rs.next()) {
                out.print("<tr>");
                out.print("<td>" + rs.getInt("id") + "</td>");
                out.print("<td>" + rs.getString("nome") + "</td>");
                out.print("</tr>");
            }
            out.print("</table></body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
