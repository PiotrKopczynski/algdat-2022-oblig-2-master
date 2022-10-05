# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Piotr Ludvig Kopczynski, s374151, s374151@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Piotr gjorde alt :). 


# Oppgavebeskrivelse

I oppgave 1 så gikk vi frem ved å ført implementere metoden antall() som bare returnerer variabelen antall. Metoden tom() ble implementert
ved at den bare sjekker om hodevariabelen er null. Hvis ja så er listen tom. I konstruktøren DobbeltLenketListe(T[] a), blir det først sjekket
om listen a er null og passende exception blir gitt. Deretter blir det sjekket om listen a er tom. Hvis ja så gjør funksjonen ikke noe. Hvis
ikke det sistnevnte er tilfellet så finner funksjonen i en while løkke første verdi i a som ikke er null og gjør det til nodene current og hode.
Deretter går en forløkke gjennom resten av lista og gjør om verdier fra a som ikke er null til noder. Løkken holder styr på en current node og
en next node som begge får oppdatert henholdsvis sine neste- og forrige-peker. Etter for-løkken har kjørt ferdig, blir hale variabelen i klassen
DobbeltLenketListe satt til current noden som tilsvarer den siste verdien i a som ikke er null.

I oppgave 2a) brukte jeg StringBuilder og en for-løkke til å implementere toString() og omvendtString(). Før og etter for-løkka legges 
klammeparantesene inn, og inne i løkka legges det til verdiene og kommaene. Metodene sjekker også for det spesialtilfellet der listen er tom.
Det eneste forskjellen mellom metodene er at toString bruker hode og neste pekere mens omvendtString bruker forrige pekere og hale til å 
traversere listen.
2b) ble løst ved å først sjekke om verdien er null ved bruk av requireNonNull metoden fra Objects. Deretter behandles spesialtilfellet der
lista er tom fra før ved at det nye elementet deklareres som hode og hale. Hvis ikke det er tilfellet, så legges elementet inn ved å bare endre
nestepekeren til hale, sette forrigepekeren til det nye elementet, og deklarere det nye elementet som hale. Til slutt inkrementeres antall
og endringer med 1.

I oppgave 3a) ble finnNode() implementert ved å først finne indeksen til midten av lista, og så skrive if else statement det lista blir traversert
ved hjelp av en for løkke enten fra starten av lista til indeks, eller fra slutten av lista til indeks basert på hva som blir raskest. Metoden
hent() blir da implementert enkelt ved å først bruke indeksKontroll() metoden til Liste, og så ved å returnere verdien til resultatet av
finnNode() metoden. oppdater() metoden ble implementert ved at den først sjekker om indeksen er gyldig ved å bruke indeksKontroll(). Deretter
sjekker den om den nye verdien er null og kaster et unntak hvis det er tilfellet. Deretter brukes funksjonen finnNode() til å finne noden på
den gitte indeksen. Den gamle verdien til nodel lagres i variabelen gammel, og så blir verdien til noden endret til den nye verdien. Til slutt
inkrementeres også variabelen endringer med 1.
Oppgave 3b) ble løst ved at først metoden fratilKontroll() ble implementert (Metoden er basert på programkode 1.2.3 a) fra kompendiet). Metoden
blir brukt i starten av subliste() for å kontrollere intervallet. Deretter blir spesialtilfellet der fra=til sjekket og en tom Liste blir
returnert. Det neste steget var å finne den første noden som skal være med i sublisten, og til det ble metoden finnNode() brukt. Deretter brukes
en for-løkke med fra og til til å traversere lista videre og legge inn nye elementer inn i sublisten ved å bruke leggInn() metoden.

I oppgave 4 ble metoden indeksTil() implementert ved at den definerer hode som startnode, og bruker en for-løkke fra 0 til antall til å traversere
hele lista og returnerer indeksen til den første noden fra venstre som inneholder verdi. Dersom funksjonen ikke returnerer noe i for-løkken, 
returneres bare -1. Metoden inneholder() ble enkelt implementert ved at den finner indeksen til verdien ved å bruke indeksTil() metoden, og
returnerer true hvis den ikke er lik -1, og returnerer false hvis den er det.

I oppgave 5, implementeres metoden leggInn ved at den først sjekker om indeksen er gyldig ved bruk av indeksKontroll(). Deretter sjekkes det
om verdien som skal legges inn er null og et unntak kastes hvis den er det. Deretter sjekkes spesialtilfellet det lista er tom fra før. Da brukes
bare metoden leggInn siden den allerede tar hånd om det spesialtilfellet og inkrementerer endringer og antall. De neste to spesialtilfellene
som blir sjekket er om noden legges in forrest eller bakerst i lista. Da forandres bare pekerene til hode/hale og ny node blir laget som peker på
den forrige hodet/halen. Til slutt behandles tilfellet der noden settes inn mellom to andre noder, ved at noden som er på indeksen der den nye 
noden skal bli satt inn finnes med metoden finnNode(). Deretter endres neste pekeren til den forrige noden til den nye noden. Så settes neste og
forrige pekerne til den nye noden, og til slutt endres pekeren til noden vi fant med finnNode() til å peke på den nye noden. endringer og antall
inkrementeres på slutten med 1.


