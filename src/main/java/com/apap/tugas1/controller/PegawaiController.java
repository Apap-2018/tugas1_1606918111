package com.apap.tugas1.controller;

import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.service.PegawaiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PegawaiController{
    @Autowired
    private PegawaiService pegawaiService;

    @RequestMapping("/")
    private String searchPegawai() {
        
        return "home";
    }

    @RequestMapping(value="/pegawai/view", method = RequestMethod.GET)
    public String viewPegawai(@RequestParam (value="nip") String nip, Model model){
        PegawaiModel pegawai= pegawaiService.getPegawaiByNip(nip);
        model.addAttribute("pegawai", pegawai);
        return "viewPegawai";

        // todo: view gaji pegawai
    }

    // @RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
    // private String add(Model model) {
    //     model.addAttribute("pegawai", new PegawaiModel());
    //     return "addPegawai";
    // }

    


}