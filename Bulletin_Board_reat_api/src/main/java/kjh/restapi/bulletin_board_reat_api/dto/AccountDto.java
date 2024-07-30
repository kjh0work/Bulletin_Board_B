package kjh.restapi.bulletin_board_reat_api.dto;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import kjh.restapi.bulletin_board_reat_api.entity.Board;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class AccountDto {

    public AccountDto(String userHash, String userName,String email){
        this.userHash = userHash;
        this.userName = userName;
        this.email = email;
    }

    private String userHash;
    private String userName;
    private String email;
    private String role;
    private LocalDate signUpDate;
    private LocalDate lastLoginDate = LocalDate.now();


}
