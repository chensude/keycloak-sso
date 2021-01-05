package com.etocrm.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author csd
 * @desc
 * @date 1/3/21 1:30 PM
 **/
public abstract  class BaseService {
    @Autowired
    CurrentUserProvider currentUserProvider;

    protected CurrentUser getCurrentUser() {
        return currentUserProvider.getCurrentUser();
    }
}
