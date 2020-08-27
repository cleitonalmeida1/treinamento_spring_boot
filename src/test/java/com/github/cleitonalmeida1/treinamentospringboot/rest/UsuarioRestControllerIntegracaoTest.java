package com.github.cleitonalmeida1.treinamentospringboot.rest;

import com.github.cleitonalmeida1.treinamentospringboot.entity.Usuario;
import com.github.cleitonalmeida1.treinamentospringboot.repository.UsuarioRepository;
import com.github.cleitonalmeida1.treinamentospringboot.service.impl.UsuarioServiceImpl;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioRestControllerIntegracaoTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private MockMvc mockMvc;
    private Usuario usuarioAdmin;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        usuarioAdmin = new Usuario();
        usuarioAdmin.setNome("Smartphone da Silva");
        usuarioAdmin.setLogin("smartphone.silva");
        usuarioAdmin.setSenha("123");
        usuarioAdmin.setAdmin(true);

        usuarioService.salvar(usuarioAdmin);
    }

    @Test
    public void deveBuscarUsuarioPorFiltrosTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/usuario?login=smartphone.silva")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Usuario usuarioResponse = new Gson().fromJson(retirarColchetes(mvcResult.getResponse().getContentAsString()), Usuario.class);

        Assert.assertEquals(usuarioResponse.getLogin(), "smartphone.silva");
    }

    @Test
    public void deveEditarUmUsuarioTest() throws Exception {

        Usuario clodovil = new Usuario();
        clodovil.setId(1);
        clodovil.setNome("Clodovil");
        clodovil.setLogin("clodovil");
        clodovil.setSenha("123");
        clodovil.setAdmin(false);

        MvcResult mvcResult = mockMvc.perform(put("/api/usuario/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(clodovil)))
                .andExpect(status().isOk()).andReturn();

        Usuario usuarioCadastrado = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), Usuario.class);

        Assert.assertEquals(usuarioCadastrado.getLogin(), "clodovil");
    }

    private String retirarColchetes(String response) {
        return response.replace("[", "").replace("]", "");
    }
}
