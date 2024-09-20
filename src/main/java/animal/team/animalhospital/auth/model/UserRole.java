package animal.team.animalhospital.auth.model;

public enum UserRole {

    USER("USER"),
    ADMIN("ADMIN");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "role='" + role + '\'' +
                "} " + super.toString();
    }
}
