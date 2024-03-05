package com.nhnacademy.edu.springboot.account.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionWebController {
    @Autowired
    VersionProperties versionProperties;

    @ConfigurationProperties("com.nhn.account.system")
    @Getter
    @Setter
    @ToString
    public static class VersionProperties {
        String version;

        @ConstructorBinding
        public VersionProperties(String version) {
            this.version = version;
        }
    }

    @GetMapping("/system/version")
    public String getVersion() {
        return "{\"result\" :" + "\"" + versionProperties.getVersion() + "\"}";
    }
}
