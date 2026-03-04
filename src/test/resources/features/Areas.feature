Feature: Areas

  @ConsultarArea1
  Scenario: Consulta de areas
    Given dado que estoy en la página
    When consulto el area con ID 2267
    Then valido que el código de respuesta sea 200

  @ConsultarArea2
  Scenario Outline: Consulta de areas
    Given dado que estoy en la página
    When consulto el area con ID <id>
    Then valido que el código de respuesta sea <codigo>
    And la respuesta debe contener el id <id>, name <name>, parentAreaId <parentAreaId>

    Examples:
      | id   | name    | parentAreaId | codigo |
      | 2001 | Africa  | 2267         | 200    |
      | 2002 | Albania | 2077         | 201    |
      | 2002 | Albania | 2077         | 200    |



  @Creanuevaarea
  Scenario: Creación de nueva área de fútbol
    Given dado que estoy en la página
    When creo una nueva área con ID "1111" nombre "Mi Nueva Area" y código "MNA"
    Then valido que el código de respuesta sea 200