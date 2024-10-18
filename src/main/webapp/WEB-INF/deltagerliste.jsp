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
	<title>Deltagerliste</title>
  </head>
  <body>
	<h2>Deltagerliste</h2>
	<table>
		<tr>
		    <th>Kjønn</th>
		    <th align="left">Navn</th>
		    <th align="left">Mobil</th>
		</tr>
		<c:forEach var="deltager" items="${deltagerListe}">
		<tr>
		    <td align="center">${deltager.kjonn == "mann" ? "&#9794;" : "&#9792;"}</td>
		    <td>${deltager.fornavn}&nbsp;${deltager.etternavn}</td>
		    <td>${deltager.mobil}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>
