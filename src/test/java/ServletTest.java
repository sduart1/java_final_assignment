import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.*;

import com.origami.teach.servlet.StockSearchServlet;
import org.junit.Test;
public class ServletTest {

    @Test
    public void testServlet() throws Exception {

        /**
         * Mock all Http types
         */
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ServletConfig servletConfig = mock(ServletConfig.class);
        ServletContext servletContext = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        /**
         * set parameters
         */
        when(request.getParameter("symbol")).thenReturn("APPL");
        when(request.getParameter("from")).thenReturn("1981-01-25");
        when(request.getParameter("until")).thenReturn("2020-02-15");
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);
        when(request.getRequestDispatcher("/stockResponse.jsp")).thenReturn(dispatcher);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        /**
         * create new mocked servlet, initialize, and pass mocked request + response
         */
        StockSearchServlet stockSearchServlet = new StockSearchServlet();
        stockSearchServlet.init(servletConfig);
        stockSearchServlet.doPost(request, response);


        servletContext = request.getSession().getServletContext();
        dispatcher =
                servletContext.getRequestDispatcher("/stockResponse.jsp");
        dispatcher.forward(request, response);

        verify(request, atLeast(1)).getParameter("symbol");
        writer.flush();
        assertTrue(stringWriter.toString().contains("Test"));

    }

}