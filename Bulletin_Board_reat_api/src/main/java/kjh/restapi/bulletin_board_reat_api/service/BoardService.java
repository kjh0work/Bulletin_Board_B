package kjh.restapi.bulletin_board_reat_api.service;

import kjh.restapi.bulletin_board_reat_api.entity.Board;
import kjh.restapi.bulletin_board_reat_api.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public Board save(Board board) {
        return boardRepository.save(board);
    }
}
