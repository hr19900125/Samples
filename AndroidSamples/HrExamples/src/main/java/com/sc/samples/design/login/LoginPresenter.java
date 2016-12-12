package com.sc.samples.design.login;

/**
 *
 */
public interface LoginPresenter {

    void validateCredentials(String username, String password);

    void onDestroy();

}
