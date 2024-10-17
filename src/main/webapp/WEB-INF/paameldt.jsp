<%-- Denne må alltid være med for å si at resultatet er av typen ... --%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%-- Denne tar bort whitespace i toppen av generert HTML --%>
<%@ page trimDirectiveWhitespaces="true" %>

<%-- Denne må være med for å kunne bruke <c:forEach>-taggen --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
  <head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="simple.css">
	<title>Påmeldingsbekreftelse</title>
  </head>
  <body>
    <h2>Påmeldingsbekreftelse</h2>
    <p>Påmeldingen er mottatt for</p>
    <p>
    &nbsp;&nbsp;&nbsp;Arne<br />
    &nbsp;&nbsp;&nbsp;Arnesen<br />
    &nbsp;&nbsp;&nbsp;90123456<br /> &nbsp;&nbsp;&nbsp;mann
    </p>
    <a href="deltagerliste">Gå til deltagerlisten</a>
  </body>
</html>
