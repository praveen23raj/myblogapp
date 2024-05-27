package com.blogapp1.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min = 3 ,message = "title shuld b 3 Char!!!")
    private String title;
    @NotEmpty
    private String description;
    private String content;
}
