import Database.DatabaseConnection;
import controllers.*;

import model.Compra;
import utilities.consoleinput.validators.IntegerValidator;
import utilities.consoleinput.validators.StringValidator;
import utilities.consolemenu.command.*;
import utilities.consolemenu.model.*;
import utilities.consolemenu.renderer.*;
import utilities.consolemenu.input.*;
import utilities.consoleinput.InputReader;
import utilities.consoleinput.Messages;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection conn = new DatabaseConnection();
        ProductoController productoController = new ProductoController();
        CarritoController carritoController = new CarritoController();
        CompraController compraController = new CompraController();
        ResenaController resenaController = new ResenaController();
        UsuarioController usuarioController = new UsuarioController();
        int[] usuarioId = new int[1];

        try {
            conn.getConnection();
            System.out.println("Conectado a la base de datos 🚀");
        } catch (Exception e) {
            System.out.println("Error al conectar a la base de datos");
            e.printStackTrace();
        }

        // Configurar el menú
        MenuRenderer renderer = new DefaultMenuRenderer();
        InputValidator inputValidator = new DefaultInputValidator();

        Menu menuPrincipal = new Menu("Sistema Carrito de Compras", inputValidator, renderer);
        Menu menuLogin = new Menu("Login", inputValidator, renderer);

        // Adicionando comando al menu principal

        menuPrincipal.addCommandItem("1", "Signup", () -> {
            System.out.println("Ingresar datos:");
            String nombre = InputReader.read(Messages.get("prompt.enter.name"),
                    new StringValidator().minLength(2).maxLength(50));
            String email = InputReader.read(Messages.get("prompt.enter.email"),
                    new StringValidator().email());
            String password = InputReader.read("Ingrese password: ",
                    new StringValidator().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$"));

            usuarioController.addUsuario(nombre, email, password);
        });

        menuPrincipal.addCommandItem("2", "Login", () -> {
            System.out.println("Ingresar credenciales:");
            String email = InputReader.read(Messages.get("prompt.enter.email"),
                    new StringValidator().email());
            String password = InputReader.read("Ingrese password: ",
                    new StringValidator());
            int id = usuarioController.login(email, password);
            if (id != -1) {
                usuarioId[0] = id;
                menuLogin.display();
            } else {
                System.out.println("Credenciales incorrectas");
            }
        });

        menuLogin.addCommandItem("1", "Listar productos", () -> {
            productoController.displayProductos();
        });

        menuLogin.addCommandItem("2", "Agregar producto al carrito", () -> {
            int productoId = InputReader.read("Ingrese el ID del producto: ", new IntegerValidator());
            int cantidad = InputReader.read("Ingrese la cantidad: ", new IntegerValidator());
            carritoController.agregarProductoAlCarrito(usuarioId[0],productoId, cantidad);
        });

        menuLogin.addCommandItem("3", "Ver carrito", () -> {
            carritoController.verCarrito(usuarioId[0]);
        });

        menuLogin.addCommandItem("4", "Eliminar producto del carrito", () -> {
            int idProducto = InputReader.read("Ingrese el ID del producto: ", new IntegerValidator());
            carritoController.removeProductoFromCarrito(usuarioId[0], idProducto);
        });

        menuLogin.addCommandItem("5", "Realizar compra", () -> {
            Compra compra = new Compra(usuarioId[0]);
            compraController.addCompra(compra, usuarioId[0]);
        });

        menuLogin.addCommandItem("6", "Ver historial de compras", () -> {
            compraController.displayHistorialCompras(usuarioId[0]);
        });

        menuLogin.addCommandItem("7", "Agregar reseña", () -> {
            int productoId = InputReader.read("Ingrese el ID del producto: ", new IntegerValidator());
            String comentario = InputReader.read("Ingrese el comentario: ", new StringValidator());
            int calificacion = InputReader.read("Ingrese la calificación(1 al 5): ", new IntegerValidator().min(1).max(5));
            resenaController.addResena(usuarioId[0],productoId,comentario, calificacion);
        });

        menuLogin.addCommandItem("0", "Regresar", new Command() {
            @Override
            public void execute() {
                // Regresa al menú anterior
                menuLogin.exit();
            }
        });

        menuPrincipal.addCommandItem("0", "Exit", new ExitCommand());
        // Ejecutar el menú
        try {
            menuPrincipal.display();
        } catch (Exception e) {
            System.out.println("Se produjo un error en el sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
