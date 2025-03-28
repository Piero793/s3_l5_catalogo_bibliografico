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

    // Rimozione di un prestito
    public void rimuoviPrestito(Long id) {
        Prestito prestito = ricercaPerId(id);
        if (prestito != null) {
            em.remove(prestito);
        }
    }

    // Recupera tutti i prestiti attivi
    public List<Prestito> recuperaPrestitiAttivi() {
        return em.createQuery(
                        "SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva IS NULL", Prestito.class)
                .getResultList();
    }

    // Recupera tutti i prestiti scaduti
    public List<Prestito> recuperaPrestitiScaduti() {
        return em.createQuery(
                        "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < CURRENT_DATE AND p.dataRestituzioneEffettiva IS NULL", Prestito.class)
                .getResultList();
    }
}
