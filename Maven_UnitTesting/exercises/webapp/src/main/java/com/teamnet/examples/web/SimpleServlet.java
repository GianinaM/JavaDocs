package com.teamnet.examples.web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Created by Gianina.Carp on 7/10/2017.
 */
public class SimpleServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("SimpleServlet Executed");
        out.flush();
        out.close();
    }
}