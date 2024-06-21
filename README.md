# findyourguide

Este repositorio contiene el backend principal de la aplicación TourFinder. Esta API REST está desarrollada usando Spring Boot y está diseñada para manejar todas las operaciones críticas de la aplicación, incluyendo la gestión de usuarios, autenticación, y transacciones con la base de datos.

## Tecnologías Utilizadas

- Spring Boot: Framework para la creación de aplicaciones y servicios con mínimo esfuerzo.
- Spring Security & JWT: Para la autenticación y la autorización mediante tokens seguros.
- Spring Data JPA: Para la interacción simplificada con la base de datos mediante Java Persistence API.

## Funcionalidades

- [x] CRUD de usuarios
- [x] Autenticación y autorización de usuarios
- [x] Gestión de guías y turistas
- [ ] CRUD de servicios
- [ ] Reservas y gestión de servicios ofrecidos

## Comenzando

### Prerrequisitos

Asegúrate de tener Java 11 o superior instalado en tu máquina, así como Maven para la gestión de dependencias del proyecto.

### Instalación

Clona el repositorio y navega al directorio del proyecto:

```bash
git clone https://github.com/FacuMartinezVidal/findyourguide.git
cd findyourguide
```

Instala todas las dependencias requeridas:

```bash
mvn install
```

### Configuración

Configura las variables de entorno necesarias para la conexión con la base de datos, así como cualquier otro servicio externo requerido.

### Ejecutando la Aplicación

Para iniciar la aplicación en modo de desarrollo, ejecuta:

```bash
mvn spring-boot:run
```

Esto iniciará el servidor en `localhost:8080`, donde la API estará disponible para interactuar.

## Testing

Para ejecutar los tests unitarios y de integración del proyecto:

```bash
mvn test
```

## Contribuyendo

Si deseas contribuir al proyecto, por favor revisa las directrices de contribución antes de enviar tus pull requests.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para detalles.
