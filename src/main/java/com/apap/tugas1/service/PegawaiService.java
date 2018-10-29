package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;

public interface PegawaiService {
    PegawaiModel getPegawaiByNip(String nip);
    void addPegawai(PegawaiModel pegawai);
    List<PegawaiModel> getPegawaiByInstansiJabatan(InstansiModel instansi, JabatanModel jabatan);
    PegawaiModel findOldest(InstansiModel instansi);
    PegawaiModel findYoungest(InstansiModel instansi);
    void updatePegawai(PegawaiModel pegawai);
    List<PegawaiModel> getPegawaiByProvinsi(ProvinsiModel provinsi);
    List<PegawaiModel> getPegawaiByJabatan(JabatanModel jabatan);
    List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi);
    List<PegawaiModel> getPegawaiByProvinsiJabatan(ProvinsiModel provinsi, JabatanModel jabatan);


   
}