package sopra.monrdv.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sopra.formation.model.Adresse;
import sopra.formation.model.Salle;
import sopra.formation.repository.ISalleRepository;
import sopra.monrdv.web.dto.SalleDTO;

@Controller
@RequestMapping("/salle")
public class SalleController {

	@Autowired
	private ISalleRepository salleRepo = null;

	// ETAPE 1 : Réception de la requête
	@GetMapping({ "", "/list" })
	public String list(Model model) {
		// ETAPE 2 : Récupération des données
		List<Salle> salles = salleRepo.findAll();
		
		List<SalleDTO> sallesDTO = new ArrayList<SalleDTO>();
		
		for(Salle salle : salles) {
			sallesDTO.add(new SalleDTO(salle));
		}

		// ETAPE 3 : Renseigner les données du Model (en les nommant)
		model.addAttribute("salles", sallesDTO);

		// ETAPE 4 : Appel de la Vue
		return "salle/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("salle", new SalleDTO());

		return "salle/form";
	}

	@GetMapping("/edit")
	public String edit(Model model, @RequestParam Long id) {
		Optional<Salle> optSalle = salleRepo.findById(id);

		if (optSalle.isPresent()) {
			model.addAttribute("salle", new SalleDTO(optSalle.get()));

			return "salle/form";
		} else {
			return "forward:list";
		}
	}

	
	@PostMapping("/save")
	public String save(@ModelAttribute("salle") SalleDTO salleDTO) {
		
		Salle salle = null;
		
		if(salleDTO.getId() != null) {
			salle = salleRepo.findById(salleDTO.getId()).get();
		} else {
			salle = new Salle();
		}
		
		salle.setNom(salleDTO.getNom());
		salle.setCapacite(salleDTO.getCapacite());
		salle.setVideoProjecteur(salleDTO.getVideoProjecteur());
		salle.setAdr(new Adresse(salleDTO.getRue(), salleDTO.getComplement(), salleDTO.getCodePostal(), salleDTO.getVille()));

		
		salleRepo.save(salle);

		return "redirect:list";
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "forward:list";
	}

	@GetMapping("/remove")
	public String remove(@RequestParam Long id) {
		salleRepo.deleteById(id);

		return "redirect:list";
	}

}
