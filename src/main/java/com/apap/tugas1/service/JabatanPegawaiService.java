package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;

public interface JabatanPegawaiService {
    List<PegawaiModel> findPegawaiByJabatan(Long idProvinsi, Long idInstansi, Long idJabatan);
    List<JabatanPegawaiModel> getAllJabatanPegawaiModel();
    List<JabatanModel> findJabatanById(Long idJabatan);

}