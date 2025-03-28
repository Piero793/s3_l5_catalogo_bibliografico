package it.epicode.main;

import it.epicode.DAO.ArchivioDAO;
import it.epicode.DAO.UtenteDAO;
import it.epicode.DAO.PrestitoDAO;
import it.epicode.classi.*;
import it.epicode.enums.Periodicita;
import it.epicode.exception.ArchivioDAOException;
import it.epicode.exception.UtenteDAOException;
import it.epicode.exception.PrestitoDAOException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Avvio dell'applicazione...");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        ArchivioDAO archivioDAO = new ArchivioDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);

        try (emf; em) {
            // Creazione e persistenza degli elementi del catalogo
            logger.info("Creazione degli elementi del catalogo...");
            Libro libro = Libro.builder()
                    .isbn("1234567890")
                    .titolo("Il Signore degli Anelli")
                    .autore("J.R.R. Tolkien")
                    .genere("Fantasy")
                    .annoPubblicazione(1954)
                    .numeroPagine(423)
                    .build();

            Libro libro2 = Libro.builder()
                    .isbn("9876543210")
                    .titolo("National Geographic - Scienza")
                    .autore("Autore Sconosciuto")
                    .genere("Scienza")
                    .annoPubblicazione(1888)
                    .numeroPagine(100)
                    .build();

            Rivista rivista = Rivista.builder()
                    .isbn("7894561230")
                    .titolo("National Geographic")
                    .annoPubblicazione(1888)
                    .numeroPagine(50)
                    .periodicita(Periodicita.MENSILE)
                    .build();

            em.getTransaction().begin();
            try {
                archivioDAO.aggiungiElemento(libro);
                archivioDAO.aggiungiElemento(libro2);
                archivioDAO.aggiungiElemento(rivista);
                logger.info("Elementi aggiunti al catalogo con successo!");
            } catch (ArchivioDAOException.AggiuntaElementoException e) {
                logger.error("Errore nell'aggiunta degli elementi al catalogo: {}", e.getMessage());
            }
            em.getTransaction().commit();

            // Creazione e persistenza degli utenti
            logger.info("Creazione dell'utente...");
            Utente utente = Utente.builder()
                    .nome("Mario")
                    .cognome("Rossi")
                    .dataDiNascita(LocalDate.of(1990, 10, 7))
                    .prestiti(new ArrayList<>())
                    .build();

            em.getTransaction().begin();
            try {
                utenteDAO.aggiungiUtente(utente);
                logger.info("Utente aggiunto con successo!");
            } catch (UtenteDAOException.AggiuntaUtenteException e) {
                logger.error("Errore nell'aggiunta dell'utente: {}", e.getMessage());
            }
            em.getTransaction().commit();

            // Creazione e persistenza dei prestiti
            logger.info("Creazione del prestito...");
            Prestito prestito = Prestito.builder()
                    .utente(utente)
                    .elementoPrestato(libro)
                    .dataInizioPrestito(LocalDate.of(2025, 3, 28))
                    .build();
            prestito.calcolaDataRestituzionePrevista();

            em.getTransaction().begin();
            try {
                prestitoDAO.aggiungiPrestito(prestito);
                logger.info("Prestito aggiunto con successo!");
            } catch (PrestitoDAOException.AggiuntaPrestitoException e) {
                logger.error("Errore nell'aggiunta del prestito: {}", e.getMessage());
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            logger.error("Errore generico durante l'operazione: {}", e.getMessage());
        } finally {
            logger.info("Chiusura delle risorse completata.");
        }

        logger.info("DB creato!");
    }
}
