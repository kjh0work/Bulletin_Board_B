package kjh.restapi.bulletin_board_reat_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class UpdateContentDto {
    private Long contentId;
    private String title;
    private String article;
}
