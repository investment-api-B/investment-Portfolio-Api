package org.bartoszwojcik.investmentportfolioapi.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequestDto(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName
) {
}
