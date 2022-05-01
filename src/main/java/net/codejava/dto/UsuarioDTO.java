/*
    EVIDENCIA FINAL     : UsuarioDTO
    AUTORA              : Maria Tchijov Cruz
    FECHA               : 01 may 2022
    DTO para la entidad usuario
*/

package net.codejava.dto;

import lombok.Data;

@Data
public class UsuarioDTO 
{
    //Atributos de la clase
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private char genero;
    private String usuario;
    private String contraseña;

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
