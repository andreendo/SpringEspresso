package br.ufscar.dc.dsw.config.seeders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);
    private final UsuarioSeeder usuarioSeeder;
    private final EstrategiaSeeder estrategiaSeeder;
    private final ProjetoSeeder projetoSeeder;

    public DatabaseSeeder(UsuarioSeeder usuarioSeeder, EstrategiaSeeder estrategiaSeeder, ProjetoSeeder projetoSeeder) {
        this.usuarioSeeder = usuarioSeeder;
        this.estrategiaSeeder = estrategiaSeeder;
        this.projetoSeeder = projetoSeeder;
    }

    public void seedDatabase() {
        logger.info("Iniciando seed do banco de dados...");
        
        usuarioSeeder.seedUsuarios();
        estrategiaSeeder.seedEstrategias();
        projetoSeeder.seedProjetos();
        
        logger.info("Seed do banco de dados concluído!");
    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();
    }
}