package ma.TP.restTp.TpRest.domaine;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO  implements Serializable {
    private Long id;
    @Size(min = 1, max = 30 , message = "description size  must be between 1 and 30")
    private String description;
    private double price;
    @Min(value = 1 , message = "quantity must be greater than 1")
    private double quantity;
}
