package kjh.restapi.bulletin_board_reat_api.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Content {

    @Id @GeneratedValue
    private Long contentId;
    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;
    private String title;
    private String writer;
    @Lob
    private String article;
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateTime;
}
