package br.com.anaelisa.petproject.application.error;

public class ResourceAlreadyExists extends RuntimeException {

    public ResourceAlreadyExists(String message) {
        super(message);
    }
}
