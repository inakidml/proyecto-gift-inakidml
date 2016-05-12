CREATE OR REPLACE PACKAGE UPDATES IS
PROCEDURE UPDATE_CATEGORIA(V_OLD_NOMBRE IN CATEGORIAS.NOMBRE_CAT%TYPE,V_NEW_NOMBRE IN CATEGORIAS.NOMBRE_CAT%TYPE);
PROCEDURE UPDATE_PREGUNTA(V_ID_PR PREGUNTAS.ID_PR%TYPE, V_TEXTO_PR PREGUNTAS.TEXTO_PR%TYPE, V_ID_CAT_PR PREGUNTAS.ID_CAT_PR%TYPE);
PROCEDURE UPDATE_RESPUESTA(V_ID_RP RESPUESTAS.ID_RP%TYPE, V_TEXTO_RP RESPUESTAS.TEXTO_RP%TYPE, V_VALOR_PR RESPUESTAS.VALOR_PR%TYPE);
END UPDATES;

CREATE OR REPLACE PACKAGE BODY UPDATES IS
PROCEDURE UPDATE_CATEGORIA(V_OLD_NOMBRE IN CATEGORIAS.NOMBRE_CAT%TYPE,V_NEW_NOMBRE IN CATEGORIAS.NOMBRE_CAT%TYPE) AS
BEGIN
UPDATE CATEGORIAS SET NOMBRE_CAT=V_NEW_NOMBRE WHERE NOMBRE_CAT=V_OLD_NOMBRE;
END UPDATE_CATEGORIA;
PROCEDURE UPDATE_PREGUNTA(V_ID_PR PREGUNTAS.ID_PR%TYPE, V_TEXTO_PR PREGUNTAS.TEXTO_PR%TYPE, V_ID_CAT_PR PREGUNTAS.ID_CAT_PR%TYPE) AS
BEGIN
UPDATE PREGUNTAS SET TEXTO_PR=V_TEXTO_PR, ID_CAT_PR=V_ID_CAT_PR WHERE ID_PR=V_ID_PR;
END UPDATE_PREGUNTA;
PROCEDURE UPDATE_RESPUESTA(V_ID_RP RESPUESTAS.ID_RP%TYPE, V_TEXTO_RP RESPUESTAS.TEXTO_RP%TYPE, V_VALOR_PR RESPUESTAS.VALOR_PR%TYPE) AS
BEGIN
UPDATE RESPUESTAS SET TEXTO_RP=V_TEXTO_RP, VALOR_PR=V_VALOR_PR WHERE ID_RP=V_ID_RP;
END UPDATE_RESPUESTA;
END UPDATES;




--PRUEBAS

