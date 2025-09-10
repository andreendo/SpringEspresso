package br.ufscar.dc.dsw.e2e;


import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserLoginE2EIT {

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
                new BrowserType.LaunchOptions().setHeadless(true)
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

        assertTrue(page.locator("#app_header").textContent().contains("Espresso Game Testing"));

        page.locator("#username").fill("admin@admin.com");
        page.locator("#password").fill("admin");
        page.locator("#login_button").click();

        var loggedUser = page.locator("#auth_user").textContent();
        assertEquals("admin@admin.com", loggedUser);

        // pode ver usuários, projetos, estratégias, sessões de teste
    }

    @Test
    void shouldTesterLoginSuccessfully() throws Exception {
        var url = "http://localhost:" + serverPort;
        page.navigate(url);

        assertTrue(page.locator("#app_header").textContent().contains("Espresso Game Testing"));

        page.locator("#username").fill("tester@tester.com");
        page.locator("#password").fill("tester");
        page.locator("#login_button").click();

        var loggedUser = page.locator("#auth_user").textContent();
        assertEquals("tester@tester.com", loggedUser);

        // FALTANDO ASSERTIVAS
        // usuários
        page.locator("a[href='/usuarios/listar']").click();
        page.locator("a[href='/home']").click(); // voltar

        // projetos
        page.locator("a[href='/projetos/listar']").click();
        page.locator("a[href='/home']").click(); // voltar

        // estratégias
        page.locator("a[href='/estrategias']").click();
        page.locator("a[href='/home']").click(); // voltar

        // sessões de teste
        page.locator("a[href='/sessoes/minhas-sessoes']").click();
        page.locator("a[href='/']").click(); // voltar

    }

    @Test
    void shouldVisitorSeeStrategies() throws Exception {
        var url = "http://localhost:" + serverPort;
        page.navigate(url);

        assertTrue(page.locator("#app_header").textContent().contains("Espresso Game Testing"));

        page.locator("#see_strategies_button").click();
        var pageTitle = page.locator("#strategies_list_header").textContent();
        assertTrue(pageTitle.contains("Estratégias") || pageTitle.contains("Strategies"));
    }
}
