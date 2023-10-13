# Documentación de la Aplicación - Procesador de Archivos con Validación

Esta documentación proporciona una descripción detallada de una aplicación Java que utiliza el framework Spring y la biblioteca OpenFeign para procesar archivos CSV y XLSX, validar los datos en esos archivos y comunicarse con un servicio externo de validación.

## Estructura de Paquetes

- `com.OpenFeign.WorkshopSpringProcessor.Controller`: Contiene el controlador principal de la aplicación que maneja las solicitudes HTTP relacionadas con la importación y procesamiento de archivos CSV y XLSX.

- `com.OpenFeign.WorkshopSpringProcessor.Client`: Contiene la interfaz del cliente Feign que se utiliza para comunicarse con un servicio externo de validación.

- `com.OpenFeign.WorkshopSpringProcessor.Service`: Contiene el servicio que procesa los archivos CSV y XLSX y convierte sus datos en objetos Java. 

- `com.OpenFeign.WorkshopSpringValidator.Controller`: En este paquete se encuentra el controlador del servicio de validación.

- `com.OpenFeign.WorkshopSpringValidator.Service`: Contiene los servicios de validación para objetos CSVEntry y XLSXEntry.


## Controlador Principal (ReaderController)

El controlador principal `ReaderController` se encarga de gestionar las solicitudes relacionadas con el procesamiento de archivos. Está anotado con `@RestController` y se define en la ruta base "/api/v1/processor".

- Método `processCSVOrXLSX`: Este método permite procesar archivos CSV o XLSX mediante solicitudes POST. Para utilizar este método, se debe enviar una solicitud POST con un cuerpo que contenga un archivo JSON con la dirección del archivo que se desea procesar. El archivo JSON debe tener un campo llamado "filePath" que contenga la ruta del archivo a procesar. Aquí hay un ejemplo de cómo debería ser la solicitud:

```json
{
  "filePath": "/ruta/al/archivo.csv"
}
```

El controlador procesará el archivo en la dirección especificada y enviará las entradas individuales al servicio de validación, contando cuántas son válidas y cuántas son inválidas. El resultado se devolverá como una respuesta HTTP.

Asegúrate de proporcionar la dirección del archivo en el campo "filePath" del cuerpo de la solicitud para que el controlador pueda procesar el archivo correctamente.

## Cliente Feign (EntryValidatorClient)

La interfaz `EntryValidatorClient` define un cliente Feign que se comunica con un servicio externo denominado "validator-service" en "http://localhost:8081/api/v1/entryValidator". Proporciona un método `validateEntry` que valida una entrada enviada en formato JSON al servicio externo.

## Servicio de Procesamiento (Reader)

El servicio `Reader` se utiliza para procesar archivos CSV y XLSX. Contiene los siguientes métodos:

- Método `readCSVOrXLSX`: Toma la ruta de un archivo como entrada y determina si es un archivo CSV o XLSX. Llama a métodos privados para leer y procesar los archivos en objetos Java.

- Método `readCSV`: Lee datos desde un archivo CSV y los convierte en objetos `Entry`, que se almacenan en una lista y se devuelven.

- Método `readXLSX`: Lee datos desde un archivo XLSX y los convierte en objetos `Entry`.

## Controlador de Validación (EntryValidatorController)

El controlador `EntryValidatorController` maneja solicitudes POST en la ruta "/validateEntry". Dependiendo del tipo de entrada en el cuerpo de la solicitud, llama a los servicios de validación apropiados para validar los datos.

## Servicio de Validación de CSV (CSVEntryValidator)

El servicio `CSVEntryValidator` realiza validaciones específicas para las entradas CSV. Esto incluye la validación de campos como el correo electrónico, la fecha de nacimiento y el título del trabajo.

## Servicio de Validación de XLSX (XLSXEntryValidator)

El servicio `XLSXEntryValidator` realiza validaciones específicas para las entradas XLSX. Esto incluye la validación de campos como la ubicación de la lesión y el tipo de informe.

## Modelos (Entry, CSVEntry, XLSXEntry)

- `Entry` es una clase que representa una entrada que puede ser de tipo "CSVEntry" o "XLSXEntry".

- `CSVEntry` y `XLSXEntry` son clases que representan los datos específicos de las entradas CSV y XLSX, respectivamente.

Esta estructura sigue los principios de diseño de una aplicación Spring con un controlador que maneja las solicitudes HTTP, servicios para el procesamiento y validación de datos, y modelos para representar los datos. La comunicación con el servicio de validación externo se realiza a través de Feign, lo que permite una integración sencilla con servicios externos.