Feature: Gestión de ordenes de la tienda

  @CrearOrden
  Scenario Outline: Crear una nueva orden
    Given dado que estoy en la pagina
    When creo una orden con id<id>, petId<petId>, quantity<quantity>
    Then el código de estado de la respuesta debe ser <codigo>
    And la respuesta debe contener el id<id>, petId<petId>, quantity<quantity>
    Examples:
      | id  | petId | quantity | codigo |
      | 201 | 2     | 3        | 200    |
      | 202 | 3     | 1        | 200    |
      | 203 | 3     | 1        | 200    |
      | 204 | 3     | 1        | 200    |


    @LoginPetStore
    Scenario: Login en la tienda de mascotas
        Given dado que estoy en la pagina
        When inicio sesión con el usuario "lord" y contraseña "12345678"
        Then el codigo de respuesta es del inicio de session es 200