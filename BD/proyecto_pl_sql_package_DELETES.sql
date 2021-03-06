CREATE OR REPLACE PACKAGE DELETES IS
PROCEDURE DELETE_CATEGORIA(V_NOMBRE IN CATEGORIAS.NOMBRE_CAT%TYPE);
PROCEDURE DELETE_PREGUNTA(V_ID_PR PREGUNTAS.ID_PR%TYPE);
PROCEDURE DELETE_RESPUESTA(V_ID_RP RESPUESTAS.ID_RP%TYPE);
END DELETES;

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



--PRUEBAS

EXECUTE DELETES.DELETE_CATEGORIA('PRUEBA');
