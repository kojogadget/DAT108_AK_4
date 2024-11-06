DROP TABLE deltager;

CREATE TABLE deltager (
    mobil CHARACTER (8) PRIMARY KEY,
    hash CHARACTER (64) NOT NULL,
    salt CHARACTER (32) NOT NULL,
    fornavn CHARACTER VARYING (40),
    etternavn CHARACTER VARYING (40),
    kjonn CHARACTER VARYING (6)
);

INSERT INTO deltager (mobil, hash, salt, fornavn, etternavn, kjonn)
VALUES (
    '91111111', 
    'EAC24ED7D5C061CDC1F92E563855CA5D56B950BD6B82DD089AB23024C1C3833F', 
    '0845ADF61744DCBC79F34061F46D2E2C', 
    'Donald', 
    'Larsen', 
    'mann'
    );

INSERT INTO deltager (mobil, hash, salt, fornavn, etternavn, kjonn)
VALUES (
    '92222222', 
    '84BB0057B14757A7C0A8A3806FCDE629D13D179DC5378A2BE30C5104C086BF95', 
    'A0EE41A24CD1B94BBF1B3D8C240BA128', 
    'Dolly', 
    'Hansen', 
    'kvinne'
    );

INSERT INTO deltager (mobil, hash, salt, fornavn, etternavn, kjonn)
VALUES (
    '93333333', 
    '2FDDCBC5479B57E5E4F60000450DA6E84D245D5F56446B089677C2EF46F19C0B', 
    '1DCCF48E69C90AF1EEF9ADEAA12AEC94', 
    'Ole', 
    'Olsen', 
    'mann'
    );

INSERT INTO deltager (mobil, hash, salt, fornavn, etternavn, kjonn)
VALUES (
    '94444444', 
    '5D42AF835D9DF82AFADCE76E38C774B9A13CAE7827F4FC7166A9B468E6F48928', 
    '91709C98C16C4E34A44330DF46581992', 
    'Dole', 
    'Knutsen', 
    'mann'
    );

INSERT INTO deltager (mobil, hash, salt, fornavn, etternavn, kjonn)
VALUES (
    '95555555', 
    'CE983273CDCD0C1B53A02AF4DBF4FFB73E1ED230D1E58BB9CF1644F3B70DAB59', 
    '3D4A0F2839EC8160C9846A6E41AD0E91', 
    'Doffen', 
    'Jonsen', 
    'mann'
    );

