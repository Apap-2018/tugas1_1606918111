package com.apap.tugas1.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanPegawaiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PegawaiController{
    @Autowired
    private PegawaiService pegawaiService;
    
    @Autowired
    private ProvinsiService provinsiService;

    @Autowired
    private InstansiService instansiService;

    @Autowired
    private JabatanService jabatanService;

    @Autowired
    private JabatanPegawaiService jabatanPegawaiService;

    @RequestMapping("/")
    private String searchPegawai() {
        return "home";
    }

    @RequestMapping(value="/sipeg/home")
    private String home(){
        return "home";
    }

    @RequestMapping(value="/pegawai/view", method = RequestMethod.GET)
    public String viewPegawai(@RequestParam (value="nip") String nip, Model model){
        PegawaiModel pegawai= pegawaiService.getPegawaiByNip(nip);
        Double gajiPokok= 0.0;
        for(JabatanPegawaiModel jabatan: pegawai.getJabatanPegawai()){
            if(jabatan.getJabatan().getGajiPokok() > gajiPokok){
             gajiPokok = jabatan.getJabatan().getGajiPokok();
            }
        }
        
        Double gaji= gajiPokok + (pegawai.getInstansi().getProvinsi().getPresentaseTunjangan()/100 * gajiPokok);
        model.addAttribute("gaji", gaji.intValue());
        model.addAttribute("pegawai", pegawai);
        return "viewPegawai";
    }

    @RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
    private String addPegawai(Model model) {
        //ngoper model ke front end
        PegawaiModel pegawai= new PegawaiModel();
        pegawai.setJabatanPegawai(new ArrayList());
        pegawai.getJabatanPegawai().add(new JabatanPegawaiModel());
        model.addAttribute("pegawai", pegawai);

        //isi set jabatanPegawai di pegawaiModel
        List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
        List<InstansiModel> listInstansi = instansiService.getAllInstansi();
        List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
        model.addAttribute("listProvinsi", listProvinsi);
        model.addAttribute("listInstansi", listInstansi);
        model.addAttribute("listJabatan", listJabatan);
        return "addPegawai";

    }

    //PegawaiModel yang dibuat di get nanti dioper kesini
    @RequestMapping(value= "/pegawai/tambah", method= RequestMethod.POST, params={"addRow"})
    private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, Model model){
        
        //tambah new JabatanPegawaiModel ke setnya
        pegawai.getJabatanPegawai().add(new JabatanPegawaiModel());
        
        model.addAttribute("pegawai", pegawai);

        //add lagi ke front end soalnya nanti ke reload
        List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
        List<InstansiModel> listInstansi = instansiService.getAllInstansi();
        List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
        model.addAttribute("listProvinsi", listProvinsi);
        model.addAttribute("listInstansi", listInstansi);
        model.addAttribute("listJabatan", listJabatan);
        return "addPegawai";
    }

    @RequestMapping(value= "/pegawai/tambah", method= RequestMethod.POST)
    private String addPegawaiSuccess(@ModelAttribute PegawaiModel pegawai, Model model){
        pegawaiService.addPegawai(pegawai);
        model.addAttribute("pegawai", pegawai);
        return "addPegawaiSubmit";
    }

    @RequestMapping(value="/pegawai/ubah", method= RequestMethod.GET)
    public String updatePegawai(@RequestParam(value="nip") String nip, Model model){
        
        List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
        List<InstansiModel> listInstansi = instansiService.getAllInstansi();
        List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
        
        model.addAttribute("pegawai", pegawaiService.getPegawaiByNip(nip));
        model.addAttribute("listProvinsi", listProvinsi);
        model.addAttribute("listInstansi", listInstansi);
        model.addAttribute("listJabatan", listJabatan);
        return "updatePegawai";
    }

    @RequestMapping(value="/pegawai/ubah", method= RequestMethod.POST)
    public String updatePegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model){
        pegawaiService.updatePegawai(pegawai);
        return "updatePegawaiSubmit";
    }


    @RequestMapping(value="/pegawai/cari", method=RequestMethod.GET)
    public String cariPegawaiSubmit(@RequestParam(value="idProvinsi", required=false) Long idProvinsi, 
                                    @RequestParam(value="idInstansi", required=false) Long idInstansi, 
                                    @RequestParam(value="idJabatan", required= false) Long idJabatan, 
                                    Model model){
        List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
        List<InstansiModel> listInstansi = instansiService.getAllInstansi();
        List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
        model.addAttribute("listProvinsi", listProvinsi);
        model.addAttribute("listInstansi", listInstansi);
        model.addAttribute("listJabatan", listJabatan);
        
        if(idJabatan!=null){
            InstansiModel instansi = instansiService.getInstansiById(idInstansi);
            JabatanModel jabatan = jabatanService.getJabatanById(idJabatan);
            model.addAttribute("pegawai", pegawaiService.getPegawaiByInstansiJabatan(instansi, jabatan));

        }

        return "cariPegawai";
    }

    @RequestMapping(value="/pegawai/tertua-termuda", method=RequestMethod.GET)
    public String pegawaiTuaMuda(@RequestParam(value="idInstansi") long idInstansi, Model model){
        InstansiModel instansi= instansiService.getInstansiById(idInstansi);

        model.addAttribute("termuda", pegawaiService.findYoungest(instansi));
        model.addAttribute("tertua", pegawaiService.findOldest(instansi));

        return "tertua-termuda";
    }
        

}