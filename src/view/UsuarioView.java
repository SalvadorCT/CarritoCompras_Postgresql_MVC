package view;

import model.Usuario;
import java.util.ArrayList;

public class UsuarioView {
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printUsuarios(ArrayList<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNombre() + " - " + usuario.getEmail());
        }
    }
}
