package kjh.restapi.bulletin_board_reat_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ContentDto {
    private Long contentId;
    private Long boardId;
    private String accountId;
    private String title;
    private String article;
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateTime;
}
