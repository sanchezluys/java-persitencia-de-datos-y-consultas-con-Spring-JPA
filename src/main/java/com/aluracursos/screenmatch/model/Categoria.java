package com.aluracursos.screenmatch.model;

public enum Categoria {

    COMEDY("Comedy"),
    DRAMA("Drama"),
    THRILLER("Thriller"),
    ROMANCE("Romance"),
    SCI_FI("Sci fi"),
    ACTION("Action"),
    FANTASY("FantASY"),
    HORROR("Horror"),
    MUSICAL("Musical"),
    WAR("War"),
    ADVENTURE("Adventure"),
    FAMILY("Family"),
    WESTERN("Western"),
    CRIME("Crime");

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
