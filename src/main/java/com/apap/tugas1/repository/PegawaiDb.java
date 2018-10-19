package com.apap.tugas1.repository;

import java.sql.Date;
import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel, Long> {
    PegawaiModel findByNip(String nip);
    List<PegawaiModel> findPegawaiByInstansi(InstansiModel instansi);
    List<PegawaiModel> findByTanggalLahirAndTahunMasukOrderByTanggalLahirAscTahunMasukAsc(Date TanggalMasuk, String TahunMasuk);
    List<PegawaiModel> findByInstansiOrderByTanggalLahirAsc(InstansiModel instansi);
    List<PegawaiModel> findByInstansiOrderByTanggalLahirDesc(InstansiModel instansi);


    
}