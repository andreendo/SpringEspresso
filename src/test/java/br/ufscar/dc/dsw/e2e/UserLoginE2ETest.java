package br.ufscar.dc.dsw.e2e;


import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserLoginE2ETest {

    @LocalServerPort
    private int serverPort;

    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeAll
    public static void beforeAll() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
    }

    @BeforeEach
    public void beforeEach() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterAll
    public static void afterAll() {
        browser.close();
        playwright.close();
    }

    @AfterEach
    public void afterEach() {
        context.close();
    }

    @Test
    void shouldAdminLoginSuccessfully() throws Exception {
        var url = "http://localhost:" + serverPort;
        page.navigate(url);

        assertTrue(page.locator("h1").textContent().contains("Espresso Game Testing"));

        page.locator("#username").fill("admin@admin.com");
        page.locator("#password").fill("admin");
        page.locator(".button-primary").click();

        var loggedUser = page.locator("h2 > span:nth-of-type(2)").textContent();
        assertEquals("admin@admin.com", loggedUser);

        // TODO
    }
}
