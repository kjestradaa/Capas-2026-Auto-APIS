package com.everis.base;

import com.everis.base.models.Areas;
import com.everis.base.models.ChildAreas;
import io.restassured.RestAssured;


import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


public class AreasStep {

    private String URL_BASE = "http://api.football-data.org/v4/";
    private Areas areas;
    private int codigoRespuesta; //solo para POST
    public void consultaArea(int id){
         areas =
                given()
                        .baseUri(URL_BASE)
                        .log()
                        .all()
                        .when()
                        .get("areas/"+id)
                        .as(Areas.class)

                ;
        listarAreas(areas);
    }

    private void listarAreas(Areas areas){
        System.out.println("*******************************");
        System.out.println("*******************************");
        System.out.println("OUT: ID: " + areas.getId());
        System.out.println("OUT: Name: " + areas.getName());
        System.out.println("OUT: Code: " + areas.getCode());
        System.out.println("OUT: Flag: " + areas.getFlag());
        System.out.println("OUT: Parent Area ID: " + areas.getParentAreaId());
        System.out.println("OUT: Parent Area: " + areas.getParentArea());
        System.out.println("*******************************");
        System.out.println("OUT: Child Area: ");
        if(areas.getChildAreas() != null){
            for (ChildAreas childAreas : areas.getChildAreas()){
                System.out.println("OUT: ID: " + childAreas.getId());
                System.out.println("OUT: Name: " + childAreas.getName());
                System.out.println("OUT: Country Code: " + childAreas.getCountryCode());
                System.out.println("OUT: Flag: " + childAreas.getFlag());
                System.out.println("OUT: Parent Area ID: " + childAreas.getParentAreaId());
                System.out.println("OUT: Parent Area: " + childAreas.getParentArea());
                System.out.println("*******************************");
                System.out.println("*******************************");
            }
        }
    }



    public void creoNuevaArea(String id, String nombre, String codigo) {
        Areas nuevaOrden = new Areas(id, nombre, codigo);

        codigoRespuesta = given()
                .baseUri(URL_BASE)
                .contentType("application/json")
                .body(nuevaOrden)
                .when()
                .post("areas")
                .then()
                .statusCode(200)
                .extract()
                .statusCode();
    }

    public void validarCodigoRespuesta(int i){
        assertThat(lastResponse().statusCode(), is(i));
    }

    public Areas obtenerRespuestadeAreas(){
        return areas;
    }



}
