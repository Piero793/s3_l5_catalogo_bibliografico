package it.epicode.DAO;

import it.epicode.classi.Prestito;
import it.epicode.exception.PrestitoDAOException;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrestitoDAO {

    private EntityManager em;

    // Aggiunta di un nuovo prestito
    public void aggiungiPrestito(Prestito prestito) throws PrestitoDAOException.AggiuntaPrestitoException {
        try {
            em.persist(prestito);
        } catch (Exception e) {
            throw new PrestitoDAOException.AggiuntaPrestitoException("Errore durante l'aggiunta del prestito: " + prestito);
        }
    }

    // Ricerca prestito per ID
    public Prestito ricercaPerId(Long id) throws PrestitoDAOException.RicercaPrestitoPerIdException {
        try {
            Prestito prestito = em.find(Prestito.class, id);
            if (prestito == null) {
                throw new PrestitoDAOException.RicercaPrestitoPerIdException("Prestito non trovato con ID: " + id);
            }
            return prestito;
        } catch (Exception e) {
            throw new PrestitoDAOException.RicercaPrestitoPerIdException("Errore durante la ricerca del prestito con ID: " + id);
        }
    }
}