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

#### Cliente
- Se añadió la clase `UpdateVaccinatePayLoad` que sirve para transportar toda la información necesario para actualizar un registro de vacunación desde el cliente hacia el servidor 
- Se añadieron los paquetes `POJOS`, `NET/DTO` y `NETWORK`


## [0.1] - 03/11/2025

### Fixed

#### Server:
- Se arreglo la mayot parte del modelo, para adaptarse a sockets
- Se implemento lista no por parametro de binary tree sino utilizando la logica actual
- Se realizaron cambios en clase ModelVaccination, preparando dejar listo para actualizar el presenter
- Se simplifico logica del modelo
- Queda pendiente terminar de mejorar modelo, actualizar presenter y reestructurar logica jsons en persistence, podiblemente con uso de gson

#### Cliente:
- Se arreglaron todos los metodos del presenter para que funcinonen eficientemente con el servidor.
- Se arreglaron e implementaron correctamente los metodos de la `ViewInterface`.

### Aded 

#### Server:

- Se implemento logica de paquete net, contiene response para servidor y request para vista, se debe implementar en ambos proyectos usando la misma logica
- Se preparo Connection para coneccion con gson entre cliente y servidor

### Deprecated

### Server: 

- Algun que otro metodo del modelo fueron despreciados y estarán comentados en caso de una futura actualización que utilice estos

## [0.2] - 08/11/2025

### Fixed

### Server:

- Se a actualizado el servidor para que funcione sin errores
- Se ha modificado conexion entre modelo y persistencia
- Se modificaron rutas en config para funcionar correctamente

## Add

### Server:

- Se ha agregado una clase pojo para unir person con sus demas clases
- Se ha agregado a vacinate el atributo documentNumber de la persona
- Se implementaron hilos en controlador
- El servidor primero carga datos y luego solicita el puerto para despues funcionar
- Se agregaron 2 JSON correspondientes al modelo que remplazan los que antes estaban

## Deleted

### Server

- Se eliminaron las 3 clases dto que eran incoherentes
- Se elimino la clase mapeo, simplificandola a PersonData
- Se eliminaron 3 JSON anteriores de data