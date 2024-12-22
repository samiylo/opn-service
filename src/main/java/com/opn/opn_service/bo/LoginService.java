package com.opn.opn_service.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

import java.security.*;

import static com.opn.opn_service.config.JWTokenImpl.generateRSAKeyPair;

@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    HashMap<String, String> securityKeyList = new HashMap<>();

    public String getLoginKey() throws Exception {
        KeyPair keyPair = generateRSAKeyPair();
        String publicKeyString = java.util.Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        String privateKeyString = java.util.Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

        securityKeyList.put("public", publicKeyString);
        securityKeyList.put("private", privateKeyString);

        return publicKeyString;
    }

    public boolean validateKeyPair(String token) throws Exception {
        String publicKeyString = securityKeyList.get("public");
        String privateKeyString = securityKeyList.get("private");

        if (publicKeyString == null || privateKeyString == null) {
            throw new IllegalStateException("Keys are not generated yet.");
        }

        byte[] publicKeyBytes;
        byte[] privateKeyBytes;

        publicKeyBytes = java.util.Base64.getDecoder().decode(token);
        privateKeyBytes = java.util.Base64.getDecoder().decode(privateKeyString);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PublicKey publicKey;
        try {
            publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
        }
        catch (Exception ex) {
            logger.error(ex.getMessage());
            return false;
        }
        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

        // Validate by signing and verifying a test message
        String testMessage = "TestMessage";
        byte[] messageBytes = testMessage.getBytes();

        // Sign the message with the private key
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(privateKey);
        signer.update(messageBytes);
        byte[] signature = signer.sign();

        // Verify the signature with the public key
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(publicKey);
        verifier.update(messageBytes);
        boolean isVerified = verifier.verify(signature);

        if(isVerified) {
            logger.info("Security Key Validated ::: ");
        }
        return isVerified;

    }
}