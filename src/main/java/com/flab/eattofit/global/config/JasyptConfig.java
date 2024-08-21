package com.flab.eattofit.global.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    private static final String JASYPT_ENCRYPTOR_BEAN = "jasyptEncryptor";
    private static final String JASYPT_ALGORITHM = "PBEWithMD5AndDES";
    private static final int HASH_ITERATION = 1000;
    private static final int JASYPT_POOL_SIZE = 2;
    private static final String JASYPT_SALT_GENERATOR_CLASS = "org.jasypt.salt.RandomSaltGenerator";
    private static final String JASYPT_STRING_OUTPUT_TYPE = "base64";

    @Value("${ENCRYPT_KEY}")
    private String ENCRYPT_KEY;

    @Bean(name = JASYPT_ENCRYPTOR_BEAN)
    public StringEncryptor encrypt() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        saveJasyptAttributes(config);
        encryptor.setConfig(config);

        return encryptor;
    }

    private void saveJasyptAttributes(final SimpleStringPBEConfig config) {
        config.setPassword(ENCRYPT_KEY);
        config.setAlgorithm(JASYPT_ALGORITHM);
        config.setKeyObtentionIterations(HASH_ITERATION);
        config.setPoolSize(JASYPT_POOL_SIZE);
        config.setSaltGeneratorClassName(JASYPT_SALT_GENERATOR_CLASS);
        config.setStringOutputType(JASYPT_STRING_OUTPUT_TYPE);
    }
}
