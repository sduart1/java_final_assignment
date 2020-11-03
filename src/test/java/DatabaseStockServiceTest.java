import com.origami.teach.model.StockQuote;
import com.origami.teach.servlet.DatabaseStockService;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class DatabaseStockServiceTest {

    @Test
    public void testGetQouteRange() throws Exception {
        DatabaseStockService databaseStockService = new DatabaseStockService();

        String symbol = "GOOG";
        Calendar from = Calendar.getInstance();
        from.set(1981, 01, 25, 0, 0, 0);
        Calendar until = Calendar.getInstance();
        until.set(2020, 02, 15, 0, 0, 0);

        List<StockQuote> stockQuote = databaseStockService.getQuote(symbol, from, until);
        System.out.println(stockQuote);
        assertNotNull("Verify we can get a stock quote from the db", stockQuote);

    }


}
