# Proyecto Sistema Gestion de Vacunas

cambios registrados para elaborar un

Added (Añadido): Para nuevas funcionalidades.

Changed (Cambiado): Para cambios en funcionalidades existentes.

Deprecated (Obsoleto): Para funcionalidades que serán eliminadas a futuro.

Removed (Eliminado): Para funcionalidades eliminadas en esta versión.

Fixed (Corregido): Para correcciones de errores (bugs).

Security (Seguridad): Para correcciones de vulnerabilidades.

## [0] - 25/10/2025

### Added

#### Servidor:

- Se cambio nombre de clases de modelo
- se grego int doses en Vaccinate
- se cambio totalDoses en Vaccine para el total de dosis maximas
- En person se agrego un arbol binario de vaccinate que representa las vacunaciones que ha realizado una persona
- Se implemento comparable en Person, Vaccine y VaccineModel
- Se inicio socket en servidor con clase Control y Presenter
- Se agrego maven para actualizacion automatica de dependencias y librerias externas, eliminando la necesidad de usar archivos .jar agregandolos en pom.xml
- Se creo clase Request y response para enviar archivos .json al cliente
- Se creo calse en  FilesPersistence para agregar lectura de json y eliminar este del modelo
- Se agrego arbol binario de personas y vacunas en clase VaccineModel para tener registro de datos de base de datos en el proyecto por lado del servidor

## [0.1] - 03/11/2025

### Fixed

#### Server:
- Se arreglo la mayot parte del modelo, para adaptarse a sockets
- Se implemento lista no por parametro de binary tree sino utilizando la logica actual
- Se realizaron cambios en clase ModelVaccination, preparando dejar listo para actualizar el presenter
- Se simplifico logica del modelo
- Queda pendiente terminar de mejorar modelo, actualizar presenter y reestructurar logica jsons en persistence, podiblemente con uso de gson

### Aded 

#### Server:

- Se implemento logica de paquete net, contiene response para servidor y request para vista, se debe implementar en ambos proyectos usando la misma logica
- Se preparo Connection para coneccion con gson entre cliente y servidor

### Deprecated

### Server: 

- Algun que otro metodo del modelo fueron despreciados y estarán comentados en caso de una futura actualización que utilice estos