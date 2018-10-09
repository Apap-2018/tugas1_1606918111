package com.apap.tugas1.controller;

import com.apap.tugas1.service.JabatanService;

import java.util.List;

import com.apap.tugas1.model.JabatanModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JabatanController{
    @Autowired
    private JabatanService jabatanService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String jabatanHome(Model model){
        List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
        model.addAttribute("listJabatan", listJabatan);
        return "home";
    }

    @RequestMapping(value="/jabatan/tambah", method = RequestMethod.GET)
    private String addJabatan(Model model){
        return "addJabatan";
    }

    @RequestMapping(value="/jabatan/tambah", method =RequestMethod.POST)
    private String addJabatanSuccess(@ModelAttribute JabatanModel jabatan, Model model){
        jabatanService.addJabatan(jabatan);
        model.addAttribute("jabatan", jabatan);
        return "addJabatanSuccess";
    }

    //jabatan/view?idJabatan={id_jabatan}
    @RequestMapping(value="/jabatan/view", method= RequestMethod.GET)
    private String viewJabatan(@RequestParam(value="idJabatan") Long idJabatan, Model model){
        JabatanModel jabatan = jabatanService.getJabatanById(idJabatan);
        model.addAttribute("jabatan", jabatan);
        return "viewJabatan";

        //todo: delete update jabatan

    }

}