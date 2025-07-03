package com.data.user.service.impl;

import com.data.user.domain.PhoneInfoEntity;
import com.data.user.domain.UserInfoEntity;
import com.data.user.api.input.PhoneInfo;
import com.data.user.api.input.UserInfo;
import com.data.user.repository.UserInfoRepository;
import com.data.user.service.UserInfoCreationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInfoCreationServiceImpl implements UserInfoCreationService {


    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInfoCreationServiceImpl(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public UserInfoEntity createUserInfo(final UserInfo userInfo){
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setName(userInfo.getName());
        userInfoEntity.setEmail(userInfo.getEmail());
        userInfoEntity.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoEntity.setPhones(buildPhones(userInfo.getPhones(), userInfoEntity));
        userInfoRepository.save(userInfoEntity);
        return userInfoEntity;
    }

    private List<PhoneInfoEntity> buildPhones(final List<PhoneInfo> phones, final UserInfoEntity userInfoEntity){
        return phones.stream().map( phoneDetail->{
            PhoneInfoEntity phoneInfoEntity = new PhoneInfoEntity();
            phoneInfoEntity.setNumber(phoneDetail.getNumber());
            phoneInfoEntity.setCitycode(phoneDetail.getCitycode());
            phoneInfoEntity.setCountrycode(phoneDetail.getCountrycode());
            phoneInfoEntity.setUser(userInfoEntity);
            return phoneInfoEntity;
        }).collect(Collectors.toList());
    }


}
