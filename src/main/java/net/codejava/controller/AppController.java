package net.codejava.controller;

import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import net.codejava.Formulario;
import net.codejava.dto.ImcDTO;
import net.codejava.dto.UsuarioDTO;
import net.codejava.entity.Imc;
import net.codejava.entity.Usuario;
import net.codejava.services.ImcService;
import net.codejava.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController 
{
    @Autowired
    private ImcService imcService;
    
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String viewHomePage(HttpSession session, Model model) 
    {
        if (session.getAttribute("mySessionAttribute") != null) 
        {
            Iterable<Imc> listImc = imcService.getImc();
            model.addAttribute("listImc", listImc);            
            return "index";
        } 
        else 
        {
            Iterable<Usuario> listUsuarios = userService.getUsuario();
            session.setAttribute("listUsuarios", listUsuarios);
            model.addAttribute("formulario", new Formulario());
            return "login";
        }       
    }

    @RequestMapping("/login")
    public String login(HttpSession session, Model model,
            @ModelAttribute("formulario") Formulario formulario) 
    {
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) session.getAttribute("listUsuarios");
        boolean encontrado = usuarios.stream().anyMatch(x -> 
                x.getEmail().equals(formulario.getEmail()) 
                        && x.getContraseña().equals(formulario.getPassword()));
        if (encontrado == true)
        {
            session.setAttribute("mySessionAttribute", "login");
            Iterator<Usuario> iterador = usuarios.iterator();
            while(iterador.hasNext())
            {
                Usuario usuario = iterador.next();
                String email = usuario.getEmail();
                if(email.equals(formulario.getEmail()))
                {model.addAttribute("usuario", usuario);}
            }
            return "redirect:/";
        }
        else
        {
            session.setAttribute("mySessionAttribute", null);
            return "login";
        }
    }

    @RequestMapping("/newImc")
    public String showNewImcPage(Model model, @ModelAttribute("usuario") Usuario usuario) 
    {
        ImcDTO imcDTO = new ImcDTO();
        imcDTO.setEmail(usuario.getEmail());
        model.addAttribute("imc", imcDTO);
        return "new_imc";
    }
    
    @RequestMapping("/newUsuario")
    public String showNewUserPage(Model model) 
    {
        model.addAttribute("usuario", new UsuarioDTO());
        return "new_usuario";
    }
    
    @RequestMapping(value = "/saveImc", method = RequestMethod.POST)
    public String saveImc(@ModelAttribute("imc") Imc imc) 
    {
        float peso = imc.getPeso();
        float estatura = imc.getEstatura();
        imc.setImc((float) (peso / pow(estatura,2.0)));
        imcService.guardarImc(imc);
        return "redirect:/";
    }

    @RequestMapping(value = "/saveUsuario", method = RequestMethod.POST)
    public String saveUsuario(@ModelAttribute("usuario") Usuario usuario) 
    {
        userService.guardarUsuario(usuario);
        return "redirect:/";
    }

    @RequestMapping("/editImc/{id}")
    public ModelAndView showEditImcPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_imc");
        Optional<Imc> imc = imcService.getImcById(id);
        mav.addObject("imc", imc);
        return mav;
    }
    
    @RequestMapping("/delete/{id}")
    public String deleteImc(@PathVariable(name = "id") int id) 
    {
        imcService.borrarImc(id);
        return "redirect:/";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) 
    {
        session.setAttribute("mySessionAttribute", null);
        return "redirect:/";
    }
}
