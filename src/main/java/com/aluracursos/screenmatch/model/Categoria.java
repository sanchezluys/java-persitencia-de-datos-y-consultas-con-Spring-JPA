package com.aluracursos.screenmatch.model;

public enum Categoria {

    ACCION("Action"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIMEN("Crime"),
    ROMANCE("Romance"),
    DOCUMENTAL("Documentary"),
    CORTOMETRAJE("Short");

    private String categoriasOmdb;

    Categoria(String categoriasOmdb) {
        this.categoriasOmdb = categoriasOmdb;
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
}
