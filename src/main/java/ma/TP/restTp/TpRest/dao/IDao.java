package ma.TP.restTp.TpRest.dao;

import ma.TP.restTp.TpRest.service.model.Article;

import java.util.List;

public interface IDao {
    Article findById(Long id);
    List<Article> findAll();
    void save(Article article);
    void delete(Long id);

}
