package kjh.restapi.bulletin_board_reat_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Board {

    @Id @GeneratedValue
    private Long boardId;

    @Column(unique = true)
    private String boardName;

    @Column
    private LocalDateTime createTime = LocalDateTime.now();

//    @OneToMany(mappedBy = "board")
//    private List<Content> contentList = new ArrayList<>();
}
