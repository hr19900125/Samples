package com.ryan.oauth2;

public class OauthServiceImpl implements OauthService {

    private OauthDao oauthDao;
    public void setOauthDao(OauthDao oauthDao) {
		this.oauthDao = oauthDao;
	}

	@Override
    public OauthClientDetails loadOauthClientDetails(String clientId) {
        return oauthDao.findOauthClientDetails(clientId);
    }
}