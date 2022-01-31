# UNIMIB Modules

![Version](https://img.shields.io/badge/version-1.0.0-success)
![License](https://img.shields.io/github/license/UnimibSoftEngCourse2022/progetto-questionari-1-dev-team)
![Stars](https://img.shields.io/github/stars/UnimibSoftEngCourse2022/progetto-questionari-1-dev-team)
![Build](https://github.com/UnimibSoftEngCourse2022/progetto-questionari-1-dev-team/actions/workflows/build.yml/badge.svg)

Il progetto UNIMIB Modules è un progetto all'interno del quale gestire e compilari i questionari, dotata di alta usabilità, tolleranza ai guasti e performance.

È possibile usare il servizio a [questo link](http://unimibquestionari-env.eba-3behr9mi.eu-central-1.elasticbeanstalk.com/).

[Documentazione OpenAPI](https://unimibsoftengcourse2022.github.io/progetto-questionari-1-dev-team/)   
[Documentazione tecnica](https://github.com/UnimibSoftEngCourse2022/progetto-questionari-1-dev-team/blob/documentation/Documentation/DocumentoDiProgetto.pdf)  
[Documentazione JavaDOC](https://unimibsoftengcourse2022.github.io/progetto-questionari-1-dev-team/javadoc/)  
[Elenco release](https://github.com/UnimibSoftEngCourse2022/progetto-questionari-1-dev-team/releases)

## Struttura repository

- Progetto Java: `src`
- Progetto AngularJS: `src/main/resources/static`
- Database: `sql`

## Requisiti dipendenze

- Per l'esecuzione dal binario è necessario Java 11 o superiore;
- Per la compilazione e il download delle dipendenze del progetto Java è necessario Maven 3.8.3 o superiore;
- Per il download delle dipendenze del progetto AngularJS è necessario Yarn 1.22.17 o superiore;
- Un database MariaDB o MySQL.

## Comandi per la compilazione

- Nella cartella `src/main/resources/static` eseguire il comando: `yarn install`
- Nella radice del progetto eseguire il comando: `mvn clean verify`
- Al termine della compilazione il binario si troverà nella cartella `target` e potrà essere avviato col comando `java -jar target/unimibmodules.jar`

## Istruzioni per l'avvio
È possibile usare il servizio senza avviarlo in locale a [questo link](http://unimibquestionari-env.eba-3behr9mi.eu-central-1.elasticbeanstalk.com/).  
In alternativa è possibile avviarlo da codice sorgente o da binario seguendo le seguenti istruzioni:
- Avvio da codice sorgente
   1. Creare un database per il progetto (chiamarlo, per esempio, unimibdb);
   2. Aprire il file `src/main/resources/application.properties` e impostare le seguenti proprietà:
       * `spring.datasource.url`: inserire l'url nel formato JDBC per il database (esempio: `jdbc:mysql://indirizzodatabase:porta/nomedatabase?eventualiparametri=valore`);
       * `spring.datasource.username`: nome utente per l'accesso al database;
       * `spring.datasource.password`: password per l'accesso al database.  
      **Non è possibile avviare il server senza aver configurato il database.**
   3. Aprire il file `src/main/java/it/unimib/unimibmodules/controller/AWSToken.java` e impostare le seguenti chiavi:
       * `ACCESS_KEY_ID`: inserire l'username della route fornita dal servizio AWS;
       * `ACCESS_KEY_VALUE`: inserire la passsword della route fornita dal servizio AWS.  
      **È possibile avviare il server senza configurare AWS, ma non sarà possibile effettuare il caricamento delle immagini**
   4. Aprire il file `src/main/java/it/unimib/unimibmodules/service/MailServiceImpl.java` e impostare le seguenti informazioni:
       * `EMAIL_USERNAME`: l'indirizzo email;
       * `EMAIL_PASSWORD`: la password per accedere all'indirizzo email;
       * `EMAIL_SMTP_HOST`: l'indirizzo dell'host SMTP;
       * `EMAIL_SMTP_AUTH`: `true` se è richiesta l'autenticazione, `false` altrimenti;
       * `EMAIL_SMTP_PORT`: la porta del server SMTP;
       * `EMAIL_SMTP_TLS`: `true` se è richiesto TLS, `false` altrimenti.  
      Potrebbe essere necessario abilitare le applicazioni non sicure sul proprio provider email.  
      **È possibile avviare il server senza configurare SMTP. ma non sarà possibile effettuare l'invio delle e-mail**
   5. Avviare il progetto da un IDE o dal binario compilato.
- Avvio da binario:
   1. Per avviare l'esecuzione da binario, è sufficiente utilzzare il comando `java -jar unimibmodules-1.0.0-SNAPSHOT.jar`;  

Dopo averlo avviato, il progetto sarà raggiungibile all'URL `localhost:5000`.

Attenzione: se dovesse essere necessario riavviare il programma è consigliabile impostare la proprietà `spring.jpa.hibernate.ddl-auto` a `none` oppure `validate`.  
Al primo avvio potrebbero verificarsi errori del tipo `SQLException`; questi sono causati da errori di Hibernate durante la creazione del database. In questo caso è sufficiente lanciare nuovamente l'applicazione. Se, dopo aver avviato il progetto una seconda volta, si dovessero verificare ulteriori problemi di questo tipo, è possibile creare il database usando lo script all'interno della cartella `sql`.  
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
