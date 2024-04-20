# Sistema de Gestión de Biblioteca

Este es un proyecto de ejemplo de un Sistema de Gestión de Biblioteca desarrollado en Java utilizando Hibernate como framework de mapeo objeto-relacional (ORM) y MySQL como base de datos.

## Requisitos

- Java Development Kit (JDK) 8 o superior
- Maven
- MySQL Server
- Conector MySQL para Java

## Configuración

1. Clona este repositorio en tu máquina local:

    ```bash
    git clone https://github.com/tu_usuario/sistema-gestion-biblioteca.git
    ```

2. Crea una base de datos MySQL llamada `biblioteca`.

3. Importa el proyecto en tu IDE de preferencia como un proyecto Maven existente.

4. Configura la conexión a la base de datos MySQL en el archivo `src/main/resources/hibernate.cfg.xml`, modificando las propiedades `hibernate.connection.url`, `hibernate.connection.username` y `hibernate.connection.password` según tu configuración de MySQL.

5. Ejecuta la aplicación. Puedes ejecutar el método `main` en la clase `Main` para iniciar la aplicación.

## Funcionalidades

El sistema de gestión de biblioteca ofrece las siguientes funcionalidades:

- Gestión de libros: permite agregar, editar, eliminar y buscar libros en la biblioteca.
- Gestión de lectores: permite agregar, editar, eliminar y buscar lectores registrados.
- Gestión de préstamos: permite registrar préstamos de libros a lectores, devolver libros prestados y ver el historial de préstamos de un lector.

## Contribuciones

Las contribuciones son bienvenidas. Si deseas contribuir a este proyecto, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama para tu funcionalidad (`git checkout -b feature/nueva-funcionalidad`).
3. Haz tus cambios y commitéalo (`git commit -am 'Agrega una nueva funcionalidad'`).
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`).
5. Crea un nuevo Pull Request.

## Licencia

Este proyecto está bajo la Licencia MIT. Para más detalles, consulta el archivo [LICENSE](LICENSE).
