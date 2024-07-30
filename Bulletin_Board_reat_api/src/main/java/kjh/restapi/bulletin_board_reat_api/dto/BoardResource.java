package kjh.restapi.bulletin_board_reat_api.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import kjh.restapi.bulletin_board_reat_api.controller.BoardController;
import kjh.restapi.bulletin_board_reat_api.entity.Board;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Getter @Setter
public class BoardResource extends RepresentationModel<BoardResource> {

    //@JsonUnwrapped
    private Board board;

    public BoardResource(Board board, Links... links){
        this.board = board;
        add(linkTo(BoardController.class).slash(board.getBoardId()).withSelfRel());
    }
}
