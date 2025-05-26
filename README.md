# 💊 Mediexpress - Sistema de Microservicios Distribuidos

Proyecto desarrollado como parte de la Evaluación Parcial 2 (40%) del curso de Arquitectura de Software.  
El sistema gestiona pedidos, inventario y logística médica a través de microservicios independientes pero coordinados.

---

## 🧱 Microservicios implementados

| Microservicio | Puerto | Descripción |
|---------------|--------|-------------|
| `inventario`  | `8082` | Gestión de productos disponibles |
| `pedidos`     | `8083` | Creación y registro de pedidos de productos |
| `logística`   | `8084` | Registro de entregas y coordinación de despachos |

---

## 🔁 Integraciones realizadas

1. ✔ **`pedidos ↔ inventario`**  
   Valida la existencia de un producto antes de registrar un pedido.

2. ✔ **`pedidos ↔ logística`**  
   Crea automáticamente una entrega cuando se genera un nuevo pedido.

---

## 🔌 Tecnologías utilizadas

- Java 17  
- Spring Boot  
- Spring Data JPA  
- WebClient (para llamadas entre microservicios)  
- MySQL (vía Docker o local)  
- Postman (para pruebas de endpoints)  
- Git + GitHub  
- Trello (para gestión de tareas)

---

## 🚀 Cómo ejecutar los microservicios

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/mediexpress.git
