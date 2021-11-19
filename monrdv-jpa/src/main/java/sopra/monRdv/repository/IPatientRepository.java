package sopra.monRdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sopra.monRdv.model.Patient;

public interface IPatientRepository extends JpaRepository<Patient, Long> {

}
