package com.data.user.api.input;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

import static com.data.user.messages.UserInfoMessages.*;

@Schema(description = "Datos básicos del usuario")
public class UserInfo {

    @NotBlank(message = NAME_BLANK)
    @Size(min = 2, max = 50)
    @Schema(description = NAME_DESCRIPTION, example = NAME_EXAMPLE)
    private String name;

    @NotBlank(message = EMAIL_BLANK)
    @Email(message = EMAIL_INVALID)
    @Schema(description = EMAIL_DESCRIPTION, example = EMAIL_EXAMPLE)
    private String email;

    @NotBlank(message = PASSWORD_BLANK)
    @Size(min = 8, max = 20, message = PASSWORD_LENGTH)
    @Schema(description = PASSWORD_DESCRIPTION, example = PASSWORD_EXAMPLE)
    private String password;

    @NotNull
    @NotEmpty
    @Schema(description = PHONES_DESCRIPTION)
    private List<@Valid PhoneInfo> phones;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhoneInfo> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneInfo> phones) {
        this.phones = phones;
    }
}
