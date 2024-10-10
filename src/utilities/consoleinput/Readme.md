# Documentación del Paquete `consoleinput`

---

## Índice

- [Introducción](#introducción)
- [Características](#características)
- [Instalación](#instalación)
- [Uso Básico](#uso-básico)
- [Validadores Disponibles](#validadores-disponibles)
    - [IntegerValidator](#integervalidator)
    - [DoubleValidator](#doublevalidator)
    - [StringValidator](#stringvalidator)
    - [DateValidator](#datevalidator)
    - [CustomValidator](#customvalidator)
- [Internacionalización](#internacionalización)
- [Ejemplos de Uso](#ejemplos-de-uso)
    - [Lectura de un Número Entero en un Rango Específico](#lectura-de-un-número-entero-en-un-rango-específico)
    - [Lectura de un Número Decimal Positivo](#lectura-de-un-número-decimal-positivo)
    - [Validación de una Dirección de Email](#validación-de-una-dirección-de-email)
    - [Validación de una Fecha en Formato Personalizado](#validación-de-una-fecha-en-formato-personalizado)
    - [Uso de Validadores Personalizados](#uso-de-validadores-personalizados)
- [Configuración del Idioma](#configuración-del-idioma)
- [Extensión y Personalización](#extensión-y-personalización)
- [Consideraciones Finales](#consideraciones-finales)

---

## Introducción

El paquete `consoleinput` es una biblioteca diseñada para facilitar la validación de entradas de usuario en aplicaciones de consola Java. Proporciona una serie de validadores flexibles y modulares que permiten validar diferentes tipos de datos, aplicar condiciones personalizadas y soportar múltiples idiomas mediante internacionalización.

---

## Características

- **Validación de Diferentes Tipos de Datos**: Soporta validación de enteros, números decimales, cadenas, fechas y tipos personalizados.
- **Condiciones Personalizadas**: Permite aplicar condiciones y restricciones personalizadas a las entradas.
- **Patrón Builder**: Utiliza una API fluida para configurar validadores de manera sencilla y legible.
- **Validadores Predefinidos**: Incluye validadores predefinidos para casos comunes (emails, números de teléfono, contraseñas, etc.).
- **Internacionalización**: Soporta múltiples idiomas para los mensajes de error mediante archivos de recursos (`.properties`).
- **Extensibilidad**: Fácil de extender para soportar nuevos tipos de validaciones o formatos de entrada.

---

## Instalación

Agrega el paquete `consoleinput` a tu proyecto Java. Si utilizas un sistema de construcción como Maven o Gradle, puedes incluirlo como una dependencia. Si no, simplemente agrega los archivos `.java` a tu proyecto en la estructura de paquetes correspondiente.

---

## Uso Básico

Para utilizar `consoleinput`, importa las clases necesarias en tu código:

```java
import utilities.consoleinput.InputReader;
import utilities.consoleinput.validators.*;
import utilities.consoleinput.Messages;
```

Luego, puedes leer y validar entradas de usuario utilizando los validadores proporcionados.

---

## Validadores Disponibles

### IntegerValidator

Valida entradas de tipo entero (`int`).

**Métodos Disponibles:**

- `min(int minValue)`: Establece el valor mínimo permitido.
- `max(int maxValue)`: Establece el valor máximo permitido.
- `condition(Predicate<Integer> condition, String errorMessageKey, Object... params)`: Agrega una condición personalizada.
- `errorMessage(String errorMessageKey, Object... params)`: Establece el mensaje de error para la última condición agregada.

**Ejemplo:**

```java
int edad = InputReader.read("Ingrese su edad: ", new IntegerValidator().min(0).max(150));
```

### DoubleValidator

Valida entradas de tipo decimal (`double`).

**Métodos Disponibles:**

- `min(double minValue)`: Establece el valor mínimo permitido.
- `max(double maxValue)`: Establece el valor máximo permitido.
- `condition(Predicate<Double> condition, String errorMessageKey, Object... params)`: Agrega una condición personalizada.
- `errorMessage(String errorMessageKey, Object... params)`: Establece el mensaje de error para la última condición agregada.

**Ejemplo:**

```java
double salario = InputReader.read("Ingrese su salario: ", new DoubleValidator().min(0.0));
```

### StringValidator

Valida entradas de tipo cadena (`String`).

**Métodos Disponibles:**

- `minLength(int minLength)`: Establece la longitud mínima.
- `maxLength(int maxLength)`: Establece la longitud máxima.
- `matches(String regex)`: Establece un patrón de expresión regular que la cadena debe cumplir.
- `condition(Predicate<String> condition, String errorMessageKey, Object... params)`: Agrega una condición personalizada.
- `errorMessage(String errorMessageKey, Object... params)`: Establece el mensaje de error para la última condición agregada.

**Ejemplo:**

```java
String nombre = InputReader.read("Ingrese su nombre: ", new StringValidator().minLength(1).maxLength(50));
```

### DateValidator

Valida entradas de fecha (`LocalDate`).

**Métodos Disponibles:**

- `withFormat(String pattern)`: Establece el formato de fecha esperado (ejemplo: `"dd/MM/yyyy"`).
- `condition(Predicate<LocalDate> condition, String errorMessageKey, Object... params)`: Agrega una condición personalizada.
- `errorMessage(String errorMessageKey, Object... params)`: Establece el mensaje de error para la última condición agregada.

**Ejemplo:**

```java
LocalDate fechaNacimiento = InputReader.read("Ingrese su fecha de nacimiento (dd/MM/yyyy): ",
    new DateValidator().withFormat("dd/MM/yyyy"));
```

### CustomValidator

Permite crear validadores para tipos personalizados.

**Uso:**

```java
CustomValidator<T> validator = new CustomValidator<>(input -> {
    // Lógica para convertir la entrada a tipo T
});
```

**Ejemplo:**

```java
CustomValidator<URL> urlValidator = new CustomValidator<>(input -> new URL(input))
    .condition(url -> url.getProtocol().equals("https"), "error.url.https");
```

---

## Internacionalización

El paquete soporta múltiples idiomas mediante el uso de archivos de recursos `.properties`. Los mensajes de error y prompts se obtienen de estos archivos según la configuración regional establecida.

**Archivos de Recursos:**

- `messages.properties`: Archivo por defecto (inglés).
- `messages_es.properties`: Archivo para español.

**Configuración del Idioma:**

```java
Messages.setLocale(new Locale("es"));
```

---

## Ejemplos de Uso

### Lectura de un Número Entero en un Rango Específico

```java
int opcion = InputReader.read("Seleccione una opción (1-5): ", new IntegerValidator().min(1).max(5));
System.out.println("Opción seleccionada: " + opcion);
```

### Lectura de un Número Decimal Positivo

```java
double monto = InputReader.read("Ingrese el monto: ", new DoubleValidator().min(0.0));
System.out.println("Monto ingresado: " + monto);
```

### Validación de una Dirección de Email

Utilizando el validador predefinido:

```java
String email = InputReader.read("Ingrese su email: ", StringValidator.email());
System.out.println("Email ingresado: " + email);
```

### Validación de una Fecha en Formato Personalizado

```java
LocalDate fechaEvento = InputReader.read("Ingrese la fecha del evento (dd/MM/yyyy): ",
    new DateValidator().withFormat("dd/MM/yyyy")
    .condition(date -> !date.isBefore(LocalDate.now()), "error.date.past"));
System.out.println("Fecha del evento: " + fechaEvento);
```

**Agregar Mensaje Personalizado en `messages.properties`:**

```properties
error.date.past = La fecha no puede ser en el pasado.
```

### Uso de Validadores Personalizados

**Validar un Número de Teléfono de 10 Dígitos:**

```java
String telefono = InputReader.read("Ingrese su número de teléfono: ",
    new StringValidator().matches("\\d{10}").errorMessage("error.phone.invalid"));
System.out.println("Teléfono ingresado: " + telefono);
```

**Agregar Mensaje Personalizado en `messages.properties`:**

```properties
error.phone.invalid = El número de teléfono debe tener 10 dígitos.
```

---

## Configuración del Idioma

Puedes establecer el idioma de los mensajes antes de utilizar los validadores:

```java
import utilities.consoleinput.Messages;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        // Establecer idioma a español
        Messages.setLocale(new Locale("es"));

        // Resto del código...
    }
}
```

---

## Extensión y Personalización

El paquete `consoleinput` es extensible y permite:

- **Agregar Nuevos Validadores**: Puedes crear tus propios validadores implementando la interfaz `InputValidator<T>`.
- **Personalizar Mensajes de Error**: Añade tus propias claves y mensajes en los archivos de recursos para mensajes de error personalizados.
- **Soportar Nuevos Idiomas**: Agrega archivos `messages_{language}.properties` para soportar más idiomas.

---

## Consideraciones Finales

El paquete `consoleinput` facilita la validación de entradas en aplicaciones de consola, mejorando la experiencia del usuario y reduciendo la cantidad de código necesario para validar entradas comunes. Al soportar internacionalización y personalización, es una herramienta versátil para desarrolladores Java.