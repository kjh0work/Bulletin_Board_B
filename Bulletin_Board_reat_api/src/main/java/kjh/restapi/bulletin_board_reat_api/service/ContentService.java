package kjh.restapi.bulletin_board_reat_api.service;

import kjh.restapi.bulletin_board_reat_api.dto.ContentDto;
import kjh.restapi.bulletin_board_reat_api.dto.CreateContentDto;
import kjh.restapi.bulletin_board_reat_api.dto.UpdateContentDto;
import kjh.restapi.bulletin_board_reat_api.entity.Account;
import kjh.restapi.bulletin_board_reat_api.entity.Board;
import kjh.restapi.bulletin_board_reat_api.entity.Content;
import kjh.restapi.bulletin_board_reat_api.repository.AccountRepository;
import kjh.restapi.bulletin_board_reat_api.repository.BoardRepository;
import kjh.restapi.bulletin_board_reat_api.repository.ContentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Content saveContent(CreateContentDto contentDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Content content = modelMapper.map(contentDto, Content.class);

        Account account = accountRepository.findByUserHash(contentDto.getAccountId());
        Board board = boardRepository.findById(contentDto.getBoardId()).orElseThrow();

        content.setAccount(account);
        content.setBoard(board);

        return contentRepository.save(content);
    }

    public Page<Content> findAllPage(Pageable pageable) {
        return contentRepository.findAll(pageable);
    }

    public Content findById(Long contentId) {
        return contentRepository.findById(contentId).orElseThrow();
    }

    public Content updateContent(UpdateContentDto contentDto) {
        //ToDo
        //orElseThrow부분 리팩토링 필요할 듯
        Content content = this.contentRepository.findById(contentDto.getContentId()).orElseThrow();

        content.setTitle(contentDto.getTitle());
        content.setArticle(contentDto.getArticle());

        //트랜잭션 관리, 변경 감지란?
        return this.contentRepository.save(content);
    }

    public void deleteContent(Long contentId) {
        this.contentRepository.deleteById(contentId);
    }
}
