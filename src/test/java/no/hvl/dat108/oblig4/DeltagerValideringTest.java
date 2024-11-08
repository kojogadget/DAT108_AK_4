package no.hvl.dat108.oblig4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import no.hvl.dat108.oblig4.model.DeltagerSkjema;

public class DeltagerValideringTest {

	private Validator validator;
	private DeltagerSkjema testDeltager;

	@BeforeEach
	void setup() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();

		testDeltager = new DeltagerSkjema();
		testDeltager.setFornavn("Mikke");
		testDeltager.setEtternavn("Mus");
		testDeltager.setMobil("12345678");
		testDeltager.setPassord("Passord1!");
		testDeltager.setKjonn("mann");
	}
	
	@Test
	void testDeltagerHarGyldigeInitVerdier() {
		Set<ConstraintViolation<DeltagerSkjema>> violations = validator.validate(testDeltager);
		assertTrue(violations.isEmpty());
	}

	@Test
	void testFornavnErObligatorisk() {
		testDeltager.setFornavn(null);
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Fornavn er obligatorisk");
	}

	@ParameterizedTest
	@ValueSource(strings = {" Ole", "Ole ", "O1e", "Ole!"})
	void testFornavnErUgyldigFormatert(String navnFraListe) {
		testDeltager.setFornavn(navnFraListe);
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Tillate tegn er kun bokstaver, mellomrom og enkel bindestrek mellom delnavn");
	}

	@Test
	void testEtternavnErObligatorisk() {
		testDeltager.setEtternavn(null);
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Etternavn er obligatorisk");
	}

	@ParameterizedTest
	@ValueSource(strings = {" Duck", "Duck ", "D0ck", "Duck!"})
	void testEtternavnErUgyldigFormatert(String navnFraListe) {
		testDeltager.setEtternavn(navnFraListe);
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Tillate tegn er kun bokstaver og enkel bindestrek mellom delnavn");
	}

	@Test
	void testMobilErObligatorisk() {
		testDeltager.setMobil(null);
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Mobilnummer er obligatorisk");
	}

	@ParameterizedTest
	@ValueSource(strings = {"123456", "12345678 ", " 12345678", "abcdefgh"})
	void testMobilErUgyldigFormatert(String mobilFraListe) {
		testDeltager.setMobil(mobilFraListe);
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Nummer er kun gyldig ved 8 siffer");
	}

	@Test
	void testPassordErObligatorisk() {
		testDeltager.setPassord(null);
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Passord er obligatorisk");
	}

	@ParameterizedTest
	@ValueSource(strings = {"Passord1", "passord1!", "Passord!", "PASSORD1!"})
	void testPassordErUgyldigFormatert(String passFraListe) {
		testDeltager.setPassord(passFraListe);
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Passordet må være minst 8 tegn og minimum inneholde en liten og en stor bokstav, et tall og et symbol");
	}


	@Test
	void testKjonnErObligatorisk() {
		testDeltager.setKjonn(null);
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Kjønn er obligatorisk");
	}

	@ParameterizedTest
	@ValueSource(strings = {"annet", "mannlig kvinne"})
	void testKjonnErUgyldigFormatert(String kjonnFraListe) {
		testDeltager.setKjonn(kjonnFraListe);
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Kjønn kan kun være 'mann' eller 'kvinne' i denne oppgaven");
	}


	private void sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen(String feilmelding) {
		Set<ConstraintViolation<DeltagerSkjema>> violations = validator.validate(testDeltager);

		assertFalse(violations.isEmpty());
		assertEquals(1, violations.size());

		String violationMessage = violations.iterator().next().getMessage();
		assertEquals(feilmelding, violationMessage);
	}
}
