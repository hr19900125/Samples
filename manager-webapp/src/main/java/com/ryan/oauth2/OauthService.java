package com.ryan.oauth2;

public interface OauthService {

    OauthClientDetails loadOauthClientDetails(String clientId);
    
}
