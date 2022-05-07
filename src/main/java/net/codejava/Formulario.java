/*
    EVIDENCIA FINAL     : Formulario
    AUTORA              : Maria Tchijov Cruz
    FECHA               : 01 may 2022
    Clase que servirá para el login
*/

package net.codejava;

import java.util.List;

import net.codejava.entity.Usuario;

public class Formulario 
{
    //Atributos de la clase
    private String email;
    private String password;

    //Getters y setters
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    //Método para buscar al usuario
    public Usuario searchUser(List<Usuario> listUsuarios, String email, String contraseña)
    {
        //Se crea la variable usuario
        Usuario usuario = new Usuario();

        //Se busca al usuario según su email y contraseña
        boolean encontrado = listUsuarios.stream().anyMatch(x -> 
                x.getEmail().equals(email) && x.getContraseña().equals(contraseña));
        
        if(encontrado == true)
        {
            for(int x=0; x<listUsuarios.size(); x++)
            {
                Usuario usuarioIndex = listUsuarios.get(x);
                String emailIndex = usuarioIndex.getEmail();
                if(emailIndex.equals(email))
                {usuario = listUsuarios.get(x);}
            }
        }

        return usuario;
    }
}