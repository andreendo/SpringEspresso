package br.ufscar.dc.dsw.controllers;

import br.ufscar.dc.dsw.dtos.UsuarioDTO;
import br.ufscar.dc.dsw.models.enums.Papel;
import br.ufscar.dc.dsw.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UsuarioController.class)
class UsuarioControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldReturnListaUsuariosViewWithUsersInModel() throws Exception {
        // Arrange - configurar o setup do teste
        List<UsuarioDTO> usuarios = List.of(
                new UsuarioDTO(UUID.randomUUID(), "nome1", "nome1@mail.com", Papel.ADMIN),
                new UsuarioDTO(UUID.randomUUID(), "nome2", "nome2@mail.com", Papel.TESTER)
        );
        given(usuarioService.buscarTodos()).willReturn(usuarios);

        // Act and Assert - executar o teste em si, e realizar assertivas
        mockMvc.perform(get("/usuarios/listar"))
                .andExpect(status().isOk())
                .andExpect(view().name("usuario/lista"))
                .andExpect(model().attributeExists("listaUsuarios"))
                .andExpect(model().attribute("contextPath", "/usuarios"))
                .andExpect(model().attribute("listaUsuarios", usuarios));

        // assertivas relacionadas ao mock
        Mockito.verify(usuarioService).buscarTodos();
        Mockito.verifyNoMoreInteractions(usuarioService);
    }
}