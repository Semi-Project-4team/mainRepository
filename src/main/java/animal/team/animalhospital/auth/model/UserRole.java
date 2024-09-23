package animal.team.animalhospital.auth.model;

public enum UserRole {

    USER_PERSON("USER_PERSON"),
    ADMIN_HOSPITAL("USER_HOSPITAL");

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
                '}';
    }
}
