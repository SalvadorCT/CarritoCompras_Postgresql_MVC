# Carrito de compras con PostgreSQL

## Descripción
Este proyecto es un carrito de compras que se conecta a una base de datos PostgreSQL. El proyecto está desarrollado en Java utilizando Maven como herramienta de gestión de dependencias. 

La arquitectura del proyecto sigue el patrón de diseño Modelo-Vista-Controlador (MVC).

## Estructura del proyecto
```
CarritoComprasPostgrsql/
├── src/
│   ├── controllers/
│   │   ├── CarritoController.java
│   │   ├── CompraController.java
│   │   └── ResenaController.java
│   ├── model/
│   │   ├── Carrito.java
│   │   ├── Compra.java
│   │   ├── Producto.java
│   │   ├── Resena.java
│   │   ├── Usuario.java
│   │   └── dao/
│   │       ├── CarritoDAO.java
│   │       ├── CompraDAO.java
│   │       ├── ProductoDAO.java
│   │       └── ResenaDAO.java
│   ├── view/
│   │   ├── CarritoView.java
│   │   ├── CompraView.java
│   │   ├── ProductoView.java
│   │   ├── ResenaView.java
│   │   └── UsuarioView.java
│   └── main/
│       └── Main.java
├── resources/
│   └── db.sql
├── pom.xml
└── README.md
```

## Requisitos
- Java 8
- Maven
- PostgreSQL
- IDE (IntelliJ IDEA, Eclipse, NetBeans, etc.)
- Git
- Docker (opcional)

## Esquema de la base de datos
El esquema de la base de datos está compuesto por las siguientes tablas:

- `Usuario`
- `Producto`
- `Resena`
- `Compra`
- `CompraProducto`
- `Carrito`

### Setup

1. Clone the repository:
   ```sh
   git clone https://github.com/SalvadorCT/CarritoCompras_Postgresql_MVC.git 
```

