REM **************************************************************************
REM Copyright Mourad OUZIRI
REM Base de données de l application Web <Billets de trains>
REM ***************************************************************************

SET FEEDBACK OFF

PROMPT -->> Preparation de tables

DROP TABLE LIGNES CASCADE CONSTRAINTS PURGE;
DROP TABLE DEPARTS CASCADE CONSTRAINTS PURGE;
DROP TABLE BILLETS CASCADE CONSTRAINTS PURGE;
DROP TABLE VOYAGEURS CASCADE CONSTRAINTS PURGE;

PROMPT -->> Creation des tables

CREATE TABLE LIGNES
(
NumeroLigne NUMBER(5) CONSTRAINT pk_lignes PRIMARY KEY,
VilleDepart VARCHAR2(20),
VilleDestination VARCHAR2(20),
Duree NUMBER(5)
);

CREATE TABLE DEPARTS
(
NumeroDepart NUMBER(5) CONSTRAINT pk_departs PRIMARY KEY,
NumeroLigne NUMBER(5) CONSTRAINT fk_dep_lig REFERENCES LIGNES(NumeroLigne),
DateDep DATE,
Capacite NUMBER(5)
);

CREATE TABLE VOYAGEURS
(
LoginVoyageur VARCHAR2(20) CONSTRAINT pk_voyageurs PRIMARY KEY,
PassVoyageur VARCHAR2(20),
NomVoyageur VARCHAR2(20),
AdresseVoyageur VARCHAR2(50)
);

CREATE TABLE BILLETS
(
NumeroBillet NUMBER(5) CONSTRAINT pk_billets PRIMARY KEY,
NumeroDepart NUMBER(5) CONSTRAINT fk_bil_dep REFERENCES DEPARTS(NumeroDepart),
LoginVoyageur VARCHAR2(20) CONSTRAINT fk_bil_voy REFERENCES VOYAGEURS(LoginVoyageur)
);

PROMPT -->> Insertion de donnees

insert into LIGNES values (1, 'Paris', 'Lyon', 120);
insert into LIGNES values (2, 'Paris', 'Bordeaux', 720);
insert into LIGNES values (3, 'Paris', 'Marseille', 900);
insert into LIGNES values (4, 'Brest', 'Paris', 620);

insert into DEPARTS values (10, 1, '12-03-2015', 3);
insert into DEPARTS values (20, 1, '15-03-2015', 2);
insert into DEPARTS values (30, 1, '22-03-2015', 5);
insert into DEPARTS values (40, 2, '30-03-2015', 4);
insert into DEPARTS values (50, 4, '05-04-2015', 5);
insert into DEPARTS values (60, 4, '28-05-2015', 300);

PROMPT -->> Creation de la base de donnees terminee

commit;

SET FEEDBACK ON
