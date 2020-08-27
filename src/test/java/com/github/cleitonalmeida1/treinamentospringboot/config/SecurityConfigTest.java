package com.github.cleitonalmeida1.treinamentospringboot.config;

import com.github.cleitonalmeida1.treinamentospringboot.entity.Usuario;
import com.github.cleitonalmeida1.treinamentospringboot.service.impl.UsuarioServiceImpl;
import com.google.common.net.HttpHeaders;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityConfigTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    private MockMvc mockMvc;
    private Usuario usuarioAdmin;
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

        usuarioAdmin = new Usuario();
        usuarioAdmin.setNome("Cleiton Almeida");
        usuarioAdmin.setLogin("cleiton.almeida");
        usuarioAdmin.setSenha("123");
        usuarioAdmin.setAdmin(true);

        usuarioService.salvar(usuarioAdmin);
    }

    @Test
    public void salvarSemAutenticacaoTest() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setNome("Jose");
        usuario.setLogin("jose");
        usuario.setSenha("123");
        usuario.setAdmin(false);

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/api/usuario").header(HttpHeaders.AUTHORIZATION, "Basic Y2xlaXRvbi5hbG1laWRhOjEpMw==")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(usuario)))
                .andExpect(status().isUnauthorized()).andReturn();

        Assert.assertEquals("Unauthorized", response.getResponse().getErrorMessage());
    }

    @Test
    public void salvarComAutenticacaoTest() throws Exception {
        Usuario astrogildo = new Usuario();
        astrogildo.setNome("Astrogildo");
        astrogildo.setLogin("astrogildo");
        astrogildo.setSenha("1234");
        astrogildo.setAdmin(false);

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/api/usuario").header(HttpHeaders.AUTHORIZATION, "Basic Y2xlaXRvbi5hbG1laWRhOjEyMw==")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(astrogildo)))
                .andExpect(status().isCreated()).andReturn();

        Usuario usuarioCadastrado = new Gson().fromJson(response.getResponse().getContentAsString(), Usuario.class);
        assertNotNull(usuarioCadastrado.getId());
        assertEquals(usuarioCadastrado.getNome(), astrogildo.getNome());
    }
}
