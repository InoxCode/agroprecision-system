package com.agroprecision.identity.application.port.in;

import com.agroprecision.identity.application.service.LoginResult;

public interface LoginUseCase {

    LoginResult login(String email, String password);
}