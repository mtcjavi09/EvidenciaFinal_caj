package net.codejava.controller;

import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.List;
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
            Usuario loggedUser = (Usuario) session.getAttribute("usuarioAutenticado");
            ArrayList<Usuario> sessionUser = new ArrayList();
            sessionUser.add(loggedUser);
            model.addAttribute("sessionUser", sessionUser);
            List<Imc> imcCompleto = (List<Imc>) imcService.getImc();
            ArrayList<Imc> listImc = new ArrayList();
            for(int x=0; x<imcCompleto.size(); x++)
            {
                Imc imcIndex = imcCompleto.get(x);
                String email = imcIndex.getEmail();
                
                if(email.equals(loggedUser.getEmail()))
                {listImc.add(imcIndex);}
            }
            model.addAttribute("listImc", listImc);
            return "index";
        } 
        else 
        {
            model.addAttribute("formulario", new Formulario());
            return "login";
        }       
    }

    @RequestMapping("/login")
    public String login(HttpSession session, Model model,
            @ModelAttribute("formulario") Formulario formulario) 
    {
        List<Usuario> listUsuarios = userService.getUsuario();
        session.setAttribute("listUsuarios", listUsuarios);
        boolean encontrado = listUsuarios.stream().anyMatch(x -> 
                x.getEmail().equals(formulario.getEmail()) 
                        && x.getContraseña().equals(formulario.getPassword()));
        if (encontrado == true)
        {
            session.setAttribute("mySessionAttribute", "login");
            for(int x=0; x<listUsuarios.size(); x++)
            {
                Usuario usuarioIndex = listUsuarios.get(x);
                String email = usuarioIndex.getEmail();
                
                if(email.equals(formulario.getEmail()))
                {session.setAttribute("usuarioAutenticado", usuarioIndex);}
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
    public String showNewImcPage(HttpSession session, Model model) 
    {
        Usuario loggedUser = (Usuario) session.getAttribute("usuarioAutenticado");
        ImcDTO imcDTO = new ImcDTO();
        String email = loggedUser.getEmail();
        imcDTO.setEmail(email);

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
        float peso = imc.getPeso();
        float estatura = imc.getEstatura();
        float calculoImc = (float) (peso / pow(estatura,2.0));
        imc.setImc(calculoImc);
        String explicaResultado = "";

        if(calculoImc < 18.5)
        {explicaResultado = "Peso bajo";}

        else
        {
            if(calculoImc>=18.5 && calculoImc<=24.9)
            {explicaResultado = "Peso normal";}

            else
            {
                if(calculoImc>=25.0 && calculoImc<=29.9)
                {explicaResultado = "Sobrepeso";}

                else
                {explicaResultado = "Obesidad";}
            }
        }
        imc.setExplicaResultado(explicaResultado);
        imcService.guardarImc(imc);
        return "redirect:/";
    }

    @RequestMapping(value = "/saveUsuario", method = RequestMethod.POST)
    public String saveUsuario(@ModelAttribute("usuario") Usuario usuario) 
    {
        userService.guardarUsuario(usuario);
        return "redirect:/";
    }
    
    @RequestMapping("/changePassword/{id}")
    public ModelAndView showChangePasswordPage(@PathVariable(name = "id") int id) 
    {
        ModelAndView mav = new ModelAndView("change_password");
        Optional<Usuario> user = userService.getUsuarioById(id);
        mav.addObject("user", user);
        return mav;
    }
    
    @RequestMapping("/editUser/{id}")
    public ModelAndView showEditUsuarioPage(@PathVariable(name = "id") int id) 
    {
        ModelAndView mav = new ModelAndView("edit_usuario");
        Optional<Usuario> changedUser = userService.getUsuarioById(id);
        mav.addObject("changedUser", changedUser);
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
