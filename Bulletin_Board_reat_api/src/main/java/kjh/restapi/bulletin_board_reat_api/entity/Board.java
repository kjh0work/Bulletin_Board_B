package kjh.restapi.bulletin_board_reat_api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Board {

    @Id @GeneratedValue
    private Long boardId;
    private String boardName;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @OneToMany(mappedBy = "board")
    private List<Content> contentList;
}
