package com.codingdojo.javierulloa.controladores;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorBase {
	
	
	@GetMapping("/Gold")
	public String index() {
		
		return "index.jsp";
	}
	
	
	@PostMapping("/jugar")
	public String jugar(HttpSession session,@RequestParam("lugar") String lugar ) {
		
		int oro = 0;
		ArrayList<String> actividades = new ArrayList<String>();
		
		if(session.getAttribute("oro")== null) {
			session.setAttribute("oro", 0);
			session.setAttribute("actividades", actividades);
		}
		else {
			oro = (int) session.getAttribute("oro");
			actividades =(ArrayList<String>) session.getAttribute("actividades");
		}
		
		int min = 0;
		int max = 0;
		
		
		
		
		switch(lugar) {
			case "granja":
				min=10;
				max=20;
				break;
			
			case "cueva":
				min = 5;
				max = 10;
				break;
				
			case "casa":
				min=2;
				max=5;
				break;
			case "casino":
				min= -50;
				max= 50;
				break;
		
		}	
		
		int random = new Random().nextInt(max - min +1)+min;
		
		oro += random;
		session.setAttribute("oro", oro);
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("MMM d y h:mm");
		String fechaformato = formato.format(fecha);
		
		String mensaje="";
		if (random<0) {
			mensaje = "entraste a "+lugar+" y perdiste "+random+" de oro  "+fechaformato ;
		}
		else {
			mensaje = "entraste a "+lugar+" y ganaste "+random+" de oro  "+fechaformato ;
		}
		 
		
		actividades.add(mensaje);
		
		session.setAttribute("actividades", actividades);
		return "redirect:/Gold";
	}

}
