# MicroServicios Con Spring Boot

_Configuración de MicroServicios con SpringBoot, Spring Cloud, Eureka Netflix, Sleuth_

## Comenzando 🚀

_Realizar FORK o Clone Repository._ 

Mira **Deployment** para conocer como desplegar el proyecto.


### Pre-requisitos 📋

_Java 8 u 11_

_IntelliJ_

_Gradle_

_Normalmente si estas importando elimina el WorkSpace de IntelliJ -> Carpeta .idea, esto ayudara a que nuevamente se carguen las dependicnias de GRADLE_

_Instalar Plugin Lombok_

_Habilitar Java Annotation Processing_

_Importar Scripts haciendo caso  a la advertencia de IntelliJ Idea_

_Se esta sincronizando con config-data en GIT por lo tanto este debe estar habilitado_

_Paciencia_


### Instalación 🔧

_Importar el Codigo en IntelliJ_

_Arrancar en Orden Deployement_

## Despliegue 📦

1. Ejecutar Config Service -- Debe estar en JAVA 8 -- Port 8080
2. Ejecutar Admin Service -- Debe estar en JAVA 8 -- Port 8086 
    [Optional] http://localhost:8091/actuator/ -- http://localhost:8083/applications
3. Ejecutar Registry Service --Debe estar en JAVA 8 -- Port: 8081 
    [Optional] http://localhost:8099/ -->EUREKA

---- Break = "Esperemos a que levante" ----

4. Ejecutar MicroServicios, verificar si quedan registaros en los adminPlatform y configPlaftorm
5. Los servicios estan registrados en el Config Data

## FINISH