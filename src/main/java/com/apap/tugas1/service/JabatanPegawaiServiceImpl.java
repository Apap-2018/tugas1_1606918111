package com.apap.tugas1.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.JabatanPegawaiDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class JabatanPegawaiServiceImpl implements JabatanPegawaiService{
    @Autowired
    private JabatanPegawaiDb jabatanPegawaiDb;


    @Override
    public List<JabatanPegawaiModel> getAllJabatanPegawaiModel() {
        return jabatanPegawaiDb.findAll();
    }

    @Override
    public List<PegawaiModel> findPegawaiByJabatan(Long idProvinsi, Long idInstansi, Long idJabatan) {
        List<PegawaiModel> listPegawai= new ArrayList<PegawaiModel>();
        for(JabatanPegawaiModel jabatanPegawai: getAllJabatanPegawaiModel()){
            if ( jabatanPegawai.getJabatan().getId() == idJabatan && jabatanPegawai.getPegawai().getInstansi().getId()==idInstansi && jabatanPegawai.getPegawai().getInstansi().getProvinsi().getId()==idProvinsi){
                listPegawai.add(jabatanPegawai.getPegawai());
            }

        }
        return listPegawai;
    }

    @Override
    public List<JabatanModel> findJabatanById(Long idJabatan) {
        List<JabatanModel> jabatan = new ArrayList<JabatanModel>();
        for(JabatanPegawaiModel j: getAllJabatanPegawaiModel()){
            if(j.getJabatan().getId() == idJabatan){
                jabatan.add(j.getJabatan());
            }
        }
        return jabatan;
	}


}