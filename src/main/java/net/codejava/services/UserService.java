/*
    EVIDENCIA FINAL     : ImcService
    AUTORA              : Maria Tchijov Cruz
    FECHA               : 11 abr 2022
    Servicio para la tabla usuario
*/

package net.codejava.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import net.codejava.entity.Usuario;
import net.codejava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService 
{
    @Autowired
    UserRepository userRepository;

    public List<Usuario> getUsuario() {return (List<Usuario>) userRepository.findAll();}
    
    public Optional<Usuario> getUsuarioById(Integer id) {return userRepository.findById(id);}
    
    public Usuario guardarUsuario(Usuario usuario) {return userRepository.save(usuario);}

    public Usuario actualizarUsuario(Integer id, Usuario usuario) 
    {
        usuario.setId(id);
        return userRepository.save(usuario);
    }

    public void borrarUsuario(Integer id) 
    {
        Optional<Usuario> usuario = userRepository.findById(id);
        userRepository.delete(usuario.get());
    }
}
