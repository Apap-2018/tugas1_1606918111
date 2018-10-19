package com.apap.tugas1.service;



import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
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

    @Override
    public PegawaiModel getPegawaiByNip(String nip) {
        return pegawaiDb.findByNip(nip);
    }

    @Override
    public void addPegawai(PegawaiModel pegawai) {
        String nip = generateNip(pegawai);
        pegawai.setNip(nip);
        pegawaiDb.save(pegawai);

    }

    @Override
    public void updatePegawai(PegawaiModel pegawai) {
        pegawai.setNip(this.generateNip(pegawai));

        PegawaiModel findPegawai = pegawaiDb.getOne(pegawai.getId());
        findPegawai.setNama(pegawai.getNama());
        findPegawai.setTempatLahir(pegawai.getTempatLahir());
        findPegawai.setTanggalLahir(pegawai.getTanggalLahir());;
        findPegawai.setTahunMasuk(pegawai.getTahunMasuk());
        findPegawai.getInstansi().getProvinsi().setNama(pegawai.getInstansi().getProvinsi().getNama());
        findPegawai.getInstansi().setNama(pegawai.getInstansi().getNama());
        findPegawai.setJabatanPegawai(pegawai.getJabatanPegawai());

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
        //pegawai dengan tanggal lahir dan tahun masuk yang sama
       
        String result = "";
        result+= pegawai.getInstansi().getId() + pegawai.tanggalLahirToStr() + pegawai.getTahunMasuk();
        System.out.println(result);
        int endChar= pegawaiDb.findByTanggalLahirAndTahunMasukOrderByTanggalLahirAscTahunMasukAsc(pegawai.getTanggalLahir(), pegawai.getTahunMasuk()).size();

        if(endChar < 10){
            result+= "0" + endChar;
        }
        else{
            result+= endChar;
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


}  