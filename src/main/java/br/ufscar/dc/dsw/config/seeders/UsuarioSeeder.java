package br.ufscar.dc.dsw.config.seeders;

import br.ufscar.dc.dsw.dtos.UsuarioCadastroDTO;
import br.ufscar.dc.dsw.models.UsuarioModel;
import br.ufscar.dc.dsw.models.enums.Papel;
import br.ufscar.dc.dsw.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UsuarioSeeder {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioSeeder.class);
    private final UsuarioService usuarioService;

    public UsuarioSeeder(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void seedUsuarios() {
        logger.info("Verificando usuários iniciais...");

        // Criar usuário 'admin' se não existir
        UsuarioModel adminExistente = usuarioService.buscarPorEmail("admin@admin.com");
        if (adminExistente == null) {
            UsuarioCadastroDTO admin = new UsuarioCadastroDTO(
                    null, // ID is null for new users
                    "Administrador",
                    "admin@admin.com",
                    "admin",
                    Papel.ADMIN
            );
            usuarioService.salvarNovoUsuario(admin);
            logger.info("✓ Usuário 'admin' criado.");
        } else {
            logger.info("✓ Usuário 'admin' já existe.");
        }

        // Criar usuário 'tester' se não existir
        UsuarioModel testerExistente = usuarioService.buscarPorEmail("tester@tester.com");
        if (testerExistente == null) {
            UsuarioCadastroDTO tester = new UsuarioCadastroDTO(
                    null, // ID is null for new users
                    "Tester",
                    "tester@tester.com",
                    "tester",
                    Papel.TESTER
            );
            usuarioService.salvarNovoUsuario(tester);
            logger.info("✓ Usuário 'tester' criado.");
        } else {
            logger.info("✓ Usuário 'tester' já existe.");
        }
    }
} 