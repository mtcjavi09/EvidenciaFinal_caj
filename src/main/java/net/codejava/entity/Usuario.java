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
import lombok.Data;
import net.codejava.dto.UsuarioDTO;

@Entity
@Table(name = "usuario")
@Data
public class Usuario 
{
    //Columnas que contendrá la tabla en base de datos
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
    
    @Column(name="usuario")
    private String usuario;
    
    @Column(name="contraseña")
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
        this.usuario = usuarioDTO.getUsuario();
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
    public String getUsuario() {return usuario;}
    public void setUsuario(String usuario) {this.usuario = usuario;}
    public String getContraseña() {return contraseña;}
    public void setContraseña(String contraseña) {this.contraseña = contraseña;}
}
