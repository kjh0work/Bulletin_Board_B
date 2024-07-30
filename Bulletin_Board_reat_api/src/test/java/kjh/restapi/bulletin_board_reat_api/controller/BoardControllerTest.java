package kjh.restapi.bulletin_board_reat_api.controller;

import kjh.restapi.bulletin_board_reat_api.entity.Board;
import kjh.restapi.bulletin_board_reat_api.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BoardControllerTest {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("정상적으로 board 생성")
    @WithMockUser(roles = "ADMIN")
    public void createBoard() throws Exception {
        String boardName = "FreeBoard";

        this.mockMvc.perform(post("/board")
                .content(boardName).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("board.boardName").value("FreeBoard"))
                ;
    }

    @Test
    @DisplayName("정상적으로 Board 전체 목록 가져오기")
    //@WithMockUser(roles = "USER")
    public void getAllBoard() throws Exception {
        generateBoard(5);

        this.mockMvc.perform(get("/board"))
                .andDo(print())
                .andExpect(status().isOk())
                ;
    }

    private void generateBoard(int n){
        IntStream.range(0,n).forEach( i -> {
            Board board = new Board();
            board.setBoardName(i+" board");
            boardRepository.save(board);
        });
    }
}