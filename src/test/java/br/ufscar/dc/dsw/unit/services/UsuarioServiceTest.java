package br.ufscar.dc.dsw.unit.services;

import br.ufscar.dc.dsw.models.UsuarioModel;
import br.ufscar.dc.dsw.repositories.UsuarioRepository;
import br.ufscar.dc.dsw.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    private UsuarioService usuarioService;
    private UsuarioRepository usuarioRepositoryMock;
    private BCryptPasswordEncoder bCryptPasswordEncoderMock;

    @BeforeEach
    void setUp() {
        usuarioRepositoryMock = mock(UsuarioRepository.class);
        bCryptPasswordEncoderMock = mock(BCryptPasswordEncoder.class);
        usuarioService = new UsuarioService(usuarioRepositoryMock, bCryptPasswordEncoderMock);
    }

    @Test
    void testLoadUserByUsernameSuccessfully() throws Exception {
        // Arrange
        UsuarioModel usuarioModel = new UsuarioModel();
        when(usuarioRepositoryMock.findByEmail("user1@email.com")).thenReturn(Optional.of(usuarioModel));

        // Act
        var userDetails = usuarioService.loadUserByUsername("user1@email.com");

        //Assert
        assertThat(userDetails).isNotNull();
    }

    @Test
    void testLoadUserByUsernameWithError() throws Exception {
        // Arrange
        when(usuarioRepositoryMock.findByEmail("user2@email.com")).thenReturn(Optional.empty());

        // Act
        Executable action = () -> usuarioService.loadUserByUsername("user2@email.com");

        // Assert
        var exception = assertThrows(UsernameNotFoundException.class, action);
        assertThat(exception.getMessage()).isEqualTo("Usuário não encontrado: user2@email.com");
    }

    @Test
    void testExcluirSucessfully() throws Exception {
        // Arrange
        UUID uuid = UUID.randomUUID();
        when(usuarioRepositoryMock.existsById(uuid)).thenReturn(Boolean.TRUE);

        // Act
        usuarioService.excluir(uuid);

        // Assert
        verify(usuarioRepositoryMock, times(1)).deleteById(uuid);
    }

    @Test
    void testExcluirFailWithNonExistingUser() throws Exception {
        // Arrange
        UUID uuid = UUID.randomUUID();
        when(usuarioRepositoryMock.existsById(uuid)).thenReturn(Boolean.FALSE);

        // Act
        Executable action = () -> usuarioService.excluir(uuid);

        // Assert
        assertThrows(IllegalArgumentException.class, action);
        verify(usuarioRepositoryMock, times(0)).deleteById(uuid);
    }

    @Test
    void testExcluirFailWithDataIntegrityViolation() throws Exception {
        // Arrange
        UUID uuid = UUID.randomUUID();
        when(usuarioRepositoryMock.existsById(uuid)).thenReturn(Boolean.TRUE);
        doThrow(new DataIntegrityViolationException(""))
            .when(usuarioRepositoryMock).deleteById(uuid);

        // Act
        Executable action = () -> usuarioService.excluir(uuid);

        // Assert
        assertThrows(RuntimeException.class, action);
        verify(usuarioRepositoryMock, times(1)).deleteById(uuid);
    }
}