# ms-datos

Microservicio para consolidar datos por sucursal, tipo y periodo, con trazabilidad de cargas.

## Requisitos

- Java 21
- Maven 3.9+
- Docker y Docker Compose

## Levantar con Docker

```shell
docker compose up --build
```

La API queda disponible en `http://localhost:8080`.

## Endpoints principales

- `GET /api/fuentes-datos`
- `GET /api/fuentes-datos/{id}`
- `POST /api/fuentes-datos`
- `PUT /api/fuentes-datos/{id}`
- `DELETE /api/fuentes-datos/{id}`

- `GET /api/sucursales`
- `GET /api/sucursales/{id}`
- `POST /api/sucursales`
- `PUT /api/sucursales/{id}`
- `DELETE /api/sucursales/{id}`

- `GET /api/datos-consolidados`
- `GET /api/datos-consolidados/{id}`
- `POST /api/datos-consolidados`
- `PUT /api/datos-consolidados/{id}`
- `DELETE /api/datos-consolidados/{id}`

- `GET /api/logs-carga`
- `GET /api/logs-carga/{id}`
- `POST /api/logs-carga`
- `PUT /api/logs-carga/{id}`
- `DELETE /api/logs-carga/{id}`

## Configuracion

La conexion a PostgreSQL se controla via variables de entorno:

- `DB_HOST` (default: `localhost`)
- `DB_PORT` (default: `5432`)
- `DB_NAME` (default: `msdatos`)
- `DB_USER` (default: `msdatos`)
- `DB_PASSWORD` (default: `msdatos`)

Flyway se ejecuta automaticamente al iniciar la aplicacion.
