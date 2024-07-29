package kjh.restapi.bulletin_board_reat_api.controller;

import kjh.restapi.bulletin_board_reat_api.dto.ContentResource;
import kjh.restapi.bulletin_board_reat_api.dto.CreateContentDto;
import kjh.restapi.bulletin_board_reat_api.dto.UpdateContentDto;
import kjh.restapi.bulletin_board_reat_api.entity.Content;
import kjh.restapi.bulletin_board_reat_api.oauth2.CustomOauth2User;
import kjh.restapi.bulletin_board_reat_api.service.ContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/content", consumes = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping("/create")
    public ResponseEntity createContent(@Validated  @RequestBody CreateContentDto contentDto, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        //Todo
        //validator를 사용해 검증 로직 추가

        Content newContent = contentService.saveContent(contentDto);

        ContentResource contentResource = new ContentResource(newContent);
        //self
        contentResource.add(linkTo(methodOn(ContentController.class).createContent(contentDto, errors)).slash(newContent.getContentId()).withSelfRel());

        contentResource.add(linkTo(methodOn(ContentController.class).getContent(newContent.getContentId(), null)).withRel("get_content"));
        contentResource.add(linkTo(methodOn(ContentController.class).updateContent(null, null)).withRel("update"));
        contentResource.add(linkTo(methodOn(ContentController.class).deleteContent(newContent.getContentId())).withRel("delete"));
        contentResource.add(linkTo(methodOn(ContentController.class).getUserContentList(null, null)).withRel("get_content_list"));

        URI uri = linkTo(methodOn(ContentController.class).createContent(contentDto, errors)).toUri();

        return ResponseEntity.created(uri).body(contentResource);
    }

    @PutMapping("/update")
    public ResponseEntity updateContent(@Validated @RequestBody UpdateContentDto contentDto, Errors errors){
        Content content = contentService.updateContent(contentDto);
        ContentResource resource = new ContentResource(content);
        resource.add(linkTo(methodOn(ContentController.class).updateContent(contentDto,errors)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/user")
    public String getUser(@AuthenticationPrincipal CustomOauth2User user){
        return user.getUserHash()+", "+user.getName();
        /*
        * 출력해본 결과
        * CustomOauth2User로 principle을 받을 시
        * naver_afmYm9tb5_FFSDrYAM_dwpX9ZASYPL195K0un22xa80, 김재훈
        * 이렇게 출력된다.
        * 결론 : userhash로 user와 account를 같은지 판단할 수 있을 듯
        */
    }

    @GetMapping("/get")
    public ResponseEntity getContent(@RequestParam Long contentId, @AuthenticationPrincipal CustomOauth2User user) {
        Content content = contentService.findById(contentId);
        ContentResource resource = new ContentResource(content);

        if(user.getUserHash().equals(content.getAccount().getUserHash())) {
            resource.add(linkTo(methodOn(ContentController.class).updateContent(null, null)).withRel("update"));
            resource.add(linkTo(methodOn(ContentController.class).deleteContent(content.getContentId())).withRel("delete"));
        }
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/user/get")
    public ResponseEntity getUserContentList(Pageable pageable, PagedResourcesAssembler<Content> assembler){
        Page<Content> page = contentService.findAllPage(pageable);
        var ContentModel = assembler.toModel(page, c -> {
            ContentResource resource = new ContentResource(c);
           //resource.add(linkTo())
            return resource;
        });
        return ResponseEntity.ok(ContentModel);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteContent(@RequestParam Long contentId){
        contentService.deleteContent(contentId);
        return ResponseEntity.ok().build();
    }
}
