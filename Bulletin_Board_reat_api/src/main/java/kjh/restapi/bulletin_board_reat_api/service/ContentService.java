package kjh.restapi.bulletin_board_reat_api.service;

import kjh.restapi.bulletin_board_reat_api.entity.Content;
import kjh.restapi.bulletin_board_reat_api.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public Content saveContent(Content content) {
        return contentRepository.save(content);
    }
}
