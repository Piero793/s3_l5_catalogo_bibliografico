package it.epicode.DAO;

import it.epicode.classi.Utente;
import it.epicode.exception.UtenteDAOException;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UtenteDAO {

    private EntityManager em;

    // Aggiunta di un nuovo utente
    public void aggiungiUtente(Utente utente) throws UtenteDAOException.AggiuntaUtenteException {
        try {
            em.persist(utente);
        } catch (Exception e) {
            throw new UtenteDAOException.AggiuntaUtenteException("Errore durante l'aggiunta dell'utente: " + utente);
        }
    }

    // Ricerca utente per numero di tessera
    public Utente ricercaPerNumeroTessera(int numeroTessera) throws UtenteDAOException.RicercaUtentePerTesseraException {
        try {
            Utente utente = em.find(Utente.class, numeroTessera);
            if (utente == null) {
                throw new UtenteDAOException.RicercaUtentePerTesseraException("Utente non trovato con numero di tessera: " + numeroTessera);
            }
            return utente;
        } catch (Exception e) {
            throw new UtenteDAOException.RicercaUtentePerTesseraException("Errore durante la ricerca dell'utente con numero di tessera: " + numeroTessera);
        }
    }

    // Rimozione di un utente
    public void rimuoviUtente(int numeroTessera) throws UtenteDAOException.RimozioneUtenteException {
        try {
            Utente utente = ricercaPerNumeroTessera(numeroTessera);
            if (utente != null) {
                em.remove(utente);
            } else {
                throw new UtenteDAOException.RimozioneUtenteException("Impossibile rimuovere: Utente non trovato con numero di tessera: " + numeroTessera);
            }
        } catch (Exception e) {
            throw new UtenteDAOException.RimozioneUtenteException("Errore durante la rimozione dell'utente con numero di tessera: " + numeroTessera);
        }
    }
}
