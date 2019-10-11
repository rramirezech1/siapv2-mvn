/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.util;

import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author CTESOFT
 */
@FacesValidator("validatorFechaActividad")
public class ValidatorFechaActividad implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }

        Date fechaInicio = (Date) JsfUtil.getVarSesion("fechaInicio");
        Date fechaFin = (Date) JsfUtil.getVarSesion("fechaFin");
        
        Date fecha = (Date) value;
        if (fecha.before(fechaInicio) || fecha.after(fechaFin)) {
            JsfUtil.addWarningMessage("La fecha seleccionada esta fuera del rango de inicio y fin del convenio seleciconado.");
        }
    }
}
