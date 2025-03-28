package it.epicode.DAO;


import it.epicode.classi.Utente;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UtenteDAO {

    private EntityManager em;

    // Aggiunta di un nuovo utente
    public void aggiungiUtente(Utente utente) {
        em.persist(utente);
    }

    // Ricerca utente per numero di tessera
    public Utente ricercaPerNumeroTessera(int numeroTessera) {
        return em.find(Utente.class, numeroTessera);
    }

    // Rimozione di un utente
    public void rimuoviUtente(int numeroTessera) {
        Utente utente = ricercaPerNumeroTessera(numeroTessera);
        if (utente != null) {
            em.remove(utente);
        }
    }

}
