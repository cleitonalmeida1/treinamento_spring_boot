package com.github.cleitonalmeida1.treinamentospringboot.rest;

import com.github.cleitonalmeida1.treinamentospringboot.entity.Usuario;
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

        when(usuarioService.buscarPorId(1)).thenReturn(josefina);
        usuarioRestController.buscarPorId(1);

        verify(usuarioService, times(1)).buscarPorId(1);
    }

    @Test
    public void salvarTest() {
        MockitoAnnotations.initMocks(this);

        Usuario josefina = new Usuario();
        josefina.setId(1);
        josefina.setNome("Josefina");

        when(usuarioService.salvar(josefina)).thenReturn(josefina);
        usuarioRestController.salvar(josefina);

        verify(usuarioService, times(1)).salvar(josefina);
    }

    @Test
    public void editarTest() {
        MockitoAnnotations.initMocks(this);

        Usuario clodovil = new Usuario();
        clodovil.setId(1);
        clodovil.setNome("Clodovil");

        usuarioRestController.editar(1, clodovil);

        verify(usuarioService, times(1)).editar(1, clodovil);
    }

    @Test
    public void deletarTest() {
        MockitoAnnotations.initMocks(this);

        usuarioRestController.deletar(1);

        verify(usuarioService, times(1)).deletar(1);
    }
}
