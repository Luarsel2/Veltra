package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final EmailService emailService;

    public HomeController(EmailService emailService) {
        this.emailService = emailService;
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
        try {
            emailService.enviarMensajeDeContacto(nombre, email, mensaje);
            return "redirect:/?enviado=true#contacto";
        } catch (Exception e) {
            System.out.println("Error enviando correo: " + e.getMessage());
            return "redirect:/?error=true#contacto";
        }
    }
}