REM **************************************************************************
REM Copyright Mourad OUZIRI
REM Base de données de l application Web <TousAuSoleilWeb>
REM ***************************************************************************

SET FEEDBACK OFF

PROMPT -->> Preparation de tables

DROP TABLE VOLS CASCADE CONSTRAINTS PURGE;
DROP TABLE COMPTES CASCADE CONSTRAINTS PURGE;
DROP TABLE RESERVATIONS CASCADE CONSTRAINTS PURGE;

PROMPT -->> Creation des tables

CREATE TABLE VOLS
(
NumeroVol NUMBER(5) CONSTRAINT pk_Vols PRIMARY KEY,
Destination VARCHAR2(25),
DateDepart Date,
NombrePlaces NUMBER(3),
Prix NUMBER(8,2)
);

CREATE TABLE COMPTES
(
Login VARCHAR2(25) CONSTRAINT pk_login PRIMARY KEY,
Mdp VARCHAR2(25) CONSTRAINT notnull_mdp NOT NULL,
Nom VARCHAR2(25),
Prenom VARCHAR2(25),
Role VARCHAR2(14) NOT NULL CONSTRAINT check_role CHECK (Role IN ('Client', 'Administrateur'))
);

CREATE TABLE RESERVATIONS
(
NumeroRes NUMBER(5) CONSTRAINT pk_reservation PRIMARY KEY,
Login VARCHAR2(25) CONSTRAINT fk_res_cpt REFERENCES COMPTES(Login),
NumeroVol NUMBER(5) CONSTRAINT fk_res_vol REFERENCES VOLS(NumeroVol),
NombrePlaces NUMBER(2) CONSTRAINT check_nbplaces CHECK (NombrePlaces > 0),
Confirmation CHAR(3) DEFAULT 'NON' CHECK (Confirmation IN ('OUI', 'NON')) 
);

PROMPT -->> Creation du compte administrateur

insert into COMPTES (Login, Mdp, Role) values ('admin', 'admin', 'Administrateur');

commit;

SET FEEDBACK ON