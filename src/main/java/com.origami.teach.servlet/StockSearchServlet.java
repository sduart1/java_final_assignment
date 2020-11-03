package com.origami.teach.servlet;

import com.origami.teach.model.StockQuote;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Simple Example of how a servlet can access form submission data
 * and act on it accordingly.
 */
public class StockSearchServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        /**
         * All values passed from stockQuote.jsp form
         */
        String symbol = request.getParameter("symbol");
        String from = request.getParameter("from");
        String until = request.getParameter("until");

        /**
         * Convert String dates to Date type
         */

        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate date1 = LocalDate.parse(from);
        LocalDate date2 = LocalDate.parse(until);
        Date fromDate = Date.from(date1.atStartOfDay(defaultZoneId).toInstant());
        Date untilDate = Date.from(date2.atStartOfDay(defaultZoneId).toInstant());

        /**
         * Convert Date types(originally String types) to Calendar types
         */

        Calendar cal_from = Calendar.getInstance();
        cal_from.setTime(fromDate);
        Calendar cal_until = Calendar.getInstance();
        cal_until.setTime(untilDate);

        /**
         *  Create an instance of Database Stock Service and call the
         *  getQuote(String, Calendar, Calendar) method to return a
         *  list of stock quotes of type List<StockQuote>
         *
         *  If no stock data is found, exception is thrown
         */

        DatabaseStockService databaseStockService = new DatabaseStockService();
        List<StockQuote> stockQuote = null;
        try {
            stockQuote = databaseStockService.getQuote(symbol, cal_from, cal_until);
        } catch (StockServiceException e) {
            throw new ServletException(e.getMessage(), e);
        }


        /**
         * Get session and pass the list of stock quotes as attribute
         * If there were no quotes found, send over empty quote
         *
         * NOTE: I could not get my program to work without passing
         *      some type of String through my list and verifying it
         *      in the stockResponse.jsp page
         */
        HttpSession session = request.getSession();
        session.setAttribute("quote", stockQuote);


        /**
         * Request Dispatch to stockResponse.jsp
         */
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher =
                servletContext.getRequestDispatcher("/stockResponse.jsp");
        dispatcher.forward(request, response);


    }

}