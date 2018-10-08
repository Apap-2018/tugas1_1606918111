package com.apap.tugas1.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Entity
@Table(name="jabatan")
public class JabatanModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max=255)
    @Column(name="nama", nullable=false)
    private String namaJabatan;

    @NotNull
    @Size(max=255)
    @Column(name="deskripsi", nullable=false)
    private String deskripsiJabatan;

    @NotNull
    @Column(name="gaji_pokok")
    private Double gajiPokok;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_instansiPegawai", referencedColumnName = "id_instansi", nullable=false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private InstansiModel instansi;






}