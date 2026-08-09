package com.dannys.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;

@Controller
public class RestaurantController {

    @GetMapping("/")
    public String inicio(Model model) {
        List<Map<String, String>> menu = List.of(
            Map.of("nombre", "Waffles con helado", "descripcion", "Los waffles de la casa acompañados de una bola de helado de tu preferencia, fresas y syrup de chocolate.", "precio", "Q40.00", "imagen", "/wafflesHelado.png"),
            Map.of("nombre", "Pancakes con Arandanos", "descripcion", "Pancakes rellenos de arandanos y bacompañados de mantequilla, leche condensada o miel.", "precio", "Q40.00", "imagen", "/panquequeArandanos.png"),
            Map.of("nombre", "Waffles con Tocino", "descripcion", "Servidos con tocino fresco, mantequilla y jalea de la casa.", "precio", "Q45.00", "imagen", "/panquequeBacon.png"),
            Map.of("nombre", "Desayuno Americano", "descripcion", "Huevos estrellados o al gusto acompañados de tocino, hashbrown y una porcion de pancakes, de bebida café de la casa.", "precio", "Q55.00", "imagen", "/desayunoAmeri.png"),
            Map.of("nombre", "Desayuno Ranchero", "descripcion", "Huevos revueltos con tomate y cebolla, frijoles volteados acompañados de queso, jamon o tocino, platanos fritos, salsa rancheras y pan.", "precio", "Q60.00", "imagen", "/desayunoRanchero.png"),
            Map.of("nombre", "Desayuno Infantil", "descripcion", "Pancake en forma de mickey mouse, acompañado de syrup de chocolate y fruta de temporada.", "precio", "Q35.00", "imagen", "/desayunoInfantil.png")
        );

        model.addAttribute("nombreRestaurante", "Danny's Pancakes");
        model.addAttribute("menu", menu);
        return "index";
    }
}