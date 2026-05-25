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

La API queda disponible en `http://localhost:8083`.

## Swagger UI

La especificación OpenAPI y Swagger UI están siempre disponibles (incluso en producción):

- Swagger UI: `http://localhost:8083/q/swagger-ui/`
- OpenAPI spec: `http://localhost:8083/q/openapi`

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

## Ejecutar en otra maquina

### Opcion 1: Docker Compose (recomendada)

1. Instalar Docker y Docker Compose en la maquina destino.
2. Clonar o copiar la carpeta del proyecto.
3. Ejecutar:

```shell
docker compose up --build
```

La base de datos se crea limpia y las migraciones Flyway se aplican automaticamente.

### Opcion 2: Desarrollo local

1. Instalar Java 21, Maven 3.9+ y PostgreSQL 16.
2. Crear la base de datos `msdatos` en PostgreSQL.
3. Configurar variables de entorno (o usar defaults) segun `application.properties`.
4. Ejecutar:

```shell
mvn quarkus:dev
```

La aplicacion se levanta en `http://localhost:8083` con recarga en caliente.

### Migrar datos existentes a otra maquina

Si ya tienes datos en la maquina original y quieres llevarlos a otra:

**Exportar (maquina origen):**
```shell
docker compose exec postgres pg_dump -U msdatos msdatos > datos.sql
```

**Importar (maquina destino):**
```shell
docker compose cp datos.sql msdatos-postgres:/datos.sql
docker compose exec postgres psql -U msdatos -d msdatos -f /datos.sql
```
