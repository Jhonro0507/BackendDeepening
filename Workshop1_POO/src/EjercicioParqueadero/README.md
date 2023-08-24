### Administración de un parqueadero

Este código es un programa de Java que simula un sistema de parqueadero. El programa utiliza la clase `Parqueadero` para gestionar el estacionamiento de carros. Al ejecutar el programa, se crea una instancia de `Parqueadero` con un costo de tiempo de 4500 por hora. Luego, se entra en un bucle que permite al usuario parquear carros en el parqueadero. 

En cada iteración del bucle, el programa le pregunta al usuario si desea parquear un carro. Si la respuesta es "1", el programa solicita información sobre el carro, como la placa, la marca y el modelo. Luego, pide al usuario que ingrese el tiempo de estacionamiento deseado. A continuación, el programa utiliza el método `cobrarPorTiempo` de la clase `Parqueadero` para calcular el costo del estacionamiento y registra la información del carro.

Después, el programa pide al usuario que ingrese la fila y columna donde desea estacionar el carro, y utiliza el método `parquearCarro` de la clase `Parqueadero` para asignar esa ubicación al carro.

Si el usuario no desea parquear más carros (ingresa un número distinto de "1"), el bucle se detiene y el programa muestra el estado actual del parqueadero utilizando el método `mostrarParqueadero` de la clase `Parqueadero`.

En resumen, este código crea un programa que permite a los usuarios simular el parqueo de carros en un parqueadero virtual, registrando la información de los carros, calculando los costos y mostrando el estado del parqueadero.

![](https://github.com/Jhonro0507/BackendDeepening/blob/main/Workshop1_POO/src/EjercicioEmbarcaciones/Workshop1_POO.drawio.png)
