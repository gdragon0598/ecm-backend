package com.huan.ecommerce.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RatingCommentDTO {
    @NotBlank(message = "Comment text must not be blank")
    private String commentText;

    @Min(value = 1, message = "rating should be between 1 and 5")
    @Max(value = 5, message = "rating should be between 1 and 5")
    private Integer rating;

    @NotNull(message = "productId must not be null")
    private Integer productId;

    @NotNull(message = "userId must not be null")
    private Integer userId;
}
