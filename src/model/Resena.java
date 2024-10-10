package model;

public class Resena {
    private String comentario;
    private int calificacion;

    public Resena(String comentario, int calificacion) {
        this.comentario = comentario;
        this.calificacion = calificacion;
    }
    public String getComentario() {
        return comentario;
    }
    public int getCalificacion() {
        return calificacion;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
