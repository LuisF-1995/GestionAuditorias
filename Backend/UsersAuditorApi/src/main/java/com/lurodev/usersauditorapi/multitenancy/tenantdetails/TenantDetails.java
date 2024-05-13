package com.lurodev.usersauditorapi.multitenancy.tenantdetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TenantDetails {
    private String identifier;
    private boolean enabled;

    public String schema() {
        return identifier;
    }
}
