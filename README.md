
# Task Tracker CLI

Questo è un semplice progetto per il monitoraggio delle spese per gestire le tue finanze.

## Risorse usate
Articolo che descrive le funzionalità, obiettivi del progetto:

https://roadmap.sh/projects/expense-tracker

## Requisiti

- Eclipse
- Java 11 o superiore
- Maven

## Installazione

1. Clona il repository:

   git clone https://github.com/Vito-28/ExpenseTracker.git
   
   cd ExpenseTracker

2. Compila il progetto:

   mvn clean install

3. Esegui l'applicazione:

   mvn exec:java

## Comandi

- **Aggiungi una spesa con una descrizione e un importo**:

  $ expense-tracker add --description "Lunch" --amount 20
  
- **Aggiorna o Elimina la spesa**:

  $ expense-tracker update 1 --description "Test Update" --amount 30
  
  $ expense-tracker delete --id 2

- **Visualizza tutte le spese**:

  $ expense-tracker list
  
- **Visualizza un riepilogo di tutte le spese**:
  
  $ expense-tracker summary

- **Visualizza un riepilogo delle spese per un mese specifico (dell'anno in corso)**:

  $ expense-tracker summary --month 8
  
- **Comando per terminare l'applicazione**:
  
  $ expense-tracker exit

## Come funziona

Ogni spesa ha una descrizione e un importo, una data. Le spese vengono salvate in un file JSON.

## NOTA: 

Il codice è ancora in fase di miglioramento.


## Licenza

Questo progetto è distribuito sotto la Licenza MIT.
