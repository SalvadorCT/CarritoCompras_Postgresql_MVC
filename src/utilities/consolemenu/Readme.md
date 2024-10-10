# Documentación del Paquete `consolemenu`

---

## Índice

- [Introducción](#introducción)
- [Características](#características)
- [Instalación](#instalación)
- [Uso Básico](#uso-básico)
- [Componentes Principales](#componentes-principales)
    - [Menu](#menu)
    - [MenuItem](#menuitem)
    - [Command](#command)
    - [MenuRenderer](#menurenderer)
    - [InputValidator](#inputvalidator)
- [Personalización del Menú](#personalización-del-menú)
- [Ejemplos de Uso](#ejemplos-de-uso)
    - [Creación de un Menú Simple](#creación-de-un-menú-simple)
    - [Uso de Submenús](#uso-de-submenús)
    - [Implementación de Comandos Personalizados](#implementación-de-comandos-personalizados)
- [Patrones de Diseño Aplicados](#patrones-de-diseño-aplicados)
- [Extensión y Personalización](#extensión-y-personalización)
- [Consideraciones Finales](#consideraciones-finales)

---

## Introducción

El paquete `consolemenu` es una biblioteca Java diseñada para simplificar la creación de menús interactivos en aplicaciones de consola. Proporciona una estructura flexible y modular que permite definir menús, submenús y acciones asociadas de manera sencilla y organizada.

---

## Características

- **Creación Fácil de Menús**: Define menús y opciones de manera sencilla.
- **Soporte para Submenús**: Crea menús jerárquicos con submenús anidados.
- **Patrón Command**: Asocia acciones a las opciones del menú utilizando el patrón Command.
- **Personalización**: Personaliza la apariencia y el comportamiento del menú.
- **Validación de Entradas**: Valida las opciones ingresadas por el usuario.

---

## Instalación

Agrega el paquete `consolemenu` a tu proyecto Java. Si utilizas un sistema de construcción como Maven o Gradle, puedes incluirlo como una dependencia. Si no, simplemente agrega los archivos `.java` a tu proyecto en la estructura de paquetes correspondiente.

---

## Uso Básico

Para utilizar `consolemenu`, importa las clases necesarias en tu código:

```java
import utilities.consolemenu.*;
import utilities.consolemenu.command.*;
import utilities.consolemenu.renderer.*;
import utilities.consolemenu.input.*;
```

Luego, puedes crear instancias de `Menu`, agregar opciones y comandos, y mostrar el menú al usuario.

---

## Componentes Principales

### Menu

Representa un menú que contiene una lista de opciones (`MenuItem`).

**Métodos Clave:**

- `addCommandItem(String key, String description, Command command)`: Agrega una opción que ejecuta un comando.
- `addSubmenuItem(String key, String description, Menu submenu)`: Agrega una opción que muestra un submenú.
- `display()`: Muestra el menú y maneja la interacción con el usuario.
- `setRenderer(MenuRenderer renderer)`: Establece el renderizador del menú.
- `setInputValidator(InputValidator inputValidator)`: Establece el validador de entrada.
- `exit()`: Cierra el menú actual y regresa al menú anterior.

### MenuItem

Representa una opción en el menú.

**Atributos Clave:**

- `key`: La tecla o comando que el usuario ingresa para seleccionar la opción.
- `description`: Descripción de la opción que se muestra al usuario.
- `action`: La acción a ejecutar (puede ser un `Command` o un `Menu`).

### Command

Interfaz que define el método `execute()`. Las clases que implementan `Command` encapsulan acciones a realizar cuando se selecciona una opción del menú.

**Ejemplo:**

```java
public class SaludarCommand implements Command {
    @Override
    public void execute() {
        System.out.println("¡Hola, usuario!");
    }
}
```

### MenuRenderer

Interfaz para personalizar la visualización del menú. Se proporciona una implementación por defecto (`DefaultMenuRenderer`), pero puedes crear tus propios renderizadores.

### InputValidator

Interfaz para validar la entrada del usuario. La implementación por defecto es `DefaultInputValidator`.

---

## Personalización del Menú

Puedes personalizar el menú estableciendo tu propio `MenuRenderer` e `InputValidator`.

**Establecer un Renderer Personalizado:**

```java
MenuRenderer renderer = new MiMenuRenderer();
menu.setRenderer(renderer);
```

**Establecer un Validador de Entrada Personalizado:**

```java
InputValidator inputValidator = new MiInputValidator();
menu.setInputValidator(inputValidator);
```

---

## Ejemplos de Uso

### Creación de un Menú Simple

```java
Menu menuPrincipal = new Menu("Menú Principal");

menuPrincipal.addCommandItem("1", "Saludar", new SaludarCommand());
menuPrincipal.addCommandItem("2", "Despedirse", new DespedirseCommand());
menuPrincipal.addCommandItem("0", "Salir", new ExitCommand());

menuPrincipal.display();
```

**Implementación de Comandos:**

```java
public class SaludarCommand implements Command {
    @Override
    public void execute() {
        System.out.println("¡Hola, usuario!");
    }
}

public class DespedirseCommand implements Command {
    @Override
    public void execute() {
        System.out.println("¡Adiós, usuario!");
    }
}
```

### Uso de Submenús

```java
Menu menuPrincipal = new Menu("Menú Principal");
Menu menuConfiguracion = new Menu("Configuración");

menuConfiguracion.addCommandItem("1", "Cambiar Idioma", new CambiarIdiomaCommand());
menuConfiguracion.addCommandItem("0", "Regresar", () -> menuConfiguracion.exit());

menuPrincipal.addSubmenuItem("1", "Configuración", menuConfiguracion);
menuPrincipal.addCommandItem("0", "Salir", new ExitCommand());

menuPrincipal.display();
```

### Implementación de Comandos Personalizados

```java
public class CambiarIdiomaCommand implements Command {
    @Override
    public void execute() {
        // Lógica para cambiar el idioma
        System.out.println("Idioma cambiado.");
    }
}
```

---

## Patrones de Diseño Aplicados

- **Patrón Command**: Permite encapsular acciones en objetos, facilitando la extensión y mantenimiento.
- **Patrón Composite**: Los menús y submenús se manejan de manera jerárquica.
- **Principios SOLID**: La biblioteca está diseñada siguiendo los principios SOLID para asegurar flexibilidad y mantenibilidad.

---

## Extensión y Personalización

- **Crear Nuevos Renderizadores**: Implementa la interfaz `MenuRenderer` para cambiar la apariencia del menú.
- **Personalizar la Validación de Entradas**: Implementa la interfaz `InputValidator` para cambiar cómo se valida la entrada del usuario.
- **Agregar Nuevas Funcionalidades**: Extiende las clases existentes o crea nuevas para agregar funcionalidad específica.

---

## Consideraciones Finales

El paquete `consolemenu` simplifica la creación de menús interactivos en aplicaciones de consola Java, permitiendo un desarrollo más rápido y organizado. Su flexibilidad y diseño modular lo hacen adecuado para una amplia gama de aplicaciones.
