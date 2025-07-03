package com.data.user.messages;

public interface UserInfoMessages {
    String PHONE_NUMBER = "^\\d{7,10}$";
    String CITY_CODE = "^\\d{2,3}$";
    String COUNTRY_CODE = "^\\d{1,3}$";

    // --- UserInfo ---
    String NAME_BLANK = "El nombre no puede estar vacío";
    String NAME_DESCRIPTION = "Nombre completo del usuario";
    String NAME_EXAMPLE = "Boris Palacios";

    String EMAIL_BLANK = "El correo no puede estar vacío";
    String EMAIL_INVALID = "Correo electrónico inválido";
    String EMAIL_DESCRIPTION = "Correo electrónico del usuario";
    String EMAIL_EXAMPLE = "borispalacios@bci.com";

    String PASSWORD_BLANK = "La contraseña no puede estar vacía";
    String PASSWORD_LENGTH = "La contraseña debe tener entre 8 y 20 caracteres";
    String PASSWORD_DESCRIPTION = "Contraseña del usuario (mínimo 8 caracteres)";
    String PASSWORD_EXAMPLE = "abc123!";

    String PHONES_DESCRIPTION = "Lista de teléfonos del usuario";

    // --- PhoneInfo ---
    String NUMBER_BLANK = "El número no puede estar vacío";
    String NUMBER_PATTERN = "El número de contacto debe tener entre 7 y 15 dígitos";
    String NUMBER_DESCRIPTION = "Número de teléfono del usuario";
    String NUMBER_EXAMPLE = "3007773322";

    String CITYCODE_BLANK = "El código de ciudad no puede estar vacío";
    String CITYCODE_PATTERN = "El código de ciudad debe tener 2 o 3 dígitos";
    String CITYCODE_DESCRIPTION = "Código de ciudad";
    String CITYCODE_EXAMPLE = "57";

    String COUNTRYCODE_BLANK = "El código de país no puede estar vacío";
    String COUNTRYCODE_PATTERN = "El código de país debe tener entre 1 y 3 dígitos";
    String COUNTRYCODE_DESCRIPTION = "Código de país";
    String COUNTRYCODE_EXAMPLE = "1";
    String INFO_USER_CONTACT="Información de contacto del usuario";
    String EXISING_EMAIL = "El correo ya está registrado.";
    String ERROR_CREATING_USER="Error al crear el usuario";
    String ERROR_SERVER="Servicio no disponible, contacte al administrador.";
    String PASSWORD_WITHOUT_FORMAT="La contraseña no cumple con el formato requerido";

}
