package kjh.restapi.bulletin_board_reat_api.repository;

import kjh.restapi.bulletin_board_reat_api.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
