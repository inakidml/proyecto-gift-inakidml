CREATE TABLE CATEGORIAS (
    ID_CAT     NUMBER(3) CONSTRAINT CA_ID_PK PRIMARY KEY,
    NOMBRE_CAT VARCHAR2 (50) NOT NULL
);

CREATE TABLE PREGUNTAS(
    ID_PR             NUMBER(4) CONSTRAINT PR_ID_PK PRIMARY KEY,
    TEXTO_PR          VARCHAR2 (200) NOT NULL,
    ID_CAT_PR         NUMBER(3) NOT NULL, 
	CONSTRAINT PR_CA_FK FOREIGN KEY (ID_CAT_PR) REFERENCES CATEGORIAS ON DELETE CASCADE

);

CREATE TABLE RESPUESTAS(
    ID_RP           NUMBER(4) CONSTRAINT RP_ID_PK PRIMARY KEY,
    TEXTO_RP        VARCHAR2 (200) NOT NULL,
    VALOR_PR        NUMBER(1) NOT NULL,
    ID_PR_RP        NUMBER(4) NOT NULL,
	CONSTRAINT RP_PR_FK FOREIGN KEY ( ID_PR_RP ) REFERENCES PREGUNTAS ON DELETE CASCADE
);


--CABECERAS------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE PACKAGE INSERTS IS
PROCEDURE INSERTAR_CATEGORIA(V_NOMBRE IN CATEGORIAS.NOMBRE_CAT%TYPE, 
                             V_ID_DEV OUT NUMBER);
PROCEDURE INSERTAR_PREGUNTA(V_TEXTO IN PREGUNTAS.TEXTO_PR%TYPE, 
                            V_CAT IN CATEGORIAS.NOMBRE_CAT%TYPE, 
                            V_ID_DEV OUT NUMBER);
PROCEDURE INSERTAR_RESPUESTA(V_TEXTO IN RESPUESTAS.TEXTO_RP%TYPE,
                             V_VALOR IN RESPUESTAS.VALOR_PR%TYPE, 
                             V_ID_PR IN RESPUESTAS.ID_PR_RP%TYPE,
                             V_ID_DEV OUT NUMBER);
END INSERTS;

CREATE OR REPLACE PACKAGE UPDATES IS
PROCEDURE UPDATE_CATEGORIA(V_OLD_NOMBRE IN CATEGORIAS.NOMBRE_CAT%TYPE,V_NEW_NOMBRE IN CATEGORIAS.NOMBRE_CAT%TYPE);
PROCEDURE UPDATE_PREGUNTA(V_ID_PR PREGUNTAS.ID_PR%TYPE, V_TEXTO_PR PREGUNTAS.TEXTO_PR%TYPE, V_ID_CAT_PR PREGUNTAS.ID_CAT_PR%TYPE);
PROCEDURE UPDATE_RESPUESTA(V_ID_RP RESPUESTAS.ID_RP%TYPE, V_TEXTO_RP RESPUESTAS.TEXTO_RP%TYPE, V_VALOR_PR RESPUESTAS.VALOR_PR%TYPE);
END UPDATES;

CREATE OR REPLACE PACKAGE DELETES IS
PROCEDURE DELETE_CATEGORIA(V_NOMBRE IN CATEGORIAS.NOMBRE_CAT%TYPE);
PROCEDURE DELETE_PREGUNTA(V_ID_PR PREGUNTAS.ID_PR%TYPE);
PROCEDURE DELETE_RESPUESTA(V_ID_RP RESPUESTAS.ID_RP%TYPE);
END DELETES;

CREATE OR REPLACE PACKAGE SELECTS IS
TYPE C_CURSOR IS REF CURSOR;
TYPE PR_CURSOR IS REF CURSOR;
TYPE RP_CURSOR IS REF CURSOR;

PROCEDURE SELECT_CATEGORIAS(V_CAT OUT C_CURSOR);
PROCEDURE SELECT_PREGUNTAS(V_ID_CAT IN PREGUNTAS.ID_CAT_PR%TYPE, V_PR OUT PR_CURSOR);
PROCEDURE SELECT_RESPUESTAS(V_ID_PR IN RESPUESTAS.ID_PR_RP%TYPE, V_RP OUT RP_CURSOR);
END SELECTS;


--CUERPOS----------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE PACKAGE BODY INSERTS IS

PROCEDURE INSERTAR_CATEGORIA(V_NOMBRE IN CATEGORIAS.NOMBRE_CAT%TYPE, V_ID_DEV OUT NUMBER)AS
V_ID CATEGORIAS.ID_CAT%TYPE;
BEGIN
SELECT MAX(ID_CAT)INTO V_ID FROM CATEGORIAS;
IF V_ID IS NOT NULL THEN
V_ID := V_ID+1;
ELSE
V_ID:=1;
END IF;
INSERT INTO CATEGORIAS VALUES(V_ID, V_NOMBRE);
V_ID_DEV:=V_ID;
COMMIT;
END INSERTAR_CATEGORIA;

PROCEDURE INSERTAR_PREGUNTA(V_TEXTO IN PREGUNTAS.TEXTO_PR%TYPE, 
                            V_CAT IN CATEGORIAS.NOMBRE_CAT%TYPE, 
                            V_ID_DEV OUT NUMBER)
                            AS                            
V_ID_CAT PREGUNTAS.ID_CAT_PR%TYPE;
BEGIN
SELECT ID_CAT INTO V_ID_CAT FROM CATEGORIAS WHERE NOMBRE_CAT=V_CAT;
SELECT MAX(ID_PR) INTO V_ID_DEV FROM PREGUNTAS;
IF V_ID_DEV IS NOT NULL THEN
V_ID_DEV := V_ID_DEV+1;
ELSE
V_ID_DEV:=1;
END IF;
INSERT INTO PREGUNTAS VALUES(V_ID_DEV, V_TEXTO, V_ID_CAT);
COMMIT;
EXCEPTION
WHEN NO_DATA_FOUND THEN
RAISE_APPLICATION_ERROR(-20001, 'CATEGORIA NO EXISTE');
END INSERTAR_PREGUNTA;

PROCEDURE INSERTAR_RESPUESTA(V_TEXTO IN RESPUESTAS.TEXTO_RP%TYPE,
                             V_VALOR IN RESPUESTAS.VALOR_PR%TYPE, 
                             V_ID_PR IN RESPUESTAS.ID_PR_RP%TYPE,
                             V_ID_DEV OUT NUMBER) AS
BEGIN
SELECT MAX(ID_RP) INTO V_ID_DEV FROM RESPUESTAS;
IF V_ID_DEV IS NOT NULL THEN
V_ID_DEV := V_ID_DEV+1;
ELSE
V_ID_DEV:=1;
END IF;
INSERT INTO RESPUESTAS VALUES(V_ID_DEV, V_TEXTO, V_VALOR, V_ID_PR);
COMMIT;
END INSERTAR_RESPUESTA;

END INSERTS;


CREATE OR REPLACE PACKAGE BODY UPDATES IS

PROCEDURE UPDATE_CATEGORIA(V_OLD_NOMBRE IN CATEGORIAS.NOMBRE_CAT%TYPE,V_NEW_NOMBRE IN CATEGORIAS.NOMBRE_CAT%TYPE) AS
BEGIN
UPDATE CATEGORIAS SET NOMBRE_CAT=V_NEW_NOMBRE WHERE NOMBRE_CAT=V_OLD_NOMBRE;
COMMIT;
END UPDATE_CATEGORIA;

PROCEDURE UPDATE_PREGUNTA(V_ID_PR PREGUNTAS.ID_PR%TYPE, V_TEXTO_PR PREGUNTAS.TEXTO_PR%TYPE, V_ID_CAT_PR PREGUNTAS.ID_CAT_PR%TYPE) AS
BEGIN
UPDATE PREGUNTAS SET TEXTO_PR=V_TEXTO_PR, ID_CAT_PR=V_ID_CAT_PR WHERE ID_PR=V_ID_PR;
COMMIT;
END UPDATE_PREGUNTA;

PROCEDURE UPDATE_RESPUESTA(V_ID_RP RESPUESTAS.ID_RP%TYPE, V_TEXTO_RP RESPUESTAS.TEXTO_RP%TYPE, V_VALOR_PR RESPUESTAS.VALOR_PR%TYPE) AS
BEGIN
UPDATE RESPUESTAS SET TEXTO_RP=V_TEXTO_RP, VALOR_PR=V_VALOR_PR WHERE ID_RP=V_ID_RP;
COMMIT;
END UPDATE_RESPUESTA;

END UPDATES;

CREATE OR REPLACE PACKAGE BODY DELETES IS
PROCEDURE DELETE_CATEGORIA(V_NOMBRE IN CATEGORIAS.NOMBRE_CAT%TYPE) AS
BEGIN
DELETE FROM CATEGORIAS WHERE NOMBRE_CAT=V_NOMBRE;
COMMIT;
END DELETE_CATEGORIA;
PROCEDURE DELETE_PREGUNTA(V_ID_PR PREGUNTAS.ID_PR%TYPE) AS
BEGIN
DELETE FROM PREGUNTAS WHERE ID_PR=V_ID_PR;
COMMIT;
END DELETE_PREGUNTA;
PROCEDURE DELETE_RESPUESTA(V_ID_RP RESPUESTAS.ID_RP%TYPE) AS
BEGIN
DELETE FROM RESPUESTAS WHERE ID_RP=V_ID_RP;
COMMIT;
END DELETE_RESPUESTA;
END DELETES;

CREATE OR REPLACE PACKAGE BODY SELECTS IS
PROCEDURE SELECT_CATEGORIAS(V_CAT OUT C_CURSOR)AS
BEGIN
OPEN V_CAT FOR
		SELECT * FROM CATEGORIAS;
EXCEPTION
WHEN NO_DATA_FOUND THEN
DBMS_OUTPUT.PUT_LINE('NINGUNA CATEGOR�A');
END SELECT_CATEGORIAS;

PROCEDURE SELECT_PREGUNTAS(V_ID_CAT IN PREGUNTAS.ID_CAT_PR%TYPE, V_PR OUT PR_CURSOR)AS
BEGIN
OPEN V_PR FOR
		SELECT * FROM PREGUNTAS WHERE ID_CAT_PR=V_ID_CAT;
EXCEPTION
WHEN NO_DATA_FOUND THEN
DBMS_OUTPUT.PUT_LINE('NINGUNA PREGUNTA');
END SELECT_PREGUNTAS;

PROCEDURE SELECT_RESPUESTAS(V_ID_PR IN RESPUESTAS.ID_PR_RP%TYPE, V_RP OUT RP_CURSOR)AS
BEGIN
OPEN V_RP FOR
		SELECT * FROM RESPUESTAS WHERE ID_PR_RP=V_ID_PR;
EXCEPTION
WHEN NO_DATA_FOUND THEN
DBMS_OUTPUT.PUT_LINE('NINGUNA RESPUESTA');
END SELECT_RESPUESTAS;
END SELECTS;
