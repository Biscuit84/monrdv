package sopra.formation.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sopra.formation.model.Adresse;
import sopra.formation.model.Civilite;
import sopra.formation.model.Evaluation;
import sopra.formation.model.Stagiaire;
import sopra.formation.repository.IEvaluationRepository;
import sopra.formation.repository.IPersonneRepository;

@Controller
@RequestMapping("/stagiaire")
public class StagiaireController {

	@Autowired
	private IPersonneRepository personneRepo = null;
	@Autowired
	private IEvaluationRepository evaluationRepo = null;

	// ETAPE 1 : Réception de la requête
	@GetMapping({"", "/list"})
	public String list(Model model) {
		// ETAPE 2 : Récupération des données
		List<Stagiaire> stagiaires = personneRepo.findAllStagiaire();

		// ETAPE 3 : Renseigner les données du Model (en les nommant)
		model.addAttribute("mesStagiaires", stagiaires);

		// ETAPE 4 : Appel de la Vue (JSP)
		return "stagiaire/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("stagiaire", new Stagiaire());
		model.addAttribute("civilites", Civilite.values());
		model.addAttribute("evaluations", evaluationRepo.findAllOrphan(null));

		return "stagiaire/form";
	}

	@GetMapping("/edit")
	public String edit(Model model, @RequestParam Long id) {	
		Optional<Stagiaire> optStagiaire = personneRepo.findStagiaireById(id);

		if (optStagiaire.isPresent()) {
			model.addAttribute("stagiaire", optStagiaire.get());
			model.addAttribute("civilites", Civilite.values());
			model.addAttribute("evaluations", evaluationRepo.findAllOrphan(id));

			return "stagiaire/form";
		} else {
			return "forward:list";
		}
	}
	
	@GetMapping("/editWithPathVariable/{id}")
	public String editWithPathVariable(Model model, @PathVariable Long id) {	
		Optional<Stagiaire> optStagiaire = personneRepo.findStagiaireById(id);

		if (optStagiaire.isPresent()) {
			model.addAttribute("stagiaire", optStagiaire.get());
			model.addAttribute("civilites", Civilite.values());
			model.addAttribute("evaluations", evaluationRepo.findAllOrphan(id));

			return "stagiaire/form";
		} else {
			return "forward:list";
		}
	}

	@PostMapping("/save")
	public String save(@RequestParam(required = false) Long id,
			@RequestParam(required = false, defaultValue = "0") Integer version, @RequestParam Civilite civilite,
			@RequestParam String nom, @RequestParam String prenom,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dtNaissance,
			@RequestParam String rue, @RequestParam String codePostal, @RequestParam String ville,
			@RequestParam(value = "evaluation", required = false) Long evaluationId) {

		Stagiaire stagiaire = new Stagiaire(civilite, nom, prenom, null, null, dtNaissance, null);
		stagiaire.setId(id);
		stagiaire.setVersion(version);
		stagiaire.setAdresse(new Adresse(rue, null, codePostal, ville));

		if (evaluationId != null) {
			Evaluation evaluation = new Evaluation();
			evaluation.setId(evaluationId);

			stagiaire.setEvaluation(evaluation);
		}

		personneRepo.save(stagiaire);

		return "redirect:list";
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "forward:list";
	}

	@GetMapping("/remove")
	public String remove(@RequestParam Long id) {
		personneRepo.deleteById(id);

		return "redirect:list";
	}

}
