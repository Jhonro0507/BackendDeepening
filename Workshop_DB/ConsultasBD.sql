USE workshop_BD;
-- 1. Devuelve un listado con el código de oficina y la ciudad donde hay oficinas.
desc oficina;
SELECT ofi_codigo, ofi_ciudad FROM oficina;


-- 2. Devuelve un listado con la ciudad y el teléfono de las oficinas de España.
desc oficina;
SELECT ofi_ciudad, ofi_telefono FROM oficina WHERE ofi_pais = "Spain";


-- 3. Devuelve un listado con el nombre, apellidos y email de los empleados cuyo jefe tiene un código de jefe igual a 7.
desc empleado;
SELECT emple_nombre, emple_apellido1, emple_apellido2, emple_email FROM empleado WHERE emple_jefe_codigo = 7;


-- 4. Devuelve un listado con el código de cliente de aquellos clientes que realizaron algún pago en 2008. 
-- Tenga en cuenta que deberá eliminar aquellos códigos de cliente que aparezcan repetidos. Resuelva la consulta:
desc pago;
--        Utilizando la función YEAR de MySQL.
SELECT DISTINCT pago_clien_codigo FROM pago WHERE YEAR(pago_fecha) = 2008;
--        Utilizando la función DATE_FORMAT de MySQL.
SELECT DISTINCT pago_clien_codigo FROM pago WHERE DATE_FORMAT(pago_fecha, '%Y') = '2008';


-- 5.   ¿Cuántos empleados hay en la compañía?
desc empleado;
SELECT COUNT(*) AS Total_Empleados FROM empleado;

-- 6. ¿Cuántos clientes tiene cada país?
desc cliente;
SELECT clien_pais, COUNT(*) AS Total_Clientes FROM cliente GROUP BY clien_pais;

-- 7. ¿Cuál fue el pago medio en 2009?
desc pago;
SELECT AVG(pago_total) AS Promedio FROM pago WHERE YEAR(pago_fecha) = 2009;

-- 8. ¿Cuántos pedidos hay en cada estado? Ordena el resultado de forma descendente por el número de pedidos.
desc pedido;
SELECT pedi_estado, COUNT(*) AS Pedidos_Totales FROM pedido GROUP BY pedi_estado ORDER BY Pedidos_Totales DESC;

-- 9. Calcula el precio de venta del producto más caro y barato en una misma consulta.
desc producto;
SELECT MAX(produc_precio_venta) AS Mas_Caro, MIN(produc_precio_venta) AS Mas_Barato FROM producto;

-- 10. Devuelve el nombre del cliente con mayor límite de crédito.
desc cliente;
SELECT clien_nombre FROM cliente ORDER BY clien_limite_credito DESC LIMIT 1;

-- 11. Devuelve el nombre del producto que tenga el precio de venta más caro.
desc producto;
SELECT produc_nombre FROM producto ORDER BY produc_precio_venta DESC LIMIT 1;

-- 12. Devuelve el nombre del producto del que se han vendido más unidades. (Tenga en cuenta que tendrá 
-- que calcular cuál es el número total de unidades que se han vendido de cada producto a partir de los datos de la 
-- tabla detalle_pedido)
desc detalle_pedido;
desc producto;
SELECT p.produc_nombre FROM producto p
INNER JOIN (
    SELECT deta_produc_codigo, SUM(deta_cantidad) AS VentasTotalesPorProducto FROM detalle_pedido
    GROUP BY deta_produc_codigo ORDER BY VentasTotalesPorProducto DESC LIMIT 1
) d ON p.produc_codigo = d.deta_produc_codigo;

-- 13. Los clientes cuyo límite de crédito sea mayor que los pagos que haya realizado. (Sin utilizar INNER JOIN).
desc cliente;
desc pago;
SELECT clien_codigo, clien_nombre, clien_limite_credito FROM cliente
WHERE clien_limite_credito > (SELECT SUM(pago_total) FROM pago WHERE clien_codigo = cliente.clien_codigo);

-- 14.  Devuelve el listado de clientes indicando el nombre del cliente y cuantos pedidos ha realizado. 
-- Tenga en cuenta que pueden existir clientes que no han realizado ningún pedido.
desc cliente;
desc pedido;
SELECT c.clien_nombre, COUNT(p.pedi_codigo) AS Pedidos_Totales FROM cliente c
LEFT JOIN pedido p ON c.clien_codigo = p.pedi_clien_codigo GROUP BY c.clien_nombre;

-- 15. Devuelve el nombre, apellidos, puesto y teléfono de la oficina de aquellos empleados que no sean
-- representante de ventas de ningún cliente.
desc empleado;
desc oficina;
SELECT e.emple_nombre, e.emple_apellido1, e.emple_apellido2, e.emple_puesto, o.ofi_telefono FROM empleado e
JOIN oficina o ON e.emple_ofi_codigo = o.ofi_codigo
WHERE e.emple_codigo NOT IN (SELECT DISTINCT emple_codigo FROM cliente WHERE emple_codigo IS NOT NULL);

-- 16. Devuelve las oficinas donde no trabajan ninguno de los empleados que hayan sido los representantes
-- de ventas de algún cliente que haya realizado la compra de algún producto de la gama Frutales.
desc oficina;
desc empleado;
desc cliente;
desc pedido;
desc detalle_pedido;
desc producto;
desc gama;
SELECT DISTINCT o.ofi_codigo, o.ofi_ciudad FROM oficina o LEFT JOIN empleado e ON o.ofi_codigo = e.emple_ofi_codigo
WHERE o.ofi_codigo NOT IN (
    SELECT DISTINCT o.ofi_codigo FROM oficina o
    JOIN empleado e ON o.ofi_codigo = e.emple_ofi_codigo
    JOIN cliente c ON e.emple_codigo = c.clien_emple_codigo
    JOIN pedido p ON c.clien_codigo = p.pedi_clien_codigo
    JOIN detalle_pedido dp ON p.pedi_codigo = dp.deta_pedi_codigo
    JOIN producto pr ON dp.deta_produc_codigo = pr.produc_codigo
    JOIN gama g ON pr.produc_gama_gama = g.gama_gama
    WHERE g.gama_gama = 'Frutales'
);


-- 17. Devuelve el listado de clientes indicando el nombre del cliente y cuantos pedidos ha realizado. 
-- Tenga en cuenta que pueden existir clientes que no han realizado ningún pedido.
desc cliente;
desc pedido;
SELECT c.clien_nombre, COUNT(p.pedi_codigo) FROM cliente c
LEFT JOIN pedido p ON c.clien_codigo = p.pedi_codigo GROUP BY c.clien_nombre;

-- 18. Devuelve un listado con los nombres de los clientes y el total pagado por cada uno de ellos. 
-- Tenga en cuenta que pueden existir clientes que no han realizado ningún pago.
desc cliente;
desc pago;
SELECT c.clien_nombre, IFNULL(SUM(p.pago_total), 0) FROM cliente c
LEFT JOIN pago p ON c.clien_codigo = p.pago_clien_codigo GROUP BY c.clien_nombre;