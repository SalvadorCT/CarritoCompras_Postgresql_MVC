package model;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String password;
    private String rol;

    public Usuario() {}
    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public Usuario(String nombre, String email, String password, String rol) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(int id, String nombre, String email, String password, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }
    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setEmail(String correo) {this.email = correo;}
    public void setPassword(String contrasena) {this.password = contrasena;}
    public String getRol() {return rol;}
    public void setRol(String rol) {this.rol = rol;}

    public boolean verificarPassword(String password) {
        return this.password.equals(password);
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
