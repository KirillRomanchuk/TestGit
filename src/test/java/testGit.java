import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.testGit.components.FileComponent;
import org.testGit.components.Header;
import org.testGit.components.SearchResultComponent;
import org.testGit.pages.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Properties;

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

    @Parameters({"fileName"})
    @Test(priority = 4)
    public void openPomXml(String fileName) {
        ProjectPage projectPage = site.projectPage();
        LinkedHashMap<String, FileComponent> filesMap = projectPage.filesMap();

        filesMap.get(fileName).fileLink().click();
        site.filePage(fileName).waitPageIsOpened(15);
    }

    @Parameters({"fileName"})
    @Test(priority = 5)
    public void readFile(String fileName) throws XmlPullParserException, IOException {
        FilePage filePage = site.filePage(fileName);
        String fileText = filePage.fileText().getText();
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new ByteArrayInputStream(fileText.getBytes(StandardCharsets.UTF_8)));
        Properties prop = model.getProperties();
        prop.forEach((key, value) -> System.out.println(""
                .concat(key.toString())
                .concat(" ")
                .concat(value.toString())));
        prop.forEach((key, value) -> logger.info(""
                .concat(key.toString())
                .concat(" ")
                .concat(value.toString())));

        String testNgVersionInPom = prop.entrySet()
                .stream()
                .filter(m -> m.getKey().toString().contains("testng"))
                .findFirst().get().getValue().toString();

        Assert.assertEquals(testNgVersionInPom, "7.4.0");
    }
}
