package kjh.restapi.bulletin_board_reat_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kjh.restapi.bulletin_board_reat_api.dto.CreateContentDto;

import kjh.restapi.bulletin_board_reat_api.entity.Account;
import kjh.restapi.bulletin_board_reat_api.entity.Board;
import kjh.restapi.bulletin_board_reat_api.entity.Content;
import kjh.restapi.bulletin_board_reat_api.repository.AccountRepository;
import kjh.restapi.bulletin_board_reat_api.repository.BoardRepository;
import kjh.restapi.bulletin_board_reat_api.repository.ContentRepository;
import kjh.restapi.bulletin_board_reat_api.service.ContentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ContentRepository contentRepository;

    //@MockBean
    @Autowired
    private ContentService contentService;

    @Autowired
    private ObjectMapper objectMapper;

//    @BeforeEach
//    public void setup(){
//        Content content = generateContent("naver_account");
//        when(contentService.saveContent(any(CreateContentDto.class))).thenReturn(content);
//        when(contentService.findById(content.getContentId())).thenReturn(content);
//    }


    @Test
    @DisplayName("정상적으로 Content를 생성")
    @WithMockUser
    public void createContent() throws Exception {
        String userhash = "naver_account";

        CreateContentDto contentDto = generateContentDto(userhash);
        String json = objectMapper.writeValueAsString(contentDto);

        this.mockMvc.perform(post("/content/create")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("$.content.contentId").exists())
                ;
    }

    @Test
    @DisplayName("정상적으로 content 가져오기")
    @WithMockUser
    public void getContent_200() throws Exception {
        String userhash = "naver_account";

        CreateContentDto contentDto = generateContentDto(userhash);
        Content content = this.contentService.saveContent(contentDto);

        this.mockMvc.perform(get("/content/get")
                        .contentType(MediaType.APPLICATION_JSON)
                .param("contentId",content.getContentId()+""))
                .andDo(print())
                .andExpect(status().isOk())
                ;
    }


    private CreateContentDto generateContentDto(String userhash){
        Account account = new Account();
        account.setUserHash(userhash);
        account.setRole("USER");
        account.setEmail("user@email.com");

        this.accountRepository.save(account);

        Board board = new Board();
        board.setBoardName("Free_Board");

        Board savedBoard = this.boardRepository.save(board);

        CreateContentDto contentDto = CreateContentDto.builder()
                .accountId(userhash)
                .boardId(savedBoard.getBoardId())
                .title("title0")
                .article("article_Test")
                .createTime(LocalDateTime.now())
                .lastUpdateTime(LocalDateTime.now())
                .build();
        return contentDto;
    }

    public Content generateContent(String userhash){
        Account account = new Account();
        account.setUserHash(userhash);
        account.setRole("USER");
        account.setEmail("user@email.com");

        this.accountRepository.save(account);

        Board board = new Board();
        board.setBoardName("Free_Board");

        Board savedBoard = this.boardRepository.save(board);
        Content content = new Content();
        content.setAccount(account);
        content.setBoard(savedBoard);
        content.setTitle("title");
        content.setArticle("article");
        return this.contentRepository.save(content);
    }

}