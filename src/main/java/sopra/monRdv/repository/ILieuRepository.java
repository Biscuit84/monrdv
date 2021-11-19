package sopra.monRdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sopra.monRdv.model.Lieu;

public interface ILieuRepository extends JpaRepository<Lieu, Long> {

}
