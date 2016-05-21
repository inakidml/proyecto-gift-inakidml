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

--PRUEBAS
VARIABLE ID_DEV NUMBER;
EXECUTE INSERTS.INSERTAR_CATEGORIA('PRUEBA', :ID_DEV);
PRINT ID_DEV;
DELETE FROM CATEGORIAS;
SELECT * FROM CATEGORIAS;
EXECUTE INSERTS.INSERTAR_PREGUNTA('�Pregunta numero uno de prueba?', 'PRUEBA', :ID_DEV);
PRINT ID_DEV;
SELECT * FROM PREGUNTAS;
EXECUTE INSERTS.INSERTAR_RESPUESTA('RESPUESTA UNO A LA PREGUNTA UNO', 0, 0001, :ID_DEV);
EXECUTE INSERTS.INSERTAR_RESPUESTA('RESPUESTA DOS A LA PREGUNTA UNO', 1, 0001, :ID_DEV);
EXECUTE INSERTS.INSERTAR_RESPUESTA('RESPUESTA TRES A LA PREGUNTA UNO', 1, 0001, :ID_DEV);
PRINT ID_DEV;
SELECT * FROM RESPUESTAS;
