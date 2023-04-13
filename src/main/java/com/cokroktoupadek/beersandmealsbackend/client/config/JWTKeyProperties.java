package com.cokroktoupadek.beersandmealsbackend.client.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


@ConfigurationProperties(prefix="keypair")
public record JWTKeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
