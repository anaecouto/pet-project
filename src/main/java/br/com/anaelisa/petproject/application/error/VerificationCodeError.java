package br.com.anaelisa.petproject.application.error;

public class VerificationCodeError extends RuntimeException {
    public VerificationCodeError(String message) {
        super(message);
    }
}
