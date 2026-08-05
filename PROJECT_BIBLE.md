# Waiting Room — Project Bible

> Documento di riferimento ufficiale per visione, UX, design, sviluppo e uso dell’AI.
>
> Ultimo aggiornamento: 5 agosto 2026

---

## 1. Identità del progetto

**Nome:** Waiting Room  
**Slogan:** *Le idee possono aspettare.*  
**Tipo:** app Android personale  
**Tecnologie principali:** Kotlin, Jetpack Compose, Material 3, Room  
**Distribuzione:** uso personale; non è prevista la pubblicazione sul Play Store.

Waiting Room è una piccola casa digitale in cui i pensieri possono aspettare al sicuro.

Non nasce come task manager, agenda, knowledge base o alternativa a Google Keep, Notion o Obsidian. Il suo scopo è più semplice: permettere di togliere rapidamente un’idea dalla testa senza doverla sviluppare, organizzare o pianificare subito.

Principio centrale:

> Non è il momento di pensarci, ma è abbastanza importante da conservarla.

L’esperienza deve generare soprattutto **sollievo**:

> L’idea è al sicuro. Posso tornare a quello che stavo facendo.

---

## 2. Principi di prodotto

1. **Cattura immediata**  
   Salvare un pensiero deve richiedere pochissimi secondi.

2. **Nessun obbligo di azione immediata**  
   Un’idea può restare in attesa senza diventare automaticamente un’attività.

3. **Riduzione del carico mentale**  
   L’app deve semplificare, non creare nuove responsabilità.

4. **Metafora coerente**  
   L’interfaccia rappresenta una casa tranquilla: Reception, porta, Studio e Archivio.

5. **Calma visiva**  
   Colori tenui, spazio, animazioni discrete e assenza di elementi aggressivi.

6. **Affidabilità prima delle funzioni extra**  
   Le idee non devono andare perse.

7. **Semplicità personale**  
   L’app è costruita per Claudio, non per coprire tutti i possibili casi d’uso di un prodotto pubblico.

### Domanda di controllo per ogni nuova funzione

> Questa funzione riduce davvero il carico mentale oppure trasforma Waiting Room in un’altra app di produttività?

Se prevale la seconda risposta, la funzione probabilmente non appartiene al progetto.

---

## 3. Metafora narrativa

### 3.1 Reception

È il luogo in cui si consegna un pensiero.

Non è un semplice form di inserimento. Per questo l’azione principale si chiama **Conserva**, non “Salva”.

Obiettivi:

- cattura rapida;
- sensazione di accoglienza;
- nessuna richiesta di classificare o organizzare subito;
- accesso diretto alla Waiting Room.

### 3.2 Porta e transizione

La porta rappresenta il passaggio dell’idea dalla mente a un luogo sicuro.

Flusso narrativo previsto:

1. si scrive un’idea;
2. si preme **Conserva**;
3. l’idea entra nello Studio;
4. la porta si chiude;
5. l’utente riceve una conferma rassicurante.

Messaggio coerente con il concept:

> La tua idea è al sicuro.

L’animazione deve essere breve, morbida e non rallentare l’uso.

### 3.3 Waiting Room / Studio

È la stanza in cui le idee ancora vive aspettano.

Non deve sembrare una lista tradizionale. Le idee sono mostrate come post-it su una bacheca di sughero, all’interno di un ambiente domestico caldo e tranquillo.

L’utente può:

- osservare le idee in attesa;
- cercarne una;
- aprirla;
- aggiungere appunti;
- lasciarla dov’è;
- rimandarla;
- segnarla come fatta;
- eliminarla.

### 3.4 Dettaglio idea / Bottom sheet

Il bottom sheet serve a decidere il destino dell’idea, non solo a modificare dati.

Contenuti principali:

- titolo;
- data e ora;
- elenco degli appunti;
- campo per aggiungere un appunto;
- azioni **Fatto**, **Rimanda**, **Elimina**.

### 3.5 Archivio

L’Archivio non è il cestino.

Raccoglie le idee che hanno completato il proprio ciclo. Nel concept è rappresentato come uno schedario, per mantenere la metafora fisica della casa.

---

## 4. Mockup come fonte di verità

Il mockup approvato è la **specifica visiva ufficiale** del progetto, non una semplice ispirazione.

Quando il comportamento dell’app è corretto ma l’aspetto differisce dal mockup, si deve avvicinare la UI al mockup senza rompere la logica già funzionante.

Priorità di fedeltà:

1. struttura e gerarchia delle schermate;
2. proporzioni e spaziature;
3. palette e atmosfera;
4. tipografia;
5. forme, ombre e raggi degli angoli;
6. illustrazioni e ambientazione;
7. microanimazioni.

La fedeltà deve restare compatibile con:

- leggibilità;
- accessibilità di base;
- tastiera aperta;
- diverse altezze dello schermo;
- dispositivo principale Samsung SM-S948B in verticale.

---

## 5. Sistema visivo

### 5.1 Atmosfera

Parole chiave:

- calma;
- casa;
- attesa;
- sicurezza;
- calore;
- ordine senza rigidità;
- spazio e respiro.

Da evitare:

- look da dashboard aziendale;
- colori saturi o aggressivi;
- troppe icone;
- densità elevata;
- gamification;
- interfaccia da task manager;
- animazioni vistose o lente.

### 5.2 Palette di riferimento

Palette estratta dal mockup:

| Ruolo | Colore |
|---|---|
| Verde principale | `#A8B69A` |
| Crema caldo | `#EDE6D5` |
| Sfondo quasi bianco | `#F7F3EA` |
| Neutro caldo | `#D9D4C7` |
| Azzurro tenue | `#AEC6D8` |

Altri colori funzionali devono restare tenui e coerenti con l’atmosfera.

### 5.3 Tipografia

La tipografia deve essere semplice, leggibile e morbida.

Gerarchia prevista:

- titolo grande;
- titolo medio;
- testo normale;
- testo secondario.

Il titolo “Waiting Room” deve essere immediatamente riconoscibile ma non aggressivo.

### 5.4 Iconografia

Icone previste:

- microfono;
- aggiungi;
- fatto;
- rimanda;
- archivio;
- ricerca;
- lampadina;
- freccia indietro.

Usare icone coerenti tra loro. Evitare emoji come elementi definitivi dell’interfaccia.

---

## 6. Specifiche delle schermate

### 6.1 Reception

Elementi obbligatori:

- sfondo caldo quasi bianco;
- scena domestica d’ingresso nella parte superiore;
- porta verde oliva chiusa;
- lampada sospesa;
- tavolino o consolle;
- quadro botanico;
- piante;
- titolo “Waiting Room”;
- sottotitolo “Le idee possono aspettare.”;
- campo multilinea con testo “Cosa ti è venuto in mente?”;
- icona microfono in basso a destra nel campo;
- pulsante verde **Conserva**;
- card inferiore con accesso alla Waiting Room;
- contatore dinamico: “1 idea in attesa” / “N idee in attesa”.

Comportamento:

- il pulsante è disabilitato quando il testo è vuoto;
- il salvataggio usa la logica Room esistente;
- dopo il salvataggio il campo viene gestito coerentemente con il flusso approvato;
- tastiera e contenuto non devono sovrapporsi in modo inutilizzabile.

### 6.2 Waiting Room / Studio

Elementi obbligatori:

- stanza calda e luminosa;
- grande bacheca di sughero;
- post-it color pastello;
- leggere rotazioni dei post-it;
- ombre morbide;
- titolo o navigazione superiore coerente;
- accesso all’Archivio;
- barra di ricerca inferiore.

Ogni post-it mostra almeno:

- titolo dell’idea;
- data;
- numero di appunti.

Interazioni previste:

- tocco per aprire il dettaglio;
- swipe verso destra: **Fatto**;
- swipe verso sinistra: **Rimanda**;
- animazioni semplici, comprensibili e reversibili finché l’azione non viene confermata.

### 6.3 Bottom sheet

Elementi obbligatori:

- foglio con angoli superiori arrotondati;
- maniglia superiore;
- titolo;
- data;
- sezione **Appunti**;
- card separate per gli appunti;
- menu contestuale per singolo appunto quando previsto;
- campo “Aggiungi un appunto…”;
- pulsante aggiungi;
- azioni inferiori **Fatto**, **Rimanda**, **Elimina**.

Il bottom sheet deve emergere sopra lo Studio con sfondo oscurato e sfocato solo se tecnicamente sostenibile senza compromettere prestazioni e leggibilità.

### 6.4 Archivio

Elementi obbligatori:

- titolo “Archivio”;
- navigazione indietro;
- ricerca;
- metafora visiva dello schedario;
- elenco delle idee completate;
- data e numero di appunti;
- apertura del dettaglio quando utile.

---

## 7. Animazioni

Le animazioni sono parte della narrazione, ma non devono ostacolare l’uso.

### Animazione principale

1. l’utente scrive un’idea;
2. preme **Conserva**;
3. compare il post-it;
4. la porta si apre o rappresenta il passaggio;
5. il post-it entra nello Studio;
6. la porta si chiude;
7. compare la conferma “La tua idea è al sicuro.”

### Microanimazioni

- comparsa dei post-it;
- apertura e chiusura del bottom sheet;
- aggiunta di un appunto;
- swipe Fatto/Rimanda;
- pressione dei pulsanti;
- apertura dello schedario, se realizzabile senza complessità eccessiva.

Linee guida:

- durate brevi;
- easing morbido;
- niente rimbalzi eccessivi;
- niente animazioni decorative continue;
- rispettare la preferenza di sistema per la riduzione delle animazioni quando possibile.

---

## 8. Funzionalità principali

Stato funzionale rilevato e da preservare:

- creazione di un’idea;
- salvataggio locale con Room;
- conteggio delle idee in attesa;
- visualizzazione delle idee sulla bacheca;
- apertura del dettaglio;
- aggiunta di appunti;
- ricerca;
- rimando;
- completamento;
- archivio;
- eliminazione.

Queste funzioni non devono essere riscritte durante il redesign se non è strettamente necessario.

### Priorità futura personale

- protezione dei dati;
- esportazione e importazione;
- eventuale backup personale semplice.

Non sono priorità:

- account utenti;
- social;
- analytics;
- monetizzazione;
- requisiti Play Store;
- architettura server complessa.

---

## 9. Architettura e qualità tecnica

Principi:

- Kotlin e Jetpack Compose;
- Material 3;
- Room per persistenza locale;
- ViewModel e repository;
- Flow/StateFlow dove già adottati;
- componenti Compose piccoli e riutilizzabili;
- nessuna complessità non necessaria;
- dipendenze esterne solo quando indispensabili;
- logica separata dalla presentazione;
- niente riscritture ampie senza motivazione.

Struttura UI desiderata, adattabile al progetto reale:

```text
ui/
├── reception/
├── studio/
├── idea/
├── archive/
├── components/
├── animations/
└── theme/
```

Questa struttura è una direzione, non un ordine di refactoring immediato. Prima di spostare file esistenti va valutato il beneficio reale.

---

## 10. Metodo di sviluppo

### Ruoli

**Claudio**

- decide l’esperienza e approva il risultato visivo;
- testa l’app sul dispositivo reale;
- conferma ogni fase prima di procedere.

**ChatGPT**

- agisce come lead developer e project manager;
- analizza il contesto prima di proporre modifiche;
- prepara il piano e indica i file da modificare;
- revisiona risultati, errori e differenze dal mockup;
- prepara prompt precisi per l’Agente AI di Android Studio.

**Agente AI di Android Studio**

- esegue compiti tecnici circoscritti;
- modifica solo i file autorizzati;
- compila e segnala ciò che ha cambiato;
- non decide autonomamente l’architettura o il prodotto.

### Regola obbligatoria prima del codice

Per ogni nuova funzionalità o intervento significativo:

1. analizzare lo stato attuale;
2. spiegare il piano;
3. elencare i file previsti;
4. indicare rischi ed effetti collaterali;
5. attendere la conferma di Claudio;
6. solo dopo generare o applicare modifiche.

### Dimensione dei task

Preferire task piccoli e verificabili.

Esempi corretti:

- creare il composable dell’illustrazione Reception;
- rifinire solo il campo di inserimento;
- creare un componente StickyNote;
- implementare soltanto l’animazione di comparsa;
- uniformare i colori del bottom sheet.

Esempi da evitare:

- “rifai tutta l’app”;
- “rendi tutto identico al mockup” in un unico intervento;
- riscrivere centinaia di righe senza necessità;
- modificare database, Gradle e UI insieme.

---

## 11. Regole per i prompt dell’Agente AI

Ogni prompt deve specificare:

- obiettivo unico;
- file consentiti;
- file vietati;
- comportamento da preservare;
- riferimento visivo;
- criteri di accettazione;
- obbligo di compilazione;
- riepilogo finale delle modifiche.

Template:

```text
CONTEXT
Waiting Room is a personal Android app built with Kotlin, Jetpack Compose and Room.
The approved mockup is the visual source of truth.

TASK
[One narrowly defined task.]

ALLOWED FILES
[List exact files or a small area.]

DO NOT MODIFY
- database entities
- DAOs
- repositories
- ViewModel logic
- Gradle and signing
- unrelated screens

PRESERVE
[List existing behavior.]

VISUAL TARGET
[Describe exact mockup section.]

ACCEPTANCE CRITERIA
[Measurable checks.]

VALIDATION
- run Gradle sync when relevant
- compile the app
- fix only errors caused by this task
- summarize modified files
- do not continue with another screen
```

---

## 12. Workflow Git e sicurezza

Prima di un intervento importante:

```bash
git status
git add .
git commit -m "checkpoint: before <task>"
git push
```

Dopo una modifica approvata:

```bash
git add .
git commit -m "ui: <descrizione breve>"
git push
```

Regole:

- un commit per modifica coerente;
- evitare commit che mescolano UI, database e configurazione;
- non salvare chiavi API, password, keystore o credenziali;
- controllare sempre il diff prima del commit;
- in caso di risultato errato, tornare al checkpoint invece di accumulare correzioni casuali.

---

## 13. Roadmap

### Fase 0 — Stabilità

- verificare che il progetto compili;
- preservare il salvataggio Room;
- controllare firma e configurazione Gradle;
- creare checkpoint Git affidabili.

### Fase 1 — Reception fedele al mockup

- scena domestica;
- proporzioni;
- campo idea;
- pulsante Conserva;
- card di accesso alla Waiting Room;
- adattamento tastiera e schermi.

### Fase 2 — Studio fedele al mockup

- stanza;
- bacheca;
- post-it;
- ricerca;
- accesso Archivio.

### Fase 3 — Bottom sheet

- struttura;
- appunti;
- azioni;
- microanimazioni.

### Fase 4 — Archivio

- schedario;
- lista;
- ricerca;
- dettaglio.

### Fase 5 — Interazioni

- swipe Fatto/Rimanda;
- animazione porta;
- ingresso del post-it;
- conferma “La tua idea è al sicuro.”

### Fase 6 — Affidabilità personale

- migrazioni Room sicure;
- esportazione/importazione;
- test del ripristino;
- rifinitura finale.

---

## 14. Criteri di accettazione generali

Una modifica è approvabile quando:

- l’app compila;
- la logica precedente continua a funzionare;
- il risultato è visivamente più vicino al mockup;
- non introduce dipendenze inutili;
- non modifica file non necessari;
- funziona sul Samsung SM-S948B;
- resta utilizzabile con tastiera aperta;
- non perde dati esistenti;
- Claudio approva lo screenshot sul dispositivo reale.

---

## 15. Stato del progetto al 5 agosto 2026

### Confermato visivamente

- Reception esistente con inserimento idea e contatore;
- bacheca con almeno un post-it;
- ricerca nello Studio;
- bottom sheet con titolo, data, ora, appunti e azioni;
- progetto aperto e avviato su Samsung SM-S948B;
- repository GitHub pubblico sulla branch `master`.

### Da verificare tecnicamente prima di modifiche ampie

- stato esatto della compilazione dopo le ultime modifiche dell’Agente;
- tre warning segnalati dall’Agente;
- diff non ancora approvato di `WaitingRoomScreen.kt` e `ui/theme/Color.kt`;
- coerenza del file `Implementation Plan` creato o modificato dall’Agente;
- assenza di regressioni nella persistenza Room;
- stato della configurazione di firma.

### Prossima decisione operativa

Prima di proseguire con il redesign:

1. lasciare terminare l’Agente;
2. non accodare altri prompt;
3. compilare e avviare l’app;
4. acquisire screenshot della Reception;
5. controllare il riepilogo e i warning;
6. confrontare il risultato con il mockup;
7. decidere se accettare, correggere o ripristinare il checkpoint.

---

## 16. Regola finale

Waiting Room deve sembrare una piccola casa tranquilla, non uno strumento che chiede produttività.

Ogni scelta tecnica e visiva deve proteggere questa promessa:

> Le idee possono aspettare.