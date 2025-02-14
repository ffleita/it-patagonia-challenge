# it-patagonia-challenge

### Consigna
El challenge se trata de generar los siguientes 3 endpoints:

- Uno que traiga las empresas que hicieron transferencias el último mes
- Otro que traiga las empresas que se adhirieron el último mes.
- El último que haga la adhesión de una empresa.

Deseable: usar arquitectura hexagonal
Base: puede usarse relacional o no relacional
Datos de la empresa: CUIT, Razón Social, Fecha Adhesión
Datos de la transferencia: Importe, Id Empresa, Cuenta Débito, Cuenta Crédito.

### Solución
Se resolvió el challenge utiliando una arquitectura hexagonal, para la estructura de carpetas y la nomenclatura de los archivos tomé una referencia de internet dado que no tengo experiencia con este tipo de arquitecturas, solo conocimientos teóricos.
Se utilizó el campo CUIT de la empresa como primary key de la tabla para optimizar espacio.
Se agregó el atributo fechaTransferencia a la entidad Transferencia ya que por consigna se pide obtener aquellas empresas que hayan realizado transacciones en el último mes.
Se configuraron queries custom en la implementación del repositorio JPA en la capa de infraestructura para filtrar los resultados en la consulta a la BD y no en un proceso utilizando Java.
Se agregó un archivo de inicialización data.sql.

El stack tecnológico utilizado es el siguiente:
- Java versión 21
- SpringBoot
- Base de datos Mysql (local)
- Mockito
- JUnit
- Base de datos H2 embebida para los tests

El principal desafío que encontré fue implementar los conceptos de casos de uso, puertos y adaptadores. Pude abstraer las capas domain y application de las dependencias externas incorporadas por SpringBoot. Para proyectos chicos
no parece una solución óptima ya que la implementación es compleja, pero para proyectos grandes es una solución robusta.

### Paths expuestos para cada servicio
- /empresas/added/date GET
- /empresas/transferencias/date GET
- /empresas POST body: {
        "cuit": 30123456781,
        "razonSocial": "Empresa"
    }
