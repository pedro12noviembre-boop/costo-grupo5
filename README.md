# Costo Grupo 5 – Taller 4: Refactorización SOLID con Factory y Observer

**Universidad Agraria del Ecuador · Programación 3 · 4.º semestre B · Período 2026–2027 CII**
**Docente:** Ing. Hamilton Francisco Villamar Barros, MSc.
**Integrantes:** Diana Mosquera, Kerly Cabello, Allison Cobos, Mariela Cedeño, Pedro Holguin
**Tema del grupo:** G05 CadenaFria (monitoreo de cámaras de frío)

## Roles del Taller 4
| Rol | Integrante(s) |
|---|---|
| Responsable de dominio y fábrica | Kerly Cabello |
| Responsable de alertas | Diana Mosquera y Pedro Holguin |
| Responsable de pruebas y documentación | Mariela Cedeño y Allison Cobos |

## Paquetes
- `dominio`: records, interfaz sellada `Producto`, `TipoProducto`, `LecturaFrio`, `Trazable`.
- `reglas`: estrategias del Taller 3 (`EstrategiaCosto`, `Estrategias`).
- `fabrica`: `FabricaProductos`, `DatosProducto`.
- `alertas`: `TipoAlerta`, `Alerta`, `ReglaAlerta`, `Reglas`, `ObservadorAlertas`, `MotorAlertas`, `ConfiguracionFrio`, `FabricaObservadores`.

Las tres alertas del tema: `TEMPERATURA_FUERA_RANGO`, `RUPTURA_PROLONGADA`, `PUERTA_ABIERTA`.

## Diagnóstico previo (paso 2)
Análisis del código del Taller 3 (costos agrícolas con banano, cacao y arroz) antes de refactorizar.

| Pregunta | Respuesta del grupo |
|---|---|
| ¿Qué clase tiene más de un motivo para cambiar? | `CalculadoraCostos`: además de ejecutar la estrategia seleccionada y manejar el `Optional`, también redondea los valores (`redondear`). Cambiaría si se modifica el cálculo o si se modifica el formato del resultado. `Main` también mezclaba la creación de los objetos con la presentación de los resultados. |
| ¿Dónde se usa `new` directamente sobre los records? | En `Main` y en las pruebas (`crearLoteBanano()` crea con `new` el `Lote`, el `Banano` y cada `Insumo`). Con el Taller 4 la creación pasa por `FabricaProductos`. |
| ¿Hay umbrales o tarifas como números sueltos dentro de una regla? | Sí: el IVA del 15 %, el subsidio del 20 % a la urea, el jornal de USD 18,50 y los jornales por hectárea (18 en banano y 25 en cacao orgánico) estaban escritos dentro de las estrategias. En CadenaFria los umbrales viven en `ConfiguracionFrio` y ninguna regla los trae escritos. |
| ¿Hay herencia solo para reutilizar código? | No. `Cultivo` es una interfaz sellada que permite solo `Banano`, `Cacao` y `Arroz`, y los demás tipos son records sin herencia de clases. Se mantiene la composición. |

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
| Temperatura mínima / máxima | 0 °C / 4 °C | Rango común de los dos productos del tema. Para pescado fresco, la SFDA (Arabia Saudita) pide mantenerlo entre –1 °C y 4 °C durante el transporte, almacenamiento y venta, y la FAO recomienda mantener las cámaras de producto refrigerado por debajo de 4 °C. Para flor de corte, las guías de manejo poscosecha usan cerca de 1 °C a 4 °C (Floralife recomienda 1–3 °C). Se eligió 0–4 °C para cubrir ambos con una sola configuración. |
| Minutos de ruptura | 30 | Criterio del grupo, no proviene de una norma: tiempo máximo que se tolera fuera de rango antes de considerar que la cadena de frío se rompió. Es un valor de partida y se cambia con `cambiarMinutosRuptura(...)`. |
| Minutos de puerta abierta | 5 | Criterio del grupo, no proviene de una norma: una puerta abierta más de 5 minutos deja entrar suficiente aire caliente como para sospechar una subida de temperatura. Se cambia con `cambiarMinutosPuertaAbierta(...)`. |

Todas las reglas usan comparación estricta: en el umbral exacto **no** se dispara la alerta.

**Fuentes:**
- SFDA. *Requirements of Fish Storage by Refrigeration and Freezing.* https://sfda.gov.sa/sites/default/files/2021-04/RequirementsofFishStoragebyRefrigerationandFreezing.pdf
- FAO. *Food loss and waste in fish value chains – regulatory environment (freezing and storage).* https://www.fao.org/flw-in-fish-value-chains/value-chain/flw-in-fish-value-chainsvalue-chainprocessing-storage/freezing/regulatory-environment/es/
- Floralife. *Temperature Control – What is it?* https://floralife.com/?p=23228

## Cómo ejecutar
- Pruebas: `mvn clean verify` (20 pruebas: 14 del Taller 4 y 6 del Taller 3, todas en verde).
- Demostración: `java -cp target/classes ec.edu.uagraria.cadenafria.Main`

## Diagrama UML
Ver `docs/uml-unidad1.png`.

## Declaración de uso de IA
Se usó IA (Claude) para generar la estructura base del código, las pruebas y la documentación; el grupo compiló, ejecutó las 20 pruebas en verde y revisó el pull request.
