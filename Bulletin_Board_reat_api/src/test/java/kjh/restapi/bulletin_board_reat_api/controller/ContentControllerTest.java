package kjh.restapi.bulletin_board_reat_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kjh.restapi.bulletin_board_reat_api.dto.CreateContentDto;

import kjh.restapi.bulletin_board_reat_api.entity.Account;
import kjh.restapi.bulletin_board_reat_api.entity.Board;
import kjh.restapi.bulletin_board_reat_api.entity.Content;
import kjh.restapi.bulletin_board_reat_api.repository.AccountRepository;
import kjh.restapi.bulletin_board_reat_api.repository.BoardRepository;
import kjh.restapi.bulletin_board_reat_api.repository.ContentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    @DisplayName("정상적으로 Content를 생성")
    @WithMockUser
    public void createContent() throws Exception {

        CreateContentDto contentDto = generateContentDto();
        String json = objectMapper.writeValueAsString(contentDto);

        this.mockMvc.perform(post("/Content/create")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                ;
    }

    private CreateContentDto generateContentDto(){
        CreateContentDto contentDto = CreateContentDto.builder()
                .accountId("naver_account")
                .boardId(0L)
                .title("title0")
                .article("article_Test")
                .createTime(LocalDateTime.now())
                .lastUpdateTime(LocalDateTime.now())
                .build();
        return contentDto;
    }

//    @Test
//    @DisplayName("Account, Board, Content 연관관계 테스트")
//    public void JPATest(){
//        CreateContentDto contentDto =  new CreateContentDto(0L,"naver_account"+0L,"title"+0L,"article"+0L, LocalDateTime.now(),LocalDateTime.now());
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        Content content = modelMapper.map(contentDto, Content.class);
//        Content saved = contentRepository.save(content);
//        System.out.println("==========================");
//        System.out.println(saved.toString());
//        System.out.println("==========================");
//    }
}