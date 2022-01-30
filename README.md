# UNIMIB Modules

[Documentazione OpenAPI](https://unimibsoftengcourse2022.github.io/progetto-questionari-1-dev-team/)   
[Documentazione tecnica](https://github.com/UnimibSoftEngCourse2022/progetto-questionari-1-dev-team/blob/documentation/Documentation/DocumentoDiProgetto.pdf)  
[Elenco release](https://github.com/UnimibSoftEngCourse2022/progetto-questionari-1-dev-team/releases)

## Struttura repository

- Progetto Java: `src`
- Progetto AngularJS: `src/main/resources/static`

## Requisiti dipendenze

- Per l'esecuzione dal binario è necessario Java 11 o superiore;
- Per la compilazione e il download delle dipendenze del progetto Java è necessario Maven 3.8.3 o superiore;
- Per il download delle dipendenze del progetto AngularJS è necessario Yarn 1.22.17 o superiore;
- Un database MariaDB o MySQL.

## Comandi per la compilazione

- Nella cartella `src/main/resources/static` eseguire il comando: `yarn install`
- Nella radice del progetto eseguire il comando: `mvn clean verify`

## Istruzioni per l'installazione

1. Creare un database per il progetto (chiamarlo, per esempio, unimibdb);
2. Aprire il file `application.properties` e impostare le seguenti proprietà:
    * `spring.datasource.url`: inserire l'url nel formato JDBC per il database (esempio: `jdbc:mysql://indirizzodatabase:porta/nomedatabase?eventualiparametri=valore`);
    * `spring.datasource.username`: nome utente per l'accesso al database;
    * `spring.datasource.password`: password per l'accesso al database.
3. Per avviare l'esecuzione da binario, è sufficiente utilzzare il comando `java -jar unimibmodules-1.0.0-SNAPSHOT.jar`;

Attenzione: se dovesse essere necessario riavviare il programma è consigliabile impostare la proprietà `spring.jpa.hibernate.ddl-auto` a `none`.  
Al primo avvio potrebbero verificarsi errori del tipo `SQLException`; questi sono causati da errori di Hibernate durante la creazione del database. In questo caso è sufficiente lanciare nuovamente l'applicazione.  
Se dovessero presentarsi altri eccezioni di Hibernate o riguardanti la creazione di bean, è possibile che si sia verificato un errore nella connessione al database.

## Istruzioni per l'utilizzo

- [Registrazione](https://github.com/UnimibSoftEngCourse2022/progetto-questionari-1-dev-team/wiki/Registrazione)
- [Accesso](https://github.com/UnimibSoftEngCourse2022/progetto-questionari-1-dev-team/wiki/Login)
- [Homepage](https://github.com/UnimibSoftEngCourse2022/progetto-questionari-1-dev-team/wiki/Homepage)
- [Navbar](https://github.com/UnimibSoftEngCourse2022/progetto-questionari-1-dev-team/wiki/Navbar)
- [Creazione domanda](https://github.com/UnimibSoftEngCourse2022/progetto-questionari-1-dev-team/wiki/Creazione-domanda)
- [Modifica domanda](https://github.com/UnimibSoftEngCourse2022/progetto-questionari-1-dev-team/wiki/Modifica-domanda)
- [Creazione questionario](https://github.com/UnimibSoftEngCourse2022/progetto-questionari-1-dev-team/wiki/Creazione-questionario)
- [Modifica questionario](https://github.com/UnimibSoftEngCourse2022/progetto-questionari-1-dev-team/wiki/Modifica-questionario)
- [Compilazione questionario](https://github.com/UnimibSoftEngCourse2022/progetto-questionari-1-dev-team/wiki/Compilazione-questionario)
- [Modifica risposte questionario](https://github.com/UnimibSoftEngCourse2022/progetto-questionari-1-dev-team/wiki/Modifica-risposte-questionario)
