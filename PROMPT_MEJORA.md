# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/pragma/payments/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/payments/Application.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/domain/port/PaymentRepository.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/domain/port/ExternalServicePort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentMongoRepository.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/usecase/PaymentUseCase.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/usecase/PaymentUseCase.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/ExternalServiceAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/ExternalServiceAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/exception/GlobalExceptionHandler.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/application/usecase/PaymentUseCaseTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/infrastructure/adapter/ExternalServiceAdapterTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentMongoRepository.java` — `PaymentStatus.name`: Se invoca `name` sobre `PaymentStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/usecase/PaymentUseCase.java` — `Payment.operationNumber`: Se invoca `operationNumber` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/usecase/PaymentUseCase.java` — `Payment.idempotencyKey`: Se invoca `idempotencyKey` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/usecase/PaymentUseCase.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/ExternalServiceAdapter.java` — `Payment.operationNumber`: Se invoca `operationNumber` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/ExternalServiceAdapter.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/ExternalServiceAdapter.java` — `Payment.currency`: Se invoca `currency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/ExternalServiceAdapter.java` — `Payment.channel`: Se invoca `channel` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/ExternalServiceAdapter.java` — `Payment.customerId`: Se invoca `customerId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/ExternalServiceAdapter.java` — `Payment.accountNumber`: Se invoca `accountNumber` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/ExternalServiceAdapter.java` — `Payment.paymentMethod`: Se invoca `paymentMethod` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `PaymentRequest.operationNumber`: Se invoca `operationNumber` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `PaymentRequest.amount`: Se invoca `amount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `PaymentRequest.channel`: Se invoca `channel` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `PaymentRequest.currency`: Se invoca `currency` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `PaymentRequest.customerId`: Se invoca `customerId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `PaymentUseCase.getPaymentByOperationNumberAndChannel`: Se invoca `getPaymentByOperationNumberAndChannel` sobre `PaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `PaymentUseCase.cancelPayment`: Se invoca `cancelPayment` sobre `PaymentUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `Payment.operationNumber`: Se invoca `operationNumber` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `Payment.currency`: Se invoca `currency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `Payment.channel`: Se invoca `channel` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `Payment.status`: Se invoca `status` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `Payment.createdAt`: Se invoca `createdAt` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `Payment.antifraudResponse`: Se invoca `antifraudResponse` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `Payment.riskResponse`: Se invoca `riskResponse` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `Payment.bankCoreResponse`: Se invoca `bankCoreResponse` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java` — `Payment.paymentGatewayResponse`: Se invoca `paymentGatewayResponse` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/usecase/PaymentUseCaseTest.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `pom.xml` — `de.flapdoodle.embed:de.flapdoodle.embed.mongo@4.15.1`: de.flapdoodle.embed:de.flapdoodle.embed.mongo declara la version 4.15.1, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollo de software, Tecnología Java, Senior L2

### Brecha de conocimiento
Comprueba manejo de errores y reintentos.

### Reto
- Tema: E2E technologiesWork 202610091709
- Seniority: senior-l2
- Tipo: practical
- Título: Implementación de manejo de errores y reintentos en un sistema de procesamiento de pagos
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Identificación de puntos de falla y definición de estrategias de reintento — objetivo: Definir las estrategias de reintento para los diferentes servicios externos con los que interactúa el sistema. — entregable (NO resolver): Documento que describe las estrategias de reintento para cada servicio externo.
- Fase 2: Implementación de manejo de errores y reintentos — objetivo: Implementar el manejo de errores y reintentos en el sistema de procesamiento de pagos. — entregable (NO resolver): Código que implementa el manejo de errores y reintentos en el sistema de procesamiento de pagos.
- Fase 3: Validación del manejo de errores y reintentos — objetivo: Validar que el sistema de procesamiento de pagos maneja correctamente los errores y realiza reintentos automáticos. — entregable (NO resolver): Reporte de pruebas que valida el manejo de errores y reintentos en el sistema de procesamiento de pagos.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>payments</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>payments</name>
    <description>Sistema de procesamiento de pagos con manejo de errores y reintentos</description>

    <properties>
        <java.version>21</java.version>
        <resilience4j.version>2.2.0</resilience4j.version>
        <reactor-netty.version>1.1.18</reactor-netty.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-mongodb-reactive</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-reactor</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>de.flapdoodle.embed</groupId>
            <artifactId>de.flapdoodle.embed.mongo</artifactId>
            <version>4.15.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mock-server</groupId>
            <artifactId>mockserver-client-java</artifactId>
            <version>5.15.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/payments/Application.java ===
package com.pragma.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Schedulers;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class Application {
    private final ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors() * 2);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Bean
    public ExecutorService taskExecutor() {
        return executorService;
    }

    @Bean
    public Schedulers.ImmediateScheduler immediateScheduler() {
        return Schedulers.newParallel("payment-scheduler", executorService.getPoolSize());
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
server:
  port: 8080
  netty:
    connection-timeout: 2000ms
    idle-timeout: 60s

spring:
  application:
    name: payments-service
  data:
    mongodb:
      uri: mongodb://localhost:27017/payments_db
      auto-index-creation: true
      repositories:
        type: reactive

resilience4j:
  circuitbreaker:
    instances:
      antifraudService:
        registerHealthIndicator: true
        slidingWindowType: TIME_BASED
        slidingWindowSize: 10
        failureRateThreshold: 50
        waitDurationInOpenState: 5s
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
      coreBankingService:
        registerHealthIndicator: true
        slidingWindowType: COUNT_BASED
        slidingWindowSize: 5
        failureRateThreshold: 60
        waitDurationInOpenState: 10s
        permittedNumberOfCallsInHalfOpenState: 2
  retry:
    instances:
      antifraudService:
        maxRetryAttempts: 3
        waitDuration: 500ms
        enableExponentialBackoff: true
        exponentialBackoffMultiplier: 2
      coreBankingService:
        maxRetryAttempts: 2
        waitDuration: 1000ms
      paymentGateway:
        maxRetryAttempts: 4
        waitDuration: 300ms
  bulkhead:
    instances:
      antifraudService:
        maxConcurrentCalls: 100
      coreBankingService:
        maxConcurrentCalls: 50
      paymentGateway:
        maxConcurrentCalls: 200

management:
  endpoints:
    web:
      exposure:
        include: health, metrics, circuitbreakers, retries, bulkhead
  endpoint:
    health:
      show-details: always
  health:
    circuitbreakers:
      enabled: true
    retries:
      enabled: true
    bulkhead:
      enabled: true

external-services:
  antifraud:
    base-url: http://antifraud-service:8081
    timeout: 1500ms
    retry-attempts: 3
  risk-bureau:
    base-url: http://risk-bureau-service:8082
    timeout: 2000ms
  core-banking:
    base-url: http://core-banking-service:8083
    timeout: 2500ms
    retry-attempts: 2
  payment-gateway:
    base-url: http://payment-gateway-service:8084
    timeout: 1800ms
    retry-attempts: 4
  credit-originator:
    base-url: http://credit-originator-service:8085
    timeout: 2200ms

// === ARCHIVO: src/main/java/com/pragma/payments/domain/model/Payment.java ===
package com.pragma.payments.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record Payment(
    String id,
    String operationNumber,
    String channel,
    BigDecimal amount,
    String currency,
    String accountOrigin,
    String accountDestination,
    PaymentStatus status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    String idempotencyKey,
    int retryCount,
    String antifraudResponse,
    String riskResponse,
    String bankCoreResponse,
    String paymentGatewayResponse) {

    public enum PaymentStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED,
        REJECTED
    }

    public Payment {
        Objects.requireNonNull(operationNumber, "Operation number cannot be null");
        Objects.requireNonNull(channel, "Channel cannot be null");
        Objects.requireNonNull(amount, "Amount cannot be null");
        Objects.requireNonNull(currency, "Currency cannot be null");
        Objects.requireNonNull(accountOrigin, "Account origin cannot be null");
        Objects.requireNonNull(accountDestination, "Account destination cannot be null");
        Objects.requireNonNull(idempotencyKey, "Idempotency key cannot be null");
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (retryCount < 0) {
            throw new IllegalArgumentException("Retry count cannot be negative");
        }
    }

    public Payment withStatus(PaymentStatus newStatus) {
        return new Payment(
            this.id,
            this.operationNumber,
            this.channel,
            this.amount,
            this.currency,
            this.accountOrigin,
            this.accountDestination,
            newStatus,
            this.createdAt,
            LocalDateTime.now(),
            this.idempotencyKey,
            this.retryCount,
            this.antifraudResponse,
            this.riskResponse,
            this.bankCoreResponse,
            this.paymentGatewayResponse
        );
    }

    public Payment incrementRetryCount() {
        return new Payment(
            this.id,
            this.operationNumber,
            this.channel,
            this.amount,
            this.currency,
            this.accountOrigin,
            this.accountDestination,
            this.status,
            this.createdAt,
            this.updatedAt,
            this.idempotencyKey,
            this.retryCount + 1,
            this.antifraudResponse,
            this.riskResponse,
            this.bankCoreResponse,
            this.paymentGatewayResponse
        );
    }

    public Payment withAntifraudResponse(String response) {
        return new Payment(
            this.id,
            this.operationNumber,
            this.channel,
            this.amount,
            this.currency,
            this.accountOrigin,
            this.accountDestination,
            this.status,
            this.createdAt,
            LocalDateTime.now(),
            this.idempotencyKey,
            this.retryCount,
            response,
            this.riskResponse,
            this.bankCoreResponse,
            this.paymentGatewayResponse
        );
    }

    public Payment withRiskResponse(String response) {
        return new Payment(
            this.id,
            this.operationNumber,
            this.channel,
            this.amount,
            this.currency,
            this.accountOrigin,
            this.accountDestination,
            this.status,
            this.createdAt,
            LocalDateTime.now(),
            this.idempotencyKey,
            this.retryCount,
            this.antifraudResponse,
            response,
            this.bankCoreResponse,
            this.paymentGatewayResponse
        );
    }

    public Payment withBankCoreResponse(String response) {
        return new Payment(
            this.id,
            this.operationNumber,
            this.channel,
            this.amount,
            this.currency,
            this.accountOrigin,
            this.accountDestination,
            this.status,
            this.createdAt,
            LocalDateTime.now(),
            this.idempotencyKey,
            this.retryCount,
            this.antifraudResponse,
            this.riskResponse,
            response,
            this.paymentGatewayResponse
        );
    }

    public Payment withPaymentGatewayResponse(String response) {
        return new Payment(
            this.id,
            this.operationNumber,
            this.channel,
            this.amount,
            this.currency,
            this.accountOrigin,
            this.accountDestination,
            this.status,
            this.createdAt,
            LocalDateTime.now(),
            this.idempotencyKey,
            this.retryCount,
            this.antifraudResponse,
            this.riskResponse,
            this.bankCoreResponse,
            response
        );
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/port/PaymentRepository.java ===
package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Payment;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface PaymentRepository {
    Mono<Payment> save(Payment payment);
    Mono<Payment> findById(String id);
    Mono<Payment> findByOperationNumberAndChannel(String operationNumber, String channel);
    Mono<Payment> findByIdempotencyKey(String idempotencyKey);
    Mono<Void> deleteById(String id);
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/port/ExternalServicePort.java ===
package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Payment;
import reactor.core.publisher.Mono;

public interface ExternalServicePort {
    Mono<String> validateAntifraud(Payment payment);
    Mono<String> evaluateRisk(Payment payment);
    Mono<String> processBankCore(Payment payment);
    Mono<String> executePaymentGateway(Payment payment);
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/PaymentMongoRepository.java ===
package com.pragma.payments.infrastructure.adapter;



import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.infrastructure.exception.PaymentProcessingException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.port.PaymentRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class PaymentMongoRepository implements PaymentRepository {

    private final ReactiveMongoTemplate mongoTemplate;
    private static final String COLLECTION_NAME = "payments";

    public PaymentMongoRepository(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<Payment> save(Payment payment) {
        return mongoTemplate.save(payment, COLLECTION_NAME)
                .onErrorResume(e -> Mono.error(
                        new com.pragma.payments.infrastructure.exception.PaymentProcessingException(
                                "Error al guardar el pago en MongoDB: " + e.getMessage(), e)));
    }

    @Override
    public Mono<Payment> findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Payment.class, COLLECTION_NAME)
                .switchIfEmpty(Mono.empty())
                .onErrorResume(e -> Mono.error(
                        new com.pragma.payments.infrastructure.exception.PaymentProcessingException(
                                "Error al buscar el pago por ID: " + e.getMessage(), e)));
    }

    @Override
    public Mono<Payment> findByOperationNumberAndChannel(String operationNumber, String channel) {
        Query query = new Query()
                .addCriteria(Criteria.where("operationNumber").is(operationNumber))
                .addCriteria(Criteria.where("channel").is(channel));
        return mongoTemplate.findOne(query, Payment.class, COLLECTION_NAME)
                .switchIfEmpty(Mono.empty())
                .onErrorResume(e -> Mono.error(
                        new com.pragma.payments.infrastructure.exception.PaymentProcessingException(
                                "Error al buscar el pago por número de operación y canal: " + e.getMessage(), e)));
    }

    @Override
    public Mono<Payment> findByIdempotencyKey(String idempotencyKey) {
        Query query = new Query(Criteria.where("idempotencyKey").is(idempotencyKey));
        return mongoTemplate.findOne(query, Payment.class, COLLECTION_NAME)
                .switchIfEmpty(Mono.empty())
                .onErrorResume(e -> Mono.error(
                        new com.pragma.payments.infrastructure.exception.PaymentProcessingException(
                                "Error al buscar el pago por clave de idempotencia: " + e.getMessage(), e)));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.remove(query, COLLECTION_NAME)
                .then()
                .onErrorResume(e -> Mono.error(
                        new com.pragma.payments.infrastructure.exception.PaymentProcessingException(
                                "Error al eliminar el pago: " + e.getMessage(), e)));
    }

    public Mono<Payment> updateStatus(String id, Payment.PaymentStatus newStatus) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("status", newStatus.name());
        return mongoTemplate.findAndModify(query, update, Payment.class, COLLECTION_NAME)
                .switchIfEmpty(Mono.empty())
                .onErrorResume(e -> Mono.error(
                        new com.pragma.payments.infrastructure.exception.PaymentProcessingException(
                                "Error al actualizar el estado del pago: " + e.getMessage(), e)));
    }

    public Mono<Payment> findByOperationNumber(String operationNumber) {
        Query query = new Query(Criteria.where("operationNumber").is(operationNumber));
        return mongoTemplate.findOne(query, Payment.class, COLLECTION_NAME)
                .switchIfEmpty(Mono.empty())
                .onErrorResume(e -> Mono.error(
                        new com.pragma.payments.infrastructure.exception.PaymentProcessingException(
                                "Error al buscar el pago por número de operación: " + e.getMessage(), e)));
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/application/usecase/PaymentUseCase.java ===
package com.pragma.payments.application.usecase;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.ExternalServicePort;
import com.pragma.payments.domain.port.PaymentRepository;
import com.pragma.payments.infrastructure.exception.PaymentProcessingException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Service
public class PaymentUseCase {

    private static final Logger log = LoggerFactory.getLogger(PaymentUseCase.class);
    private static final int MAX_RETRY_COUNT = 3;
    private static final Duration RETRY_WAIT_DURATION = Duration.ofMillis(500);

    private final PaymentRepository paymentRepository;
    private final ExternalServicePort externalServicePort;

    public PaymentUseCase(PaymentRepository paymentRepository, ExternalServicePort externalServicePort) {
        this.paymentRepository = paymentRepository;
        this.externalServicePort = externalServicePort;
    }

    @Retry(name = "paymentProcessing")
    public Mono<Payment> processPayment(Payment payment) {
        log.info("Iniciando procesamiento de pago con número de operación: {}", payment.operationNumber());

        return paymentRepository.findByIdempotencyKey(payment.idempotencyKey())
                .flatMap(existingPayment -> {
                    log.info("Pago idempotente encontrado, retornando resultado existente: {}", existingPayment.id());
                    return Mono.just(existingPayment);
                })
                .switchIfEmpty(Mono.defer(() -> executePaymentFlow(payment)))
                .doOnSuccess(result -> log.info("Pago procesado exitosamente: {}", result.id()))
                .doOnError(error -> log.error("Error en procesamiento de pago: {}", error.getMessage()));
    }

    private Mono<Payment> executePaymentFlow(Payment payment) {
        Payment pendingPayment = payment.withStatus(PaymentStatus.PENDING)
                .withIdempotencyKey(payment.idempotencyKey() != null ? payment.idempotencyKey() : UUID.randomUUID().toString());

        return paymentRepository.save(pendingPayment)
                .flatMap(savedPayment -> validateAntifraud(savedPayment)
                        .flatMap(antifraudResponse -> processRiskEvaluation(savedPayment, antifraudResponse))
                        .flatMap(riskResponse -> processBankCore(riskResponse.getKey(), riskResponse.getValue()))
                        .flatMap(bankCoreResponse -> executePaymentGateway(bankCoreResponse.getKey(), bankCoreResponse.getValue()))
                        .flatMap(gatewayResponse -> updatePaymentWithGatewayResponse(gatewayResponse.getKey(), gatewayResponse.getValue()))
                );
    }

    @CircuitBreaker(name = "antifraudCircuitBreaker", fallbackMethod = "antifraudFallback")
    private Mono<Payment> validateAntifraud(Payment payment) {
        log.info("Validando antifraude para el pago: {}", payment.id());
        return externalServicePort.validateAntifraud(payment)
                .flatMap(response -> {
                    log.info("Respuesta de antifraude recibida: {}", response);
                    Payment updatedPayment = payment.withAntifraudResponse(response)
                            .withStatus(PaymentStatus.ANTIFRAUD_VALIDATED);
                    return paymentRepository.save(updatedPayment)
                            .thenReturn(updatedPayment);
                });
    }

    @CircuitBreaker(name = "riskEvaluationCircuitBreaker", fallbackMethod = "riskEvaluationFallback")
    private Mono<java.util.Map.Entry<Payment, String>> processRiskEvaluation(Payment payment, String antifraudResponse) {
        log.info("Evaluando riesgo para el pago: {}", payment.id());
        return externalServicePort.evaluateRisk(payment)
                .flatMap(response -> {
                    log.info("Respuesta de evaluación de riesgo recibida: {}", response);
                    Payment updatedPayment = payment.withRiskResponse(response)
                            .withStatus(PaymentStatus.RISK_EVALUATED);
                    return paymentRepository.save(updatedPayment)
                            .map(savedPayment -> java.util.Map.entry(savedPayment, response));
                });
    }

    @CircuitBreaker(name = "bankCoreCircuitBreaker", fallbackMethod = "bankCoreFallback")
    private Mono<java.util.Map.Entry<Payment, String>> processBankCore(Payment payment, String riskResponse) {
        log.info("Procesando en core bancario para el pago: {}", payment.id());
        return externalServicePort.processBankCore(payment)
                .flatMap(response -> {
                    log.info("Respuesta del core bancario recibida: {}", response);
                    Payment updatedPayment = payment.withBankCoreResponse(response)
                            .withStatus(PaymentStatus.BANK_CORE_PROCESSED);
                    return paymentRepository.save(updatedPayment)
                            .map(savedPayment -> java.util.Map.entry(savedPayment, response));
                });
    }

    @CircuitBreaker(name = "paymentGatewayCircuitBreaker", fallbackMethod = "paymentGatewayFallback")
    private Mono<java.util.Map.Entry<Payment, String>> executePaymentGateway(Payment payment, String bankCoreResponse) {
        log.info("Ejecutando gateway de pago para el pago: {}", payment.id());
        return externalServicePort.executePaymentGateway(payment)
                .flatMap(response -> {
                    log.info("Respuesta del gateway de pago recibida: {}", response);
                    Payment updatedPayment = payment.withPaymentGatewayResponse(response)
                            .withStatus(PaymentStatus.COMPLETED);
                    return paymentRepository.save(updatedPayment)
                            .map(savedPayment -> java.util.Map.entry(savedPayment, response));
                });
    }

    private Mono<Payment> updatePaymentWithGatewayResponse(Payment payment, String gatewayResponse) {
        log.info("Actualizando estado final del pago: {}", payment.id());
        return paymentRepository.save(payment.withStatus(PaymentStatus.COMPLETED));
    }

    private Mono<Payment> antifraudFallback(Payment payment, Throwable t) {
        log.error("Fallback de antifraude activado para pago: {}. Error: {}", payment.id(), t.getMessage());
        Payment failedPayment = payment.withStatus(PaymentStatus.FAILED)
                .withAntifraudResponse("FALLBACK: " + t.getMessage());
        return paymentRepository.save(failedPayment)
                .thenReturn(failedPayment);
    }

    private Mono<java.util.Map.Entry<Payment, String>> riskEvaluationFallback(Payment payment, Throwable t) {
        log.error("Fallback de evaluación de riesgo activado para pago: {}. Error: {}", payment.id(), t.getMessage());
        Payment failedPayment = payment.withStatus(PaymentStatus.FAILED)
                .withRiskResponse("FALLBACK: " + t.getMessage());
        return paymentRepository.save(failedPayment)
                .map(savedPayment -> java.util.Map.entry(savedPayment, "FALLBACK: " + t.getMessage()));
    }

    private Mono<java.util.Map.Entry<Payment, String>> bankCoreFallback(Payment payment, Throwable t) {
        log.error("Fallback de core bancario activado para pago: {}. Error: {}", payment.id(), t.getMessage());
        Payment failedPayment = payment.withStatus(PaymentStatus.FAILED)
                .withBankCoreResponse("FALLBACK: " + t.getMessage());
        return paymentRepository.save(failedPayment)
                .map(savedPayment -> java.util.Map.entry(savedPayment, "FALLBACK: " + t.getMessage()));
    }

    private Mono<java.util.Map.Entry<Payment, String>> paymentGatewayFallback(Payment payment, Throwable t) {
        log.error("Fallback de gateway de pago activado para pago: {}. Error: {}", payment.id(), t.getMessage());
        Payment failedPayment = payment.withStatus(PaymentStatus.FAILED)
                .withPaymentGatewayResponse("FALLBACK: " + t.getMessage());
        return paymentRepository.save(failedPayment)
                .map(savedPayment -> java.util.Map.entry(savedPayment, "FALLBACK: " + t.getMessage()));
    }

    public Mono<Payment> getPaymentById(String id) {
        log.info("Consultando pago por ID: {}", id);
        return paymentRepository.findById(id)
                .switchIfEmpty(Mono.error(new PaymentProcessingException("Pago no encontrado con ID: " + id)));
    }

    public Mono<Payment> getPaymentByOperationNumber(String operationNumber, String channel) {
        log.info("Consultando pago por número de operación: {} y canal: {}", operationNumber, channel);
        return paymentRepository.findByOperationNumberAndChannel(operationNumber, channel)
                .switchIfEmpty(Mono.error(new PaymentProcessingException(
                        "Pago no encontrado con número de operación: " + operationNumber + " y canal: " + channel)));
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/ExternalServiceAdapter.java ===
package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.port.ExternalServicePort;
import com.pragma.payments.infrastructure.exception.ExternalServiceException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

@Component
public class ExternalServiceAdapter implements ExternalServicePort {

    private static final Logger log = LoggerFactory.getLogger(ExternalServiceAdapter.class);

    private final WebClient webClient;

    @Value("${external.services.antifraud.url:http://localhost:8081/api/antifraud}")
    private String antifraudUrl;

    @Value("${external.services.risk.url:http://localhost:8082/api/risk}")
    private String riskUrl;

    @Value("${external.services.bankcore.url:http://localhost:8083/api/bankcore}")
    private String bankCoreUrl;

    @Value("${external.services.gateway.url:http://localhost:8084/api/gateway}")
    private String gatewayUrl;

    @Value("${external.services.timeout:5000}")
    private int serviceTimeout;

    public ExternalServiceAdapter(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    @CircuitBreaker(name = "antifraudService", fallbackMethod = "antifraudFallback")
    @Retry(name = "antifraudRetry")
    public Mono<String> validateAntifraud(Payment payment) {
        log.info("Llamando servicio de antifraude para el pago: {}", payment.operationNumber());

        return webClient.post()
                .uri(antifraudUrl + "/validate")
                .bodyValue(buildAntifraudRequest(payment))
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(Duration.ofMillis(serviceTimeout))
                .map(response -> {
                    String status = (String) response.getOrDefault("status", "UNKNOWN");
                    log.info("Respuesta de antifraude: {}", status);
                    return status;
                })
                .doOnError(e -> log.error("Error en servicio de antifraude: {}", e.getMessage()))
                .onErrorResume(e -> Mono.error(
                        new ExternalServiceException("Error en servicio de antifraude: " + e.getMessage(), e)));
    }

    @Override
    @CircuitBreaker(name = "riskEvaluationService", fallbackMethod = "riskEvaluationFallback")
    @Retry(name = "riskEvaluationRetry")
    public Mono<String> evaluateRisk(Payment payment) {
        log.info("Llamando servicio de evaluación de riesgo para el pago: {}", payment.operationNumber());

        return webClient.post()
                .uri(riskUrl + "/evaluate")
                .bodyValue(buildRiskRequest(payment))
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(Duration.ofMillis(serviceTimeout))
                .map(response -> {
                    String level = (String) response.getOrDefault("riskLevel", "MEDIUM");
                    log.info("Nivel de riesgo evaluado: {}", level);
                    return level;
                })
                .doOnError(e -> log.error("Error en servicio de evaluación de riesgo: {}", e.getMessage()))
                .onErrorResume(e -> Mono.error(
                        new ExternalServiceException("Error en servicio de evaluación de riesgo: " + e.getMessage(), e)));
    }

    @Override
    @CircuitBreaker(name = "bankCoreService", fallbackMethod = "bankCoreFallback")
    @Retry(name = "bankCoreRetry")
    public Mono<String> processBankCore(Payment payment) {
        log.info("Llamando servicio de core bancario para el pago: {}", payment.operationNumber());

        return webClient.post()
                .uri(bankCoreUrl + "/process")
                .bodyValue(buildBankCoreRequest(payment))
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(Duration.ofMillis(serviceTimeout))
                .map(response -> {
                    String transactionId = (String) response.getOrDefault("transactionId", "N/A");
                    log.info("ID de transacción bancaria: {}", transactionId);
                    return transactionId;
                })
                .doOnError(e -> log.error("Error en servicio de core bancario: {}", e.getMessage()))
                .onErrorResume(e -> Mono.error(
                        new ExternalServiceException("Error en servicio de core bancario: " + e.getMessage(), e)));
    }

    @Override
    @CircuitBreaker(name = "paymentGatewayService", fallbackMethod = "paymentGatewayFallback")
    @Retry(name = "paymentGatewayRetry")
    public Mono<String> executePaymentGateway(Payment payment) {
        log.info("Llamando servicio de gateway de pago para el pago: {}", payment.operationNumber());

        return webClient.post()
                .uri(gatewayUrl + "/execute")
                .bodyValue(buildGatewayRequest(payment))
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(Duration.ofMillis(serviceTimeout))
                .map(response -> {
                    String authorizationCode = (String) response.getOrDefault("authorizationCode", "N/A");
                    log.info("Código de autorización: {}", authorizationCode);
                    return authorizationCode;
                })
                .doOnError(e -> log.error("Error en servicio de gateway de pago: {}", e.getMessage()))
                .onErrorResume(e -> Mono.error(
                        new ExternalServiceException("Error en servicio de gateway de pago: " + e.getMessage(), e)));
    }

    private Map<String, Object> buildAntifraudRequest(Payment payment) {
        return Map.of(
                "operationNumber", payment.operationNumber(),
                "amount", payment.amount().toString(),
                "currency", payment.currency(),
                "channel", payment.channel(),
                "customerId", payment.customerId());
    }

    private Map<String, Object> buildRiskRequest(Payment payment) {
        return Map.of(
                "operationNumber", payment.operationNumber(),
                "amount", payment.amount().toString(),
                "customerId", payment.customerId(),
                "channel", payment.channel());
    }

    private Map<String, Object> buildBankCoreRequest(Payment payment) {
        return Map.of(
                "operationNumber", payment.operationNumber(),
                "amount", payment.amount().toString(),
                "currency", payment.currency(),
                "customerId", payment.customerId(),
                "accountNumber", payment.accountNumber() != null ? payment.accountNumber() : "");
    }

    private Map<String, Object> buildGatewayRequest(Payment payment) {
        return Map.of(
                "operationNumber", payment.operationNumber(),
                "amount", payment.amount().toString(),
                "currency", payment.currency(),
                "customerId", payment.customerId(),
                "paymentMethod", payment.paymentMethod() != null ? payment.paymentMethod() : "CARD");
    }

    private Mono<String> antifraudFallback(Payment payment, Throwable t) {
        log.warn("Fallback de antifraude activado para pago: {}. Razón: {}", payment.operationNumber(), t.getMessage());
        return Mono.just("FALLBACK_APPROVED");
    }

    private Mono<String> riskEvaluationFallback(Payment payment, Throwable t) {
        log.warn("Fallback de evaluación de riesgo activado para pago: {}. Razón: {}", payment.operationNumber(), t.getMessage());
        return Mono.just("FALLBACK_MEDIUM_RISK");
    }

    private Mono<String> bankCoreFallback(Payment payment, Throwable t) {
        log.warn("Fallback de core bancario activado para pago: {}. Razón: {}", payment.operationNumber(), t.getMessage());
        return Mono.just("FALLBACK_TXN_" + System.currentTimeMillis());
    }

    private Mono<String> paymentGatewayFallback(Payment payment, Throwable t) {
        log.warn("Fallback de gateway de pago activado para pago: {}. Razón: {}", payment.operationNumber(), t.getMessage());
        return Mono.just("FALLBACK_AUTH_" + System.currentTimeMillis());
    }

    public void updateServiceUrls(String antifraud, String risk, String bankCore, String gateway) {
        this.antifraudUrl = antifraud;
        this.riskUrl = risk;
        this.bankCoreUrl = bankCore;
        this.gatewayUrl = gateway;
    }

    public void updateTimeout(int timeout) {
        this.serviceTimeout = timeout;
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/exception/PaymentProcessingException.java ===
package com.pragma.payments.infrastructure.exception;

public class PaymentProcessingException extends RuntimeException {
    private final String paymentId;
    private final String operation;

    public PaymentProcessingException(String message) {
        super(message);
        this.paymentId = null;
        this.operation = null;
    }

    public PaymentProcessingException(String message, Throwable cause) {
        super(message, cause);
        this.paymentId = null;
        this.operation = null;
    }

    public PaymentProcessingException(String message, String paymentId, String operation) {
        super(message);
        this.paymentId = paymentId;
        this.operation = operation;
    }

    public PaymentProcessingException(String message, String paymentId, String operation, Throwable cause) {
        super(message, cause);
        this.paymentId = paymentId;
        this.operation = operation;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getOperation() {
        return operation;
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/exception/ExternalServiceException.java ===
package com.pragma.payments.infrastructure.exception;

public class ExternalServiceException extends RuntimeException {
    private final String serviceName;
    private final int httpStatusCode;
    private final boolean retryable;

    public ExternalServiceException(String message, String serviceName) {
        super(message);
        this.serviceName = serviceName;
        this.httpStatusCode = 0;
        this.retryable = true;
    }

    public ExternalServiceException(String message, String serviceName, Throwable cause) {
        super(message, cause);
        this.serviceName = serviceName;
        this.httpStatusCode = 0;
        this.retryable = true;
    }

    public ExternalServiceException(String message, String serviceName, int httpStatusCode, boolean retryable) {
        super(message);
        this.serviceName = serviceName;
        this.httpStatusCode = httpStatusCode;
        this.retryable = retryable;
    }

    public ExternalServiceException(String message, String serviceName, int httpStatusCode, boolean retryable, Throwable cause) {
        super(message, cause);
        this.serviceName = serviceName;
        this.httpStatusCode = httpStatusCode;
        this.retryable = retryable;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public boolean isRetryable() {
        return retryable;
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/config/Resilience4jConfig.java ===
package com.pragma.payments.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofSeconds(2))
                .retryExceptions(Exception.class)
                .build();
        return RetryRegistry.of(config);
    }

    @Bean
    public BulkheadRegistry bulkheadRegistry() {
        BulkheadConfig config = BulkheadConfig.custom()
                .maxConcurrentCalls(100)
                .maxWaitDuration(Duration.ofMillis(500))
                .build();
        return BulkheadRegistry.of(config);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/exception/IdempotencyException.java ===
package com.pragma.payments.infrastructure.exception;

public class IdempotencyException extends RuntimeException {

    private final String idempotencyKey;
    private final String operationNumber;

    public IdempotencyException(String message) {
        super(message);
        this.idempotencyKey = null;
        this.operationNumber = null;
    }

    public IdempotencyException(String message, String idempotencyKey) {
        super(message);
        this.idempotencyKey = idempotencyKey;
        this.operationNumber = null;
    }

    public IdempotencyException(String message, String idempotencyKey, String operationNumber) {
        super(message);
        this.idempotencyKey = idempotencyKey;
        this.operationNumber = operationNumber;
    }

    public IdempotencyException(String message, Throwable cause) {
        super(message, cause);
        this.idempotencyKey = null;
        this.operationNumber = null;
    }

    public IdempotencyException(String message, String idempotencyKey, Throwable cause) {
        super(message, cause);
        this.idempotencyKey = idempotencyKey;
        this.operationNumber = null;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getOperationNumber() {
        return operationNumber;
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/exception/GlobalExceptionHandler.java ===
package com.pragma.payments.infrastructure.exception;

import com.pragma.payments.domain.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IdempotencyException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleIdempotencyException(IdempotencyException ex) {
        log.warn("Violación de idempotencia: {} - Key: {}", ex.getMessage(), ex.getIdempotencyKey());
        
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("error", "Conflict");
        body.put("message", ex.getMessage());
        body.put("idempotencyKey", ex.getIdempotencyKey());
        
        if (ex.getOperationNumber() != null) {
            body.put("operationNumber", ex.getOperationNumber());
        }
        
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(body));
    }

    @ExceptionHandler(PaymentProcessingException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handlePaymentProcessingException(PaymentProcessingException ex) {
        log.error("Error procesamiento pago: {} - Payment ID: {}", ex.getMessage(), ex.getPaymentId(), ex);
        
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", HttpStatus.UNPROCESSABLE_ENTITY.value());
        body.put("error", "Payment Processing Failed");
        body.put("message", ex.getMessage());
        body.put("paymentId", ex.getPaymentId());
        
        if (ex.getCause() != null) {
            body.put("cause", ex.getCause().getMessage());
        }
        
        HttpStatus status = ex.isRetryable() ? HttpStatus.SERVICE_UNAVAILABLE : HttpStatus.UNPROCESSABLE_ENTITY;
        return Mono.just(ResponseEntity.status(status).body(body));
    }

    @ExceptionHandler(ExternalServiceException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleExternalServiceException(ExternalServiceException ex) {
        log.error("Error servicio externo: {} - Servicio: {}", ex.getMessage(), ex.getServiceName(), ex);
        
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", HttpStatus.BAD_GATEWAY.value());
        body.put("error", "External Service Error");
        body.put("message", ex.getMessage());
        body.put("serviceName", ex.getServiceName());
        body.put("retryable", ex.isRetryable());
        
        HttpStatus status = ex.isRetryable() ? HttpStatus.SERVICE_UNAVAILABLE : HttpStatus.BAD_GATEWAY;
        return Mono.just(ResponseEntity.status(status).body(body));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Argumento inválido: {}", ex.getMessage());
        
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage());
        
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleGenericException(Exception ex) {
        log.error("Error inesperado: {}", ex.getMessage(), ex);
        
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", "Error interno del servidor");
        
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body));
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/PaymentController.java ===
package com.pragma.payments.infrastructure.adapter;


import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.application.usecase.PaymentUseCase;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.infrastructure.exception.IdempotencyException;
import com.pragma.payments.infrastructure.exception.PaymentProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    private static final String IDEMPOTENCY_HEADER = "X-Idempotency-Key";
    
    private final PaymentUseCase paymentUseCase;

    public PaymentController(PaymentUseCase paymentUseCase) {
        this.paymentUseCase = paymentUseCase;
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> processPayment(
            @RequestHeader(value = IDEMPOTENCY_HEADER, required = false) String idempotencyKey,
            @RequestBody PaymentRequest request) {
        
        log.info("Recibida solicitud de pago - OperationNumber: {}, Amount: {}, Channel: {}",
                request.operationNumber(), request.amount(), request.channel());
        
        if (idempotencyKey != null && !idempotencyKey.isBlank()) {
            log.debug("Key de idempotencia proporcionada: {}", idempotencyKey);
        }
        
        if (request.operationNumber() == null || request.operationNumber().isBlank()) {
            return Mono.error(new IllegalArgumentException("El número de operación es requerido"));
        }
        
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            return Mono.error(new IllegalArgumentException("El monto debe ser mayor a cero"));
        }
        
        if (request.channel() == null || request.channel().isBlank()) {
            return Mono.error(new IllegalArgumentException("El canal de pago es requerido"));
        }
        
        Payment payment = new Payment(
            request.operationNumber(),
            request.amount(),
            request.channel(),
            Payment.PaymentStatus.PENDING,
            request.currency() != null ? request.currency() : "USD",
            request.customerId()
        );
        
        return paymentUseCase.processPayment(payment, idempotencyKey)
            .map(this::buildSuccessResponse)
            .onErrorResume(IdempotencyException.class, ex -> 
                Mono.just(ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(buildErrorResponse("CONFLICT", ex.getMessage(), idempotencyKey))))
            .onErrorResume(PaymentProcessingException.class, ex -> 
                Mono.just(ResponseEntity.status(ex.isRetryable() ? HttpStatus.SERVICE_UNAVAILABLE : HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(buildErrorResponse("PAYMENT_FAILED", ex.getMessage(), null))))
            .onErrorResume(IllegalArgumentException.class, ex -> 
                Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(buildErrorResponse("BAD_REQUEST", ex.getMessage(), null))));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Payment>> getPayment(@PathVariable String id) {
        log.info("Consultando pago con ID: {}", id);
        
        return paymentUseCase.getPaymentById(id)
            .map(payment -> ResponseEntity.ok(payment))
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, 
                "Pago no encontrado")));
    }

    @GetMapping("/by-operation")
    public Mono<ResponseEntity<Payment>> getPaymentByOperation(
            @RequestParam String operationNumber,
            @RequestParam String channel) {
        log.info("Consultando pago por operación: {} y canal: {}", operationNumber, channel);
        
        return paymentUseCase.getPaymentByOperationNumberAndChannel(operationNumber, channel)
            .map(payment -> ResponseEntity.ok(payment))
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, 
                "Pago no encontrado para la operación y canal especificados")));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> cancelPayment(@PathVariable String id) {
        log.info("Cancelando pago con ID: {}", id);
        
        return paymentUseCase.cancelPayment(id)
            .then(Mono.just(ResponseEntity.noContent().build()))
            .onErrorResume(ex -> Mono.just(ResponseEntity.notFound().build()));
    }

    private ResponseEntity<Map<String, Object>> buildSuccessResponse(Payment payment) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", payment.id());
        response.put("operationNumber", payment.operationNumber());
        response.put("amount", payment.amount());
        response.put("currency", payment.currency());
        response.put("channel", payment.channel());
        response.put("status", payment.status().name());
        response.put("createdAt", payment.createdAt() != null ? payment.createdAt().toString() : Instant.now().toString());
        
        if (payment.antifraudResponse() != null) {
            response.put("antifraudResponse", payment.antifraudResponse());
        }
        if (payment.riskResponse() != null) {
            response.put("riskResponse", payment.riskResponse());
        }
        if (payment.bankCoreResponse() != null) {
            response.put("bankCoreResponse", payment.bankCoreResponse());
        }
        if (payment.paymentGatewayResponse() != null) {
            response.put("paymentGatewayResponse", payment.paymentGatewayResponse());
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private Map<String, Object> buildErrorResponse(String error, String message, String idempotencyKey) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", error);
        response.put("message", message);
        response.put("timestamp", Instant.now().toString());
        
        if (idempotencyKey != null) {
            response.put("idempotencyKey", idempotencyKey);
        }
        
        return response;
    }

    public record PaymentRequest(
        String operationNumber,
        BigDecimal amount,
        String channel,
        String currency,
        String customerId
    ) {}
}

// === ARCHIVO: src/test/java/com/pragma/payments/application/usecase/PaymentUseCaseTest.java ===
package com.pragma.payments.application.usecase;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.PaymentRepository;
import com.pragma.payments.domain.port.ExternalServicePort;
import com.pragma.payments.infrastructure.exception.IdempotencyException;
import com.pragma.payments.infrastructure.exception.PaymentProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("PaymentUseCase - Pruebas Unitarias")
class PaymentUseCaseTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ExternalServicePort externalServicePort;

    private PaymentUseCase paymentUseCase;

    @BeforeEach
    void setUp() {
        paymentUseCase = new PaymentUseCase(paymentRepository, externalServicePort);
    }

    @Test
    @DisplayName("Debe procesar pago exitosamente cuando todos los servicios responden correctamente")
    void debeProcesarPagoExitosamente() {
        Payment payment = new Payment(
            "op-001", "channel-web", "123456", new BigDecimal("1000.00"),
            "COP", LocalDateTime.now(), PaymentStatus.PENDING, 0
        );

        when(paymentRepository.findByOperationNumberAndChannel(anyString(), anyString()))
            .thenReturn(Mono.empty());
        when(paymentRepository.findByIdempotencyKey(anyString()))
            .thenReturn(Mono.empty());
        when(paymentRepository.save(any(Payment.class)))
            .thenReturn(Mono.just(payment));
        when(externalServicePort.validateAntifraud(any(Payment.class)))
            .thenReturn(Mono.just("APPROVED"));
        when(externalServicePort.evaluateRisk(any(Payment.class)))
            .thenReturn(Mono.just("LOW_RISK"));
        when(externalServicePort.processBankCore(any(Payment.class)))
            .thenReturn(Mono.just("SUCCESS"));
        when(externalServicePort.executePaymentGateway(any(Payment.class)))
            .thenReturn(Mono.just("AUTHORIZED"));

        StepVerifier.create(paymentUseCase.processPayment(payment))
            .assertNext(result -> {
                assertNotNull(result);
                assertEquals(PaymentStatus.COMPLETED, result.status());
            })
            .verifyComplete();

        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(externalServicePort, times(1)).validateAntifraud(any(Payment.class));
        verify(externalServicePort, times(1)).evaluateRisk(any(Payment.class));
        verify(externalServicePort, times(1)).processBankCore(any(Payment.class));
        verify(externalServicePort, times(1)).executePaymentGateway(any(Payment.class));
    }

    @Test
    @DisplayName("Debe rechazar pago por idempotencia cuando ya existe con mismo operation number y channel")
    void debeRechazarPagoPorIdempotencia() {
        Payment existingPayment = new Payment(
            "op-001", "channel-web", "123456", new BigDecimal("1000.00"),
            "COP", LocalDateTime.now(), PaymentStatus.COMPLETED, 1
        );

        when(paymentRepository.findByOperationNumberAndChannel("op-001", "channel-web"))
            .thenReturn(Mono.just(existingPayment));

        Payment newPayment = new Payment(
            "op-001", "channel-web", "123456", new BigDecimal("1000.00"),
            "COP", LocalDateTime.now(), PaymentStatus.PENDING, 0
        );

        StepVerifier.create(paymentUseCase.processPayment(newPayment))
            .expectError(IdempotencyException.class)
            .verify();

        verify(paymentRepository, never()).save(any(Payment.class));
        verify(externalServicePort, never()).validateAntifraud(any(Payment.class));
    }

    @Test
    @DisplayName("Debe rechazar pago cuando antifraude retorna REJECTED")
    void debeRechazarPagoCuandoAntifraudeRechaza() {
        Payment payment = new Payment(
            "op-002", "channel-mobile", "789012", new BigDecimal("5000.00"),
            "COP", LocalDateTime.now(), PaymentStatus.PENDING, 0
        );

        when(paymentRepository.findByOperationNumberAndChannel(anyString(), anyString()))
            .thenReturn(Mono.empty());
        when(paymentRepository.findByIdempotencyKey(anyString()))
            .thenReturn(Mono.empty());
        when(paymentRepository.save(any(Payment.class)))
            .thenReturn(Mono.just(payment));
        when(externalServicePort.validateAntifraud(any(Payment.class)))
            .thenReturn(Mono.just("REJECTED"));

        StepVerifier.create(paymentUseCase.processPayment(payment))
            .assertNext(result -> {
                assertNotNull(result);
                assertEquals(PaymentStatus.FAILED, result.status());
            })
            .verifyComplete();

        verify(externalServicePort, times(1)).validateAntifraud(any(Payment.class));
        verify(externalServicePort, never()).evaluateRisk(any(Payment.class));
        verify(externalServicePort, never()).processBankCore(any(Payment.class));
        verify(externalServicePort, never()).executePaymentGateway(any(Payment.class));
    }

    @Test
    @DisplayName("Debe fallar cuando el servicio externo lanza excepción")
    void debeFallarCuandoServicioExternoLanzaExcepcion() {
        Payment payment = new Payment(
            "op-003", "channel-web", "345678", new BigDecimal("2000.00"),
            "COP", LocalDateTime.now(), PaymentStatus.PENDING, 0
        );

        when(paymentRepository.findByOperationNumberAndChannel(anyString(), anyString()))
            .thenReturn(Mono.empty());
        when(paymentRepository.findByIdempotencyKey(anyString()))
            .thenReturn(Mono.empty());
        when(paymentRepository.save(any(Payment.class)))
            .thenReturn(Mono.just(payment));
        when(externalServicePort.validateAntifraud(any(Payment.class)))
            .thenReturn(Mono.error(new RuntimeException("Servicio antifraude no disponible")));

        StepVerifier.create(paymentUseCase.processPayment(payment))
            .expectError(PaymentProcessingException.class)
            .verify();

        ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);
        verify(paymentRepository, atLeastOnce()).save(paymentCaptor.capture());
        assertEquals(1, paymentCaptor.getValue().retryCount());
    }

    @Test
    @DisplayName("Debe guardar pago con estado FAILED cuando todos los servicios fallan")
    void debeGuardarPagoFailedCuandoTodosLosServiciosFallan() {
        Payment payment = new Payment(
            "op-004", "channel-web", "111222", new BigDecimal("3000.00"),
            "USD", LocalDateTime.now(), PaymentStatus.PENDING, 0
        );

        when(paymentRepository.findByOperationNumberAndChannel(anyString(), anyString()))
            .thenReturn(Mono.empty());
        when(paymentRepository.findByIdempotencyKey(anyString()))
            .thenReturn(Mono.empty());
        when(paymentRepository.save(any(Payment.class)))
            .thenReturn(Mono.just(payment));
        when(externalServicePort.validateAntifraud(any(Payment.class)))
            .thenReturn(Mono.just("APPROVED"));
        when(externalServicePort.evaluateRisk(any(Payment.class)))
            .thenReturn(Mono.just("HIGH_RISK"));

        StepVerifier.create(paymentUseCase.processPayment(payment))
            .assertNext(result -> {
                assertNotNull(result);
                assertTrue(
                    result.status() == PaymentStatus.FAILED || result.status() == PaymentStatus.PENDING
                );
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe recuperar pago existente por idempotency key")
    void debeRecuperarPagoExistentePorIdempotencyKey() {
        String idempotencyKey = "idem-123";
        Payment existingPayment = new Payment(
            "op-005", "channel-web", idempotencyKey, new BigDecimal("1500.00"),
            "EUR", LocalDateTime.now(), PaymentStatus.COMPLETED, 0
        );

        when(paymentRepository.findByIdempotencyKey(idempotencyKey))
            .thenReturn(Mono.just(existingPayment));

        Payment newPayment = new Payment(
            "op-005", "channel-web", idempotencyKey, new BigDecimal("1500.00"),
            "EUR", LocalDateTime.now(), PaymentStatus.PENDING, 0
        );

        StepVerifier.create(paymentUseCase.processPayment(newPayment))
            .assertNext(result -> {
                assertNotNull(result);
                assertEquals(existingPayment.id(), result.id());
            })
            .verifyComplete();

        verify(paymentRepository, never()).save(any(Payment.class));
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/infrastructure/adapter/ExternalServiceAdapterTest.java ===
package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.infrastructure.exception.ExternalServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ExternalServiceAdapter - Pruebas de Integración")
class ExternalServiceAdapterTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    private ExternalServiceAdapter externalServiceAdapter;

    @BeforeEach
    void setUp() {
        externalServiceAdapter = new ExternalServiceAdapter(webClient);
    }

    @Test
    @DisplayName("Debe validar antifraude exitosamente cuando el servicio responde APPROVED")
    void debeValidarAntifraudeExitosamente() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("APPROVED"));

        StepVerifier.create(externalServiceAdapter.validateAntifraud(payment))
            .assertNext(response -> {
                assertNotNull(response);
                assertEquals("APPROVED", response);
            })
            .verifyComplete();

        verify(webClient, times(1)).post();
    }

    @Test
    @DisplayName("Debe evaluar riesgo exitosamente cuando el servicio responde LOW_RISK")
    void debeEvaluarRiesgoExitosamente() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("LOW_RISK"));

        StepVerifier.create(externalServiceAdapter.evaluateRisk(payment))
            .assertNext(response -> {
                assertNotNull(response);
                assertEquals("LOW_RISK", response);
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe procesar en core bancario exitosamente")
    void debeProcesarEnCoreBancarioExitosamente() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("SUCCESS"));

        StepVerifier.create(externalServiceAdapter.processBankCore(payment))
            .assertNext(response -> {
                assertNotNull(response);
                assertEquals("SUCCESS", response);
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe ejecutar gateway de pagos exitosamente")
    void debeEjecutarGatewayPagosExitosamente() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("AUTHORIZED"));

        StepVerifier.create(externalServiceAdapter.executePaymentGateway(payment))
            .assertNext(response -> {
                assertNotNull(response);
                assertEquals("AUTHORIZED", response);
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe lanzar ExternalServiceException cuando el servicio de antifraude falla")
    void debeLanzarExcepcionCuandoAntifraudeFalla() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class))
            .thenReturn(Mono.error(new RuntimeException("Connection timeout")));

        StepVerifier.create(externalServiceAdapter.validateAntifraud(payment))
            .expectError(ExternalServiceException.class)
            .verify();
    }

    @Test
    @DisplayName("Debe lanzar ExternalServiceException cuando el servicio de riesgo falla")
    void debeLanzarExcepcionCuandoServicioRiesgoFalla() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class))
            .thenReturn(Mono.error(new RuntimeException("Service unavailable")));

        StepVerifier.create(externalServiceAdapter.evaluateRisk(payment))
            .expectError(ExternalServiceException.class)
            .verify();
    }

    @Test
    @DisplayName("Debe lanzar ExternalServiceException cuando el core bancario falla")
    void debeLanzarExcepcionCuandoCoreBancarioFalla() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class))
            .thenReturn(Mono.error(new RuntimeException("Bank system offline")));

        StepVerifier.create(externalServiceAdapter.processBankCore(payment))
            .expectError(ExternalServiceException.class)
            .verify();
    }

    @Test
    @DisplayName("Debe lanzar ExternalServiceException cuando el gateway de pagos falla")
    void debeLanzarExcepcionCuandoGatewayPagosFalla() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class))
            .thenReturn(Mono.error(new RuntimeException("Gateway timeout")));

        StepVerifier.create(externalServiceAdapter.executePaymentGateway(payment))
            .expectError(ExternalServiceException.class)
            .verify();
    }

    @Test
    @DisplayName("Debe manejar respuesta vacía del servicio externo")
    void debeManejarRespuestaVacia() {
        Payment payment = crearPaymentBasico();

        when(webClient.post()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.empty());

        StepVerifier.create(externalServiceAdapter.validateAntifraud(payment))
            .assertNext(response -> {
                assertNotNull(response);
                assertTrue(response.isEmpty());
            })
            .verifyComplete();
    }

    private Payment crearPaymentBasico() {
        return new Payment(
            "op-test-001", "channel-web", "idem-001", new BigDecimal("1000.00"),
            "COP", LocalDateTime.now(), PaymentStatus.PENDING, 0
        );
    }
}

// === ARCHIVO: src/test/resources/application-test.yml ===
spring:
  profiles:
    active: test
  data:
    mongodb:
      uri: mongodb://localhost:27017
      database: payments_test
      auto-index-creation: true

  webflux:
    base-url: http://localhost:8080

server:
  port: 0

resilience4j:
  circuitbreaker:
    instances:
      antifraudService:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10

      riskEvaluationService:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        waitDurationInOpenState: 5s
        failureRateThreshold: 50

      bankCoreService:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        waitDurationInOpenState: 5s
        failureRateThreshold: 50

      paymentGatewayService:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        waitDurationInOpenState: 5s
        failureRateThreshold: 50

  retry:
    instances:
      antifraudService:
        maxAttempts: 3
        waitDuration: 1s
        enableExponentialBackoff: true
        exponentialBackoffMultiplier: 2
        retryExceptions:
          - java.io.IOException
          - java.util.concurrent.TimeoutException
          - org.springframework.web.reactive.function.client.WebClientResponseException

      riskEvaluationService:
        maxAttempts: 3
        waitDuration: 1s
        enableExponentialBackoff: true
        exponentialBackoffMultiplier: 2

      bankCoreService:
        maxAttempts: 2
        waitDuration: 2s

      paymentGatewayService:
        maxAttempts: 3
        waitDuration: 1s
        enableExponentialBackoff: true

  bulkhead:
    instances:
      externalServices:
        maxConcurrentCalls: 10
        maxWaitDuration: 1000ms

logging:
  level:
    com.pragma.payments: DEBUG
    org.springframework.web.reactive: DEBUG
    io.github.resilience4j: DEBUG

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,circuitbreakers
  endpoint:
    health:
      show-details: always
  health:
    circuitbreakers:
      enabled: true
```
