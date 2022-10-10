# Microservicio Users Tenpo .
.
Servicio encargado de orquestar las solicitudes del Challenge Tenpo

* Producto: [`users-tenpo`]
* Dominio-Funcional: [`N/A`]
* Legado: [`N/A`]
* Celula: [`N/A`]
* Tren: [`Canales digitales`]
* Desarrollador: [`Angye Daniela Torres`]
* Métodos: [`rests`]

Para despeglar la aplicación se requiere:
* Clonar el repositorio o descargarlo como zip.
* Tener instalado y abierto _Docker Desktop_.
* Abrir un terminal en la ubicación del archivo.
* Ejecutar el comando: **docker-compose up**.
* Abrir Postman e importar el archivo: **_Challenge Tenpo.postman_collection_** ubicado en el directorio _**postman**_ en la raiz del proyecto.

Debido a que la aplicación contiene spring security, se recomienda que para el consumo de cada uno de los servicios, se tenga un usuario registrado, posteriormente logueado.

El login retorna un token de verificación que debe ser adjuntado a las peticiones de _`Records`_ y _`Percentage`_ bien sea desde Authorization como bearer token o desde los headers como un parametro que contenga `Authorization` como key y `Brearer token` como value.
