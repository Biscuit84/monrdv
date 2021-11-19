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

import sopra.monRdv.model.Lieu;
import sopra.monRdv.repository.ILieuRepository;

@Controller
@RequestMapping("/Lieu")
public class LieuControler  {

	@Autowired
	private ILieuRepository lieuRepo =null;
	
	
	@GetMapping("/list")
	public String list(Model model) {
	List<Lieu> lieux = lieuRepo.findAll();

	model.addAttribute("mesMotifs", lieux);

	return "lieu/list";
	
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("lieu", new Lieu());
		

		return "lieu/form";
	}
	
	@GetMapping("/edit")
	public String edit(Model model, @RequestParam Long id) {	
		Optional<Lieu> optLieu = lieuRepo.findById(id);

		if (optLieu.isPresent()) {
			model.addAttribute("lieu", optLieu.get());

			return "lieu/form";
		} else {
			return "forward:list";
		}
	}
	
	@PostMapping("/save")
	public String saveBis(@ModelAttribute("lieu") @Valid Lieu lieu, BindingResult result) {
		
		//new MotifValidator().validate(lieu, result);
		
//		if(result.hasErrors()) {
//			return "lieu/form";
//		}

		lieuRepo.save(lieu);

		return "redirect:list";
	}
	@GetMapping("/cancel")
	public String cancel() {
		return "forward:list";
	}

	@GetMapping("/remove")
	public String remove(@RequestParam Long id) {
		lieuRepo.deleteById(id);

		return "redirect:list";
	}

}
