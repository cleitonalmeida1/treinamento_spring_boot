package com.github.cleitonalmeida1.treinamentospringboot.service.impl;

import com.github.cleitonalmeida1.treinamentospringboot.entity.Usuario;
import com.github.cleitonalmeida1.treinamentospringboot.repository.UsuarioRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsuarioServiceImplTest {

    @Captor
    private ArgumentCaptor<Usuario> usuarioCaptor;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    Usuario josefina;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        josefina = new Usuario();
        josefina.setId(1);
        josefina.setNome("Josefina");
        josefina.setLogin("josefa");
        josefina.setSenha("123");
        josefina.setAdmin(Boolean.FALSE);
    }

    @Test
    public void buscarPorIdTest() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(josefina));
        Usuario usuarioRetorno = usuarioService.buscarPorId(1);
        Assert.assertEquals(usuarioRetorno, josefina);
    }

    @Test(expected = ResponseStatusException.class)
    public void deveRetornarUmaExcptionCasoNaoEncontreOUsuarioTest() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());
        usuarioService.buscarPorId(1);
    }

    @Test
    public void salvarTest() {
        String senhaCritorgrafada = "senhaCriptografada";
        when(passwordEncoder.encode(josefina.getSenha())).thenReturn(senhaCritorgrafada);
        usuarioService.salvar(josefina);

        verify(usuarioRepository).save(usuarioCaptor.capture());
        Assert.assertEquals(usuarioCaptor.getValue().getSenha(), senhaCritorgrafada);
    }

    @Test
    public void carregarUsuarioPorLoginTest() {
        when(usuarioRepository.findByLogin(josefina.getLogin())).thenReturn(Optional.of(josefina));
        UserDetails userDetails = usuarioService.loadUserByUsername(josefina.getLogin());

        Assert.assertEquals(userDetails.getPassword(), josefina.getSenha());
        Assert.assertEquals(userDetails.getUsername(), josefina.getLogin());
        Assert.assertEquals(userDetails.getAuthorities().size(), 1);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void deveRetornarUmaExceptionAoAarregarUsuarioPorLoginTest() {
        when(usuarioRepository.findByLogin(josefina.getLogin())).thenReturn(Optional.empty());
        usuarioService.loadUserByUsername(josefina.getLogin());
    }

    @Test
    public void deveExcluirUmUsuarioTest() {
        when(usuarioRepository.findById(josefina.getId())).thenReturn(Optional.of(josefina));
        usuarioService.deletar(josefina.getId());

        verify(usuarioRepository).delete(usuarioCaptor.capture());
        Assert.assertEquals(usuarioCaptor.getValue().getNome(), josefina.getNome());
    }

    @Test(expected = ResponseStatusException.class)
    public void deveRetornarUmaExceptionAoExcluirUmNaoEncontradoTest() {
        when(usuarioRepository.findById(josefina.getId())).thenReturn(Optional.empty());
        usuarioService.deletar(josefina.getId());
    }
}
