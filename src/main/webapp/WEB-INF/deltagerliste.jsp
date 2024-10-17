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
            <tr>
				<td align="center">&#9792;</td>
				<td>Anne Panne</td>
				<td>234 56 789</td>
			</tr>
		
            <tr>
				<td align="center">&#9794;</td>
				<td>Arne Arnesen</td>
				<td>901 23 456</td>
			</tr>
		
            <tr>
				<td align="center">&#9794;</td>
				<td>Lars-Petter Helland</td>
				<td>123 45 679</td>
			</tr>
		
            <tr>
				<td align="center">&#9794;</td>
				<td>Per Viskelær</td>
				<td>345 34 534</td>
			</tr>
		
            <tr>
				<td align="center">&#9792;</td>
				<td>Xx-x Xxx</td>
				<td>123 21 378</td>
			</tr>

	</table>
</body>
</html>
