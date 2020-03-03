package org.psw.servlet;

import org.psw.ejb.ClienteManagerBean;
import org.psw.model.Cliente;

import javax.annotation.Resource;
import javax.ejb.EJB;
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

    @EJB(name="ClienteManagerEJB")
    ClienteManagerBean cmb;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        PrintWriter out = response.getWriter();
        try {
            con = db.getConnection();

            Statement stmt = con.createStatement();
            String query = "select * from gruppo";
            ResultSet rs = stmt.executeQuery(query);

            response.setContentType("text/html");
            out.print("<html><body>");
            out.print("<center><h1>Group Details</h1></center>");
            out.print("<table border=\"1\" cellspacing=10 cellpadding=5>");
            out.print("<tr><th>Group ID</th>");
            out.print("<th>Group Name</th></tr>");

            while (rs.next()) {
                out.print("<tr>");
                out.print("<td>" + rs.getInt("id") + "</td>");
                out.print("<td>" + rs.getString("nome") + "</td>");
                out.print("</tr>");
            }
            out.print("</table>");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(con!=null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        out.print("<table border=\"1\" cellspacing=10 cellpadding=5>");
        out.print("<tr><th>Group ID</th>");
        out.print("<th>Group Name</th></tr>");

        for(Cliente c: cmb.getClienti()) {
            out.print("<tr>");
            out.print("<td>" + c.getId() + "</td>");
            out.print("<td>" + c.getNome() + "</td>");
            out.print("</tr>");
        }
        out.print("</table></body></html>");

    }
}
