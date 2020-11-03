<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="quote" class="java.util.ArrayList" scope="session">
    <c:set target='${quote}'  value='${sessionScope.get("quote")}'/>
</jsp:useBean>

<h1>Stock query results:</h1>

        <c:forEach items = "${quote}" var ="quote">
            <tr>
                Symbol: <td>${quote.getSymbol()}</td><br>
                Date: <td>${quote.getDate()}</td><br>
                Price: <td>$${quote.getPrice()}</td><br>
                <br>
            </tr>
        </c:forEach>


