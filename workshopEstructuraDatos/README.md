# Workshop Estructuras de Datos

El código proporcionado es una implementación en Java de una clase llamada `HtmlValidator`. Esta clase tiene un método público llamado `isValidHtml` que toma una cola (Queue) de objetos `HtmlTag` como entrada y devuelve una pila (Stack) de objetos `HtmlTag` como resultado.

El propósito de este código es validar la estructura de un documento HTML, comprobando si todas las etiquetas de apertura tienen sus correspondientes etiquetas de cierre en el orden adecuado.

A continuación, se explican los principales componentes y la lógica del código:

- Se importan las clases `Queue` y `Stack` para su uso en el código.

- La función `isValidHtml` recibe una cola de etiquetas HTML (`tags`) como entrada y devuelve una pila de etiquetas HTML que representan etiquetas que no se cerraron adecuadamente.

- Se crea una pila llamada `stack` para realizar un seguimiento de las etiquetas de apertura.

- Luego, se itera a través de cada etiqueta en la cola `tags`.

- Si una etiqueta es una etiqueta de cierre automático (self-closing), se ignora y se pasa a la siguiente etiqueta.

- Si una etiqueta es una etiqueta de apertura, se agrega a la pila `stack`.

- Si una etiqueta es una etiqueta de cierre, se verifica si coincide con la etiqueta de apertura más reciente en la pila. Si coincide, se elimina la etiqueta de apertura de la pila. Si no coincide, se devuelve la pila actual como resultado, lo que indica que el HTML no es válido.

- Al final del bucle, si la pila está vacía, significa que todas las etiquetas se cerraron correctamente y, por lo tanto, el HTML es válido. Si la pila no está vacía, significa que algunas etiquetas no se cerraron adecuadamente, y se devuelve la pila como resultado para mostrar cuáles etiquetas no se cerraron correctamente.


Al ejecutar las pruebas unitarias definidas en una clase llamada `HtmlValidatorTest` utilizando JUnitCore, se calcula la cantidad de pruebas que pasaron y la cantidad de pruebas que fallaron. Si todas las pruebas pasaron (es decir, no hubo pruebas fallidas), muestra un mensaje que indica que el puntaje para la asignación es del 100%.

<p align="center">
  <img src="https://github.com/Jhonro0507/BackendDeepening/blob/main/workshopEstructuraDatos/Screen%20Shot%202023-09-26%20at%2011.18.19%20PM.png" alt="Captura de pantalla de la consola con los resultados de las pruebas unitarias">
</p>

