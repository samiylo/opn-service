package com.opn.opn_service.bo;

import com.opn.opn_service.config.JWTokenImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import static com.opn.opn_service.config.JWTokenImpl.generateRSAKeyPair;

@Service
public class LoginService {

    private static final String SECRET_MESSAGE = "cG93ZXJ0b3RoZXBlb3BsZQ==";


        public KeyPair generateJWT() throws Exception {
            return generateRSAKeyPair();

        }
}
