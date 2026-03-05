package com.everis.base;

import com.everis.base.models.Category;
import com.everis.base.models.Pet;
import com.everis.base.models.Tag;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PetAdminStep {
    private String URL_BASE= "https://petstore.swagger.io/v2";
    private int codigoRespuesta;
    private ExtractableResponse<Response> respuestaPetCreated;
    private ExtractableResponse<Response> respuestaPetGet;
    private Pet respuestaGetPetById;

    private String errorCode;
    private String errorType;
    private String errorMessage;

    public void crearPet(String id, Category category, String name, List<String> photoUrls, List<Tag> tags, String status){
        Pet nuevoPet = new Pet( id,  category,  name, photoUrls,  tags, status);

        respuestaPetCreated = given()
                .baseUri(URL_BASE)
                .contentType("application/json")
                .body(nuevoPet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .extract();
        codigoRespuesta = respuestaPetCreated.statusCode();

        respuestaGetPetById = given()
                .baseUri(URL_BASE)
                .when()
                .get("/pet/"+id)
                .as(Pet.class);

        System.out.println();
        System.out.println("ID Creado: " + respuestaGetPetById.getId());
        System.out.println("Name Creado: " + respuestaGetPetById.getName());
        System.out.println("Category Creado: " + respuestaGetPetById.getCategory().getName());
        System.out.println("PhotoURL Creado: " + respuestaGetPetById.getPhotoUrls().get(0));
        System.out.println("Tag Creado: " + respuestaGetPetById.getTags().get(0).getName());
        System.out.println("Status Creado: " + respuestaGetPetById.getStatus());

    }

    public void validarCodigoRespuesta(int codigoEsperado){
        if(codigoRespuesta != codigoEsperado){
            throw new AssertionError("Código esperado: " +codigoEsperado + "Código Obtenido: " +codigoRespuesta);
        } else System.out.println("El código es el esperado: " + codigoRespuesta);
    }

    public void validarHeaderRespuesta() {
        String headerValue = String.valueOf(respuestaPetCreated.headers());
        if (headerValue == null) {
            throw new AssertionError("El header es null");
        } else System.out.println("El header no es null y el Content-Type es: " + respuestaPetCreated.header("Content-Type")+"\n");
    }

    public Pet obtenerRespuestaPet(){
        return respuestaGetPetById;
    }

    public void searchPetById(String id){
        respuestaPetGet = given()
                .baseUri(URL_BASE)
                .when()
                .get("/pet/"+id)
                .then()
                .extract();
        codigoRespuesta = respuestaPetGet.statusCode();
        errorCode = respuestaPetGet.jsonPath().getString("code");
        errorType = respuestaPetGet.jsonPath().getString("type");
        errorMessage = respuestaPetGet.jsonPath().getString("message");

        if (codigoRespuesta==200){
            System.out.println("La mascota con ID " + id + " fue encontrada exitosamente.");
            respuestaGetPetById = given()
                    .baseUri(URL_BASE)
                    .when()
                    .get("/pet/"+id)
                    .as(Pet.class);
            System.out.println();
            System.out.println(respuestaGetPetById.toString());
        } else {
            System.out.println("\nNo se encontró la mascota con ID " + id + ". Código de respuesta: " + codigoRespuesta);
        }

    }
    public String validarErrorCodeRespuesta(String errorCodeEsperado){
        if(!errorCode.equalsIgnoreCase(errorCodeEsperado)){
            throw new AssertionError("Código de error esperado: " +errorCodeEsperado + "Código de error Obtenido: " +errorCode);
        } else System.out.println("El código de error es el esperado: " + errorCode);
        return errorCode;
    }

    public String validarErrorTypeRespuesta(String errorTypeEsperado){
        if(!errorType.equalsIgnoreCase(errorTypeEsperado)){
            throw new AssertionError("Tipo de error esperado: " +errorTypeEsperado + "Tipo de error Obtenido: " +errorType);
        } else System.out.println("El tipo de error es el esperado: " + errorType);
        return errorType;
    }

    public String validarErrorMessageRespuesta(String errorMessageEsperado){
        if(!errorMessage.contains(errorMessageEsperado)){
            throw new AssertionError("Mensaje de error esperado: " +errorMessageEsperado + "Mensaje de error Obtenido: " +errorMessage);
        } else System.out.println("El mensaje de error es el esperado: " + errorMessage);
        return errorMessage;
    }

    public void deletePetById(String id) {
        boolean flagIdValido = false;
        respuestaPetGet = given()
                .baseUri(URL_BASE)
                .when()
                .delete("/pet/"+id)
                .then()
                .extract();
        codigoRespuesta = respuestaPetGet.statusCode();
        try {
            Integer.parseInt(id);
            flagIdValido= true;
        } catch (NumberFormatException e) {
            flagIdValido = false;
        }
        if (!flagIdValido || codigoRespuesta == 200) {
        errorCode = respuestaPetGet.jsonPath().getString("code");
        errorType = respuestaPetGet.jsonPath().getString("type");
        errorMessage = respuestaPetGet.jsonPath().getString("message");}

        if (codigoRespuesta==200){
            System.out.println("\nLa mascota con ID " + id + " fue eliminada exitosamente.");
        } else {
            System.out.println("\nNo se encontró la mascota con ID " + id + ". Código de respuesta: " + codigoRespuesta);
        }
    }
}
