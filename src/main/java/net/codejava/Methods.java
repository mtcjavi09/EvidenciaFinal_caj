/*
    EVIDENCIA FINAL   :   Methods
    AUTORA            :   Maria Tchijov Cruz
    FECHA             :   06 may 2022
    Clase que contendrá los métodos necesarios para el funcionamiento de la webapp de la evidencia final.
*/

package net.codejava;


import static java.lang.Math.pow;

public class Methods 
{
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
}