package com.movie.test.inquiry.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InquiryUpdateDto {

    @Schema(description = "문의사항 아이디")
    @NotBlank(message = "문의사항 아이디는 필수값입니다.")
    private String inquiryId;

    @Schema(description = "답변여부")
    @NotBlank(message = "answerYn은 필수값입니다.")
    @Pattern(regexp = "^[Y|N]", message = "Y또는 N만 입력할 수 있습니다.")
    @Size(max = 1, message = "Y또는 N만 입력할 수 있습니다.")
    private String answerYn;

}
