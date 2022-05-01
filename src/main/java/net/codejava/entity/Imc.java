/*
    EVIDENCIA FINAL     : Imc
    AUTORA              : Maria Tchijov Cruz
    FECHA               : 19 abr 2022
    Entidad principal de la aplicación
*/

package net.codejava.entity;

//Se importan las librerías necesarias para la entidad
import static java.lang.Math.pow;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "imc")
@Data
public class Imc 
{
    //Columnas que contendrá la base de datos
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

    @Column(name="edad")
    private int edad;
    
    @Column(name="genero")
    private char genero;

    @Column(name="estatura")
    private float estatura;
    
    @Column(name="peso")
    private float peso;
    
    @Column(name="imc")
    private float imc;
    
    @Column(name="fecha")
    private String fecha;
    
    //Se generan los constructores necesarios para la aplicación
    //Constructor vacío
    public Imc() {}
    
    //Constructor con datos

    public Imc(int id, String nombre, String apellido, int edad, char genero, float estatura, float peso) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.estatura = estatura;
        this.peso = peso;
        this.imc = (float) (peso / pow(estatura,2.0));
        this.fecha = LocalDate.now().toString();
    }
    
    
    //Getters y setters
        public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getApellido() {return apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}
    public int getEdad() {return edad;}
    public void setEdad(int edad) {this.edad = edad;}
    public char getGenero() {return genero;}
    public void setGenero(char genero) {this.genero = genero;}
    public float getEstatura() {return estatura;}
    public void setEstatura(float estatura) {this.estatura = estatura;}
    public float getPeso() {return peso;}
    public void setPeso(float peso) {this.peso = peso;}
    public float getImc() {return imc;}
    public void setImc(float imc) {this.imc = imc;}
    public String getFecha() {return fecha;}
    public void setFecha(String fecha) {this.fecha = fecha;}
}