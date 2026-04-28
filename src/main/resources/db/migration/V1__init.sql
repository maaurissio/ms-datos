CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'tipo_dato') THEN
        CREATE TYPE tipo_dato AS ENUM ('VENTA', 'INVENTARIO', 'FINANCIERO', 'CLIENTE', 'OTRO');
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'estado_carga') THEN
        CREATE TYPE estado_carga AS ENUM ('PENDIENTE', 'PROCESADO', 'ERROR', 'INVALIDO');
    END IF;
END $$;

CREATE TABLE IF NOT EXISTS fuente_dato (
    id UUID PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL,
    sistema_origen VARCHAR(120) NOT NULL,
    descripcion VARCHAR(400),
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS sucursal (
    id UUID PRIMARY KEY,
    codigo VARCHAR(40) NOT NULL,
    nombre VARCHAR(120) NOT NULL,
    ciudad VARCHAR(120) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS dato_consolidado (
    id UUID PRIMARY KEY,
    fuente_dato_id UUID NOT NULL REFERENCES fuente_dato(id),
    sucursal_id UUID NOT NULL REFERENCES sucursal(id),
    tipo_dato tipo_dato NOT NULL,
    periodo DATE NOT NULL,
    payload JSONB NOT NULL,
    fecha_consolidacion TIMESTAMP NOT NULL,
    estado estado_carga NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_dato_consolidado_fuente ON dato_consolidado (fuente_dato_id);
CREATE INDEX IF NOT EXISTS idx_dato_consolidado_sucursal ON dato_consolidado (sucursal_id);
CREATE INDEX IF NOT EXISTS idx_dato_consolidado_periodo ON dato_consolidado (periodo);
CREATE INDEX IF NOT EXISTS idx_dato_consolidado_tipo ON dato_consolidado (tipo_dato);

CREATE TABLE IF NOT EXISTS log_carga (
    id UUID PRIMARY KEY,
    dato_consolidado_id UUID NOT NULL REFERENCES dato_consolidado(id) ON DELETE CASCADE,
    estado estado_carga NOT NULL,
    mensaje VARCHAR(500) NOT NULL,
    fecha TIMESTAMP NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_log_carga_dato ON log_carga (dato_consolidado_id);
CREATE INDEX IF NOT EXISTS idx_log_carga_fecha ON log_carga (fecha);
