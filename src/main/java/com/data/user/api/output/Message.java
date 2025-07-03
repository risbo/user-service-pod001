package com.data.user.api.output;

import jakarta.validation.Payload;

public class Message implements Payload {
    private String mensaje;

    public Message(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
