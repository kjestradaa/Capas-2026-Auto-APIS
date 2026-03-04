package com.everis.base.stepDefinitions;

import com.everis.base.AreasStep;
import com.everis.base.models.Areas;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.junit.Assert.assertEquals;

public class ConsultaAreaStepDef {

    @Steps
    AreasStep areasStep;

    @Given("dado que estoy en la página")
    public void dadoQueEstoyEnLaPágina() {
    }

    @When("consulto el area con ID {int}")
    public void consultoElAreaConIDId(int id) {
        areasStep.consultaArea(id);
    }

    @Then("valido que el código de respuesta sea {int}")
    public void validoQueElCódigoDeRespuestaSeaCodigo(int codigo) {
        areasStep.validarCodigoRespuesta(codigo);
    }

    @And("^la respuesta debe contener el id (.*), name (.*), parentAreaId (.*)$")
    public void laRespuestaDebeContenerElIdNameParentAreaId(String id, String name, String parentAreaId) {

        Areas areas = areasStep.obtenerRespuestadeAreas();
        assertEquals(id, areas.getId());
        assertEquals(name, areas.getName());
        assertEquals(parentAreaId, areas.getParentAreaId());
    }





    @When("creo una nueva área con ID {string} nombre {string} y código {string}")
    public void creoUnaNuevaAreaConNombreYCodigo(String id, String nombre, String codigo) {
        areasStep.creoNuevaArea(id, nombre, codigo);
    }





}
