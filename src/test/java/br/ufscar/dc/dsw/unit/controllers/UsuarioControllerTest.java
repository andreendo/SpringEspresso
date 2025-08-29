package br.ufscar.dc.dsw.unit.controllers;

import br.ufscar.dc.dsw.controllers.UsuarioController;
import br.ufscar.dc.dsw.dtos.UsuarioDTO;
import br.ufscar.dc.dsw.models.enums.Papel;
import br.ufscar.dc.dsw.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    UsuarioService usuarioServiceMock;
    UsuarioController usuarioController;

    private List<UsuarioDTO> sampleUsuarioDTOs() {
        return List.of(
                new UsuarioDTO(UUID.randomUUID(), "nome1", "nome1@mail.com", Papel.ADMIN),
                new UsuarioDTO(UUID.randomUUID(), "nome2", "nome2@mail.com", Papel.TESTER)
        );
    }

    @BeforeEach
    void setUp() {
        usuarioServiceMock = mock(UsuarioService.class);
        usuarioController = new UsuarioController(usuarioServiceMock);
    }

    @Test
    void testUsuarioListarShouldReturnViewWithUsers() throws Exception {
        when(usuarioServiceMock.buscarTodos()).thenReturn(sampleUsuarioDTOs());

        ModelMap model = new ModelMap();
        String res = usuarioController.listar(model);
        assertEquals("usuario/lista", res);
        assertEquals("/usuarios", model.get("contextPath"));
        verify(usuarioServiceMock, times(1)).buscarTodos();
        assertEquals(2, ((List<UsuarioDTO>) model.get("listaUsuarios")).size());
    }
}