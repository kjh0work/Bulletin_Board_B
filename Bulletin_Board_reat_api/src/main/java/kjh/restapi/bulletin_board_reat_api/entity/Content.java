package kjh.restapi.bulletin_board_reat_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Content {

    @Id @GeneratedValue
    private Long contentId;
    @ManyToOne
    @JoinColumn(name = "Account_ID")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;
    private String title;
    @Lob
    private String article;
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateTime;

    @Override
    public String toString() {
        return "Content{" +
                "contentId=" + contentId +
                ", account=" + "account.getUserHash()" +
                ", board=" + "board.getBoardId()" +
                ", title='" + title + '\'' +
                ", article='" + article + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
