package org.bartoszwojcik.investmentportfolioapi.dto.user;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private BigDecimal cash;
}
