package kjh.restapi.bulletin_board_reat_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Board {

    @Id @GeneratedValue
    private Long boardId;
    private String boardName;

//    @OneToMany(mappedBy = "board")
//    private List<Content> contentList = new ArrayList<>();
}
