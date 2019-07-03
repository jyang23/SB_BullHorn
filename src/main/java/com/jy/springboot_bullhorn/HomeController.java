package com.jy.springboot_bullhorn;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    BullHornRepository bullhornRepository;

    @RequestMapping("/")
    public String listBullHorns(Model model)
    {
        model.addAttribute("bullhorns",bullhornRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String bullhornForm(Model model)
    {
        model.addAttribute("bullhorn",new BullHorn());
        return "bullhornform";
    }

    @PostMapping("/process")
    public String processForm(@Valid BullHorn bullhorn, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "bullhornform";
        }
        bullhornRepository.save(bullhorn);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String delBullHorn(@PathVariable("id") long id)
    {
        bullhornRepository.deleteById(id);
        return "redirect:/";
    }

}

