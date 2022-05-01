/*
    EVIDENCIA FINAL     : ImcRepository
    AUTORA              : Maria Tchijov Cruz
    FECHA               : 19 abr 2022
    Repositorio principal de la aplicación
*/

package net.codejava.repositories;

import net.codejava.entity.Imc;
import org.springframework.data.repository.CrudRepository;


public interface ImcRepository extends CrudRepository<Imc, Integer> {}

