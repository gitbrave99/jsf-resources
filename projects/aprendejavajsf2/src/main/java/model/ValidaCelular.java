/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author georg
 */
@FacesValidator("vcelular")
public class ValidaCelular implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object t) throws ValidatorException {
        String nCelular= t.toString().trim();
        if ( nCelular.length()==0) {
             throw new ValidatorException(new FacesMessage("Ingrese celular"));
        }else{
            String erTexto= "^9\\d\\d-\\d\\d\\d-\\d\\d\\d$";
            boolean ok = Pattern.matches(erTexto,nCelular);
            if (!ok){
                throw new ValidatorException(new FacesMessage("Formato debe ser +503 0000-0000"));
            }
        }
    }
    
}
