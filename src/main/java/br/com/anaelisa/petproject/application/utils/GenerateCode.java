package br.com.anaelisa.petproject.application.utils;

import java.util.Random;

public enum GenerateCode {

    VERIFICATION_CODE {
        @Override
        String generateCode() {
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder verificationCode = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                verificationCode.append(characters.charAt(random.nextInt(characters.length())));
            }
            return verificationCode.toString();
        }
    };

    abstract String generateCode();

    public String execute() {
        return this.generateCode();
    }
}
