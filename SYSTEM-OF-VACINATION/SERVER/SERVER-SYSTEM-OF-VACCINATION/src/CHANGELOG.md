## CLASE

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