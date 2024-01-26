package ma.TP.restTp.TpRest.service;

import ma.TP.restTp.TpRest.domaine.ArticleConverter;
import ma.TP.restTp.TpRest.domaine.ArticleDTO;

import java.util.List;

public interface IService {
    ArticleDTO getById(Long id);
    List<ArticleDTO> getAll();
    void create(ArticleDTO article);
    void deleteById(Long id);
    void update(Long id,ArticleDTO article);
}
