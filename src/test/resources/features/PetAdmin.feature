@PetAdmin
Feature: Gestión de mascotas de la tienda

  @CrearMascota
  Scenario Outline: Crear una nueva Mascota
    Given estoy en la pagina de mascotas
    When creo una mascota con id"<id>", nombre"<name>", categoria"<category>", foto"<photoUrl>", tag"<tag>", estado"<status>"
    Then el código del estado de la respuesta debe ser <codigo>
    And valido el header de la respuesta
    And la respuesta debe contener el id"<id>", nombre"<name>", categoria"<category>", foto"<photoUrl>", tag"<tag>", estado"<status>"
    Examples:
      | id | name    | category | photoUrl                      | tag    | status    | codigo |
      | 1  | Laica   | perro    | http://example.com/photo1.jpg | Karen1 | available | 200    |
      | 2  | Minina  | gato     | http://example.com/photo2.jpg | Karen2 | pending   | 200    |
      | 3  | Cuchito | conejo   | http://example.com/photo3.jpg | Karen3 | sold      | 200    |
      | 4  | Tim     | ave      | http://example.com/photo4.jpg | Karen4 | available | 200    |

  @BuscarMascota
  Scenario Outline: Buscar Mascota por ID
    Given estoy en la pagina de mascotas
    When busco una mascota con id"<id>"
    Then el código del estado de la respuesta debe ser <codigo>
    And el codigo de error es "<errorCode>"
    And el tipo de error es "<errorType>"
    And el mensaje de error contiene "<errorMessage>"
    And valido el header de la respuesta
    Examples:
      | id                     | codigo | errorCode | errorType | errorMessage                    |
      | 3                      | 200    | -         | -         | -                               |
      | 1111111111111111111111 | 404    | 404       | unknown   | java.lang.NumberFormatException |
      | 111111111111           | 404    | 1         | error     | Pet not found                   |

  @EliminarMascota
  Scenario Outline: Eliminar Mascota por ID
    Given estoy en la pagina de mascotas
    When elimino una mascota con id"<id>"
    Then el código del estado de la respuesta debe ser <codigo>
    And codigo de error es "<errorCode>"
    And tipo de error es "<errorType>"
    And mensaje de error es "<errorMessage>"
    And valido el header de la respuesta
    Examples:
      | id                     | codigo | errorCode | errorType | errorMessage                    |
      | 3                      | 200    | 200       | unknown   | 3                               |
      | 1111111111111111111111 | 404    | 404       | unknown   | java.lang.NumberFormatException |
      | 3                      | 404    |           |           |                                 |