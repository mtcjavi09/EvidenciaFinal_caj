/*
    EVIDENCIA FINAL   :   Methods
    AUTORA            :   Maria Tchijov Cruz
    FECHA             :   06 may 2022
    Clase que ayudará a guardar las excepciones de la webapp y sus causas.
*/

package net.codejava;

public class Error 
{
    //Atributos de la clase
    private String error;
    private String causa;

    //Constructor de la clase
    public Error(String error, String causa) 
    {
        this.error = error;
        this.causa = causa;
    }

    //Getters y setters
    public String getError() {return error;}
    public void setError(String error) {this.error = error;}
    public String getCausa() {return causa;}
    public void setCausa(String causa) {this.causa = causa;}    
}