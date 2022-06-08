/*
    EVIDENCIA FINAL   :   Methods
    AUTORA            :   Maria Tchijov Cruz
    FECHA             :   06 may 2022
    Clase que contendrá los métodos necesarios para el funcionamiento de la webapp de la evidencia final.
*/

package net.codejava;


import static java.lang.Math.pow;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class Methods 
{

    //Variable con llave de encriptado y desencriptado
    String LLAVE = "POSTGRESQL";

    //Método para calcular el IMC
    public float calculaImc(float estatura, float peso)
    {
        //Se calcula el imc con su fórmula estándar y se regresa el resultado
        float imc = (float) (peso / pow(estatura,2.0));
        return imc;
    }

    //Método para definir la explicación del resultado
    public String explicaResultado(float imc)
    {
        //Si el imc es menor a 18.5 es peso bajo
        if(imc < 18.5)
        {return "Peso bajo";}

        else
        {
            //Si el imc está entre 18.5 y 24.9 es peso normal
            if(imc>=18.5 && imc<=24.9)
            {return "Peso normal";}

            else
            {
                //Si el imc está entre 25 y 29.0 es sobrepeso
                if(imc>=25.0 && imc<=29.9)
                {return "Sobrepeso";}

                //Si el imc es mayor a 30 es obesidad
                else
                {return "Obesidad";}
            }
        }
    }

    //Método para generar la clave de encriptación
    public SecretKeySpec generarClave(String llave) 
    {
        try 
        {
            byte[] cadena = llave.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            cadena = md.digest(cadena);
            cadena = Arrays.copyOf(cadena, 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(cadena, "AES");
            return secretKeySpec;
        } 
        catch (Exception e) 
        {return null;}
    }

    //Método para encriptar la contraseña
    public String encriptar(String contraseña) 
    {
        try
        {
            SecretKeySpec secretKeySpec = generarClave(LLAVE);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] cadena = contraseña.getBytes("UTF-8");
            byte[] encriptada= cipher.doFinal(cadena);
            String contraseñaEncriptada = Base64.encode(encriptada);
            return contraseñaEncriptada;
        }
        catch(Exception e)
        {return "";}
    }

    //Método para descencriptar la contraseña
    public String descencriptar(String contraseña) 
    {
        try
        {
            SecretKeySpec secretKeySpec = generarClave(LLAVE);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] cadena = Base64.decode(contraseña);
            byte[] desencriptado = cipher.doFinal(cadena);
            String contraseñaEncriptada = new String(desencriptado);
            return contraseñaEncriptada;
        }
        catch(Exception e)
        {return "";}
    }
}