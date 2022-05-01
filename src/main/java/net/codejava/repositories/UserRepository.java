/*
    EVIDENCIA FINAL     : UserRepository
    AUTORA              : Maria Tchijov Cruz
    FECHA               : 01 may 2022
    Repositorio para la tabla usuario
*/

package net.codejava.repositories;

import net.codejava.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Usuario, Integer> {}
