# Sistema de Gestión Empresarial v2.0

## 📋 Descripción

Sistema mejorado para la gestión de recursos humanos que permite administrar empleados, generar reportes y gestionar el historial laboral de manera eficiente.

## 🚀 Mejoras Implementadas

### 🎨 Mejoras en la Interfaz
- **Diseño Moderno**: Interfaz completamente rediseñada con Bootstrap 5
- **Responsive Design**: Optimizado para dispositivos móviles y tablets
- **Navegación Mejorada**: Menús intuitivos con iconos y dropdowns
- **Dashboard Interactivo**: Panel principal con estadísticas en tiempo real
- **Temas Visuales**: Esquema de colores profesional y coherente

### ⚙️ Mejoras en la Lógica del Backend
- **Arquitectura de Servicios**: Implementación de la capa de servicios (Service Layer)
- **Validaciones Robustas**: Validaciones tanto en el backend como en el frontend
- **Manejo de Errores**: Sistema centralizado de manejo de excepciones
- **Paginación**: Implementación de paginación para grandes volúmenes de datos
- **Transacciones**: Gestión adecuada de transacciones de base de datos

### 📊 Nuevas Funcionalidades
- **Dashboard Principal**: Vista general con estadísticas clave
- **Búsqueda Avanzada**: Filtros mejorados para empleados
- **Reportes Mejorados**: Interfaz visual para reportes con exportación
- **Gestión de Errores**: Páginas de error personalizadas
- **Configuración Avanzada**: Configuraciones optimizadas de Spring Boot

## 🏗️ Arquitectura

### Estructura del Proyecto
```
src/
├── main/
│   ├── java/com/example/tarea3/
│   │   ├── config/           # Configuraciones
│   │   ├── controllers/      # Controladores REST
│   │   ├── dto/             # Data Transfer Objects
│   │   ├── models/          # Entidades JPA
│   │   ├── repositories/    # Repositorios JPA
│   │   └── services/        # Lógica de negocio
│   └── resources/
│       ├── static/          # Archivos estáticos
│       │   ├── css/         # Estilos personalizados
│       │   └── js/          # JavaScript personalizado
│       └── templates/       # Plantillas Thymeleaf
│           ├── dashboard/   # Dashboard principal
│           ├── employees/   # Gestión de empleados
│           ├── layout/      # Layouts base
│           └── reports/     # Reportes
```

### Tecnologías Utilizadas
- **Spring Boot 3.4.4**: Framework principal
- **Spring Data JPA**: Persistencia de datos
- **Thymeleaf**: Motor de plantillas
- **Bootstrap 5**: Framework CSS
- **MySQL 8**: Base de datos
- **Maven**: Gestión de dependencias
- **Bean Validation**: Validaciones

## 🔧 Configuración

### Requisitos Previos
- Java 17+
- MySQL 8.0+
- Maven 3.6+

### Base de Datos
```sql
# Configuración en application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/hr
spring.datasource.username=user
spring.datasource.password=wasa2
```

### Ejecución
```bash
# Navegar al directorio
cd Tarea3

# Compilar y ejecutar
mvn spring-boot:run
```

## 📱 Funcionalidades

### 🏠 Dashboard Principal
- Estadísticas en tiempo real
- Accesos rápidos a funcionalidades principales
- Información del sistema
- Estado de la base de datos

### 👥 Gestión de Empleados
- **Lista Paginada**: Visualización de empleados con paginación
- **Búsqueda Avanzada**: Filtros por nombre, apellido y puesto
- **Formularios Mejorados**: Validación en tiempo real
- **Vista Detallada**: Información completa del empleado
- **CRUD Completo**: Crear, leer, actualizar y eliminar

### 📊 Reportes
- **Empleados con Salarios Altos**: Lista de empleados con salario > $15,000
- **Departamentos por Ciudad**: Análisis geográfico
- **Gerentes Experimentados**: Gerentes con más de 5 años de experiencia
- **Exportación**: Función de impresión optimizada

## 🎯 Características Técnicas

### Validaciones
- **Frontend**: Validación en tiempo real con JavaScript
- **Backend**: Bean Validation con anotaciones
- **Base de Datos**: Restricciones de integridad

### Seguridad
- **Validación de Entrada**: Sanitización de datos
- **Manejo de Errores**: Información controlada al usuario
- **Transacciones**: Rollback automático en errores

### Performance
- **Lazy Loading**: Carga diferida de relaciones
- **Paginación**: Optimización de consultas grandes
- **Caché**: Configuración de caché para recursos estáticos
- **Pool de Conexiones**: HikariCP optimizado

---

**Versión**: 2.0  
**Última Actualización**: Julio 2025  
**Estado**: Activo en Desarrollo
