package kjh.restapi.bulletin_board_reat_api.repository;

import kjh.restapi.bulletin_board_reat_api.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

    Account findByUserHash(String userHash);

}
