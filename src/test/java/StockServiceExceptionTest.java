import com.origami.teach.servlet.StockServiceException;
import org.junit.Rule;
import org.junit.rules.ExpectedException;


import org.junit.Test;

/**
 * Unit tests for StockServiceException custom exception class
 */
public class StockServiceExceptionTest {

    /**
     * Initialize the expected exception thrown
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * @throws StockServiceException Set the expected exception to StockServiceException and create an
     *                               instance of the exception being thrown. Verify it was the right one.
     */
    @Test
    public void StockServiceExceptionTest() throws StockServiceException {
        thrown.expect(StockServiceException.class);

        int testVal = 0;

        if (testVal != 1) {
            throw new StockServiceException("Expected exception thrown");
        }

    }


}
