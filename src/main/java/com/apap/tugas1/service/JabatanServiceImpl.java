package com.apap.tugas1.service;


import java.util.List;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService {
    @Autowired
    private JabatanDb jabatanDb;

    @Override
    public void addJabatan(JabatanModel jabatan) {
        jabatanDb.save(jabatan);
		
	}

    @Override
    public JabatanModel getJabatanById(Long id) {
        return jabatanDb.getOne(id);
    }

    @Override
    public List<JabatanModel> getAllJabatan() {
		return jabatanDb.findAll();
	}

}