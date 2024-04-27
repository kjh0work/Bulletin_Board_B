package kjh.restapi.bulletin_board_reat_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Data
public class Account {

    @Id
    private String userHash;
    private String userName;
    private String email;

    @OneToMany(mappedBy = "account")
    private List<Board> boardList = new ArrayList<>();
    private LocalDate signUpDate;
    private LocalDate lastLoginDate = LocalDate.now();

    public Account(String userHash, String userName,String email){
        this.userHash = userHash;
        this.userName = userName;
        this.email = email;
    }

}
