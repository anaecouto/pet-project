package br.com.anaelisa.petproject.application.component.auth.enums;

public enum EnumRoles {
    USER("USER"),
    ADMIN("ADMIN");

    private final String description;

    EnumRoles(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

