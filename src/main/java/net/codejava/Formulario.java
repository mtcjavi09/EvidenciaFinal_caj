/*
    EVIDENCIA FINAL     : Formulario
    AUTORA              : Maria Tchijov Cruz
    FECHA               : 01 may 2022
    Clase que servirá para el login
*/

package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import net.codejava.entity.Usuario;
import net.codejava.services.UserService;

public class Formulario 
{
    //Atributos de la clase
    private String email;
    private String password;

    @Autowired
    private UserService userService;

    //Getters y setters
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    //Método para buscar al usuario
    public Usuario searchUser(Formulario formulario)
    {
        //Se guardan los usuarios de la base de datos en una lista
        List<Usuario> listUsuarios = userService.getUsuario();
        //Se busca al usuario según su email y contraseña
        boolean encontrado = listUsuarios.stream().anyMatch(x -> 
                x.getEmail().equals(formulario.getEmail()) 
                        && x.getContraseña().equals(formulario.getPassword()));
        
        //Se genera una variable de tipo de usuario para guardar el resultado
        Usuario usuario = new Usuario();

        //Si se encontró al usuario, se guardan sus datos en el objeto usuario
        if(encontrado == true)
        {
            for(int x=0; x<listUsuarios.size(); x++)
            {
                Usuario usuarioIndex = listUsuarios.get(x);
                String email = usuarioIndex.getEmail();
                    
                if(email.equals(formulario.getEmail()))
                {usuario = usuarioIndex;}
            }
        }
        //Si no se encontró al usuario, se regresará null en la información del usuario
        else
        {usuario = null;}

        //Se regresa la información del objeto usuario
        return usuario;
    }
}