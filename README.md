# disney-api
- Api para consultar, crear, editar o eliminar los personajes de disney.
- La aplicacion tiene 2 roles de usuario, el rol user que permite realizar todas las operaciones de lectura y el rol admin que permite realizar todas las operaciones de
  lectura y escritura.
- La autenticacion está basada en jsonwebtoken, por lo que es necesario que al probar los endpoints se les agregue la cabecera authorization para agregar el respectivo
  token.
- Se suben las colecciones de postman en el directorio postman para que se puedan probar los endpoints.
- Para iniciar la aplicacion primero se debe crear un usuario de bases de datos (en este caso oracle). Se tienen algunos comandos en la carpeta bd que seran utiles para
  creacion del usuario y otorgarle sus permisos.
- Para iniciar la aplicacion simplemente se descargan las dependencias y se ejecuta la clase principal del proyecto.
- Al iniciar la aplicacion, se crearan automaticamente las tablas, es necesario que tan pronto se creen todas, se ejecuten las lineas 37 y 38 del archivo comandos.sql que
  se encuentra en la carpeta llamada "bd".
- Los primeros endpoints que deben ejecutarse son los de registro y login. Esto para crear un usuario y generar el token de acceso a los demás endpoints.
- Con el token generado ya se pueden realizar todas las operaciones que expone el api, dependiendo del rol del usuario que se está logeando.

