package br.ufscar.dc.dsw;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EspressoGamingTestingJpaApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

	@Test
	void loadApplicationWithHomePageAppTitle() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port, String.class))
            .contains("Espresso Game Testing");
	}

}
