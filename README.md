# Proyecto: Moodle GIFT

Un profesor del departamento de Informática de Egibide nos ha pedido una herramienta para gestionar las preguntas de tipo test en un curso de Moodle.

Nos ha pasado un fichero de texto que contiene algunas [preguntas en formato GIFT](./preguntas_ejemplo.gift), específico de esta plataforma.

Nos piden:

- El programa debe ser capaz de gestionar preguntas y categorías de preguntas (nueva, editar, borrar, ver todas...)
- Exportar preguntas a [formato GIFT](https://docs.moodle.org/all/es/Formato_GIFT), bien por categorías enteras o seleccionando preguntas individuales de una o varias categorías.

## Requisitos mínimos de funcionamiento

- Se debe construir una aplicación Java 8 con GUI, instalable en el ordenador del cliente.
- Se crearán en memoria las estructuras de datos adecuadas para trabajar con los datos.
- Se utilizará la carga diferida de las preguntas de una colección.
- El almacenamiento se hará con una [máquina virtual](https://github.com/Egibide-PROG2015/vagrant-oracle-12-ee-linux) Vagrant y la base de datos Oracle 12c. 
- En la base de datos, todos los procedimientos para la gestión de la misma tendrán que estar contenidos en un paquete. No se permite, sin causa justificada, lanzar consultas directamente al servidor. 
- El tipo de preguntas que se deben almacenar será como mínimo el que se nos ha proporcionado en el archivo de ejemplo (preguntas de tipo "selección múltiple" con cuatro opciones y una respuesta correcta). 

## Extras

- Se valorará que la aplicación sea capaz de gestionar otros tipos de preguntas de Moodle (verdadero/falso, respuesta corta...)
- Se valorará que también funcione en MySQL, usando la máquina virtual [scotch-box](https://github.com/Egibide-LM2015/scotch-box).

## Fecha de entrega

- El proyecto se entregará el día 26/05/2016.

