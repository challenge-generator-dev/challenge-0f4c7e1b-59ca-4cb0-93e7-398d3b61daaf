# Implementación de manejo de errores y reintentos en un sistema de procesamiento de pagos

El sistema de procesamiento de pagos debe manejar correctamente los errores y realizar reintentos automáticos en caso de fallos temporales. El sistema interactúa con el originador de créditos, el motor antifraude, el buró de riesgos, el core bancario y el gateway de pagos. Los pagos deben ser idempotentes con respecto al número de operación y al canal de pago. El sistema debe manejar un volumen de 1 500 solicitudes por segundo en hora pico y mantener una latencia máxima de 2 segundos por solicitud.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | E2E technologiesWork 202610091709 |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Identificación de puntos de falla y definición de estrategias de reintento

**Objetivo:** Definir las estrategias de reintento para los diferentes servicios externos con los que interactúa el sistema.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identifica los servicios externos con los que interactúa el sistema y los posibles puntos de falla.
- Define las estrategias de reintento para cada servicio, considerando la latencia máxima aceptable y el número máximo de reintentos.

**Entregable:** Documento que describe las estrategias de reintento para cada servicio externo.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la latencia máxima aceptable y el número máximo de reintentos para cada servicio.
- Evalúa la idempotencia de las operaciones con respecto al número de operación y al canal de pago.

</details>

### Fase 2: Implementación de manejo de errores y reintentos

**Objetivo:** Implementar el manejo de errores y reintentos en el sistema de procesamiento de pagos.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Implementa el manejo de errores y reintentos según las estrategias definidas en la fase anterior.
- Asegura que los pagos sean idempotentes con respecto al número de operación y al canal de pago.

**Entregable:** Código que implementa el manejo de errores y reintentos en el sistema de procesamiento de pagos.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza patrones de diseño adecuados para el manejo de errores y reintentos.
- Considera la latencia máxima aceptable y el número máximo de reintentos para cada servicio.

</details>

### Fase 3: Validación del manejo de errores y reintentos

**Objetivo:** Validar que el sistema de procesamiento de pagos maneja correctamente los errores y realiza reintentos automáticos.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Realiza pruebas unitarias y de integración para validar el manejo de errores y reintentos implementado.
- Asegura que el sistema cumpla con los requisitos de latencia y volumen de solicitudes.

**Entregable:** Reporte de pruebas que valida el manejo de errores y reintentos en el sistema de procesamiento de pagos.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza herramientas de pruebas adecuadas para realizar pruebas unitarias y de integración.
- Considera escenarios de edge cases para validar el comportamiento del sistema en situaciones límite.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son los reintentos y por qué son importantes en un sistema de procesamiento de pagos?
- **paraQueSirve**: ¿Para qué sirve el manejo de errores en un sistema de procesamiento de pagos?
- **comoSeUsa**: ¿Cómo se implementa el manejo de errores y reintentos en un sistema de procesamiento de pagos?
- **erroresComunes**: ¿Cuáles son los errores comunes que pueden ocurrir en un sistema de procesamiento de pagos y cómo se pueden manejar?
- **queDecisionesImplica**: ¿Qué decisiones implica el diseño de un sistema de procesamiento de pagos con manejo de errores y reintentos?

## Criterios de Evaluacion

- Identificación correcta de los puntos de falla en los servicios externos.
- Definición adecuada de las estrategias de reintento para cada servicio.
- Implementación correcta del manejo de errores y reintentos en el sistema.
- Validación efectiva del manejo de errores y reintentos a través de pruebas unitarias y de integración.
- Cumplimiento de los requisitos de latencia y volumen de solicitudes en el sistema.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
