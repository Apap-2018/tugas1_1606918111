package com.apap.tugas1.controller;

import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;

import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JabatanController{
    @Autowired
    private JabatanService jabatanService;

    @Autowired
    private InstansiService instansiService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String jabatanHome(Model model){
        List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
        List<InstansiModel> listInstansi= instansiService.getAllInstansi();
        model.addAttribute("listJabatan", listJabatan);
        model.addAttribute("listInstansi", listInstansi);

        return "home";
    }

    @RequestMapping(value="/jabatan/tambah", method = RequestMethod.GET)
    private String addJabatan(Model model){
        return "addJabatan";
    }

    @RequestMapping(value="/jabatan/tambah", method =RequestMethod.POST)
    private String addJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model){
        jabatanService.addJabatan(jabatan);
        model.addAttribute("jabatan", jabatan);
        return "addJabatanSubmit";
    }

    //jabatan/view?idJabatan={id_jabatan}
    @RequestMapping(value="/jabatan/view", method= RequestMethod.GET)
    private String viewJabatan(@RequestParam(value="idJabatan") Long idJabatan, Model model){
        JabatanModel jabatan = jabatanService.getJabatanById(idJabatan);
        model.addAttribute("jabatan", jabatan);
        return "viewJabatan";

        //todo: delete update jabatan

    }

    @RequestMapping(value="/jabatan/hapus", method= RequestMethod.POST)
    private String deleteJabatan(@ModelAttribute JabatanModel jabatan, Model model){
        String response = "Gagal delete";
        System.out.println("id ===================");
        System.out.println(jabatan.getId());

        if (jabatanService.removeJabatan(jabatan.getId()) ){
            response = "berhasil delete";
        }
        model.addAttribute("response", response);
        return "deleteJabatan";
    }
   

    @RequestMapping(value="/jabatan/ubah", method= RequestMethod.GET)
    public String updateJabatan(@RequestParam(value="idJabatan") Long id, Model model){
        model.addAttribute("jabatan", jabatanService.getJabatanById(id));
        return "updateJabatan";
    }

    @RequestMapping(value="/jabatan/ubah", method=RequestMethod.POST)
    private String updateJabatanSubmit(@ModelAttribute JabatanModel jabatanModel, Model model){
        jabatanService.updateJabatan(jabatanModel);
        model.addAttribute("jabatan", jabatanModel);
        return "updateJabatanSubmit";
    }

    @RequestMapping(value="/jabatan/viewall", method=RequestMethod.GET)
    private String viewAllJabatan(Model model){
        List <JabatanModel> listJabatan = jabatanService.getAllJabatan();
        model.addAttribute("listJabatan", listJabatan);
        return "viewAllJabatan";
    }


}