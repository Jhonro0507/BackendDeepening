# Documentación de la API de Reservas del Hotel Ashir

## Introducción

Bienvenido a la API de Reservas del Hotel Ashir. Esta API permite a los clientes buscar y reservar habitaciones en el Hotel Ashir, así como registrarse en la base de datos del hotel. El sistema se compone de tres entidades principales: Habitación, Cliente y Reserva.

### Entidades del Sistema

1. **Habitación**:
   - *Número*: Número de la habitación.
   - *Tipo de Habitación*: Puede ser e (estándar) o p (premium).
   - *Precio Base*: Precio base de la habitación por noche.

2. **Cliente**:
   - *Nombre*: Nombre del cliente.
   - *Apellido*: Apellido del cliente.
   - *Cédula*: Número de cédula del cliente (debe ser numérica y única).
   - *Dirección*: Dirección del cliente.
   - *Edad*: Edad del cliente.
   - *Correo Electrónico*: Correo electrónico del cliente.

3. **Reserva**:
   - *Fecha de Reserva*: Fecha de la reserva (las reservas solo se hacen por un día).
   - *Habitación*: Habitación reservada.
   - *Cliente*: Cliente que realiza la reserva.
   - *Código de Reserva*: Código único generado por el sistema.
   - *Total a Pagar*: Precio total de la reserva.

### Diagrama de Clases
<div style="text-align:center">
    <img src="https://github.com/Jhonro0507/BackendDeepening/blob/main/WorkshopJPA/UML.png" alt="Diagrama de Clases" />
</div>


### Diagrama de Entidad-Relación
<div style="text-align:center">
    <img src="https://github.com/Jhonro0507/BackendDeepening/blob/main/WorkshopJPA/ER.png" alt="Diagrama de Entidad-Relación" />
</div>


A continuación, se detallan los principales puntos de interacción con la API.


## ClienteController

**Registrar Cliente**

_Registra un nuevo cliente en el sistema._

- **URL:** `/clientes`
- **Método HTTP:** `POST`
- **Cuerpo de la Solicitud:** Cliente (objeto JSON)
- **Respuesta Exitosa:** Cliente registrado
- **Respuesta de Error:** Mensaje de error
- **Ejemplo de Solicitud:**

```json
{
    "nombre": "Juan",
    "apellido": "Pérez",
    "cedula": "123456789",
    "direccion": "Calle 123",
    "edad": 30,
    "correoElectronico": "juan@example.com"
}
```

**Obtener Cliente por ID**

_Obtén información detallada de un cliente por su ID._

- **URL:** `/clientes/{idCliente}`
- **Método HTTP:** `GET`
- **Respuesta Exitosa:** Información del cliente
- **Respuesta de Error:** Mensaje de error

**Obtener Todos los Clientes**

_Obtén una lista de todos los clientes registrados en el sistema._

- **URL:** `/clientes`
- **Método HTTP:** `GET`
- **Respuesta Exitosa:** Lista de clientes
- **Respuesta de Error:** Mensaje de error

**Actualizar Cliente**

_Actualiza la información de un cliente existente._

- **URL:** `/clientes/{id}`
- **Método HTTP:** `PUT`
- **Cuerpo de la Solicitud:** Cliente actualizado (objeto JSON)
- **Respuesta Exitosa:** Cliente actualizado
- **Respuesta de Error:** Mensaje de error
- **Ejemplo de Solicitud:**

```json
{
    "nombre": "Juan",
    "apellido": "Pérez",
    "cedula": "123456789",
    "direccion": "Nueva Dirección",
    "edad": 31,
    "correoElectronico": "juan@example.com"
}
```

**Eliminar Cliente**

_Elimina un cliente por su ID._

- **URL:** `/clientes/{id}`
- **Método HTTP:** `DELETE`
- **Respuesta Exitosa:** Mensaje de éxito
- **Respuesta de Error:** Mensaje de error

**Obtener Reservas por Cédula**

_Obtén una lista de todas las reservas asociadas a un cliente por su número de cédula._

- **URL:** `/clientes/{cedula}/reservas`
- **Método HTTP:** `GET`
- **Respuesta Exitosa:** Lista de reservas
- **Respuesta de Error:** Mensaje de error

## HabitacionController

**Registrar Habitación**

_Registra una nueva habitación en el sistema._

- **URL:** `/habitaciones`
- **Método HTTP:** `POST`
- **Cuerpo de la Solicitud:** Habitación (objeto JSON)
- **Respuesta Exitosa:** Habitación registrada
- **Respuesta de Error:** Mensaje de error
- **Ejemplo de Solicitud:**

```json
{
    "numero": 101,
    "tipo": "e",
    "precioBase": 100
}
```

**Obtener Todas las Habitaciones**

_Obtén una lista de todas las habitaciones registradas en el sistema._

- **URL:** `/habitaciones`
- **Método HTTP:** `GET`
- **Respuesta Exitosa:** Lista de habitaciones
- **Respuesta de Error:** Mensaje de error

**Obtener Habitación por ID**

_Obtén información detallada de una habitación por su ID._

- **URL:** `/habitaciones/{idHabitacion}`
- **Método HTTP:** `GET`
- **Respuesta Exitosa:** Información de la habitación
- **Respuesta de Error:** Mensaje de error

**Actualizar Habitación**

_Actualiza la información de una habitación existente._

- **URL:** `/habitaciones/{idHabitacion}`
- **Método HTTP:** `PUT`
- **Cuerpo de la Solicitud:** Habitación actualizada (objeto JSON)
- **Respuesta Exitosa:** Habitación actualizada
- **Respuesta de Error:** Mensaje de error
- **Ejemplo de Solicitud:**

```json
{
    "numero": 101,
    "tipo": "e",
    "precioBase": 110
}
```

**Eliminar Habitación**

_Elimina una habitación por su ID._

- **URL:** `/habitaciones/{idHabitacion}`
- **Método HTTP:** `DELETE`
- **Respuesta Exitosa:** Mensaje de éxito
- **Respuesta de Error:** Mensaje de error

**Obtener Habitaciones Disponibles por Fecha**

_Obtén una lista de habitaciones disponibles para una fecha específica._

- **URL:** `/habitaciones/disponibles`
- **Método HTTP:** `GET`
- **Parámetros de la Solicitud:** `fecha` (formato ISO DATE)
- **Respuesta Exitosa:** Lista de habitaciones disponibles
- **Respuesta de Error:** Mensaje de error

**Obtener Habitaciones Disponibles por Fecha y Tipo**

_Obtén una lista de habitaciones disponibles para una fecha específica y un tipo específico._

- **URL:** `/habitaciones/disponibles/filtradas`
- **Método HTTP:** `GET`
- **Parámetros de la Solicitud

:** `fecha` (formato ISO DATE), `tipo` (opcional)
- **Respuesta Exitosa:** Lista de habitaciones disponibles filtradas
- **Respuesta de Error:** Mensaje de error

## ReservaController

**Registrar Reserva**

_Registra una nueva reserva en el sistema._

- **URL:** `/reservas`
- **Método HTTP:** `POST`
- **Cuerpo de la Solicitud:** Reserva (objeto JSON)
- **Respuesta Exitosa:** Reserva registrada
- **Respuesta de Error:** Mensaje de error
- **Ejemplo de Solicitud:**

```json
{
    "fechaReserva": "2023-11-11",
    "cliente": {
        "idCliente": 1
    },
    "habitaciones": [
        {
            "idHabitacion": 1
        }
    ]
}
```

**Obtener Reserva por ID**

_Obtén información detallada de una reserva por su ID._

- **URL:** `/reservas/{id}`
- **Método HTTP:** `GET`
- **Respuesta Exitosa:** Información de la reserva
- **Respuesta de Error:** Mensaje de error

**Obtener Todas las Reservas**

_Obtén una lista de todas las reservas registradas en el sistema._

- **URL:** `/reservas`
- **Método HTTP:** `GET`
- **Respuesta Exitosa:** Lista de reservas
- **Respuesta de Error:** Mensaje de error

**Actualizar Reserva**

_Actualiza la información de una reserva existente._

- **URL:** `/reservas/{id}`
- **Método HTTP:** `PUT`
- **Cuerpo de la Solicitud:** Reserva actualizada (objeto JSON)
- **Respuesta Exitosa:** Reserva actualizada
- **Respuesta de Error:** Mensaje de error
- **Ejemplo de Solicitud:**

```json
{
    "fechaReserva": "2023-11-15",
    "cliente": {
        "idCliente": 1
    },
    "habitaciones": [
        {
            "idHabitacion": 2
        }
    ]
}
```

**Eliminar Reserva**

_Elimina una reserva por su ID._

- **URL:** `/reservas/{id}`
- **Método HTTP:** `DELETE`
- **Respuesta Exitosa:** Mensaje de éxito
- **Respuesta de Error:** Mensaje de error

Es importante recordar que esta es una documentación básica, y puedes expandirla con detalles adicionales y ejemplos según las necesidades de tu proyecto.