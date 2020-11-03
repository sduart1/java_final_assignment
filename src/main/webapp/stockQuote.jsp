<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Stock Survey</title>
</head>
<body>


Enter Stock Info: <br>

<P></P>

<form name="myform" action="servlets/RequestStockQuote/" method="post">

    Symbol:  <input type="text" name="symbol" title = "symbol">
    <br>
    From: <input type="date" name="from" title="from">
    <br>
    Until: <input type="date" name="until" title="until">
    <br>

    <input type="SUBMIT" value="OK">
    <input type="HIDDEN" name="submit" value="true">
</form>
</body>
</html>
