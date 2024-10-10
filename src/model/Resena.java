package model;

public class Resena {
    private int usuarioId;
    private int productoId;
    private String comentario;
    private int calificacion;

    public Resena() {}
    public Resena(int usuarioId, int productoId, String comentario, int calificacion) {
        this.usuarioId = usuarioId;
        this.productoId = productoId;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }
    public Resena(String comentario, int calificacion) {
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public int getUsuarioId() {return usuarioId;}
    public void setUsuarioId(int usuarioId) {this.usuarioId = usuarioId;}

    public int getProductoId() {return productoId;}
    public void setProductoId(int productoId) {this.productoId = productoId;}

    public String getComentario() {return comentario;}
    public void setComentario(String comentario) {this.comentario = comentario;}

    public int getCalificacion() {return calificacion;}
    public void setCalificacion(int calificacion) {this.calificacion = calificacion;}
}