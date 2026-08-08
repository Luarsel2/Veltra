package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    // Muestra la landing page.
    // Si venimos de un formulario enviado con éxito, mostramos el mensaje de confirmación.
    @GetMapping("/")
    public String home(@RequestParam(required = false) String enviado, Model model) {
        model.addAttribute("mostrarConfirmacion", enviado != null);
        return "index";
    }

    // Recibe los datos del formulario de contacto.
    // Por ahora solo los mostramos en la consola del servidor;
    // más adelante (cuando conectemos base de datos) los guardaremos de verdad
    // o los enviaremos por correo.
    @PostMapping("/contacto")
    public String recibirContacto(@RequestParam String nombre,
                                   @RequestParam String email,
                                   @RequestParam String mensaje) {

        System.out.println("========== Nuevo mensaje de contacto ==========");
        System.out.println("Nombre:  " + nombre);
        System.out.println("Email:   " + email);
        System.out.println("Mensaje: " + mensaje);
        System.out.println("================================================");

        // Redirigimos de vuelta a la página principal con un indicador
        // de que el envío fue exitoso (esto evita reenvíos duplicados si
        // el usuario recarga la página).
        return "redirect:/?enviado=true#contacto";
    }
}