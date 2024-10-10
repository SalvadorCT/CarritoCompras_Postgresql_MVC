package controllers;

import model.dao.UsuarioDAO;
import model.Usuario;
import view.UsuarioView;

public class UsuarioController {
    private UsuarioView usuarioView;
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioView = new UsuarioView();
        this.usuarioDAO = new UsuarioDAO();
    }

    public void addUsuario(String nombre, String email, String password) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuarioDAO.addUsuario(usuario);
        usuarioView.printMessage("Usuario registrado con éxito");
    }

    public int login(String email, String password) {
        return usuarioDAO.login(email, password);
    }
}