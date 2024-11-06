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
	<title>Påmelding</title>
        <script defer src="main.js"></script>
  </head>
  <body>
	<h2>Påmelding</h2>

	<p style="color:red;">
		<c:forEach var="feilmelding" items="${feilmeldinger}">
			${feilmelding}<br>
		</c:forEach>
	</p>

	<fieldset>
	  <form action="paamelding" method="post" id="form">
	    <label for="fornavn">Fornavn</label>
	    <input type="text" 
		    name="fornavn" 
		    autocomplete="off"
		    autofocus
		    pattern="\s*\p{L}{2,}((\s+|-)\p{L}{2,})*\s*" 
		    title="Tillate tegn er kun bokstaver, mellomrom og enkel bindestrek mellom delnavn"
		    value="${fornavn}"
		    required>

	    <label for="etternavn">Etternavn</label>
	    <input type="text" 
		    name="etternavn" 
		    autocomplete="off"
		    pattern="\s*\p{L}{2,}((-)\p{L}{2,})*\s*" 
		    title="Tillate tegn er kun bokstaver og enkel bindestrek mellom delnavn"
		    value="${etternavn}" 
		    required>

	    <label for="mobil">Mobil (8 siffer)</label>
	    <input type="text" 
		    name="mobil" 
		    autocomplete="off"
		    pattern="\s*\d{8}\s*" 
		    title="Nummer er kun gyldig ved 8 siffer"
		    value="${mobil}"
		    required>

	    <label for="passord">Passord</label>
	    <input type="password" 
		    name="passord" 
		    pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"
		    title="Passordet må være minst 8 tegn og minimum inneholde en liten og en stor bokstav, et tall og et symbol"
		    required>

	    <label for="passordBekreft">Passord repetert</label>
	    <input type="password" 
		    name="passordBekreft" 
		    pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"
		    required>

	    <label>Kjønn</label>
	    <div class="skjemaKjonn">
	      <input type="radio" 
		      name="kjonn"  
		      value="mann" 
		      checked>
	      <label>Mann</label>

	      <input type="radio" 
		      name="kjonn" 
		      value="kvinne">
	      <label>Kvinne</label>
	    </div>

	    <br>
	    <input type="submit" value="Meld meg på"> 
	  </form>
	  Hvis du er påmeldt: <a href="logginn">Logg inn</a>
	</fieldset>
  </body>
</html>
