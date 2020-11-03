package com.origami.teach.servlet;

import com.origami.teach.model.StockQuote;

import java.util.Calendar;
import java.util.List;

/**
 * This API describes how to get stock data from an external resource.
 */
public interface StockService {


    /**
     * Get a historical list of stock quotes for the provide symbol
     *
     * @param symbol the stock symbol to search for
     * @param from   the date of the first stock quote
     * @param until  the date of the last stock quote
     * @return a list of StockQuote instances
     * @throws StockServiceException if using the service generates an exception.
     *                               If this happens, trying the service may work, depending on the actual cause of the
     *                               error.
     */
    List<StockQuote> getQuote(String symbol, Calendar from, Calendar until) throws StockServiceException;

}

