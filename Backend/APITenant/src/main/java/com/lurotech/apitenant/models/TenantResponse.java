package com.lurotech.apitenant.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TenantResponse {
    @NotNull
    private Boolean success;
    @NotNull
    private Integer httpStatus;
    @NotNull
    private String message;
}
