package com.app.carbooking.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.app.carbooking.domain.User} entity.
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String userId;

    @NotNull
    private String name;

}
