# L'Assistente del Ristorante



Projekt je namenjen razvoju sistema za upravljanje restavracij, ki restavracijskim vodjem omogoča učinkovito obvladovanje rezervacij, menijev in zaposlenih. Za restavracije je ključno, da ponujajo odlično storitev in kakovostne jedi, hkrati pa učinkovito upravljajo z viri in osebjem. Rešitev bo omogočila enostavno spremljanje in načrtovanje, kar bo prispevalo k večji učinkovitosti in zadovoljstvu strank.


## Funkcionalnosti

- **Upravljanje rezervacij**: 
  - Ogled vseh rezervacij.
  - Ustvarjanje, posodabljanje in brisanje rezervacij.
  - Dodelitev zaposlenih določenim rezervacijam.

- **Upravljanje menijev**: 
  - Ogled trenutnega menija.
  - Dodajanje, posodabljanje in odstranjevanje jedi iz menija.
  - Prilagajanje menijske ponudbe glede na sezonske sestavine in trende.

- **Upravljanje zaposlenih**: 
  - Ustvarjanje in posodabljanje profilov zaposlenih.
  - Spremljanje uspešnosti zaposlenih in potreb po usposabljanju.


## Arhitektura

### Frontend
- **Tehnologija**: Angular
- **Namen**: Uporabniški vmesnik za upravljanje rezervacij, jedilnikov in osebja

### Backend mikrostoritve
1. **Service za upravljanje rezervacij** 

2. **Service za upravljanje jedilnika**

3. **Service za upravljanje osebja**

### Zunanje integracije
- **Service za upravljanje jedilnika** uporablja [calorieninjas.com API](https://calorieninjas.com) za pridobivanje informacij o hranilnih vrednostih, kot je število kalorij za jedi na jedilniku.

## Tehnične podrobnosti

### Kontejnerizacija in nastavitev
- **Docker**: Vse komponente so kontejnerizirane z uporabo Dockerja.
- **Kubernetes**: Orkestrira namestitev storitev in zagotavlja skalabilnost.
- **Azure**: Rešitev je nameščena na Microsoft Azure platformi.
- **Horizontalno skaliranje podov (HPA)**: Samodejno skaliranje storitev za obvladovanje nihanj v prometu.

### CI/CD Pipeline
- **GitHub Actions**: Avtomatizacija procesa gradnje in nameščanja.
- **Docker Hub**: Shramba Docker slik za aplikacijo.
- **Azure Kubernetes Service (AKS)**: Samodejna namestitev in upravljanje Kubernetes grozdov na Azure.

### Dokumentacija in spremljanje
- **OpenAPI specifikacija**: Na voljo na `/documentation` končni točki za dokumentacijo API-ja.
- **Health checks**: Implementirani so za spremljanje zdravja in stanja sistema.

## Tehnologije

  - **Java**
  - **KumuluzEE**
   - **Angular**
  - **Docker**
  - **Kubernetes**
  - **PostgreSQL**
    
# Nastavitev projekta

### Kloniranje repozitorija
```bash
git clone https://github.com/mkosh10/FRI-PRPO-L-Assistente-del-Ristorante.git
cd FRI-PRPO-L-Assistente-del-Ristorante
```

### Namestitev odvisnosti

#### Frontend
Pojdite v direktorij frontend in namestite potrebne odvisnosti:

```bash
cd frontend
npm install
```

#### Backend
Pojdite v direktorij LAssistenteDelRistorante in zgradite backend storitve z Mavenom::

```bash
cd LAssistenteDelRistorante
mvn clean install
```
### Nastavitev za lokalni razvoj

#### Razvoj Frontend-a
Za zagon frontend-a lokalno uporabite naslednji ukaz:

```bash
cd frontend
ng serve
```
Aplikacija bo dostopna na naslovu ```http://localhost:4200```

#### Razvoj Backend-a
Za zagon backend-a lokalno, pakirajte backend storitev in zaženite API:

```bash
cd backend
mvn clean package
java -jar api/target/api-1.0-SNAPSHOT.jar
```
API bo dostopen na naslovu ```http://localhost:8080```

#### Zagon z Dockerjem
Za gradnjo in zagon vseh storitev z uporabo Docker Compose uporabite naslednji ukaz:

```bash
docker-compose up
```
#### Zagon z Kubernetesom
Uporabite Kubernetes konfiguracije za zagon storitev:

```bash
kubectl apply -f kubernetes/deployment.yml
```

#### Preverite implementacijo
Za preverjanje, ali je bila implementacija uspešna, preverite stanje podov in storitev:

```bash
kubectl get pods
kubectl get services
```











