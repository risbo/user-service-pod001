package com.data.user.api.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

import static com.data.user.messages.UserInfoMessages.*;

@Schema(description = INFO_USER_CONTACT)
public class PhoneInfo implements Comparable<PhoneInfo>{

    @NotBlank(message = NUMBER_BLANK)
    @Pattern(regexp = PHONE_NUMBER, message = NUMBER_PATTERN)
    @Schema(description = NUMBER_DESCRIPTION, example = NUMBER_EXAMPLE)
    private String number;

    @NotBlank(message = CITYCODE_BLANK)
    @Pattern(regexp = CITY_CODE, message = CITYCODE_PATTERN)
    @Schema(description = CITYCODE_DESCRIPTION, example = CITYCODE_EXAMPLE)
    private String citycode;

    @NotBlank(message = COUNTRYCODE_BLANK)
    @Pattern(regexp = COUNTRY_CODE, message = COUNTRYCODE_PATTERN)
    @Schema(description = COUNTRYCODE_DESCRIPTION, example = COUNTRYCODE_EXAMPLE)
    private String countrycode;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    @Override
    public int compareTo(PhoneInfo o) {
        return this.number.compareTo(o.number);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PhoneInfo phoneInfo)) return false;
        return Objects.equals(number, phoneInfo.number) && Objects.equals(citycode, phoneInfo.citycode) && Objects.equals(countrycode, phoneInfo.countrycode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, citycode, countrycode);
    }
}
