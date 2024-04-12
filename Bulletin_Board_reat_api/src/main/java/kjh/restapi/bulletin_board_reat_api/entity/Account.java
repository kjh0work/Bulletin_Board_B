package kjh.restapi.bulletin_board_reat_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    private String userHash;
    private String userName;

    @OneToMany(mappedBy = "account")
    private List<Board> boardList = new ArrayList<>();
    private LocalDate createDate;
    private LocalDateTime lastLoginTime;
}
