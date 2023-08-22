### Administración de una flota de embarcaciones

Este código en Java se desarrolla como un programa para administrar las embarcaciones de la empresa MAKAI. El programa interactúa con el usuario a través de la terminal, permitiendo registrar capitanes y sus respectivas embarcaciones (yates y veleros) y luego genera un informe detallado de la flota y sus características.

Primero, se importan las clases necesarias, como `ArrayList` para manejar listas y `Scanner` para obtener entradas del usuario. Luego, se crean tres listas (`veleros`, `yates` y `capitanes`) para almacenar los objetos de embarcaciones y capitanes. Además, se instancia un objeto `Scanner` para la entrada del usuario.

Se definen métodos para agregar yates y veleros a sus listas respectivas, y se define un método `informe()` que imprime un informe detallado de las características de los capitanes y sus embarcaciones.

La función principal, `main`, comienza solicitando al usuario la cantidad de capitanes en la flota. Luego, se realiza una validación para asegurarse de que la cantidad sea mayor que cero. Además, se inicializan contadores para el total de veleros y yates.

Luego, se inicia un bucle para capturar los datos de cada capitán en la flota. Se capturan detalles como nombre, apellido y matrícula. Se pide al usuario la cantidad de veleros y yates bajo el mando de ese capitán, mientras se realizan validaciones para asegurarse de que no sean negativos ni ambos cero. Los totales de veleros y yates se actualizan en consecuencia.

Dentro de este bucle, se capturan los datos de cada velero y yate bajo el mando del capitán. Estos datos incluyen el año de fabricación, eslora, precio base y atributos específicos como la cantidad de mástiles en los veleros o la cantidad de camarotes en los yates.

Finalmente, se imprime un resumen de la flota total, incluyendo el número total de veleros, yates y el total general de embarcaciones. Luego se llama al método `informe()` para mostrar un informe detallado de capitanes, yates y veleros con sus atributos.

![](https://github.com/Jhonro0507/BackendDeepening/blob/main/Workshop1_POO/src/EjercicioEmbarcaciones/Workshop1_POO.drawio.png)
