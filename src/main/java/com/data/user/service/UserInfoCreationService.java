package com.data.user.service;


import com.data.user.domain.UserInfoEntity;
import com.data.user.api.input.UserInfo;

public interface UserInfoCreationService {

    UserInfoEntity createUserInfo(final UserInfo userInfo);
}
