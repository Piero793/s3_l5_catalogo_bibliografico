package it.epicode.DAO;

import it.epicode.classi.ElementoCatalogo;
import it.epicode.exception.ArchivioDAOException;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ArchivioDAO {

    private EntityManager em;

    // Ricerca elemento del catalogo per ISBN facendo un createQuery
    public ElementoCatalogo ricercaPerISBN(String isbn) throws ArchivioDAOException.RicercaPerISBNException {
        try {
            List<ElementoCatalogo> risultati = em.createQuery(
                            "SELECT e FROM ElementoCatalogo e WHERE e.isbn = :isbn", ElementoCatalogo.class)
                    .setParameter("isbn", isbn)
                    .getResultList();

            if (risultati.isEmpty()) {
                throw new ArchivioDAOException.RicercaPerISBNException("Nessun elemento trovato con ISBN: " + isbn);
            } else {
                return risultati.get(0); // Corretto a get(0) da getFirst()
            }
        } catch (Exception e) {
            throw new ArchivioDAOException.RicercaPerISBNException("Errore durante la ricerca dell'elemento con ISBN: " + isbn);
        }
    }

    // Aggiunta di un elemento al catalogo
    public void aggiungiElemento(ElementoCatalogo elemento) throws ArchivioDAOException.AggiuntaElementoException {
        try {
            em.persist(elemento);
        } catch (Exception e) {
            throw new ArchivioDAOException.AggiuntaElementoException("Errore durante l'aggiunta dell'elemento: " + elemento);
        }
    }

    // Rimozione di un elemento del catalogo dato un codice ISBN
    public void rimuoviElementoPerISBN(String isbn) throws ArchivioDAOException.RimozioneElementoException {
        try {
            ElementoCatalogo elemento = ricercaPerISBN(isbn);
            if (elemento != null) {
                em.remove(elemento);
            } else {
                throw new ArchivioDAOException.RimozioneElementoException("Elemento non trovato con ISBN: " + isbn);
            }
        } catch (Exception e) {
            throw new ArchivioDAOException.RimozioneElementoException("Errore durante la rimozione dell'elemento con ISBN: " + isbn);
        }
    }
}