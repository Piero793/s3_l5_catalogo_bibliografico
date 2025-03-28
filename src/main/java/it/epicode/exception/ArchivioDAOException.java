package it.epicode.exception;

public class ArchivioDAOException extends RuntimeException {

    // Costruttore che accetta solo il messaggio di errore
    public ArchivioDAOException(String message) {
        super(message);
    }

    // Eccezione per errori durante la ricerca per ISBN
    public static class RicercaPerISBNException extends ArchivioDAOException {
        public RicercaPerISBNException(String message) {
            super(message);
        }
    }

    // Eccezione per errori durante l'aggiunta di un elemento
    public static class AggiuntaElementoException extends ArchivioDAOException {
        public AggiuntaElementoException(String message) {
            super(message);
        }
    }

    // Eccezione per errori durante la rimozione di un elemento
    public static class RimozioneElementoException extends ArchivioDAOException {
        public RimozioneElementoException(String message) {
            super(message);
        }
    }
}
