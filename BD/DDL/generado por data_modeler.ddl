-- Generado por Oracle SQL Developer Data Modeler 4.1.1.888
--   en:        2016-05-10 19:41:26 CEST
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g




CREATE TABLE CATEGORIAS
  (
    ID_CAT     INTEGER NOT NULL ,
    NOMBRE_CAT VARCHAR2 (50) NOT NULL
  ) ;
ALTER TABLE CATEGORIAS ADD CONSTRAINT CATEGORIAS_PK PRIMARY KEY ( ID_CAT ) ;


CREATE TABLE PREGUNTAS
  (
    ID_PR             INTEGER NOT NULL ,
    TEXTO_PR          VARCHAR2 (200) NOT NULL ,
    CATEGORIAS_ID_CAT INTEGER NOT NULL
  ) ;
ALTER TABLE PREGUNTAS ADD CONSTRAINT PREGUNTAS_PK PRIMARY KEY ( ID_PR ) ;


CREATE TABLE RESPUESTAS
  (
    ID_RP           INTEGER NOT NULL ,
    TEXTO_RP        VARCHAR2 (200) NOT NULL ,
    VALOR_PR        CHAR (1) NOT NULL ,
    PREGUNTAS_ID_PR INTEGER NOT NULL
  ) ;
ALTER TABLE RESPUESTAS ADD CONSTRAINT RESPUESTAS_PK PRIMARY KEY ( ID_RP ) ;


ALTER TABLE PREGUNTAS ADD CONSTRAINT PREGUNTAS_CATEGORIAS_FK FOREIGN KEY ( CATEGORIAS_ID_CAT ) REFERENCES CATEGORIAS ( ID_CAT ) ON
DELETE CASCADE ;

ALTER TABLE RESPUESTAS ADD CONSTRAINT RESPUESTAS_PREGUNTAS_FK FOREIGN KEY ( PREGUNTAS_ID_PR ) REFERENCES PREGUNTAS ( ID_PR ) ON
DELETE CASCADE ;


-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             3
-- CREATE INDEX                             0
-- ALTER TABLE                              5
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
