package sopra.formation.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sopra.formation.model.Evaluation;
import sopra.formation.repository.IEvaluationRepository;
import sopra.formation.web.validator.EvaluationValidator;

@Controller
@RequestMapping("/evaluation")
public class EvaluationController {

	@Autowired
	private IEvaluationRepository evaluationRepo = null;

	// ETAPE 1 : Réception de la requête
	@GetMapping({ "", "/list" })
	public String list(Model model) {
		// ETAPE 2 : Récupération des données
		List<Evaluation> evaluations = evaluationRepo.findAll();

		// ETAPE 3 : Renseigner les données du Model (en les nommant)
		model.addAttribute("evaluations", evaluations);

		// ETAPE 4 : Appel de la Vue
		return "evaluation/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("eval", new Evaluation());

		return "evaluation/form";
	}

	@GetMapping("/edit")
	public String edit(Model model, @RequestParam Long id) {
		Optional<Evaluation> optEvaluation = evaluationRepo.findById(id);

		if (optEvaluation.isPresent()) {
			model.addAttribute("eval", optEvaluation.get());

			return "evaluation/form";
		} else {
			return "forward:list";
		}
	}

	@PostMapping("/save")
	public String save(@RequestParam(required = false) Long id,
			@RequestParam(required = false, defaultValue = "0") Integer version, @RequestParam Integer comportemental,
			@RequestParam Integer technique, @RequestParam String commentaires,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dtEvaluation) {

		Evaluation evaluation = new Evaluation();
		evaluation.setComportemental(comportemental);
		evaluation.setTechnique(technique);
		evaluation.setCommentaires(commentaires);
		evaluation.setId(id);
		evaluation.setVersion(version);
		evaluation.setDtEvaluation(dtEvaluation);

		evaluationRepo.save(evaluation);

		return "redirect:list";
	}
	
	@PostMapping("/saveBis")
	public String saveBis(@ModelAttribute("eval") @Valid Evaluation evaluation, BindingResult result) {
		
		new EvaluationValidator().validate(evaluation, result);
		
		if(result.hasErrors()) {
			return "evaluation/form";
		}

		evaluationRepo.save(evaluation);

		return "redirect:list";
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "forward:list";
	}

	@GetMapping("/remove")
	public String remove(@RequestParam Long id) {
		evaluationRepo.deleteById(id);

		return "redirect:list";
	}

}
