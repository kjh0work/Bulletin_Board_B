package kjh.restapi.bulletin_board_reat_api.oauth2;

import kjh.restapi.bulletin_board_reat_api.entity.Account;
import kjh.restapi.bulletin_board_reat_api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Component
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        if(userRequest == null){
            OAuth2Error error = new OAuth2Error("userRequest is null");
            throw new OAuth2AuthenticationException(error);
        }

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = null;
        if(registrationId.equals("naver")){
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if(registrationId.equals("google")){

        }
        else{
            throw new OAuth2AuthenticationException("unSatisfied registrationId");
        }

        String userHash = oAuth2Response.getProvider()+"_"+oAuth2Response.getProviderId();
        Account account = this.accountRepository.findByUserHash(userHash);

        if(account == null){
            account = new Account(userHash, oAuth2Response.getName(), oAuth2Response.getEmail(),"USER",LocalDate.now());
            this.accountRepository.save(account);
        }
        else{
            account.setUserName(oAuth2Response.getName());
            account.setEmail(oAuth2Response.getEmail());
            account.setLastLoginDate(LocalDate.now());
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole()));

        return new CustomOauth2User(oAuth2Response, authorities);
    }
}
