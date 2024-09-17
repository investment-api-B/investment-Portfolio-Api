# Investment Portfolio API

**Investment Portfolio API** to backendowy serwis API, który jest w trakcie rozwoju. Projekt ma na celu uproszczenie zarządzania portfelami inwestycyjnymi, oferując zestaw interfejsów API do zarządzania portfelami i inwestycjami.

## Status Projektu

Prace nad projektem są w toku. Wkrótce zostaną dodane dalsze funkcjonalności i aktualizacje. Śledź postępy projektu, aby być na bieżąco z nowymi wersjami i funkcjami.

## Technologie i Narzędzia

Projekt wykorzystuje szereg nowoczesnych technologii i narzędzi:

- **Spring Boot 3.3.3** - Ułatwia szybki rozwój aplikacji Java z minimalną konfiguracją.
- **Spring Security** - Zapewnia bezpieczny dostęp do API przez autoryzację i uwierzytelnianie.
- **Spring Data JPA** - Uproszcza zarządzanie danymi za pomocą Java Persistence API.
- **Swagger (Springdoc OpenAPI)** - Automatycznie generuje dokumentację API.
- **MapStruct** - Ułatwia mapowanie obiektów DTO w aplikacji.
- **JWT (JSON Web Token)** - Implementuje tokeny JWT dla bezpiecznej autoryzacji.
- **Liquibase** - Zarządza migracjami bazy danych i wersjonowaniem schematów.
- **Hibernate Validator** - Waliduje dane wejściowe.
- **Testcontainers** - Tworzy izolowane środowiska testowe przy użyciu kontenerów Docker.
- **H2 Database** - Używane do celów testowych.
- **MySQL** - Używane jako baza danych produkcyjna.
- **Lombok** - Redukuje kod szablonowy poprzez automatyczne generowanie getterów, setterów i innych metod.
- **Stripe API** - Integruje się z systemem płatności Stripe w celu przetwarzania płatności.
- **SerpApi** - Używane do integracji z wyszukiwarką Google w celu pozyskiwania wyników wyszukiwania.

## Instrukcje Konfiguracyjne

### Wymagania Wstępne

- Java 17
- Docker (jeśli używane do testów)

### Kroki Konfiguracji

1. Sprawdź, czy masz zainstalowany Git: `git --version`
2. Sklonuj repozytorium przy użyciu SSH: `git clone git@github.com:...`
3. Alternatywnie, użyj HTTPS: `git clone https://...`
4. Przejdź do sklonowanego repozytorium: `cd investment-portfolio-api`
5. (Opcjonalnie) Sprawdź status repozytorium: `git status`
