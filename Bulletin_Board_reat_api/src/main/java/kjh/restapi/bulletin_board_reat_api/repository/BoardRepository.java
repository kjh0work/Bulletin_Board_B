package kjh.restapi.bulletin_board_reat_api.repository;

import kjh.restapi.bulletin_board_reat_api.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
