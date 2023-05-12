package org.testGit.core.config;

import org.aeonbits.owner.Config;
import org.testGit.core.config.convertors.UserConverter;
import org.testGit.core.config.data.User;

import static org.aeonbits.owner.Config.DisableableFeature.PARAMETER_FORMATTING;

@Config.DisableFeature({PARAMETER_FORMATTING})
@Config.Sources("classpath:config.properties")
public interface WebConfig extends Config {
    @Config.Key("browser")
    String browser();

    @Config.Key("baseUrl")
    String baseUrl();

    @Config.Key("repositoryName")
    String repositoryName();

    @Config.Key("gitUserName")
    String gitUserName();

    @Key("user")
    @ConverterClass(UserConverter.class)
    User user();
}
