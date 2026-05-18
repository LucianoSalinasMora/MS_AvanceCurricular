# MS_AvanceCurricular
Microservicio encargado de calcular y gestionar el progreso académico de cada estudiante dentro de su carrera.
## Especificaciones Técnicas
* **Puerto:** `8005`
* **Base de Datos (Laragon):** `avancecurricular_db`
* **Tecnologías:** Spring Boot 4.0.6, WebClient Configurado

## Responsabilidades Principales
* Consumir las notas finales de Calificaciones (8004) para computar los créditos aprobados.
* Determinar si el estado académico es REGULAR, RIESGO_ACADEMICO o EGRESADO.

## Endpoints Principales
* `POST /api/v1/avance-curricular` - Inicializar o actualizar el progreso de un alumno.
* `GET /api/v1/avance-curricular/{rut}` - Obtener el porcentaje de avance y estado académico en tiempo real.
