package com.github.cleitonalmeida1.treinamentospringboot.rest;

import com.github.cleitonalmeida1.treinamentospringboot.entity.Usuario;
import com.github.cleitonalmeida1.treinamentospringboot.rest.controller.UsuarioRestController;
import com.github.cleitonalmeida1.treinamentospringboot.service.impl.UsuarioServiceImpl;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsuarioRestControllerTest {

    @InjectMocks
    private UsuarioRestController usuarioRestController;

    @Mock
    private UsuarioServiceImpl usuarioService;

    @Test
    public void buscarUsuarioPorIdTest() {
        MockitoAnnotations.initMocks(this);

        Usuario josefina = new Usuario();
        josefina.setId(1);
        josefina.setNome("Josefina");

        Usuario andre = new Usuario();
        andre.setId(2);
        andre.setNome("Andre");

        when(usuarioService.buscarPorId(1)).thenReturn(josefina);
        usuarioRestController.buscarPorId(1);

        when(usuarioService.buscarPorId(2)).thenReturn(josefina);
        usuarioRestController.buscarPorId(2);

        verify(usuarioService, times(1)).buscarPorId(1);
        verify(usuarioService, times(1)).buscarPorId(2);
    }

    @Test
    public void salvarTest() {
        MockitoAnnotations.initMocks(this);

        Usuario josefina = new Usuario();
        josefina.setId(1);
        josefina.setNome("Josefina");

        Usuario andre = new Usuario();
        andre.setId(2);
        andre.setNome("Andre");

        when(usuarioService.salvar(josefina)).thenReturn(josefina);
        usuarioRestController.salvar(josefina);

        when(usuarioService.salvar(andre)).thenReturn(andre);
        usuarioRestController.salvar(andre);

        verify(usuarioService, times(1)).salvar(josefina);
        verify(usuarioService, times(1)).salvar(andre);
    }
}
