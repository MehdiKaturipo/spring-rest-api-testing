package ma.TP.restTp.TpRest.domaine;

import ma.TP.restTp.TpRest.service.model.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleConverter {
    public static Article toBo(ArticleDTO dto){
        if(dto == null) return null;
        return new Article(dto.getId(),dto.getDescription(),dto.getPrice(),dto.getQuantity());
    }
    public static ArticleDTO toDto(Article bo){
        if(bo == null) return null;
        return new ArticleDTO(bo.getId(),bo.getDescription(),bo.getPrice(),bo.getQuantity());
    }
    public  static List<Article> toBos(List<ArticleDTO> dtoList){
           List<Article> boList =new ArrayList<>();
        dtoList.forEach(a -> boList.add(toBo(a)));
              return boList;
    }
    public  static List<ArticleDTO> toDtos(List<Article> boList){
        List<ArticleDTO> dtoList =new ArrayList<>();
        boList.forEach(a -> dtoList.add(toDto(a)));
        return dtoList;
    }
}
