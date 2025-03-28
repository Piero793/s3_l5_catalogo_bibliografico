package it.epicode.DAO;

import it.epicode.classi.Prestito;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PrestitoDAO {

    private EntityManager em;

    // Aggiunta di un nuovo prestito
    public void aggiungiPrestito(Prestito prestito) {
        em.persist(prestito);
    }

    // Ricerca prestito per ID
    public Prestito ricercaPerId(Long id) {
        return em.find(Prestito.class, id);
    }


}
