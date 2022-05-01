/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava.services;


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

    public Iterable<Usuario> getUsuario() {return userRepository.findAll();}
    
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
