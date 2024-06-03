package com.aluracursos.screenmatch.model;

public enum Categoria {

    ACCION("Action","Accion"),
    COMEDIA("Comedy", "Comedia"),
    DRAMA("Drama", "Drama"),
    CRIMEN("Crime", "Crimen"),
    ROMANCE("Romance", "Romance"),
    DOCUMENTAL("Documentary", "Documental"),
    CORTOMETRAJE("Short", "Cortometraje");

    private String categoriasOmdb;
    private String categoriasEspanol;

    Categoria(String categoriasOmdb, String categoriasEspanol) {
        this.categoriasOmdb = categoriasOmdb;
        this.categoriasEspanol = categoriasEspanol;
    }

    public static Categoria fromString(String text)
    {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriasOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna Categoria encontrada "+ text);
    }
    //
    public static Categoria fromEspanol(String text)
    {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriasEspanol.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna Categoria encontrada "+ text);
    }
}
