package com.everis.base;

import com.everis.base.models.User;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserStoreStep {
    private String URL_BASE = "https://petstore.swagger.io/v2";
    private int codigoRespuesta;
    private User respuestaUser;

    public void crearUsuario(int id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {

        User nuevoUsuario = new User(id, username, firstName, lastName, email, password, phone, userStatus);
        List<User> usuarios = Collections.singletonList(nuevoUsuario);

        codigoRespuesta = given()
                .baseUri(URL_BASE)
                .contentType("application/json")
                .body(usuarios)
                .when()
                .post("/user/createWithList")
                .then()
                .statusCode(200)
                .extract()
                .statusCode();

        respuestaUser = given()
                .baseUri(URL_BASE)
                .when()
                .get("/user/" + username)
                .as(User.class);
    }

    public void loginUsuario(String username, String password) {
        codigoRespuesta = given()
                .baseUri(URL_BASE)
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get("/user/login")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .statusCode();
    }

    public void validarCodigoRespuesta(int codigoEsperdo) {
        if (codigoRespuesta != codigoEsperdo) {
            throw new AssertionError("Código esperado: " + codigoEsperdo + "Código Obtenido: " + codigoRespuesta);
        }
    }
}
