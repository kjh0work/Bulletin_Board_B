package kjh.restapi.bulletin_board_reat_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import javax.swing.text.StyleContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Account {

    @Id
    private String userHash;
    private String userName;
    private String email;
    private String role;

//    @OneToMany(mappedBy = "account")
//    private List<Content> contentList = new ArrayList<>();
    private LocalDate signUpDate;
    private LocalDate lastLoginDate = LocalDate.now();

    public Account(String userHash, String name, String email, LocalDate now) {
        this.userHash = userHash;
        this.userName = name;
        this.email = email;
        this.signUpDate = now;
    }

}
