package kjh.restapi.bulletin_board_reat_api.controller;

import kjh.restapi.bulletin_board_reat_api.dto.BoardDto;
import kjh.restapi.bulletin_board_reat_api.dto.BoardResource;
import kjh.restapi.bulletin_board_reat_api.entity.Board;
import kjh.restapi.bulletin_board_reat_api.repository.BoardRepository;
import kjh.restapi.bulletin_board_reat_api.service.BoardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private ModelMapper modelMapper;

    /*
    * 1. 파라미터 입력값 확인
    * 2. 리소스를 반환, 리소스에 link정보 추가
    * 3.
    *
    * */

    @PostMapping
    public ResponseEntity createBoard(@RequestBody String boardName, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        Board board = new Board();
        board.setBoardName(boardName);
        Board saved = boardService.save(board);

        URI uri = linkTo(methodOn(BoardController.class).createBoard(boardName, errors)).toUri();

        BoardResource boardResource = new BoardResource(saved);

        return ResponseEntity.created(uri).body(boardResource);
    }


}
