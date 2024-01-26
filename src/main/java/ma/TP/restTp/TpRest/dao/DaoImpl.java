package ma.TP.restTp.TpRest.dao;

import ma.TP.restTp.TpRest.service.model.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
public class DaoImpl implements IDao{
    private  static final List<Article> articles = new ArrayList<>(
            Arrays.asList(
                    new Article(1L, "PC PORTABLE HP I7", 15000d, 10d),
                    new Article(2L, "ECRAN", 1500d, 10d),
                    new Article(3L, "CAMERA LG", 3000d, 10d),
                    new Article(4L, "SOURIS", 200d, 10d)
            )
    );
    @Override
    public Article findById(Long id) {
        return articles
                       .stream()
                       .filter(a->a.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Article> findAll() {
        return articles;
    }

    @Override
    public void save(Article article) {
          articles.add(article);
    }

    @Override
    public void delete(Long id) {
             articles.remove(articles
                     .stream()
                     .filter(a->a.getId().equals(id)).findFirst().orElse(null));
    }
}
