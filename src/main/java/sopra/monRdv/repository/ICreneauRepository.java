package sopra.monRdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sopra.monRdv.model.Creneau;

public interface ICreneauRepository extends JpaRepository<Creneau, Long> {

}
