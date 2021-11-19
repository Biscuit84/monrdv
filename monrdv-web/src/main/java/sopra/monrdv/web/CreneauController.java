package sopra.monrdv.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sopra.monRdv.model.Creneau;
import sopra.monRdv.repository.ICreneauRepository;

@Controller
@RequestMapping("/Creneau")
public class CreneauController {

	
	@Autowired
	private ICreneauRepository creneauRepo =null;
	
	
	@GetMapping("/list")
	public String list(Model model) {
	List<Creneau> creneaux = creneauRepo.findAll();

	model.addAttribute("mesCreneaux", creneaux);

	return "creneau/list";
	
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("creneau", new Creneau());
		

		return "creneau/form";
	}
	
	@GetMapping("/edit")
	public String edit(Model model, @RequestParam Long id) {	
		Optional<Creneau> optCreneau = creneauRepo.findById(id);

		if (optCreneau.isPresent()) {
			model.addAttribute("creneau", optCreneau.get());

			return "creneau/form";
		} else {
			return "forward:list";
		}
	}
	
	@PostMapping("/save")
	public String saveBis(@ModelAttribute("creneau") @Valid Creneau creneau, BindingResult result) {
		
		//new CreneauValidator().validate(creneau, result);
		
//		if(result.hasErrors()) {
//			return "creneau/form";
//		}

		creneauRepo.save(creneau);

		return "redirect:list";
	}
	@GetMapping("/cancel")
	public String cancel() {
		return "forward:list";
	}

	@GetMapping("/remove")
	public String remove(@RequestParam Long id) {
		creneauRepo.deleteById(id);

		return "redirect:list";
	}
}
