package tcc.fatec.firebase.model;

import com.google.firebase.database.Exclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tcc.fatec.firebase.util.StatusDonateEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonateModel {

    @Exclude
    private String id;

    private String data;
    private String alimento;
    private UserDetailsModel user;
    private Long status;
}