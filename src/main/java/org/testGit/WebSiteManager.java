package org.testGit;

import org.testGit.utils.browsers.BrowserManager;

public class WebSiteManager {
    private static BaseWebSite site;

    public static BaseWebSite getSite(String siteUrl) {

        if (site != null) {
            return site;
        }

        site = new BaseWebSite();

        site.loadPage(siteUrl);
        return site;
    }

    public static BaseWebSite getSiteInstance() {
        return site;
    }

    public static void resetSite() {
        site = null;
        BrowserManager.quitSession();
    }
}
