package academy.devdojo.springboot2.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnimePostRequestBody {

    @NotEmpty(message = "The anime cannot be empty")
    @NotNull(message = "The anime cannot be null")
    private String name;

}
