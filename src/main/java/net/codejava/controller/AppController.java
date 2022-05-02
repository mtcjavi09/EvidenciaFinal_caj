package net.codejava.controller;

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
            Iterable<Usuario> listUsuarios = userService.getUsuario();
            model.addAttribute("listUsuarios", listUsuarios);
            return "index";
        } 
        else 
        {
            model.addAttribute("formulario", new Formulario());
            return "login";
        }
    }

    @RequestMapping("/login")
    public String login(HttpSession session) 
    {
        session.setAttribute("mySessionAttribute", "sasas");
        // model.addAttribute("listProducts", listProducts);
        return "redirect:/";
    }

    @RequestMapping("/newImc")
    public String showNewImcPage(Model model) 
    {
        ImcDTO imcDTO = new ImcDTO();
        model.addAttribute("imc", imcDTO);

        return "new_imc";
    }
    
    @RequestMapping("/newUsuario")
    public String showNewUserPage(Model model) 
    {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        model.addAttribute("usuario", usuarioDTO);

        return "new_usuario";
    }
    
    @RequestMapping(value = "/saveImc", method = RequestMethod.POST)
    public String saveImc(@ModelAttribute("imc") Imc imc) 
    {
        imcService.guardarImc(imc);
        return "redirect:/";
    }

    @RequestMapping(value = "/saveUsuario", method = RequestMethod.POST)
    public String saveUsuario(@ModelAttribute("usuario") Usuario usuario) 
    {
        userService.guardarUsuario(usuario);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditImcPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_imc");
        Optional<Imc> imc = imcService.getImcById(id);
        mav.addObject("imc", imc);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteImc(@PathVariable(name = "id") int id) {
        imcService.borrarImc(id);
        return "redirect:/";
    }
}
