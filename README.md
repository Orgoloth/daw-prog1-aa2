De cada parque tendremos que almacenar el nombre, la extensión, la CCAA y la ciudad.

Además cada parque necesitará ciertas actuaciones en ciertos momentos del año. Estas actuaciones se tendrán que programar y ejecutar y las llevarán a cabo cuadrillas de jardineros. Cada cuadrilla tendrá un jefe de cuadrilla y jardineros. Las actuaciones tendrán una duración distinta para cada una.

Las ciudades deberán precargarse en la base de datos, no lo hará el programa.

El objetivo final será llegar a construir una aplicación que permita :

* Listar todos los parques de una determinada ciudad por nombre.
* Listar todos los parques de una cierta comunidad autónoma por nombre.
* Añadir un parque a una determinada ciudad (por nombre de ciudad), si la ciudad no existe no se añade y se informa de ello.
* Actualizar la información de un parque (primero se pedirá al usuario el nombre del parque que quiere actualizar, se buscará en la base de datos y se mostrarán los datos del parque (nombre, nombre ciudad, extensión) y a continuación se pedirán los 3 nuevos datos (nombre, nombre ciudad y extensión) y se hará la actualización.
* Seleccionar todos los parques cuyo nombre contenga una determinada cadena.
* Devolver el número de parques de una determinada ciudad que tengan una extensión individual mayor que la que desee el usuario.

Este ejercicio consiste en diseñar la aplicación, NO LA BASE DE DATOS. La base usará la diseñada en el módulo de bases de datos, en el ejercicio de feedback.

El diseño de la aplicación será muy sencillo, realiza una aplicación de consola y muestra un menú con una opción por cada punto indicado en el enunciado. Pide los datos que sean necesarios para las consultas a través del teclado.

Las sentencias SQL también las tendremos diseñadas y las usaremos en el programa.

Importante: aunque los ejemplos de conexión con bases de datos que has visto son sencillos deberemos diseñar nuestra aplicación con las clases adecuadas y todos los datos deberán estar encapsulados en objetos, no individualmente.


## Entidades y valores

Ciudad
* CCAA

Parque
* Ciudad
* Extension
* Nombre

Actuaciones
* Parque
* Fecha
* Cuadrilla

Cuadrilla
* Jefe
* Jardineros

Jardinero
* Nombre

## Casos de uso

* Listar parques de una ciudad
* Listar parques de todas las ciudades de una comunidad
* Añadir parque a una ciudad, lanzando excepción de ciudad no encontrada
* Actualizar información de un parque, por nombre, mostrando los datos actuales antes de pedir los nuevos
* Listar todos los parques cuyo nombre coincida con patrón de búsqueda
* Mostrar cuenta de parques en una ciudad dada con una extension mayor a la que se indique




