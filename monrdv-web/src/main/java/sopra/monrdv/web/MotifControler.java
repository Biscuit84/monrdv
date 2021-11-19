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

import sopra.monRdv.model.Motif;
import sopra.monRdv.repository.IMotifRepository;

@Controller
@RequestMapping("/Motif")
public class MotifControler {

	@Autowired
	private IMotifRepository motifRepo =null;
	
	
	@GetMapping("/list")
	public String list(Model model) {
	List<Motif> motifs = motifRepo.findAll();

	model.addAttribute("mesMotifs", motifs);

	return "motif/list";
	
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("motif", new Motif());
		

		return "motif/form";
	}
	
	@GetMapping("/edit")
	public String edit(Model model, @RequestParam Long id) {	
		Optional<Motif> optMotif = motifRepo.findById(id);

		if (optMotif.isPresent()) {
			model.addAttribute("motif", optMotif.get());

			return "motif/form";
		} else {
			return "forward:list";
		}
	}
	
	@PostMapping("/save")
	public String saveBis(@ModelAttribute("motif") @Valid Motif motif, BindingResult result) {
		
		//new MotifValidator().validate(motif, result);
		
//		if(result.hasErrors()) {
//			return "evaluation/form";
//		}

		motifRepo.save(motif);

		return "redirect:list";
	}
	@GetMapping("/cancel")
	public String cancel() {
		return "forward:list";
	}

	@GetMapping("/remove")
	public String remove(@RequestParam Long id) {
		motifRepo.deleteById(id);

		return "redirect:list";
	}

	
}
