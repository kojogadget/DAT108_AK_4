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
	<title>Logg inn</title>
  </head>
  <body>
	<h2>Logg inn</h2>

	<p style="color:red;">
		<c:forEach var="feilmelding" items="${redirectMessage}">
			${feilmelding}<br>
		</c:forEach>
	</p>

	<fieldset>
	  <form action="logginn" method="post" id="form">
	    <label for="mobil">Mobil</label>
	    <input type="text" 
		    name="mobil" 
		    autocomplete="off"
		    autofocus
		    required>

	    <label for="passord">Passord</label>
	    <input type="password" 
		    name="passord" 
		    autocomplete="off"
		    required>
	    <br>
	    <input type="submit" value="Logg inn"> 
	    &nbsp;
	    <a href="paamelding">Påmelding</a>
	  </form>
	</fieldset>
  </body>
</html>
