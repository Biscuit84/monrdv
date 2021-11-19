package sopra.monRdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sopra.monRdv.model.Utilisateur;

public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}
