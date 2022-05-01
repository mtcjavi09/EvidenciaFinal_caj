/*
    EVIDENCIA FINAL     : Imc
    AUTORA              : Maria Tchijov Cruz
    FECHA               : 19 abr 2022
    Tabla IMC de la base de datos con getters y setters
*/

package net.codejava.entity;

//Se importan las librerías necesarias para la entidad
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import net.codejava.dto.ImcDTO;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "IMC")
@Data
public class Imc 
{
    //Columnas que contendrá la tabla en base de datos
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="usuario")
    private String usuario;

    @Column(name="estatura")
    private float estatura;
    
    @Column(name="peso")
    private float peso;
    
    @Column(name="imc")
    private float imc;
    
    @Column(name="fecha")
    @CreationTimestamp
    private Date fecha;
    
    //Se generan los constructores necesarios para la aplicación
    //Constructor vacío
    public Imc() {}
    
    //Constructor con datos
    public Imc(ImcDTO imcDTO) 
    {
        this.id = imcDTO.getId();
        this.usuario = imcDTO.getUsuario();
        this.estatura = imcDTO.getEstatura();
        this.peso = imcDTO.getPeso();
        this.imc = imcDTO.getImc();
        this.fecha = imcDTO.getFecha();
    }

    //Getters y setters
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getUsuario() {return usuario;}
    public void setUsuario(String usuario) {this.usuario = usuario;}
    public float getEstatura() {return estatura;}
    public void setEstatura(float estatura) {this.estatura = estatura;}
    public float getPeso() {return peso;}
    public void setPeso(float peso) {this.peso = peso;}
    public float getImc() {return imc;}
    public void setImc(float imc) {this.imc = imc;}
    public Date getFecha() {return fecha;}
    public void setFecha(Date fecha) {this.fecha = fecha;} 
}