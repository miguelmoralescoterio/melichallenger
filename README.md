# MELI Challenger
Prueba tecnica

# Detalles
El proyecto fue desarrolladon con spring boot bajo el jdk-11
bajo la estructura maven

# Estructura
Se tiene una estructura de grupo de servicios, para crea microservicios dentro del mismo 
proyecto que puedan cumplira cada uno con sus funciones

 - Melichallenger: proyecto POM que contiene las especificaciones y proporciona por herencia las dependencias a los proyectos hijos
 - businessdomain: Proyecto POM donde se almacenan los microservicios que contienens las logicas de negocio
 - users: proyecto POM con la structura o framework spring boot donde esta la logica de negocio para el manejeo de los usuarios, targets y prestamos.

# Data
La base de datos implementada es MariaDB 15.1
En la ruta database, se encuantran dos ficheros:
 - ddl.sql: que contiene la informacion de las definiciones de la base de datos
 - seed.sql: contiene informaciòn con la informacion de pruebas que se uso durante el desarrollo

La base de datos se configuró en el puerto 3306, con los datos de conexion: 
```
username = melidbuser 
pasword  = m3l4ch4llep3r
```

# Ejecucion
El proyecto se ejecuca con el siguente comando estando en la raiz del proyecto
```
java -jar businessdomain/users/build/users-1.0.1.jar
```
Esto ejecutará la aplicacion en el puerto 8080

# Endpoint
Para la documentación de los endpoints se utilizó Swagger, que nos permite documentar todos los endpoints dentro de la aplicaión
esto nos permite acceder a una interfaz donde se pueden ejecutar los endpoints, desde la sigueinte url:
```
http://localhost:8080/swagger/swagger-ui/index.html
```

En la interfaz de swagger tenemos por grupos users y loans, donde estan separados los endpipints de usuarios y prestamos respectivamente.

# Creator
Miguel Morales Coterio
**[GitLab]( https://gitlab.com/miguelmoralescoterio )**.
**[GitHub]( https://github.com/miguelmoralescoterio )**.
**[Bitbucket]( https://bitbucket.org/miguelmoralescoterio )**.

* https://twitter.com/mmoralescoterio 
* https://www.linkedin.com/in/miguelmoralescoterio 
