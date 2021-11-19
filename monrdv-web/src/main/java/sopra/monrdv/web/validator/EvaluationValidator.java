package sopra.monrdv.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sopra.formation.model.Evaluation;

public class EvaluationValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Evaluation.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Evaluation evaluation = (Evaluation) target;

		if (((evaluation.getComportemental() != null && evaluation.getComportemental() < 10)
				|| (evaluation.getTechnique() != null && evaluation.getTechnique() < 10))
				&& evaluation.getCommentaires().isBlank()) {
			errors.rejectValue("commentaires", "evaluation.commentaires.error.notnull", "Veuillez renseigner le commentaire !");
		}
	}

}
