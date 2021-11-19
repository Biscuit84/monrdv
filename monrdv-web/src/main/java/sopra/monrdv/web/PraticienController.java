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

import sopra.monRdv.model.Praticien;
import sopra.monRdv.model.Specialite;
import sopra.monRdv.repository.IPraticienRepository;
import sopra.monRdv.repository.ISpecialiteRepository;
import sopra.monrdv.web.dto.PraticienDTO;
import sopra.monrdv.web.dto.PraticienDTO;


@Controller
@RequestMapping("/praticien")
public class PraticienController {

	@Autowired
	private IPraticienRepository praticienRepo = null;
	@Autowired
	private ISpecialiteRepository specialiteRepo= null;

	@GetMapping("/list")
	public String list(Model model) {
		// ETAPE 2 : Récupération des données
		List<Praticien> praticiens = praticienRepo.findAll();

		List<PraticienDTO> praticiensDTO = new ArrayList<PraticienDTO>();

		for(Praticien praticien : praticiens) {
			praticiensDTO.add(new PraticienDTO(praticien));
		}

		// ETAPE 3 : Renseigner les données du Model (en les nommant)
		model.addAttribute("praticiens", praticiensDTO);

		// ETAPE 4 : Appel de la Vue
		return "praticien/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("praticien", new PraticienDTO());

		return "praticien/form";
	}

	@GetMapping("/edit")
	public String edit(Model model, @RequestParam Long id) {
		Optional<Praticien> optPraticien = praticienRepo.findById(id);

		if (optPraticien.isPresent()) {
			model.addAttribute("praticien", new PraticienDTO(optPraticien.get()));

			return "praticien/form";
		} else {
			return "forward:list";
		}
	}
	@PostMapping("/save")
	public String save(@ModelAttribute("praticien") PraticienDTO praticienDTO) {

		Praticien praticien = null;

		if(praticienDTO.getId() != null) {
			praticien = praticienRepo.findById(praticienDTO.getId()).get();
		} else {
			praticien = new Praticien();
		}

		praticien.setNom(praticienDTO.getNom());
		praticien.setCivilite(praticienDTO.getCivilite());
		praticien.setPrenom(praticienDTO.getPrenom());
		praticien.setTelephone(praticienDTO.getTelephone());
		praticien.setSecteur(praticienDTO.getSecteur());
		praticien.setCarteVitale(praticienDTO.isCarteVitale());
		praticien.setPhoto(praticienDTO.getPhoto());
		praticien.setCheque(praticienDTO.isCheque());
		praticien.setEspece(praticienDTO.isEspece());
		praticien.setDureeCreneau(praticienDTO.getDureeCreneau());
		//praticien.setSpecialites(praticienDTO.getSpecialites());
		
		praticien = praticienRepo.save(praticien);
		
		List<String> specialites = praticienDTO.getSpecialites();
		for (String  specialite: specialites) {
			Specialite spe =new Specialite();
			spe.setNom(specialite);
			spe.setPraticien(praticien);
			
			specialiteRepo.save(spe);
		}
		
			
		
		

		return "redirect:list";
	}
	@GetMapping("/cancel")
	public String cancel() {
		return "forward:list";
	}

	@GetMapping("/remove")
	public String remove(@RequestParam Long id) {
		praticienRepo.deleteById(id);

		return "redirect:list";
	}

}
