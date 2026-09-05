package com.agroprecision.identity.application.port.out;

public interface UserCredentialPort {

    boolean credentialsAreValid(String email, String password);
}