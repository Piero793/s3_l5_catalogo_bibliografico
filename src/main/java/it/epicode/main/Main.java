package it.epicode.main;

import it.epicode.DAO.ArchivioDAO;
import it.epicode.DAO.UtenteDAO;
import it.epicode.DAO.PrestitoDAO;
import it.epicode.classi.*;
import it.epicode.enums.Periodicita;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        // DAO
        ArchivioDAO archivioDAO = new ArchivioDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);

        // Creazione degli elementi del catalogo
        Libro libro = Libro.builder()
                .isbn("1234567890")
                .titolo("Il Signore degli Anelli")
                .autore("J.R.R. Tolkien")
                .genere("Fantasy")
                .annoPubblicazione(1954)
                .numeroPagine(423)
                .build();

        Libro libro2 = Libro.builder()
                .isbn("789456231")
                .titolo("Harry Potter")
                .autore("J.K. Rowling")
                .genere("Fantasy")
                .annoPubblicazione(1997)
                .numeroPagine(423)
                .build();

        Rivista rivista = Rivista.builder()
                .isbn("9876543210")
                .titolo("National Geographic")
                .annoPubblicazione(1888)
                .numeroPagine(100)
                .periodicita(Periodicita.SETTIMANALE)
                .build();

        Rivista rivista2 = Rivista.builder()
                .isbn("58214568")
                .titolo("Home Gym")
                .annoPubblicazione(2025)
                .numeroPagine(20)
                .periodicita(Periodicita.MENSILE)
                .build();

        // Creazione degli utenti
        Utente utente = Utente.builder()
                .nome("Mario")
                .cognome("Rossi")
                .dataDiNascita(LocalDate.of(1990, 10, 7))
                .build();

        Utente utente2 = Utente.builder()
                .nome("Luigi")
                .cognome("Verdi")
                .dataDiNascita(LocalDate.of(2000, 5, 10))
                .build();

        // Inizio transazione
        em.getTransaction().begin();

        // Persistenza degli elementi del catalogo
        archivioDAO.aggiungiElemento(libro);
        archivioDAO.aggiungiElemento(libro2);
        archivioDAO.aggiungiElemento(rivista);
        archivioDAO.aggiungiElemento(rivista2);

        // Persistenza degli utenti
        utenteDAO.aggiungiUtente(utente);
        utenteDAO.aggiungiUtente(utente2);

        em.getTransaction().commit();

        // Creazione dei prestiti
        Prestito prestito1 = Prestito.builder()
                .utente(utente)
                .elementoPrestato(libro)
                .dataInizioPrestito(LocalDate.now())
                .build();
        prestito1.calcolaDataRestituzionePrevista();

        Prestito prestito2 = Prestito.builder()
                .utente(utente2)
                .elementoPrestato(rivista)
                .dataInizioPrestito(LocalDate.of(2025, 3, 20))
                .build();
        prestito2.calcolaDataRestituzionePrevista();

        em.getTransaction().begin();

        prestitoDAO.aggiungiPrestito(prestito1);
        prestitoDAO.aggiungiPrestito(prestito2);

        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("Persistenza completata con successo!");
    }
}

