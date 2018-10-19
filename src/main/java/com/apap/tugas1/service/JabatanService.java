package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.JabatanModel;

public interface JabatanService {
    void addJabatan(JabatanModel jabatan);
    boolean removeJabatan(Long id);
    JabatanModel getJabatanById(Long id);
    List<JabatanModel> getAllJabatan();
    void updateJabatan(JabatanModel jabatan);


}