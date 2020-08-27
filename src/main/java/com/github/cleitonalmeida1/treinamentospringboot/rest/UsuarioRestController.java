package com.github.cleitonalmeida1.treinamentospringboot.rest;

import com.github.cleitonalmeida1.treinamentospringboot.entity.Usuario;
import com.github.cleitonalmeida1.treinamentospringboot.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/usuario")
public class UsuarioRestController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping
    public List<Usuario> buscarPorFiltro(Usuario filtro) {
        return usuarioService.buscarPorFiltro(filtro);
    }

    @GetMapping(path = "/{id}")
    public Usuario buscarPorId(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario) {
        return usuarioService.salvar(usuario);
    }

    @PutMapping(path = "/{id}")
    public Usuario editar(@PathVariable Integer id, @RequestBody @Valid Usuario usuario) {
        return usuarioService.editar(id, usuario);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        usuarioService.deletar(id);
    }
}
