package com.apap.tugas1.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="instansi")
public class InstansiModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_instansi")
    private long id_instansi;

    @NotNull
    @Size(max=255)
    @Column(name="nama_instansi")
    private String namaInstansi;

    @NotNull
    @Size(max=255)
    @Column(name="deskripsi")
    private String deskripsi;

    @OneToMany(mappedBy="instansi", fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
    private List <PegawaiModel> pegawaiInstansi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instansi_provinsi", referencedColumnName = "id_provinsi", nullable=false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private ProvinsiModel provinsi;

    


}