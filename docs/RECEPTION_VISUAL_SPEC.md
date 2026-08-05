# Reception — Specifica visiva e funzionale

> Questa specifica sostituisce le precedenti interpretazioni generiche della Reception.
> Il mockup approvato è la fonte di verità. L'obiettivo non è “ispirarsi” al mockup, ma riprodurne struttura, proporzioni, atmosfera e comportamento con la massima fedeltà ragionevolmente ottenibile.

## 1. Diagnosi del risultato attuale

Il risultato generato dall'Agente non è approvato come versione finale perché:

- la scena domestica è stata ridotta a forme Canvas troppo semplici;
- il risultato appare piatto e datato rispetto al mockup illustrato;
- i componenti conservano un aspetto Material generico;
- campo, card e spaziature sono sovradimensionati;
- il microfono è soltanto decorativo;
- la schermata non produce la stessa impressione di profondità, calore e qualità del mockup.

## 2. Strategia corretta

### 2.1 Separare illustrazione e interfaccia

La Reception deve essere composta da:

1. **illustrazione statica ad alta qualità** della stanza;
2. **componenti Compose nativi** per titolo, sottotitolo, input, microfono, pulsante e card inferiore.

La scena principale non deve essere ricostruita con primitive Canvas elementari. Per questa app personale, un asset raster/WebP ad alta risoluzione è la soluzione più semplice e più fedele.

### 2.2 Cosa deve restare nativo

Devono restare elementi Compose reali e accessibili:

- titolo;
- sottotitolo;
- campo multilinea;
- pulsante del microfono;
- pulsante Conserva;
- card Waiting Room;
- contatore dinamico;
- navigazione.

Non incorporare questi elementi nell'immagine di sfondo.

## 3. Griglia di riferimento

La misurazione è stata ricavata dal riquadro Reception del mockup, normalizzato su una viewport di circa **360 × 744 dp**.

### 3.1 Proporzioni verticali

| Area | Posizione indicativa |
|---|---:|
| Illustrazione domestica | 0–41% altezza |
| Titolo | 44–49% |
| Sottotitolo | 50–53% |
| Campo idea | 56–70% |
| Pulsante Conserva | 73–80% |
| Card Waiting Room | 83–94% |
| Margine inferiore | 6% circa |

Tolleranza iniziale: massimo ±3% della viewport per il posizionamento verticale.

### 3.2 Dimensioni su larghezza 360 dp

| Elemento | Specifica iniziale |
|---|---:|
| Margine laterale comune | 24–26 dp |
| Illustrazione | piena larghezza, circa 290–305 dp di altezza |
| Titolo | 32–34 sp |
| Sottotitolo | 15–16 sp |
| Campo idea | 108–112 dp di altezza |
| Pulsante Conserva | 52–54 dp di altezza |
| Card Waiting Room | 76–80 dp di altezza |
| Raggio campo/card | 16–18 dp |
| Raggio pulsante | 12–14 dp |

Le dimensioni devono adattarsi tramite `BoxWithConstraints`, pesi o calcoli proporzionali; non fissare l'intera schermata a una sola risoluzione.

## 4. Illustrazione domestica

### 4.1 Composizione obbligatoria

- parete crema calda;
- pavimento chiaro con battiscopa;
- lampada sospesa a sinistra;
- quadro botanico sotto la lampada;
- tavolo in legno con vaso e libro;
- porta verde oliva leggermente a destra del centro;
- pianta grande all'estrema destra;
- luce naturale morbida;
- ombre delicate e realistiche;
- resa illustrata/editoriale, non fotorealismo duro e non flat design.

### 4.2 Divieti

- nessuna ricostruzione con rettangoli e cerchi elementari visibilmente piatti;
- nessuna icona o emoji al posto degli arredi;
- nessun effetto cartoon infantile;
- nessun gradiente Material generico;
- nessun testo o controllo incorporato nell'asset.

### 4.3 Asset

Nome consigliato:

```text
app/src/main/res/drawable-nodpi/reception_room.webp
```

Caratteristiche consigliate:

- larghezza almeno 1080 px;
- rapporto approssimativo 1.24:1;
- compressione WebP senza artefatti visibili;
- `ContentScale.Crop` o `FillWidth` valutato sul dispositivo reale;
- allineamento superiore;
- evitare ritagli della lampada, della porta e della pianta.

## 5. Tipografia e colori

### 5.1 Colori principali

| Elemento | Colore indicativo |
|---|---|
| Sfondo generale | `#F7F3EA` |
| Titolo | quasi nero caldo, circa `#191812` |
| Sottotitolo | verde-grigio scuro, circa `#6D7566` |
| Verde azione | `#A8B69A` |
| Bordo input/card | `#D9D4C7` |
| Testo secondario | `#77756F` |
| Superficie input/card | bianco crema, non bianco puro |

Il titolo della Reception nel mockup è scuro, non verde acceso.

### 5.2 Gerarchia

- **Waiting Room:** peso Medium/Semibold, non Bold pesante;
- sottotitolo: Regular, altezza di riga compatta;
- placeholder: Regular, contrasto sufficiente ma discreto;
- testo pulsante: Medium;
- card inferiore: titolo Medium/Semibold e contatore Regular.

## 6. Campo di inserimento

Specifiche:

- altezza 108–112 dp;
- larghezza disponibile meno 24–26 dp per lato;
- bordo sottile 1 dp;
- ombra molto leggera, non una grande elevation Material;
- padding interno 16 dp;
- placeholder allineato in alto a sinistra;
- microfono allineato in basso a destra;
- area tattile del microfono almeno 48 × 48 dp;
- nessuna superficie bianca brillante;
- nessun bordo spesso.

Il campo deve espandersi o scorrere in modo controllato se il testo cresce, senza alterare completamente la composizione.

## 7. Microfono — requisito funzionale

Il microfono non è decorativo.

Comportamento richiesto:

1. tocco sull'icona;
2. richiesta del permesso microfono al primo utilizzo;
3. avvio del riconoscimento vocale in italiano;
4. trascrizione nel campo dell'idea;
5. se esiste già testo, il nuovo risultato viene aggiunto separato da uno spazio;
6. durante l'ascolto l'icona cambia stato in modo discreto;
7. secondo tocco o risultato finale interrompe l'ascolto;
8. errore o indisponibilità mostrati con messaggio breve e comprensibile;
9. nessun ascolto continuo in background.

Preferire il riconoscimento sul dispositivo quando disponibile, con fallback al riconoscitore di sistema.

## 8. Pulsante Conserva

- altezza 52–54 dp;
- larghezza uguale al campo;
- riempimento verde `#A8B69A` quando attivo;
- stato disabilitato tenue ma leggibile;
- testo centrato 16 sp Medium;
- niente gradiente evidente;
- niente ombra pesante;
- pressione con feedback minimo.

## 9. Card Waiting Room

- altezza 76–80 dp;
- stessa larghezza del campo;
- superficie crema molto chiara;
- bordo sottile e ombra minima;
- icona lampadina outline a sinistra;
- titolo “Waiting Room”;
- contatore dinamico sotto il titolo;
- chevron a destra;
- intera card cliccabile;
- nessun grande spazio vuoto interno.

## 10. Stato della barra di sistema

La scena può estendersi sotto la status bar per avvicinarsi al mockup, purché:

- le icone di sistema restino leggibili;
- gli elementi interattivi rispettino gli inset;
- non vengano disegnati manualmente ora e batteria dentro l'asset.

## 11. Comportamento con tastiera

- il campo deve restare visibile quando riceve focus;
- il pulsante Conserva deve essere raggiungibile;
- la schermata può diventare scrollabile con IME aperta;
- non comprimere l'illustrazione fino a deformarla;
- evitare grandi salti di layout.

## 12. File previsti

### Intervento visivo

- `app/src/main/java/com/example/ui/screens/WaitingRoomScreen.kt`
- `app/src/main/java/com/example/ui/theme/Color.kt` solo se mancano token realmente necessari
- `app/src/main/res/drawable-nodpi/reception_room.webp`
- `app/src/main/res/values/strings.xml`

### Microfono

- `app/src/main/AndroidManifest.xml`
- `app/src/main/java/com/example/ui/screens/WaitingRoomScreen.kt`
- facoltativo e preferibile: `app/src/main/java/com/example/ui/components/SpeechInputController.kt`
- `app/src/main/res/values/strings.xml`

Non modificare database, DAO, repository, ViewModel, Gradle, firma, Waiting Room/Studio, bottom sheet o Archivio.

## 13. Ordine dei task

1. creare o inserire l'asset illustrato ad alta qualità;
2. sostituire il Canvas piatto con l'asset;
3. riallineare dimensioni e spaziature;
4. rifinire input, pulsante e card;
5. compilare e confrontare lo screenshot;
6. implementare il microfono come task separato;
7. testare permessi, dettatura e salvataggio;
8. approvare solo dopo confronto sul dispositivo reale.

## 14. Definition of Done

La Reception è conclusa solo quando:

- non sembra una schermata Material generica;
- la scena domestica ha profondità e qualità paragonabili al mockup;
- struttura e proporzioni sono riconoscibilmente le stesse;
- il microfono trascrive realmente la voce;
- salvataggio Room e navigazione non hanno regressioni;
- l'app compila senza errori;
- funziona sul Samsung SM-S948B;
- Claudio approva lo screenshot finale.