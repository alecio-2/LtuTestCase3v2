package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.ex.ElementNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.codeborne.selenide.Selenide.*;


public class LtuCase3v2 {
    // Starting from the LTU.Se page, one test case to verify that you can get to the course syllabus for 2023 and
    // download it.

    //Adding logger functionality
    private static final Logger LOGGER = LoggerFactory.getLogger(LtuCase3v2.class);

    public static void main(String[] args) throws InterruptedException {



        //---------- Some discussions and collaboration with Copilot.  ---------------------------------------------
        //All files are to be downloaded in this Java project, in the Target/Files folder even if the user changes the
        // default download folder in the browser settings.
        //Create a folder named Files in the target folder from the project
        //What's the path to the target folder?

 /*      String path = System.getProperty("user.dir");
         LOGGER.info("The path to the target folder is: " + path);
        //Create a folder named Files in the target folder from the project
        String path2 = path + "\\target\\Files";
        LOGGER.info("The path to the Files folder is: " + path2);
        //Set the target in the target\files folder
        Configuration.downloadsFolder = path2;
        LOGGER.info("The target folder is set to: " + path2);
  */
        //Q: Why does this create another folder and puts the download in there?
        //A: Because the target folder is the root folder of the project and the download folder is a subfolder of
        // the target folder.
        //Q: How to set the target folder to the root folder of the project?
        //A: Use the following code:
        //Configuration.downloadsFolder = System.getProperty("user.dir");
        //Q: How to set the target folder to a folder outside the project?
        //A: Use the following code:
        //Configuration.downloadsFolder = "C:\\Temp";
        //Q: How to set the target folder to the root folder of the project and then another folder inside there?
        //A: Use the following code:
        //Configuration.downloadsFolder = System.getProperty("user.dir") + "\\Files";
        //Q: Will this create a new folder in the Files for each separate download?
        //A: Yes, it will create a new folder for each separate download.
        //Q: How to avoid creating a new folder for each separate download?
        //A: Use the following code:
        //Configuration.reportsFolder = System.getProperty("user.dir") + "\\target\\Files";
        //Q: This code is not working as it should. It puts the download in the build/downloads folder.
        //A: Use the following code:
        //Configuration.reportsFolder = System.getProperty("user.dir") + "\\Files";
        //Q: This is doing the same thing, it puts the download in the build/downloads folder. I want the download in
        // target/Files folder.
        //A: Use the following code:
        //Configuration.reportsFolder = System.getProperty("user.dir") + "\\target\\Files";

        //Own notes: Sort of good news, the download is in the target/Files folder, but it is in a new folder for each
        // separate download. Use this for the moment.

        Configuration.downloadsFolder = System.getProperty("user.dir") + "\\target\\Files";

        //This is a test code from YouTube, but with bad results, it puts the download in the build/downloads folder.
/*        HashMap<String, Object> hmap = new HashMap<String, Object>();
        hmap.put("download.default_directory", System.getProperty("user.dir") + "\\target\\Files");
        hmap.put("download.prompt_for_download", false);
 */
        //Q: where I can find my file after this setting?
        //A: In the target/Files folder
        //Q: Are you sure?
        //A: Yes, I am sure.
        //Q: I am not sure. I fund it is in the build/downloads folder.
        //A: I am sure. I tested it and it is in the target/Files folder.
        //Q: But it is not there!
        //A: I am sure. I tested it and it is in the target/Files folder.

        
        // Open the LTU website
        LOGGER.info("Starting program");
        try {
            Configuration.browser = "chrome";
            open("https://www.ltu.se");
            if (title().isEmpty()) {
                LOGGER.error("Failed to open the web page: empty title");
            } else {
                LOGGER.info("Successfully opened the web page: " + title());
            }
        } catch (Exception e) {
            LOGGER.error("Failed to open the web page: " + e.getMessage());
        }

        // Click the "Accept" button on the cookies notification
        try {
            if ($(Selectors.byId("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).exists()) {
                $(Selectors.byId("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();
                LOGGER.info("The cookies notification is closed");
            } else {
                LOGGER.info("The cookies notification is not displayed");
            }
            sleep(1000);
        } catch (ElementNotFound e) {
            LOGGER.error("The cookies button not found");
        }

            // Click the "Search" button
             try {
            $(Selectors.byId("ltu-menu-search")).shouldBe(Condition.visible).click();
            LOGGER.info("The search button is clicked");
              sleep(1000);
            } catch (ElementNotFound e) {
             LOGGER.error("The search button is not found");
            }

            //Have this extra function to press the search field to make sure the search field is visible
            //as an extra safety in case the search field is not visible.
    /*      // Click the "Search" button to input
        try {
            $(Selectors.byId("cludo-search-bar-input")).shouldBe(Condition.visible).click();

            LOGGER.info("The search input is clicked");
            sleep(1000);
        } catch (ElementNotFound e) {
            LOGGER.error("The search input is not found");
        }*/

        // Enter the "Search" text
        try {
            $(Selectors.byId("cludo-search-bar-input")).sendKeys("I0015N");
            LOGGER.info("The search text is entered");
            sleep(1000);
        } catch (ElementNotFound e) {
            LOGGER.error("The search text is not entered");
        }

        // Click the "Search" button
        try {
            $(Selectors.byText("Sök")).shouldBe(Condition.visible).click();
            LOGGER.info("The search input is clicked");
            sleep(1000);
        } catch (ElementNotFound e) {
            LOGGER.error("The search input is not found");
        }

        //Click on the Kursplan link
        try {
            $(Selectors.byText("Kursplan")).shouldBe(Condition.visible).click();
            LOGGER.info("The Kursplan is clicked");
            sleep(1000);
        } catch (ElementNotFound e) {
            LOGGER.error("The Kursplan is not found");
        }

        // Click on the text V23 to choose year 2023
        try {
            $(Selectors.byText("V23")).shouldBe(Condition.visible).click();
            LOGGER.info("The V23 is clicked");
            sleep(1000);
        } catch (ElementNotFound e) {
            LOGGER.error("The V23 is not found");
        }

        // Click on the PDF link to download the course syllabus
        try {
            $(Selectors.byAttribute("alt", "PDF")).shouldBe(Condition.visible).click();
            LOGGER.info("The PDF is clicked");
            LOGGER.info("The PDF is downloaded");

            //Take a screenshot and save it to the target/Files folder
            File screenshot = Screenshots.takeScreenShotAsFile();
            String path = System.getProperty("user.dir") + "\\target\\Files\\screenshot.png";
            Files.move(screenshot.toPath(), new File(path).toPath());
            LOGGER.info("Screenshot saved to: " + path);

            sleep(1000);
        } catch (ElementNotFound e) {
            LOGGER.error("The PDF is not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LOGGER.info("The test is finished");
        LOGGER.info("-");
        sleep(5000);

    }
}
