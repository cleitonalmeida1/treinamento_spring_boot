package com.github.cleitonalmeida1.treinamentospringboot;

import org.junit.Assert;
import org.junit.Test;

public class UsuarioTest {

    @Test
    public void gettersAndSettersTest() {
        Usuario usuario = new Usuario();

        Integer id = 1;
        String nome = "Cleiton Gonçalves de Almeida";
        String login = "cleiton.almeida";
        String senha = "1234";
        Boolean admin = false;


        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuario.setAdmin(admin);

        Assert.assertEquals(id, usuario.getId());
        Assert.assertEquals(nome, usuario.getNome());
        Assert.assertEquals(login, usuario.getLogin());
        Assert.assertEquals(senha, usuario.getSenha());
        Assert.assertEquals(admin, usuario.getAdmin());
    }

    @Test
    public void devePossuirOMesmoHashCodeTest() {
        Usuario joao = new Usuario();
        joao.setId(2);

        Usuario clemente = new Usuario();
        clemente.setId(2);

        Assert.assertTrue(joao.hashCode() == clemente.hashCode());
    }

    @Test
    public void naoDevePossuirOMesmoHashCodeTest() {
        Usuario joao = new Usuario();
        joao.setId(2);

        Usuario clemente = new Usuario();
        clemente.setId(4);

        Assert.assertTrue(joao.hashCode() != clemente.hashCode());
    }

    @Test
    public void deveRetornarVerdadeiroCasoSejaOMesmoObjeto() {
        Usuario joao = new Usuario();
        joao.setId(2);

        Assert.assertTrue(joao.equals(joao));
    }

    @Test
    public void deveRetornarFalsoCasoOhObjetoForNulo() {
        Usuario josefina = null;
        Usuario joao = new Usuario();

        Assert.assertFalse(joao.equals(josefina));
    }

    @Test
    public void deveRetornarFalsoCasoAsClassesSejamDiferentes(){
        Usuario jose = new Usuario();
        Object object = new Object();

        Assert.assertFalse(jose.equals(object));
    }

    @Test
    public void deveRetornarVerdadeiroCasoOsIdsSejamOsMesmos(){
        Usuario andre = new Usuario();
        andre.setId(1);

        Usuario joana = new Usuario();
        joana.setId(1);

        Assert.assertTrue(andre.equals(joana));
    }

    @Test
    public void deveRetornarFalsoCasoOsIdsSejamDiferentes(){
        Usuario andre = new Usuario();
        andre.setId(1);

        Usuario joana = new Usuario();
        joana.setId(2);

        Assert.assertFalse(andre.equals(joana));
    }
}
