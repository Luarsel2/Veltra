package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.contacto.destino}")
    private String correoDestino;

    // El mismo correo de Gmail que usamos para autenticarnos (GMAIL_USERNAME).
    // Java necesita esto explícito para armar el mensaje correctamente.
    @Value("${spring.mail.username}")
    private String correoRemitente;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarMensajeDeContacto(String nombre, String email, String mensaje) {
        SimpleMailMessage correo = new SimpleMailMessage();
        correo.setFrom(correoRemitente);
        correo.setTo(correoDestino);
        correo.setSubject("Nuevo contacto desde la web — " + nombre);
        correo.setText(
            "Nombre: " + nombre + "\n" +
            "Correo: " + email + "\n\n" +
            "Mensaje:\n" + mensaje
        );
        correo.setReplyTo(email);

        mailSender.send(correo);
    }
}