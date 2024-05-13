package com.lurodev.usersauditorapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
