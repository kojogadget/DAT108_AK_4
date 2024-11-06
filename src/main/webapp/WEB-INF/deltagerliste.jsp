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
      <br>
	<p>Innlogget som: ${deltager.mobil} / ${deltager.fornavn}&nbsp;${deltager.etternavn}</p>
	<h2>Deltagerliste</h2>
	<table>
		<tr>
		    <th>Kjønn</th>
		    <th align="left">Navn</th>
		    <th align="left">Mobil</th>
		</tr>
		<c:forEach var="deltagerItem" items="${deltagerListe}">
		<tr style="${deltagerItem.mobil == deltager.mobil ? "background: green;" : ""}">
		    <td align="center">${deltagerItem.kjonn == "mann" ? "&#9794;" : "&#9792;"}</td>
		    <td>${deltagerItem.fornavn}&nbsp;${deltagerItem.etternavn}</td>
		    <td>${deltagerItem.mobil}</td>
		</tr>
		</c:forEach>
	</table>
	<form action="loggut" method="post">
	    <input type="submit" value="Logg ut" />
	</form>
</body>
</html>
