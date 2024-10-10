INSERT INTO Usuario (nombre, email, password)
VALUES ('Jesus Caceres', 'jesus.caceres@email.com', 'password123');

INSERT INTO Producto (nombre, precio, descripcion, stock)
VALUES ('Laptop', 1500.00, 'Laptop de alto rendimiento', 10);

INSERT INTO Producto (nombre, precio, descripcion, stock)
VALUES ('Smartphone', 800.00, 'Smartphone con cámara de alta resolución', 50);

INSERT INTO Resena (usuario_id, producto_id, comentario, calificacion)
VALUES (1, 1, 'Excelente producto', 5);

INSERT INTO Compra (usuario_id, total, descuento, costo_envio)
VALUES (1, 2300.00, 0, 0);

INSERT INTO CompraProducto (compra_id, producto_id, cantidad)
VALUES (1, 1, 1), -- 1 Laptop
       (1, 2, 2); -- 2 Smartphones

select * from Usuario;
select * from Producto;
select * from Resena;
select * from Compra;
select * from CompraProducto;

-- UPDATE Usuario SET nombre='Jesus Caceres' WHERE Usuario.id=1;
-- DELETE FROM Usuario WHERE Usuario.id=1;