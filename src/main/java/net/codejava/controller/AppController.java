/*
    EVIDENCIA FINAL   :   AppController
    AUTORA            :   Maria Tchijov Cruz
    FECHA             :   06 may 2022
    Controlador de la webapp de la evidencia final.
*/

package net.codejava.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import net.codejava.Error;
import net.codejava.Methods;
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
        try
        {
            if (session.getAttribute("mySessionAttribute") != null) 
            {
                //Se obtiene el usuario autenticado y se guarda en un ArrayList y se guarda como atributo del modelo
                Usuario loggedUser = (Usuario) session.getAttribute("usuarioAutenticado");
                ArrayList<Usuario> sessionUser = new ArrayList();
                sessionUser.add(loggedUser);
                model.addAttribute("sessionUser", sessionUser);

                //Se buscan los IMC a partir del correo de un usuario y se guarda en una Lista
                List<Imc> imcCompleto = (List<Imc>) imcService.getImc();
                ArrayList<Imc> listImc = new ArrayList();
                for(int x=0; x<imcCompleto.size(); x++)
                {
                    Imc imcIndex = imcCompleto.get(x);
                    String email = imcIndex.getEmail();
                    
                    if(email.equals(loggedUser.getEmail()))
                    {listImc.add(imcIndex);}
                }

                //Se guarda la lista como atributo del modelo y se llama a la vista index
                model.addAttribute("listImc", listImc);
                return "index";
            } 
            else 
            {
                //Se crea un objeto de tipo formulario y se llama a la vista del login
                model.addAttribute("formulario", new Formulario());
                return "login";
            }
        }
        catch(Exception e)
        {
            //Se crea una lista para guardar los atributos del error
            ArrayList<Object> exception = new ArrayList<Object>();
            //Se generan y se guardan el mensaje y la causa del error en un objeto de la lista
            String bug = e.getMessage();
            String causa= e.getCause().toString();
            Error error = new Error(bug,causa);
            exception.add(error);
            
            //Se guarda el objeto como atributo del modelo
            model.addAttribute("exception", exception);

            //Se llama a la vista de los errores
            return "error";
        }

    }

    @RequestMapping("/login")
    public String login(HttpSession session, Model model, @ModelAttribute("formulario") Formulario formulario) 
    {
        try
        {
            //Se guardan los usuarios de la base de datos en una lista
            List<Usuario> listUsuarios = userService.getUsuario();
            session.setAttribute("listUsuarios", listUsuarios);
            //Se busca al usuario según su email y contraseña
            boolean encontrado = listUsuarios.stream().anyMatch(x -> 
                    x.getEmail().equals(formulario.getEmail()) 
                            && x.getContraseña().equals(formulario.getPassword()));

            //Si se encontró, se guardará la información del usuario en un atributo como objeto de tipo usuario
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
            //Si no se encontró, simplemente no dejará continuar, quedando en la misma vista del login
            else
            {
                session.setAttribute("mySessionAttribute", null);
                return "login";
            }
        }
        catch(Exception e)
        {
            //Se crea una lista para guardar los atributos del error
            ArrayList<Object> exception = new ArrayList<Object>();
            //Se generan y se guardan el mensaje y la causa del error en un objeto de la lista
            String bug = e.getMessage();
            String causa= e.getCause().toString();
            Error error = new Error(bug,causa);
            exception.add(error);

            //Se guarda el objeto como atributo del modelo
            model.addAttribute("exception", exception);

            //Se llama a la vista de los errores
            return "error";
        }
    }

    @RequestMapping("/newImc")
    public String showNewImcPage(HttpSession session, Model model) 
    {
        try
        {
            //Se guarda automáticamente el email del usuario en el objeto de tipo ImcDTO
            Usuario loggedUser = (Usuario) session.getAttribute("usuarioAutenticado");
            ImcDTO imcDTO = new ImcDTO();
            String email = loggedUser.getEmail();
            imcDTO.setEmail(email);

            //Se guarda el objeto de tipo ImcDTO como un atributo del modelo
            model.addAttribute("imc", imcDTO);

            //Se llama a la vista de cálculo del imc
            return "new_imc";
        }
        catch(Exception e)
        {
            //Se crea una lista para guardar los atributos del error
            ArrayList<Object> exception = new ArrayList<Object>();
            //Se generan y se guardan el mensaje y la causa del error en un objeto de la lista
            String bug = e.getMessage();
            String causa= e.getCause().toString();
            Error error = new Error(bug,causa);
            exception.add(error);

            //Se guarda el objeto como atributo del modelo
            model.addAttribute("exception", exception);

            //Se llama a la vista de los errores
            return "error";
        }
    }
    
    @RequestMapping("/newUsuario")
    public String showNewUserPage(Model model) 
    {
        try
        {
            //Se crea un nuevo objeto de tipo UsuarioDTO para guardar lo que ingrese el usuario
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            
            //Se guarda el objeto como un atributo del modelo
            model.addAttribute("usuario", usuarioDTO);

            //Se llama a la vista para el registro del usuario
            return "new_usuario";
        }
        catch(Exception e)
        {
            //Se crea una lista para guardar los atributos del error
            ArrayList<Object> exception = new ArrayList<Object>();
            //Se generan y se guardan el mensaje y la causa del error en un objeto de la lista
            String bug = e.getMessage();
            String causa= e.getCause().toString();
            Error error = new Error(bug,causa);
            exception.add(error);

            //Se guarda el objeto como atributo del modelo
            model.addAttribute("exception", exception);

            //Se llama a la vista de los errores
            return "error";
        }
    }
    
    @RequestMapping(value = "/saveImc", method = RequestMethod.POST)
    public String saveImc(@ModelAttribute("imc") Imc imc, Model model) 
    {
        try
        {
            //Se guarda el peso y la estatura en unas variables float
            float estatura = imc.getEstatura();
            float peso = imc.getPeso();

            //Se crea el objeto methods para acceder a los métodos necesarios y se calcula el IMC
            Methods methods = new Methods();
            float calculoImc = methods.calculaImc(estatura, peso);

            //Se guarda el resultado en el objeto de tipo IMC
            imc.setImc(calculoImc);

            //Se define el mensaje a partir del resultado y se guarda en el objeto de tipo IMC
            String explicaResultado = methods.explicaResultado(calculoImc);        
            imc.setExplicaResultado(explicaResultado);

            //Se guarda el cálculo en la base de datos y se regresa a la página principal
            imcService.guardarImc(imc);
            return "redirect:/";
        }
        catch(Exception e)
        {
            //Se crea una lista para guardar los atributos del error
            ArrayList<Object> exception = new ArrayList<Object>();
            //Se generan y se guardan el mensaje y la causa del error en un objeto de la lista
            String bug = e.getMessage();
            String causa= e.getCause().toString();
            Error error = new Error(bug,causa);
            exception.add(error);

            //Se guarda el objeto como atributo del modelo
            model.addAttribute("exception", exception);


            //Se llama a la vista de los errores
            return "error";
        }
    }

    @RequestMapping(value = "/saveUsuario", method = RequestMethod.POST)
    public String saveUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) 
    {
        try
        {
            //Se guarda al usuario en la base de datos y se regresa a la página del login
            userService.guardarUsuario(usuario);
            return "redirect:/";
        }
        catch(Exception e)
        {
            //Se crea una lista para guardar los atributos del error
            ArrayList<Object> exception = new ArrayList<Object>();
            //Se generan y se guardan el mensaje y la causa del error en un objeto de la lista
            String bug = e.getMessage();
            String causa= e.getCause().toString();
            Error error = new Error(bug,causa);
            exception.add(error);

            //Se guarda el objeto como atributo del modelo
            model.addAttribute("exception", exception);
            
            //Se llama a la vista de los errores
            return "error";
        }
    }
    
    @RequestMapping("/changePassword/{id}")
    public ModelAndView showChangePasswordPage(@PathVariable(name = "id") int id) 
    {
        //Se crea un model and view para el cambio de contraseña y se guarda el resultado en la base de datos
        ModelAndView mav = new ModelAndView("change_password");
        Optional<Usuario> user = userService.getUsuarioById(id);
        mav.addObject("user", user);
        return mav;
    }
    
    @RequestMapping("/editUser/{id}")
    public ModelAndView showEditUsuarioPage(@PathVariable(name = "id") int id) 
    {
        //Se crea un model and view para el cambio de datos del usuario y se guarda el resultado en la base de datos
        ModelAndView mav = new ModelAndView("edit_usuario");
        Optional<Usuario> changedUser = userService.getUsuarioById(id);
        mav.addObject("changedUser", changedUser);
        return mav;
    }
    
    @RequestMapping("/delete/{id}")
    public String deleteImc(@PathVariable(name = "id") int id) 
    {
        //Se usa el imc para borrar el id y posteriormente se regresa a la última vista
        imcService.borrarImc(id);
        return "redirect:/";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) 
    {
        //Se cambia el atributo a null para regresar a la página del login
        session.setAttribute("mySessionAttribute", null);
        return "redirect:/";
    }
}