import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import utils.DatabaseConnectionException;

/**
 * Unit test for the DatabaseConnectionException
 */
public class DatabaseConnectionExceptionTest {

    /**
     * Initialize expected exception thrown
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * @throws DatabaseConnectionException Set the expected exception to be DatabaseConnectionException.
     *                                     Create an instance in which the exception is thrown.
     *                                     Verify the exception was correct. Null value is for an "unknown" cause.
     */
    @Test
    public void testConnection() throws DatabaseConnectionException {
        thrown.expect(DatabaseConnectionException.class);

        int testVal = 0;

        if (testVal != 1) {
            throw new DatabaseConnectionException("Testing Database Connection", null);
        }
    }
}
