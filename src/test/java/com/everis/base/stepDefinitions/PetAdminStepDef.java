package com.everis.base.stepDefinitions;

import com.everis.base.PetAdminStep;
import com.everis.base.models.Category;
import com.everis.base.models.Pet;
import com.everis.base.models.Tag;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PetAdminStepDef {

    private int codigoRespuesta;
    private boolean flagIdValido=false;

    @Steps
    PetAdminStep petAdmin;

    @Given("estoy en la pagina de mascotas")
    public void estoyEnLaPaginaMascotas() {
    }

    @When("creo una mascota con id{string}, nombre{string}, categoria{string}, foto{string}, tag{string}, estado{string}")
    public void creoUnaMascota(String id, String name, String categoryName, String fotoUrl, String tag, String status) {
        Category category = new Category(1, categoryName);
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(0, fotoUrl);
        List<Tag> tags = new ArrayList<>();
        tags.add(0, new Tag(1, tag));
        petAdmin.crearPet(id, category, name, photoUrls, tags, status);
    }

    @Then("el código del estado de la respuesta debe ser {int}")
    public void elCódigoDelEstadoDeLaRespuestaDebeSer(int codigo) {
        petAdmin.validarCodigoRespuesta(codigo);
        codigoRespuesta = codigo;
    }

    @And("valido el header de la respuesta")
    public void elHeaderDeLaRespuestaNoDebeSerNull() {
        petAdmin.validarHeaderRespuesta();
    }

    @And("la respuesta debe contener el id{string}, nombre{string}, categoria{string}, foto{string}, tag{string}, estado{string}")
    public void laRespuestaDebeContenerElIdNombreEstado(String id, String name, String categoryName, String fotoUrl, String tag, String status) {
        Pet pet = petAdmin.obtenerRespuestaPet();
        assertNotNull(pet);
        assertEquals(id, pet.getId());
        assertEquals(name, pet.getName());
        assertEquals(categoryName, pet.getCategory().getName());
        assertEquals(fotoUrl, pet.getPhotoUrls().get(0));
        assertEquals(tag, pet.getTags().get(0).getName());
        assertEquals(status, pet.getStatus());
    }

    @When("busco una mascota con id{string}")
    public void buscoUnaMascotaConIdId(String id) {
        petAdmin.searchPetById(id);
    }


    @And("el codigo de error es {string}")
    public void elCodigoDeErrorEs(String errorCode) {
        if (codigoRespuesta != 200) {
            String errorCodeObtenido = petAdmin.validarErrorCodeRespuesta(errorCode);
            assertEquals(errorCode, errorCodeObtenido);
        }
    }

    @And("el tipo de error es {string}")
    public void elTipoDeErrorEs(String errorType) {
        if (codigoRespuesta != 200) {
            String errorTypeObtenido = petAdmin.validarErrorTypeRespuesta(errorType);
            assertEquals(errorType, errorTypeObtenido);
        }
    }

    @And("el mensaje de error contiene {string}")
    public void elMensajeDeErrorContiene(String errorMessage) {
        if (codigoRespuesta != 200) {
            String errorMessageObtenido = petAdmin.validarErrorMessageRespuesta(errorMessage);
            assertEquals(errorMessage, errorMessageObtenido.substring(0, Math.min(errorMessageObtenido.length(),31)));
        }
    }

    @When("elimino una mascota con id{string}")
    public void eliminoUnaMascotaConId(String id) {
        try {
            Integer.parseInt(id);
            flagIdValido= true;
        } catch (NumberFormatException e) {
            flagIdValido = false;
        }
        petAdmin.deletePetById(id);
    }

    @And("el mensaje de error es {string}")
    public void elMensajeDeErrorEs(String errorMessage) {
        if (codigoRespuesta != 200) {
            String errorMessageObtenido = petAdmin.validarErrorMessageRespuesta(errorMessage);
            assertEquals(errorMessage, errorMessageObtenido.substring(0, Math.min(errorMessageObtenido.length(),31)));
        }
    }

    @And("codigo de error es {string}")
    public void codigoDeErrorEs(String errorCode) {
        if (!flagIdValido||codigoRespuesta == 200) {
            String errorCodeObtenido = petAdmin.validarErrorCodeRespuesta(errorCode);
            assertEquals(errorCode, errorCodeObtenido);
        } else {
            System.out.println("No hay headers ni body del response");
        }
    }

    @And("tipo de error es {string}")
    public void tipoDeErrorEs(String errorType) {
        if (!flagIdValido||codigoRespuesta == 200) {
            String errorTypeObtenido = petAdmin.validarErrorTypeRespuesta(errorType);
            assertEquals(errorType, errorTypeObtenido);
        }
    }

    @And("mensaje de error es {string}")
    public void mensajeDeErrorContiene(String errorMessage) {
        if (!flagIdValido||codigoRespuesta == 200) {
            String errorMessageObtenido = petAdmin.validarErrorMessageRespuesta(errorMessage);
            assertEquals(errorMessage, errorMessageObtenido.substring(0, Math.min(errorMessageObtenido.length(),31)));
        }
    }


}
