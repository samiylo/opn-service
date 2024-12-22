package com.opn.opn_service.bo;

import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

import static com.opn.opn_service.config.JWTokenImpl.generateRSAKeyPair;

import java.security.*;
import java.util.HashMap;

@Service
public class LoginService {

    private static String PRIVATE_KEY;

    HashMap<String, String> hashMap = new HashMap<>();

    public String generateRSA() throws Exception {
        // Generate a new RSA key pair
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();

        String publicKeyString = java.util.Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        String privateKeyString = java.util.Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

        hashMap.put("public", publicKeyString);
        hashMap.put("private", privateKeyString);

        return publicKeyString;
    }

    public boolean validateKeyPair(String token) throws Exception {
        String publicKeyString = hashMap.get("public");
        String privateKeyString = hashMap.get("private");

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
            System.out.println("Token Format Error ::: " + ex);
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

        return isVerified;

    }
}