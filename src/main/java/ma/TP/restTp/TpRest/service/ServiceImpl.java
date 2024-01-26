package ma.TP.restTp.TpRest.service;

import lombok.NoArgsConstructor;
import ma.TP.restTp.TpRest.dao.IDao;
import ma.TP.restTp.TpRest.domaine.ArticleConverter;
import ma.TP.restTp.TpRest.domaine.ArticleDTO;
import ma.TP.restTp.TpRest.service.exception.BusinessException;
import ma.TP.restTp.TpRest.service.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@NoArgsConstructor
public class ServiceImpl implements IService{
    private IDao dao;
    @Autowired
    public ServiceImpl(IDao dao) {
        this.dao = dao;
    }
    @Override
    public ArticleDTO getById(Long id) {
        return ArticleConverter.toDto(dao.findById(id));
    }

    @Override
    public List<ArticleDTO> getAll() {
        return ArticleConverter.toDtos(dao.findAll());
    }

    @Override
    public void create(ArticleDTO article) {
        Article articleFound = dao.findAll().stream().filter(a->a.getId().equals(article.getId())).findFirst().orElse(null);
        if(articleFound != null)
            throw new BusinessException("Article with the same Id=" + article.getId() + " exist in database");
            dao.save(ArticleConverter.toBo(article));

    }

    @Override
    public void deleteById(Long id) {
          dao.delete(id);
    }

    @Override
    public void update(Long id, ArticleDTO article) {
         article.setId(id);
            dao.save(ArticleConverter.toBo(article));
    }
}
