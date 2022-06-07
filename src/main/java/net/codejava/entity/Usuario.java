/*
    EVIDENCIA FINAL     : Usuario
    AUTORA              : Maria Tchijov Cruz
    FECHA               : 01 may 2022
    Tabla usuario de la base de datos con getters y setters
*/

package net.codejava.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import net.codejava.dto.UsuarioDTO;

@Entity
@Table(name = "usuario")
@Data
public class Usuario 
{
    //Columnas que contendrá la tabla en base de datos
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)    
    private int id;
    
    @Column(name="nombre")
    @NotEmpty(message="Necesitas agregar tu(s) nombre(s)")
    private String nombre;

    @Column(name="apellido")
    @NotEmpty(message="Necesitas agregar tus apellidos")
    private String apellido;
    
    @Column(name="edad")
    @Min(value=15, message="No puedes registrarte si tienes menos de 15 años")
    private int edad;
    
    @Column(name="genero")
    private char genero;
    
    @Column(name="email")
    @Email(message="El email debe tener el formato válido")
    @NotNull(message="El email no puede estar nulo")
    private String email;
    
    @Column(name="contraseña")
    @Size(min=4, message="La contraseña debe tener mínimo 4 caracteres")
    @NotEmpty(message="Necesitas ingresar tu contraseña")
    private String contraseña;

    //Se generan los constructores necesarios para la aplicación
    //Constructor vacío
    public Usuario() {}

    //Constructor con datos
    public Usuario(UsuarioDTO usuarioDTO) 
    {
        this.id = usuarioDTO.getId();
        this.nombre = usuarioDTO.getNombre();
        this.apellido = usuarioDTO.getApellido();
        this.edad = usuarioDTO.getEdad();
        this.genero = usuarioDTO.getGenero();
        this.email = usuarioDTO.getEmail();
        this.contraseña = usuarioDTO.getContraseña();
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
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getContraseña() {return contraseña;}
    public void setContraseña(String contraseña) {this.contraseña = contraseña;}
}
