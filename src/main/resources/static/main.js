"use strict";

const form = document.getElementById("form");

form.addEventListener("submit", () => {
    const [fornavn, etternavn, mobil, passord, passordBekreft] = form.getElementsByTagName("input");

    if (!validerInputFelt(fornavn, "Feil format")) return;
    if (!validerInputFelt(etternavn, "Feil format")) return;
    if (!validerInputFelt(mobil, "Feil format")) return;
    if (!validerInputFelt(passord, "Feil format")) return;

    if (!validerPassord(passord, passordBekreft)) return;

    fornavn.value = formaterNavn(fornavn.value);
    etternavn.value = formaterNavn(etternavn.value);
    mobil.value = formaterMobil(mobil.value);
    passordBekreft.remove();
})

/**
 * Validerer om et gitt inputfelt har en verdi, og viser en feilmelding hvis feltet er tomt.
 *
 * @param {HTMLInputElement} felt - Inputfeltet som skal valideres.
 * @param {string} feilmelding - Feilmelding som skal vises hvis feltet er tomt.
 * @returns {boolean} Returnerer true hvis feltet er gyldig, false hvis ikke.
 */
function validerInputFelt(felt, feilmelding) {
    felt.setCustomValidity("");
    felt.reportValidity();
    if (!felt.checkValidity()) {
        felt.setCustomValidity(feilmelding);
        felt.reportValidity();
        felt.focus();
        return false;
    };
    return true;
};

/**
 * Validerer inputfeltene for passord er like.
 *
 * @param {HTMLInputElement} passord - Første passord som er sjekket via regexp.
 * @param {HTMLInputElement} passordBekreft - Andre passord for å sjekke om er lik den første.
 * @returns {boolean} Returnerer true hvis de er like, false hvis ikke.
 */
function validerPassord(passord, passordBekreft) {
    passord.setCustomValidity("");
    passord.reportValidity();
    if (passord.value != passordBekreft.value) {
        passordBekreft.value = null;
        passord.setCustomValidity("Passordene må være lik!");
        passord.reportValidity();
        passord.focus();
        return false;
    };
    return true;
};

/**
 * Formaterer et navn slik at første bokstav i hvert ord, samt første bokstav etter bindestreker, blir kapitalisert.
 *
 * @param {string} navn - Navnet som skal formateres.
 * @returns {string} Det formaterte navnet med korrekt kapitalisering.
 */
function formaterNavn(navn) {
    const navnReg = /(^|-|\s)(\p{Ll})/gu;
    navn.toLocaleLowerCase(navigator.language);

    return navn.trim().replace(navnReg, 
        (_, symbol, tegn) => `${symbol}${tegn.toLocaleUpperCase(navigator.language)}`
    );
};

/**
 * Formaterer et mobilnummer slik at den fjerner alle mellomrom og kun beholder tallene.
 *
 * @param {string} mobil - Nummeret som skal formateres.
 * @returns {string} Det formaterte nummeret.
 */
function formaterMobil(mobil) {
    return mobil.trim().split(" ").join("");
};

