package ma.TP.restTp.TpRest.Controller;

import jakarta.validation.Valid;
import ma.TP.restTp.TpRest.domaine.ArticleDTO;
import ma.TP.restTp.TpRest.service.IService;
import ma.TP.restTp.TpRest.service.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    private final IService service;
    @Autowired
    public ArticleController(IService service) {
        this.service = service;
    }
    @GetMapping(value = "/all",produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public List<ArticleDTO> getAll(){
        return service.getAll();
    }
    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Object> getArticleById(@PathVariable(value = "id") Long id){
        ArticleDTO articleDTO = service.getById(id);
        if (articleDTO == null)
           return new ResponseEntity<>("Article with "+id+" not found", HttpStatus.OK);
        return new ResponseEntity<>(articleDTO,HttpStatus.OK);
    }
    @PostMapping("/addArticle")
    public ResponseEntity<Object> addArticle(@Valid @RequestBody ArticleDTO article){
        service.create(article);
        return new ResponseEntity<>("Article added successfully",HttpStatus.CREATED);
    }
    @PutMapping("/updateArticle/{id}")
    public ResponseEntity<Object> updateArticle(@PathVariable(value = "id") Long id,@Valid @RequestBody ArticleDTO article){
        ArticleDTO articleDTO = service.getById(id);
        if(articleDTO == null)
            return new ResponseEntity<>("Article with "+id+" not found",HttpStatus.OK);
        service.update(id,article);
        return new ResponseEntity<>("Article updated successfully",HttpStatus.OK);
    }
    @DeleteMapping("/deleteArticle/{id}")
    public ResponseEntity<Object> deleteArticle(@PathVariable(value = "id") Long id){
        ArticleDTO articleDTO = service.getById(id);
        if(articleDTO == null)
            throw new BusinessException("Article with id=" + id + " doesn't exist");
        service.deleteById(id);
        return new ResponseEntity<>("Article deleted successfully",HttpStatus.OK);
    }
}
