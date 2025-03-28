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
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        ArchivioDAO archivioDAO = new ArchivioDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);

        // Creazione e persistenza degli elementi del catalogo
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
        archivioDAO.aggiungiElemento(libro);
        archivioDAO.aggiungiElemento(libro2);
        archivioDAO.aggiungiElemento(rivista);
        em.getTransaction().commit();

        // Creazione e persistenza degli utenti
        Utente utente = Utente.builder()
                .nome("Mario")
                .cognome("Rossi")
                .dataDiNascita(LocalDate.of(1990, 10, 7))
                .prestiti(new ArrayList<>())
                .build();

        em.getTransaction().begin();
        utenteDAO.aggiungiUtente(utente);
        em.getTransaction().commit();

        // Creazione e persistenza dei prestiti
        Prestito prestito = Prestito.builder()
                .utente(utente)
                .elementoPrestato(libro)
                .dataInizioPrestito(LocalDate.of(2025, 3, 28))
                .build();
        prestito.calcolaDataRestituzionePrevista();

        em.getTransaction().begin();
        prestitoDAO.aggiungiPrestito(prestito);
        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("DB creato!");
    }
}