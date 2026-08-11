package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final EmailService emailService;
    private final ContactRepository contactRepository;

    public HomeController(EmailService emailService, ContactRepository contactRepository) {
        this.emailService = emailService;
        this.contactRepository = contactRepository;
    }

    @GetMapping("/")
    public String home(@RequestParam(required = false) String enviado,
                        @RequestParam(required = false) String error,
                        Model model) {
        model.addAttribute("mostrarConfirmacion", enviado != null);
        model.addAttribute("mostrarError", error != null);
        return "index";
    }

    @PostMapping("/contacto")
    public String recibirContacto(@RequestParam String nombre,
                                   @RequestParam String email,
                                   @RequestParam String mensaje) {

        // 1) Guardamos el contacto en la base de datos PRIMERO.
        // Esto es lo crítico: si falla, sí es un error real que el usuario debe ver.
        try {
            Contact contacto = new Contact(nombre, email, mensaje);
            contactRepository.save(contacto);
        } catch (Exception e) {
            System.out.println("Error guardando el contacto en la base de datos: " + e.getMessage());
            return "redirect:/?error=true#contacto";
        }

        // 2) Intentamos mandar el correo. Si esto falla, ya no es tan grave:
        // el contacto ya quedó guardado y lo pueden ver en /h2-console.
        try {
            emailService.enviarMensajeDeContacto(nombre, email, mensaje);
        } catch (Exception e) {
            System.out.println("Contacto guardado, pero el correo falló: " + e.getMessage());
        }

        return "redirect:/?enviado=true#contacto";
    }
}