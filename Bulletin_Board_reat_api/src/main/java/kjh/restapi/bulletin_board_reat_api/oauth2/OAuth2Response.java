package kjh.restapi.bulletin_board_reat_api.oauth2;

public interface OAuth2Response {

    String getProvider();
    String getProviderId();
    String getEmail();
    String getName();

}
