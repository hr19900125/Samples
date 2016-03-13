package com.ryan.oauth2;

public interface OauthDao {

	OauthClientDetails findOauthClientDetails(String clientId);
	
}
