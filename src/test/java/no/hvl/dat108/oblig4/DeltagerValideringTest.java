package no.hvl.dat108.oblig4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

	@Test
	void testFornavnErUgyldigFormatert() {
		testDeltager.setFornavn(" Ole");
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Tillate tegn er kun bokstaver, mellomrom og enkel bindestrek mellom delnavn");
		testDeltager.setFornavn("Ole ");
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Tillate tegn er kun bokstaver, mellomrom og enkel bindestrek mellom delnavn");
		testDeltager.setFornavn("O1e");
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Tillate tegn er kun bokstaver, mellomrom og enkel bindestrek mellom delnavn");
		testDeltager.setFornavn("Ole!");
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Tillate tegn er kun bokstaver, mellomrom og enkel bindestrek mellom delnavn");
	}

	@Test
	void testEtternavnErObligatorisk() {
		testDeltager.setEtternavn(null);
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Etternavn er obligatorisk");
	}

	@Test
	void testEtternavnErUgyldigFormatert() {
		testDeltager.setEtternavn(" Duck");
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Tillate tegn er kun bokstaver og enkel bindestrek mellom delnavn");
		testDeltager.setEtternavn("Duck ");
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Tillate tegn er kun bokstaver og enkel bindestrek mellom delnavn");
		testDeltager.setEtternavn("D0ck");
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Tillate tegn er kun bokstaver og enkel bindestrek mellom delnavn");
		testDeltager.setEtternavn("Duck!");
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Tillate tegn er kun bokstaver og enkel bindestrek mellom delnavn");
	}

	@Test
	void testMobilErObligatorisk() {
		testDeltager.setMobil(null);
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Mobilnummer er obligatorisk");
	}

	@Test
	void testMobilErUgyldigFormatert() {
		testDeltager.setMobil("123456");
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Nummer er kun gyldig ved 8 siffer");
		testDeltager.setMobil("12345678 ");
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Nummer er kun gyldig ved 8 siffer");
		testDeltager.setMobil(" 12345678");
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Nummer er kun gyldig ved 8 siffer");
		testDeltager.setMobil("abcdefgh");
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Nummer er kun gyldig ved 8 siffer");
	}

	@Test
	void testPassordErObligatorisk() {
		testDeltager.setPassord(null);
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Passord er obligatorisk");
	}

	@Test
	void testPassordErUgyldigFormatert() {
		testDeltager.setPassord("Passord1");
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Passordet må være minst 8 tegn og minimum inneholde en liten og en stor bokstav, et tall og et symbol");
		testDeltager.setPassord("passord1!");
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Passordet må være minst 8 tegn og minimum inneholde en liten og en stor bokstav, et tall og et symbol");
		testDeltager.setPassord("Passord!");
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Passordet må være minst 8 tegn og minimum inneholde en liten og en stor bokstav, et tall og et symbol");
		testDeltager.setPassord("PASSORD1!");
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Passordet må være minst 8 tegn og minimum inneholde en liten og en stor bokstav, et tall og et symbol");
	}


	@Test
	void testKjonnErObligatorisk() {
		testDeltager.setKjonn(null);
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Kjønn er obligatorisk");
	}

	@Test
	void testKjonnErUgyldigFormatert() {
		testDeltager.setKjonn("annet");
		sjekkAtDeltagerErUgyldigMedDenneFeilmeldingen("Kjønn kan kun være 'mann' eller 'kvinne' i denne oppgaven");
		testDeltager.setKjonn("mannlig kvinne");
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
