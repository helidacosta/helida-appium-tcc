package com.tcc.helidacosta.exception;

public class PlatformException extends Exception {

    public PlatformException() {
        super("Plataforma não existente. As plataformas permitidas são ios e android");
    }
}
