import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.testGit.components.FileComponent;
import org.testGit.components.Header;
import org.testGit.components.SearchResultComponent;
import org.testGit.pages.*;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

import static org.testGit.core.config.Config.gitUserName;
import static org.testGit.core.config.Config.repositoryName;

public class testGit extends Hooks {
    @Test(priority = 1)
    public void login() {
        LoginPage loginPage = site.loginPage();

        loginPage.inputLogin().sendKeys(user.getEmail());
        loginPage.inputPassword().sendKeys(user.getPassword());
        loginPage.buttonSignIn().click();
        site.homePage().waitPageIsOpened(15);
    }

    @Test(priority = 2)
    public void search() {
        HomePage homePage = site.homePage();
        Header header = homePage.header();
        header.buttonSearch().click();

        String projectName = gitUserName().concat("/").concat(repositoryName());
        header.inputSearch().sendKeys(projectName);
        header.inputSearch().pressEnter();
    }

    @Test(priority = 3)
    public void searchResult() {
        SearchResultPage searchResultPage = site.searchResultPage();
        LinkedHashMap<String, SearchResultComponent> searchResultMap = searchResultPage.searchResultMap();

        String projectName = gitUserName().concat("/").concat(repositoryName());
        searchResultMap.get(projectName).title().click();
        site.projectPage().waitPageIsOpened(15);
    }

    @Test(priority = 4)
    public void openPomXml() {
        ProjectPage projectPage = site.projectPage();
        LinkedHashMap<String, FileComponent> filesMap = projectPage.filesMap();

        String fileName = "pom.xml";
        filesMap.get(fileName).fileLink().click();
    }

    @Test(priority = 5)
    public void readFile() throws XmlPullParserException, IOException {
        FilePage filePage = site.filePage("pom.xml");
        String fileText = filePage.fileText().getText();
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new ByteArrayInputStream(fileText.getBytes(StandardCharsets.UTF_8)));
    }
}
