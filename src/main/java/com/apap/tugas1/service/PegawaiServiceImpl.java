package com.apap.tugas1.service;



import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.JabatanPegawaiDb;
import com.apap.tugas1.repository.PegawaiDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {
    @Autowired
    private PegawaiDb pegawaiDb;

    @Autowired
    private PegawaiDb provinsiDb; 

    @Autowired
    private PegawaiDb instansiDb;

    @Autowired
    private PegawaiDb jabatanDb;

    @Autowired
    private JabatanPegawaiDb jabatanPegawaiDb;

    @Override
    public PegawaiModel getPegawaiByNip(String nip) {
        return pegawaiDb.findByNip(nip);
    }

    @Override
    public void addPegawai(PegawaiModel pegawai) {
        String nip = generateNip(pegawai);
        pegawai.setNip(nip);
        
        //nyimpen temp jabatanpegawai di model, soalnya gabisa di save kalo gaada idnya
        List <JabatanPegawaiModel> tempJabatanPegawai = pegawai.getJabatanPegawai();
        pegawai.setJabatanPegawai(new ArrayList<>());

        pegawaiDb.save(pegawai);

        for(JabatanPegawaiModel jp: tempJabatanPegawai){
            jp.setPegawai(pegawai);
            jabatanPegawaiDb.save(jp);
        }

    }

    @Override
    public void updatePegawai(PegawaiModel pegawai) {
        pegawai.setNip(this.updateNip(pegawai));
    
        pegawaiDb.save(pegawai);
        for ( JabatanPegawaiModel jp : pegawai.getJabatanPegawai()) {
            jabatanPegawaiDb.save(jp);
        }
    }


    @Override
    public List<PegawaiModel> getPegawaiByInstansiJabatan(InstansiModel instansi, JabatanModel jabatan) {
        List<PegawaiModel> pegawaiByInstansi= pegawaiDb.findPegawaiByInstansi(instansi);
        List<PegawaiModel> pegawaiByJabatan = new ArrayList<PegawaiModel>();

        for(JabatanPegawaiModel j: jabatan.getJabatanPegawai()){
            pegawaiByJabatan.add(j.getPegawai());
        }
        pegawaiByInstansi.retainAll(pegawaiByJabatan);
        return pegawaiByInstansi;


    }

    private String generateNip(PegawaiModel pegawai){
        String result = "";
        result+= pegawai.getInstansi().getId() + pegawai.tanggalLahirToStr() + pegawai.getTahunMasuk();
        System.out.println(result);
        //end char: cari berdasarkan instansi
        List<PegawaiModel> existingPegawai = pegawaiDb.findByInstansiAndTanggalLahirAndTahunMasukOrderByTanggalLahirAscTahunMasukAsc(pegawai.getInstansi(), pegawai.getTanggalLahir(), pegawai.getTahunMasuk());
        int endChar = existingPegawai.isEmpty() ? 1 : Integer.parseInt( existingPegawai.get( existingPegawai.size() -1 ).getNip().substring(14) ) + 1;
        if(endChar < 10){
            result+= "0" + endChar;
        }
        else{
            result+= endChar;
        }
        return result; 
    }
    
    private String updateNip(PegawaiModel pegawai){
        String result = "";
        result+= pegawai.getInstansi().getId() + pegawai.tanggalLahirToStr() + pegawai.getTahunMasuk();
        PegawaiModel pegawaiData = pegawaiDb.getOne(pegawai.getId());
        

        if ( pegawai.getInstansi().equals(pegawaiData.getInstansi()) &&
            pegawai.getTahunMasuk().equals(pegawaiData.getTahunMasuk()) &&
            pegawai.getTanggalLahir().equals(pegawaiData.getTanggalLahir()) ) {
                System.out.println("masuk sini");
            result += pegawaiData.getNip().substring(14);
            
        }
        else {
            System.out.println("masuk situ");

            List<PegawaiModel> existingPegawai = pegawaiDb.findByInstansiAndTanggalLahirAndTahunMasukOrderByTanggalLahirAscTahunMasukAsc(pegawai.getInstansi(), pegawai.getTanggalLahir(), pegawai.getTahunMasuk());
            int endChar = existingPegawai.isEmpty() ? 1 : Integer.parseInt( existingPegawai.get( existingPegawai.size() -1 ).getNip().substring(14) ) + 1;
            if(endChar < 10){
                result+= "0" + endChar;
            }
            else{
                result+= endChar;
            }
        }
        return result;
    }

    @Override
    public PegawaiModel findOldest(InstansiModel instansi) {
        return pegawaiDb.findByInstansiOrderByTanggalLahirDesc(instansi).get(0);
    }

    @Override
    public PegawaiModel findYoungest(InstansiModel instansi) {
        return pegawaiDb.findByInstansiOrderByTanggalLahirAsc(instansi).get(0);
    }

    @Override
    public List<PegawaiModel> getPegawaiByProvinsi(ProvinsiModel provinsi) {
        List<PegawaiModel> res = new ArrayList<>();
        List<PegawaiModel> all = pegawaiDb.findAll();
        for (PegawaiModel pegawai: all) {
            if (pegawai.getInstansi().getProvinsi().equals(provinsi)) {
                res.add(pegawai);
            }
        }
        return res;
    }

    @Override
    public List<PegawaiModel> getPegawaiByJabatan(JabatanModel jabatan) {
        List<PegawaiModel> res = new ArrayList<>();
        for (JabatanPegawaiModel jabatanPegawai: jabatan.getJabatanPegawai()) {
            res.add(jabatanPegawai.getPegawai());
        }        
        return res;
    }

    @Override
    public List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi) {
        return pegawaiDb.findPegawaiByInstansi(instansi);
    }

    @Override
    public List<PegawaiModel> getPegawaiByProvinsiJabatan(ProvinsiModel provinsi, JabatanModel jabatan) {
        List<PegawaiModel> pegawaiByProvinsi = getPegawaiByProvinsi(provinsi);
        List<PegawaiModel> pegawaiByJabatan = getPegawaiByJabatan(jabatan);
        List<PegawaiModel> res = new ArrayList<>();
        // List<PegawaiModel> pegawaiByJabatan = getPegawaiByJabatan(jabatan);
        pegawaiByProvinsi.retainAll(pegawaiByJabatan);
        return pegawaiByProvinsi;
    }

    


}  