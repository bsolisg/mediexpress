# 💊 MediExpress - Sistema Distribuido con Microservicios

Este proyecto consiste en el desarrollo de un sistema de gestión distribuido para una empresa del rubro médico. Está compuesto por varios microservicios independientes que se comunican entre sí para cubrir distintas áreas funcionales de la empresa.

---

## 🏗 Estructura del Proyecto

El proyecto se compone de los siguientes microservicios:

| Microservicio    | Descripción                                     |
|------------------|-------------------------------------------------|
| `clientes`       | Gestión de clientes registrados                 |
| `configuracion`  | Configuración general del sistema               |
| `facturacion`    | Gestión de facturas emitidas                    |
| `inventario`     | Control de productos e insumos médicos          |
| `logistica`      | Registro de entregas y despachos                |
| `pedidos`        | Gestión de órdenes de compra                    |
| `proveedores`    | Administración de proveedores                   |
| `reportes`       | Generación de reportes por área                 |
| `soporte`        | Registro y atención de incidencias              |
| `usuarios`       | Autenticación y perfiles de usuario             |

---

## 🧰 Tecnologías utilizadas

- Java 21  
- Spring Boot  
- Spring Data JPA  
- Spring Web (REST)  
- WebClient (para integración entre microservicios)  
- Maven  
- MySQL (local o vía Docker)  
- Git y GitHub  
- Postman (para pruebas de endpoints)  

---

## ⚙️ Cómo ejecutar los microservicios

1. Asegúrate de tener instalado Java 21 y Maven.
2. Entra a cada carpeta de microservicio y ejecuta:

```bash
./mvnw spring-boot:run
