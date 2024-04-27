package kjh.restapi.bulletin_board_reat_api.oauth2;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class CustomOauth2User implements OAuth2User {

    private final OAuth2Response oAuth2Response;

    private final String role = "USER";

    public CustomOauth2User(OAuth2Response oAuth2Response){
        this.oAuth2Response = oAuth2Response;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role;
            }
        });
        return collection;
    }

    @Override
    public String getName() {
        return oAuth2Response.getName();
    }

    public String getUserHash(){
        return oAuth2Response.getProvider()+"_"+oAuth2Response.getProviderId();
    }
}
