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
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;
import net.codejava.dto.ImcDTO;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "imc")
@Data
public class Imc 
{
    //Columnas que contendrá la tabla en base de datos
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)    
    private int id;

    @Column(name="email")
    @Email(message="El email debe tener el formato válido")
    private String email;

    @Column(name="estatura")
    @Min(value=1, message="La estatura no puede ser menor a 1 metro")
    private float estatura;
    
    @Column(name="peso")    
    @Min(value=13, message="El peso no puede ser menor que 13")
    private float peso;
    
    @Column(name="imc")
    @Positive(message="El IMC no puede ser negativo")
    @Min(value=1, message="El IMC no puede ser cero")
    private float imc;
    

    @Column(name="explicaResultado")
    @NotNull(message = "La explicación del resultado no puede ser nulo")
    private String explicaResultado;

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
        this.email = imcDTO.getEmail();
        this.estatura = imcDTO.getEstatura();
        this.peso = imcDTO.getPeso();
        this.imc = imcDTO.getImc();
        this.explicaResultado = imcDTO.getExplicaResultado();
        this.fecha = imcDTO.getFecha();
    }

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
    public String getExplicaResultado() {return explicaResultado;}
    public void setExplicaResultado(String explicaResultado) {this.explicaResultado = explicaResultado;}
    public Date getFecha() {return fecha;}
    public void setFecha(Date fecha) {this.fecha = fecha;} 
}