package net.codejava.controller;

import java.util.Optional;
import javax.servlet.http.HttpSession;
import net.codejava.entity.Formulario;
import net.codejava.entity.Imc;
import net.codejava.services.ImcService;
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

    @RequestMapping("/new")
    public String showNewImcPage(Model model) 
    {
        Imc imc = new Imc();
        model.addAttribute("imc", imc);

        return "new_imc";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveImc(@ModelAttribute("imc") Imc Imc) 
    {
        imcService.guardarImc(Imc);
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
