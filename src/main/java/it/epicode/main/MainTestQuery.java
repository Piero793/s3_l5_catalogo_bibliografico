package it.epicode.main;

import it.epicode.DAO.ArchivioDAO;
import it.epicode.classi.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MainTestQuery {
    private static final Logger logger = LoggerFactory.getLogger(MainTestQuery.class);

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        ArchivioDAO archivioDAO = new ArchivioDAO(em);

        try (emf; em) {
            em.getTransaction().begin();

            ricercaPerISBN(archivioDAO);
            ricercaPerAnno(em);
            ricercaPerAutore(em);
            ricercaPerTitoloParziale(em);
            ricercaElementiPrestati(em);
            ricercaPrestitiScaduti(em);

            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Errore generico durante il test delle Named Query: {}", e.getMessage());
        } finally {
            logger.info("Risorse chiuse con successo.");
        }

        logger.info("Test delle Named Query completato.");
    }

    private static void ricercaPerISBN(ArchivioDAO archivioDAO) {
        try {
            logger.info("Ricerca per ISBN ('1234567890'):");
            ElementoCatalogo elemento = archivioDAO.ricercaPerISBN("1234567890");
            if (elemento != null) {
                logger.info("Elemento trovato: {}", elemento);
            } else {
                logger.warn("Nessun elemento trovato con questo ISBN.");
            }
        } catch (Exception e) {
            logger.error("Errore durante la ricerca per ISBN: {}", e.getMessage());
        }
    }

    private static void ricercaPerAnno(EntityManager em) {
        try {
            logger.info("Ricerca per anno di pubblicazione (1888):");
            List<ElementoCatalogo> elementiAnno = em.createNamedQuery("ElementoCatalogo.trovaPerAnnoPubblicazione", ElementoCatalogo.class)
                    .setParameter("anno", 1888)
                    .getResultList();
            elementiAnno.forEach(elemento -> logger.info("Elemento: {}", elemento));
        } catch (Exception e) {
            logger.error("Errore durante la ricerca per anno di pubblicazione: {}", e.getMessage());
        }
    }

    private static void ricercaPerAutore(EntityManager em) {
        try {
            logger.info("Ricerca per autore (J.R.R. Tolkien):");
            List<Libro> libriAutore = em.createNamedQuery("ElementoCatalogo.trovaPerAutore", Libro.class)
                    .setParameter("autore", "J.R.R. Tolkien")
                    .getResultList();
            libriAutore.forEach(libro -> logger.info("Libro: {}", libro));
        } catch (Exception e) {
            logger.error("Errore durante la ricerca per autore: {}", e.getMessage());
        }
    }

    private static void ricercaPerTitoloParziale(EntityManager em) {
        try {
            logger.info("Ricerca per titolo parziale ('National'):");
            List<ElementoCatalogo> elementiTitolo = em.createNamedQuery("ElementoCatalogo.trovaPerTitoloParziale", ElementoCatalogo.class)
                    .setParameter("titolo", "%National%")
                    .getResultList();
            elementiTitolo.forEach(elemento -> logger.info("Elemento: {}", elemento));
        } catch (Exception e) {
            logger.error("Errore durante la ricerca per titolo parziale: {}", e.getMessage());
        }
    }

    private static void ricercaElementiPrestati(EntityManager em) {
        try {
            logger.info("Ricerca degli elementi prestati da Mario Rossi:");
            List<ElementoCatalogo> elementiPrestati = em.createNamedQuery("Prestito.trovaElementiPrestatiDaUtente", ElementoCatalogo.class)
                    .setParameter("numeroTessera", 1)
                    .getResultList();
            elementiPrestati.forEach(elemento -> logger.info("Elemento Prestato: {}", elemento));
        } catch (Exception e) {
            logger.error("Errore durante la ricerca degli elementi prestati: {}", e.getMessage());
        }
    }

    private static void ricercaPrestitiScaduti(EntityManager em) {
        try {
            logger.info("Ricerca di prestiti scaduti e non restituiti:");
            List<Prestito> prestitiScaduti = em.createNamedQuery("Prestito.trovaPrestitiScadutiENonRestituiti", Prestito.class)
                    .getResultList();
            prestitiScaduti.forEach(prestito -> logger.info("Prestito Scaduto: {}", prestito));
        } catch (Exception e) {
            logger.error("Errore durante la ricerca di prestiti scaduti: {}", e.getMessage());
        }
    }
}
