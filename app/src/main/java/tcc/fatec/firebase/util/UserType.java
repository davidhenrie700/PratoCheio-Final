package tcc.fatec.firebase.util;

public enum UserType {

    DOADOR("doador"),
    DONATARIO("donatario");

    private String label;

    UserType(String code) {
        this.label = code;
    }

    public String getLabel() {
        return label;
    }
}
