package tcc.fatec.firebase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoModel {

    private String cep;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cidade;

    private String estado;

    @Override
    public String toString() {
        return logradouro + ", " + numero + ", " + bairro + ", CEP: " + cep + " - " + cidade + "/" + estado;
    }
}
