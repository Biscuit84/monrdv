package sopra.monRdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sopra.monRdv.model.Consultation;

public interface IConsultationRepository extends JpaRepository<Consultation, Long> {

}
