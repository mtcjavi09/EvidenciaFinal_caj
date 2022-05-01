/*
    EVIDENCIA FINAL     : ImcDTO
    AUTORA              : Maria Tchijov Cruz
    FECHA               : 01 may 2022
    DTO para la entidad IMC
*/

package net.codejava.dto;

import java.util.Date;
import lombok.Data;
import static java.lang.Math.pow;

@Data
public class ImcDTO 
{
    //Atributos de la clase
    private int id;
    private String email;
    private float estatura;
    private float peso;
    private float imc;
    private Date fecha;

    //Getters y setters
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public float getEstatura() {return estatura;}
    public void setEstatura(float estatura) {this.estatura = estatura;}
    public float getPeso() {return peso;}
    public void setPeso(float peso) {this.peso = peso;}
    public float getImc() {return imc;}
    public void setImc(float imc) {this.imc = imc;}
    public Date getFecha() {return fecha;}
    public void setFecha(Date fecha) {this.fecha = fecha;}
}
