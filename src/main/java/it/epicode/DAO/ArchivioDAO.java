package it.epicode.DAO;

import it.epicode.classi.ElementoCatalogo;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArchivioDAO {

    private EntityManager em;

    // Ricerca elemento del catalogo per ISBN come createQuery
    public ElementoCatalogo ricercaPerISBN(String isbn) {
        return em.createQuery(
                        "SELECT e FROM ElementoCatalogo e WHERE e.isbn = :isbn", ElementoCatalogo.class)
                .setParameter("isbn", isbn)
                .getSingleResult();
    }

    // Aggiunta di un elemento al catalogo
    public void aggiungiElemento(ElementoCatalogo elemento) {
        em.persist(elemento);
    }

    // Rimozione di un elemento del catalogo dato un codice ISBN
    public void rimuoviElementoPerISBN(String isbn) {
        ElementoCatalogo elemento = ricercaPerISBN(isbn);
        if (elemento != null) {
            em.remove(elemento);
        }
    }
}
