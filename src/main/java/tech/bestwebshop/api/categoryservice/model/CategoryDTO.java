package tech.bestwebshop.api.categoryservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CategoryDTO {

    @NotEmpty
    @NonNull
    private String name;
}
