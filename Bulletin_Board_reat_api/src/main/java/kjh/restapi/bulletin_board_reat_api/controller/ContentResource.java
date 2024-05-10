package kjh.restapi.bulletin_board_reat_api.controller;

import kjh.restapi.bulletin_board_reat_api.entity.Content;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter @Setter
public class ContentResource extends RepresentationModel<ContentResource> {

    private Content content;

    public ContentResource(Content content){
        this.content = content;
        //add(linkTo(ContentController.class).slash(content.getContentId()).withSelfRel());
    }
}
