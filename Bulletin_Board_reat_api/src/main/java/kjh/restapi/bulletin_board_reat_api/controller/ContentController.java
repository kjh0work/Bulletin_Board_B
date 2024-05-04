package kjh.restapi.bulletin_board_reat_api.controller;

import kjh.restapi.bulletin_board_reat_api.dto.CreateContentDto;
import kjh.restapi.bulletin_board_reat_api.entity.Content;
import kjh.restapi.bulletin_board_reat_api.service.ContentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "/Content", consumes = MediaType.APPLICATION_JSON_VALUE)
public class ContentController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity CreateContent(@Validated  @RequestBody CreateContentDto contentDto, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Content content = modelMapper.map(contentDto, Content.class);
        Content newContent = contentService.saveContent(content);

        URI uri = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ContentController.class).CreateContent(contentDto,errors)).slash(newContent.getContentId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
