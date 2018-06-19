package com.tcc.helidacosta.exception;

public class PropertyException extends Exception {

    public PropertyException() {
        super("Propriedade não existente ou valor da propriedade está vazio");
    }
}
