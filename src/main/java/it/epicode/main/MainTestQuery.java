package it.epicode.main;

import it.epicode.DAO.ArchivioDAO;
import it.epicode.DAO.PrestitoDAO;
import it.epicode.classi.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainTestQuery {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        ArchivioDAO archivioDAO = new ArchivioDAO(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);


            em.getTransaction().begin();

        // Ricerca per ISBN
        System.out.println("Ricerca per ISBN ('1234567890'):");
        ElementoCatalogo elemento = archivioDAO.ricercaPerISBN("1234567890");
        if (elemento != null) {
            System.out.println("Elemento trovato: " + elemento);
        } else {
            System.out.println("Nessun elemento trovato con questo ISBN.");
        }



        //  Ricerca per anno di pubblicazione
            System.out.println("Ricerca per anno di pubblicazione (1888):");
            List<ElementoCatalogo> elementiAnno = em.createNamedQuery("ElementoCatalogo.trovaPerAnnoPubblicazione", ElementoCatalogo.class)
                    .setParameter("anno", 1888)
                    .getResultList();
            elementiAnno.forEach(System.out::println);

            //  Ricerca per autore
            System.out.println("Ricerca per autore (J.R.R. Tolkien):");
            List<Libro> libriAutore = em.createNamedQuery("ElementoCatalogo.trovaPerAutore", Libro.class)
                    .setParameter("autore", "J.R.R. Tolkien")
                    .getResultList();
            libriAutore.forEach(System.out::println);

            // Ricerca per titolo parziale
            System.out.println("Ricerca per titolo parziale ('National'):");
            List<ElementoCatalogo> elementiTitolo = em.createNamedQuery("ElementoCatalogo.trovaPerTitoloParziale", ElementoCatalogo.class)
                    .setParameter("titolo", "%National%")
                    .getResultList();
            elementiTitolo.forEach(System.out::println);

            // Ricerca degli elementi prestati da un utente
            System.out.println("Ricerca degli elementi prestati da Mario Rossi:");
            List<ElementoCatalogo> elementiPrestati = em.createNamedQuery("Prestito.trovaElementiPrestatiDaUtente", ElementoCatalogo.class)
                    .setParameter("numeroTessera", 1) // Supponendo che il numero tessera di Mario sia 1
                    .getResultList();
            elementiPrestati.forEach(System.out::println);

            // Ricerca di prestiti scaduti e non restituiti
            System.out.println("Ricerca di prestiti scaduti e non restituiti:");
            List<Prestito> prestitiScaduti = em.createNamedQuery("Prestito.trovaPrestitiScadutiENonRestituiti", Prestito.class)
                    .getResultList();
            prestitiScaduti.forEach(System.out::println);

            em.getTransaction().commit();


            em.close();
            emf.close();


        System.out.println("Test delle Named Query completato.");
    }
}
