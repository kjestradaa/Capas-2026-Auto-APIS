package com.everis.base.stepDefinitions;

import com.everis.base.UserStoreStep;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class UserStoreSD {
    @Steps
    UserStoreStep userStoreStep;

    @When("inicio sesión con el usuario {string} y contraseña {string}")
    public void inicioSesiónConElUsuarioYContraseña(String username, String password) {
        userStoreStep.loginUsuario(username, password);
    }

    @Then("el codigo de respuesta es del inicio de session es {int}")
    public void elCodigoDeRespuestaEsDelInicioDeSessionEs(int code){
        userStoreStep.validarCodigoRespuesta(code);
    }
}
