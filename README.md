# Costo Grupo 5 – Taller 4: Refactorización SOLID con Factory y Observer

**Universidad Agraria del Ecuador · Programación 3 · 4.º semestre B · Período 2026–2027 CII**
**Docente:** Ing. Hamilton Francisco Villamar Barros, MSc.
**Integrantes:** Diana Mosquera, Kerly Cabello, Allison Cobos, Mariela Cedeño, Pedro Holguin

## Roles del Taller 4
| Rol | Integrante(s) |
|---|---|
| Responsable de dominio y fábrica | _(completar)_ |
| Responsable de alertas | _(completar; con 5 integrantes se dividen entre dos personas)_ |
| Responsable de pruebas y documentación | _(completar)_ |

## Paquetes
- `dominio`: records, interfaz sellada `Producto`, `TipoProducto`, `LecturaFrio`, `Trazable`.
- `reglas`: estrategias del Taller 3 (`EstrategiaCosto`, `Estrategias`).
- `fabrica`: `FabricaProductos`, `DatosProducto`.
- `alertas`: `TipoAlerta`, `Alerta`, `ReglaAlerta`, `Reglas`, `ObservadorAlertas`, `MotorAlertas`, `ConfiguracionFrio`, `FabricaObservadores`.

Las tres alertas del tema: `TEMPERATURA_FUERA_RANGO`, `RUPTURA_PROLONGADA`, `PUERTA_ABIERTA`.

## Diagnóstico previo (paso 2)
| Pregunta | Respuesta del grupo |
|---|---|
| ¿Qué clase tiene más de un motivo para cambiar? | _(completar con su código real del Taller 3; ej. `Main` mezclaba creación de objetos, cálculo y salida por consola)_ |
| ¿Dónde se usa `new` directamente sobre los records? | _(completar; ej. en `Main` y en las pruebas)_ |
| ¿Hay umbrales o tarifas como números sueltos? | _(completar; ej. el IVA 0,15 y la tarifa de jornal 18,50)_ |
| ¿Hay herencia solo para reutilizar código? | _(completar)_ |

## Tabla SOLID
| Principio | Clase del proyecto | Cómo se cumple |
|---|---|---|
| S – Responsabilidad única | `Reglas` / `MotorAlertas` | `Reglas` solo decide si hay alerta; `MotorAlertas` solo coordina; la salida la hacen los observadores. |
| O – Abierto/cerrado | `MotorAlertas` | Se agregan reglas y observadores con `agregarRegla` y `suscribir` sin modificar el motor. |
| L – Sustitución de Liskov | `Producto` (`FlorCorte`, `LotePescado`, `InsumoFrio`) | Cualquier record sellado puede usarse donde se espera un `Producto`. |
| I – Segregación de interfaces | `ReglaAlerta`, `ObservadorAlertas` | Interfaces funcionales de un solo método. |
| D – Inversión de dependencias | `MotorAlertas` | Depende de las interfaces `ReglaAlerta` y `ObservadorAlertas`, no de implementaciones concretas. |

## Justificación de umbrales (`ConfiguracionFrio`)
| Umbral | Valor | Justificación |
|---|---|---|
| Temperatura mínima / máxima | 0 °C / 4 °C | _(completar con la fuente consultada; rango típico de refrigeración de pescado y flor)_ |
| Minutos de ruptura | 30 | _(completar)_ |
| Minutos de puerta abierta | 5 | _(completar)_ |

Todas las reglas usan comparación estricta: en el umbral exacto **no** se dispara la alerta.

## Pruebas
`mvn clean verify` → 14 pruebas JUnit 5 (4 de fábrica, 10 de alertas y motor).

## Declaración de uso de IA
Se usó IA para generar la estructura base del código y las pruebas del Taller 4. El grupo debe verificar que compila, ejecutar `mvn clean verify` y poder explicar el motor y la fábrica. _(completar con lo que verificó cada integrante)_
