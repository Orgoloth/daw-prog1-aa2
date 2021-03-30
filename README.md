#Enunciado de la práctica

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


## Entidades y valores utilizados

Ciudad
* CCAA

Parque
* Ciudad
* Extension
* Nombre

## Casos de uso

* Listar parques de una ciudad
* Listar parques de todas las ciudades de una comunidad
* Añadir parque a una ciudad, lanzando excepción de ciudad no encontrada
* Actualizar información de un parque, por nombre, mostrando los datos actuales antes de pedir los nuevos
* Listar todos los parques cuyo nombre coincida con patrón de búsqueda
* Mostrar cuenta de parques en una ciudad dada con una extension mayor a la que se indique
* Borrar todos los parques de una ciudad dada por nombre
* Listar el nombre de todas las ciudades que contengan parques cuya suma total de su extensión, sea mayor que la que quiera el usuario.

## Comentarios sobre el diseño

Se pretende hacer uso de una arquitectura por capas, inversion de dependencias y value objects.

* infrastructure: conocido solo por el exterior y conoce solo la capa de aplicación, recoge valores primitivos o interactua con elementos externos, para evitar acoplamientos en capas mas internas con detalles de implementacion, como pueden ser bases de datos concretas o servicios externos que podrian cambiar en cualquier momento.
* application: recogiendo los servicios de aplicación, que, recibiendo valores primitivos de la capa de infraestructura los convierte a valores o entidades de dominio para interactuar con ellos, solo es conocida por la capa de infraestructura y solo se conoce a si misma y a la capa de dominio, evitando acoplamientos innecesarios.
* domain: conocida solo por la capa de aplicación y ella misma, representa las entidades de dominio con su logica y los value objects.entidades

Mencion especial a los repositorios. Se definen como interfaz en la capa de dominio para que las entidades y los servicios de aplicacion puedan interactuar con ellos sin conocer detalles de implementación, pero se implementan en la capa de infraestructura por que la implementacion concreta debe conocer por necesidad detalles del servicio externo, como una base de datos en concreto.

El código se ha dividido en tres modulos

* consoleui: para la logica relacionada directamente con la interaccíón con el usuario
* gestion: para la logica de negocio
* shared: para elementos comunes a mas de un submódulo

El paquete consoleui se divide en:

* controllers: se lanzan en funcion de la opcion elegida por el usuario, recogen informacion en valores primitivos y llaman al servicio de aplicacion correspondiente. Se consideran como capa de infraestructura
* menu: gestion del menu

El modulo gestion se divide en submodulos que representan las entidades del problema, cities y gardens, para contener toda la logica relacionada con ellos

Cada submodulo se divide en tres grandes areas, atendiendo a la descripcion general de la arquitectura por capas indicada anteriormente.



