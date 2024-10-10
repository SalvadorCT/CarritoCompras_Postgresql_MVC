CREATE TABLE Usuario (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    rol VARCHAR(20) DEFAULT 'cliente'
);
-- Rol: cliente, admin

CREATE TABLE Producto (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
	descripcion VARCHAR(100),
	stock INT NOT NULL CHECK (stock>=0)
);

CREATE TABLE Resena (
    id SERIAL PRIMARY KEY,
	usuario_id INT REFERENCES Usuario(id),
    producto_id INT REFERENCES Producto(id),
	comentario TEXT NOT NULL,
    calificacion INT NOT NULL CHECK (calificacion >= 1 AND calificacion <= 5),
	UNIQUE (usuario_id,producto_id)
);

CREATE TABLE Compra (
    id SERIAL PRIMARY KEY,
    usuario_id INT REFERENCES Usuario(id) ON DELETE CASCADE,
    total DECIMAL(10, 2) NOT NULL,
	descuento DECIMAL(5,2) DEFAULT 0,
	costo_envio DECIMAL(5,2) DEFAULT 0,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE CompraProducto (
    compra_id INT REFERENCES Compra(id) ON DELETE CASCADE,
    producto_id INT REFERENCES Producto(id),
	cantidad INT NOT NULL,
    PRIMARY KEY (compra_id, producto_id)
);

CREATE TABLE Carrito (
    usuario_id INT REFERENCES Usuario(id) ON DELETE CASCADE,
    producto_id INT REFERENCES Producto(id),
    cantidad INT NOT NULL,
    PRIMARY KEY (usuario_id, producto_id)
);
