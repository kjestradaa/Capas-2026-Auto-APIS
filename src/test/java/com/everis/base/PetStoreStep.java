package com.everis.base;

import com.everis.base.models.Order;
import static io.restassured.RestAssured.given;

public class PetStoreStep {

    private String URL_BASE= "https://petstore.swagger.io/v2";
    private int codigoRespuesta;
    private Order respuestaOrder;

    public void crearOrden(int id, int petId, int quantity){

        Order nuevaOrden = new Order(id, petId, quantity);

        codigoRespuesta = given()
                .baseUri(URL_BASE)
                .contentType("application/json")
                .body(nuevaOrden)
                .when()
                .post("/store/order")
                .then()
                .statusCode(200)
                .extract()
                .statusCode();

        respuestaOrder = given()
                .baseUri(URL_BASE)
                .when()
                .get("/store/order/"+id)
                .as(Order.class);

        System.out.println("ID Creado: " + respuestaOrder.getPetId());
        System.out.println("PetID Creado: " + respuestaOrder.getPetId());
        System.out.println("Quantity Creado: " + respuestaOrder.getQuantity());

    }


    public void validarCodigoRespuesta(int codigoEsperdo){
        if(codigoRespuesta != codigoEsperdo){
            throw new AssertionError("Código esperado: " +codigoEsperdo + "Código Obtenido: " +codigoRespuesta);
        }
    }

    public Order obtenerRespuestaOrder(){
        return respuestaOrder;
    }



}
