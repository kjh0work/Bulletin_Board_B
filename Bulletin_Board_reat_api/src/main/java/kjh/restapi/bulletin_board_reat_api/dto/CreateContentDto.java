package kjh.restapi.bulletin_board_reat_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class CreateContentDto {
    private Long boardId;
    private String accountId;
    private String title;
    private String article;
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateTime;
}
