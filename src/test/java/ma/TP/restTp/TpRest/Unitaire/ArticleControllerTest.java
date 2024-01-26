package ma.TP.restTp.TpRest.Unitaire;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.TP.restTp.TpRest.domaine.ArticleDTO;
import ma.TP.restTp.TpRest.service.IService;
import ma.TP.restTp.TpRest.service.model.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArticleControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private IService service;
    @Test
    void testGetAll() throws Exception {
        List<ArticleDTO> articles = new ArrayList<>
                (Arrays.asList(
                        new ArticleDTO(1L, "PC PORTABLE HP I7", 15000d, 10d),
                        new ArticleDTO(2L, "ECRAN", 1500d, 10d),
                        new ArticleDTO(3L, "CAMERA LG", 3000d, 10d),
                        new ArticleDTO(4L, "SOURIS", 200d, 10d)));
        when(service.getAll()).thenReturn(articles);

        mvc.perform(get("/api/article/all").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].description").value("ECRAN"))
                .andExpect(jsonPath("$[2].price").value(3000d))
                .andExpect(jsonPath("$[3].quantity").value(10d));
    }
    @Test
    void testGetById() throws Exception {
        ArticleDTO article = new ArticleDTO(1L, "PC PORTABLE HP I7", 15000d, 10d);
        Long id = 1L;
        when(service.getById(id)).thenReturn(article);
        mvc.perform(get("/api/article/id/{id}",id).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.description").value("PC PORTABLE HP I7"))
                .andExpect(jsonPath("$.price").value(15000d))
                .andExpect(jsonPath("$.quantity").value(10d));
    }
    @Test
    void testGetByIdNotFound() throws Exception {
        Long id = 1L;
        when(service.getById(id)).thenReturn(null);
        mvc.perform(get("/api/article/id/{id}",id).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Article with "+id+" not found"));
    }
    @Test
    void testAddArticle() throws Exception {
        ArticleDTO article = new ArticleDTO(5L, "PC PORTABLE HP I7", 15000d, 10d);
        when(service.getById(article.getId())).thenReturn(null) ;
        mvc.perform(post("/api/article/addArticle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(article)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value("Article added successfully"));
    }
    @Test
    void updateArticle() throws Exception {
        ArticleDTO article = new ArticleDTO(1L, "PC PORTABLE HP I7", 15000d, 10d);
        Long id = 1L;
        when(service.getById(id)).thenReturn(article);
        mvc.perform(put("/api/article/updateArticle/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(article)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Article updated successfully"));

    }
}
