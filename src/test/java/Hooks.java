import org.testGit.BaseWebSite;
import org.testGit.WebSiteManager;
import org.testGit.core.config.data.User;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static org.testGit.core.config.Config.baseUrl;
import static org.testGit.core.config.Config.user;

public class Hooks {
    protected static BaseWebSite site;
    protected static User user;

    @BeforeTest
    public static void beforeTest() {
        WebSiteManager.getSite(baseUrl());
        site = WebSiteManager.getSiteInstance();
        user = user();
    }

    @AfterTest
    public static void afterTest() {
        WebSiteManager.resetSite();
    }
}
