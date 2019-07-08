package sv.gob.mined.apps.siapv2.mvn.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "desEncriptar")
@Scope(value = "view")
public class DesEncriptar {

    private Boolean deshabilitado = true;
    private String verificacion;
    private String resultado;

    public DesEncriptar() {
    }

    public void encripValor() {
        String tStr; //Desconocido
        String key = "CGI";

        int sourcePtr; //Puntero a cadena (passUsr)
        int keyPtr; //Puntero a cadena (llave de encriptado)
        int keyLen; //Longitud de la llave de encriptado
        int tempVal = 0; //Valor temporal del codigo ASCII de la salida
        int tempKey = 0; //Valor temporal de la llave de encriptado

        keyPtr = 0; //En el codigo de PB es 1
        keyLen = key.length() - 1;

        for (sourcePtr = 0; sourcePtr < verificacion.length(); sourcePtr++) {
            tempVal = verificacion.charAt(sourcePtr);
            tempKey = key.charAt(keyPtr);

            tempVal += tempKey;

            //Asefura que los valores ASCII se encuentren en el rango de 0 a 255
            while (tempVal > 255) {
                tempVal -= 255;
            }

            tStr = String.valueOf((char) tempVal);

            resultado = resultado + tStr;

            keyPtr++;
            if (keyPtr > keyLen) {
                keyPtr = 0;
            }
        }

    }// Fin del metodo encriptar

    public void desencripValor() {
        char tStr; //Desconocido
        String key = "CGI";
        String passDesencrip = "";

        int sourcePtr; //Puntero a cadena (passUsr)
        int keyPtr; //Puntero a cadena (llave de encriptado)
        int keyLen; //Longitud de la llave de encriptado
        int tempVal = 0; //Valor temporal del codigo ASCII de la salida
        int tempKey = 0; //Valor temporal de la llave de encriptado

        keyPtr = 0; //En el codigo de PB es 1
        keyLen = key.length() - 1;

        for (sourcePtr = 0; sourcePtr < resultado.length(); sourcePtr++) {
            tempVal = resultado.charAt(sourcePtr);
            tempKey = key.charAt(keyPtr);

            tempVal -= tempKey;

            //Asefura que los valores ASCII se encuentren en el rango de 0 a 255
            while (tempVal < 0) {
                tempVal += 255;
            }

            tStr = (char) tempVal;

            passDesencrip = passDesencrip + tStr;

            keyPtr++;
            if (keyPtr > keyLen) {
                keyPtr = 0;
            }
        }

    }// Fin del metodo desencriptar

    public void nuevaVerificacion() {
        deshabilitado = false;
        verificacion = "";
        resultado = "";
    }

    public Boolean getDeshabilitado() {
        return deshabilitado;
    }

    public void setDeshabilitado(Boolean deshabilitado) {
        this.deshabilitado = deshabilitado;
    }

    public String getVerificacion() {
        return verificacion;
    }

    public void setVerificacion(String verificacion) {
        this.verificacion = verificacion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}//Fin de la clase
