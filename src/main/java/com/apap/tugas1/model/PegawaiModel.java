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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Entity
@Table(name="pegawai")
public class PegawaiModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max=255)
    @Column(name="NIP", nullable=false)
    private String nip;

    @NotNull
    @Size(max=255)
    @Column(name="nama", nullable=false)
    private String namaPegawai;

    @NotNull
    @Size(max=255)
    @Column(name="tempat_lahir",nullable=false)
    private String tempatLahir;

    @NotNull
    @Column(name="tanggal_lahir", nullable=false)
    private Date tanggalLahir;

    @NotNull
    @Size(max=255)
    @Column(name="tahun_masuk", nullable=false)
    private String tahunMasuk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_instansi", referencedColumnName = "id", nullable=false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private InstansiModel instansi;






}