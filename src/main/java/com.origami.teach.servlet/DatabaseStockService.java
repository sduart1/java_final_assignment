package com.origami.teach.servlet;

import com.origami.teach.model.StockQuote;
import utils.DatabaseUtils;
import utils.DatabaseConnectionException;
import com.origami.teach.servlet.StockServiceException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * An implementation of the StockService interface that gets
 * stock data from a database.
 */
public class DatabaseStockService implements StockService {

    /**
     * Get a historical list of stock quotes for the provided symbol
     *
     * @param symbol the stock symbol to search for
     * @param from   the date of the first stock quote
     * @param until  the date of the last stock quote
     * @return a list of StockQuote instances
     * @throws StockServiceException if using the service generates an exception.
     *                               If this happens, trying the service may work, depending on the actual cause of the
     *                               error.
     */
    @Override
    public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until) throws StockServiceException {

        List<StockQuote> stockQuotes = null;
        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();

            /*
             * Format the from date
             */
            java.util.Date date = from.getTime();
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fromFormatted = format1.format(date);

            /*
             * Format the until date
             */
            java.util.Date untilDate = until.getTime();
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String untilFormatted = format2.format(untilDate);

            /*
             * Execute SQL statement
             * SELECT * FROM quotes WHERE time BETWEEN 'from' and 'until'
             */
            String rangeString = "select * from quotes where symbol = '" + symbol + "' and time between '" + fromFormatted + "' and '"
                    + untilFormatted + "'";

            ResultSet resultSet = statement.executeQuery(rangeString);
            stockQuotes = new ArrayList<>(resultSet.getFetchSize());
            while (resultSet.next()) {
                String symbolValue = resultSet.getString("symbol");
                Date time = resultSet.getDate("time");
                BigDecimal price = resultSet.getBigDecimal("price");
                stockQuotes.add(new StockQuote(price, time, symbolValue));
            }

        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }
        if (stockQuotes.isEmpty()) {
            throw new StockServiceException("There is no stock data for:" + symbol);
        }

        return stockQuotes;
    }

}  

