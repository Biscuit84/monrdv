package sopra.monRdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sopra.monRdv.model.Motif;

public interface IMotifRepository extends JpaRepository<Motif, Long> {

}
