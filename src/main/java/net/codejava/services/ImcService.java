/*
    EVIDENCIA FINAL     : ImcService
    AUTORA              : Maria Tchijov Cruz
    FECHA               : 11 abr 2022
    Servicio para la tabla IMC
*/

package net.codejava.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import net.codejava.entity.Imc;
import net.codejava.repositories.ImcRepository;

@Service
public class ImcService 
{
    @Autowired
    ImcRepository imcRepository;

    public Iterable<Imc> getImc() {return imcRepository.findAll();}
    
    public Optional<Imc> getImcById(Integer id) {return imcRepository.findById(id);}

    public Imc guardarImc(Imc imc) {return imcRepository.save(imc);}

    public Imc actualizarImc(Integer id, Imc imc) 
    {
        imc.setId(id);
        return imcRepository.save(imc);
    }

    public void borrarImc(Integer id) 
    {
        Optional<Imc> imc = imcRepository.findById(id);
        imcRepository.delete(imc.get());
    }
}
