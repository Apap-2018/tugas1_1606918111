package com.apap.tugas1.service;

import java.util.List;

import javax.transaction.Transactional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.InstansiDb;
import com.apap.tugas1.repository.ProvinsiDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InstansiServiceImpl implements InstansiService {
    @Autowired
	private InstansiDb instansiDb;

	@Autowired
	private ProvinsiDb provinsiDb;
	
	private List<InstansiModel> instansiProvinsi;

	@Override
	public List<InstansiModel> getAllInstansi() {
		return instansiDb.findAll();
	}

	@Override
	public List<InstansiModel> getInstansiByProvinsi(Long idProvinsi) {
		ProvinsiModel provinsi = provinsiDb.getOne(idProvinsi);
		for(InstansiModel instansi: getAllInstansi()){
			if(instansi.getProvinsi().getNama().equalsIgnoreCase(provinsi.getNama())){
				instansiProvinsi.add(instansi);
			}
		}
		return instansiProvinsi;
	}

	@Override
	public InstansiModel getInstansiById(Long idInstansi) {
		return instansiDb.getOne(idInstansi);
	}


}