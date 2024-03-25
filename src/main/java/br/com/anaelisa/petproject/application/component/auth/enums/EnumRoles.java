package br.com.anaelisa.petproject.application.component.auth.enums;

public enum EnumRoles {

    COMMON(1, "COMMON"),
    USER(2, "USER"),
    ADMIN(3, "ADMIN");

    private final int id;
    private final String description;

    EnumRoles(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return this.description;
    }

    public static String getDescription(int id) {
        for (EnumRoles role : EnumRoles.values()) {
            if (role.getId() == id) {
                return role.getDescription();
            }
        }
        return null;
    }
}

