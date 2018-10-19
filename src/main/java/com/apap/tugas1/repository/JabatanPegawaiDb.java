package com.apap.tugas1.repository;

import java.util.List;

import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JabatanPegawaiDb extends JpaRepository<JabatanPegawaiModel, Long> {


}