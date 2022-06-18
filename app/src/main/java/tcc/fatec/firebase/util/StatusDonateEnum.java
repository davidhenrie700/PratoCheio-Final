package tcc.fatec.firebase.util;

public enum StatusDonateEnum {

    PENDENTE(0L, "Pendente"),
    ACEITO(1L, "Aceito"),
    A_CAMINHO(2L, "ACaminho"),
    ENTREGUE(3L, "Entregue");

    private Long codigo;
    private String label;

    StatusDonateEnum(Long codigo, String label) {
        this.codigo = codigo;
        this.label = label;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getLabel() {
        return label;
    }
}
