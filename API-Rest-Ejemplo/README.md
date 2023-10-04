# Documentación de la API REST para la Gestión de Personas

Esta documentación describe una API REST básica implementada en una aplicación Spring Boot para la gestión de personas (`Persona`).

## PersonaServicio

La clase `PersonaServicio` contiene métodos que permiten realizar operaciones CRUD en una lista de objetos `Persona`. A continuación, se detallan los métodos disponibles:

### Listar Personas

**Endpoint:** `/listarPersonas`

**Descripción:** Este método devuelve una lista de todas las personas almacenadas en la base de datos simulada.

### Obtener Persona por Cédula

**Endpoint:** `/obtenerPersonaPorCedula/{cedula}`

**Descripción:** Este método recupera una persona por su número de cédula. El número de cédula se pasa como parámetro en la URL.

### Crear Persona

**Endpoint:** `/crearPersona`

**Descripción:** Este método permite crear una nueva persona y agregarla a la lista de personas en la base de datos simulada. Se debe proporcionar un objeto `Persona` en el cuerpo de la solicitud (Request Body).

### Modificar Persona

**Endpoint:** `/modificarPersona`

**Descripción:** Este método permite actualizar la información de una persona existente en la base de datos simulada. Se debe proporcionar un objeto `Persona` con los datos actualizados en el cuerpo de la solicitud.

### Eliminar Persona por Cédula

**Endpoint:** `/eliminarPersonaPorCedula/{cedula}`

**Descripción:** Este método permite eliminar una persona de la base de datos simulada por su número de cédula. El número de cédula se pasa como parámetro en la URL.

## PersonaController

La clase `PersonaController` es un controlador REST que maneja las solicitudes HTTP relacionadas con el recurso `Persona`. A continuación, se describen los puntos finales (endpoints) disponibles:

### Listar Personas

**Endpoint:** `GET /api/v1/personas`

**Descripción:** Este endpoint recupera una lista de todas las personas almacenadas en la base de datos simulada.

### Obtener Persona por Cédula

**Endpoint:** `GET /api/v1/personas/{cedula}`

**Descripción:** Este endpoint recupera una persona por su número de cédula. El número de cédula se proporciona como parte de la URL.

### Crear Persona

**Endpoint:** `POST /api/v1/personas`

**Descripción:** Este endpoint permite crear una nueva persona y agregarla a la base de datos simulada. Se debe proporcionar un objeto `Persona` en el cuerpo de la solicitud (Request Body).

### Modificar Persona

**Endpoint:** `PUT /api/v1/personas`

**Descripción:** Este endpoint permite actualizar la información de una persona existente en la base de datos simulada. Se debe proporcionar un objeto `Persona` con los datos actualizados en el cuerpo de la solicitud.

### Eliminar Persona por Cédula

**Endpoint:** `DELETE /api/v1/personas/{cedula}`

**Descripción:** Este endpoint permite eliminar una persona de la base de datos simulada por su número de cédula. El número de cédula se proporciona como parte de la URL.

**Notas Importantes:**

- Se recomienda tener precaución al utilizar una lista en memoria como base de datos, ya que los datos solo se mantendrán mientras la aplicación esté en ejecución. En un entorno de producción, se debe considerar el uso de una base de datos real para el almacenamiento de datos.

- Asegúrese de que la clase `Persona` esté debidamente definida con sus campos y métodos getter/setter.

- Es necesario configurar adecuadamente el archivo `application.properties` o `application.yml` si se planea migrar a una base de datos real en el futuro.

Este código proporciona un punto de partida para la construcción de una API RESTful en Spring Boot para la gestión de personas. Puede ser ampliado y mejorado según los requisitos específicos de su aplicación.