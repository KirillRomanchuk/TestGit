package org.testGit.core.config;

import org.aeonbits.owner.ConfigFactory;
import org.testGit.core.config.data.User;

public class Config {
    private static final WebConfig webConfig = ConfigFactory.create(WebConfig.class);

    public static String browser() {
        return webConfig.browser();
    }

    public static String baseUrl() {
        return webConfig.baseUrl();
    }

    public static String repositoryName() {
        return webConfig.repositoryName();
    }

    public static String gitUserName() {
        return webConfig.gitUserName();
    }

    public static User user() {
        return webConfig.user();
    }
}
