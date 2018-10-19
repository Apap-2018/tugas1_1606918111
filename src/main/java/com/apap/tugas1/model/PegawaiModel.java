package com.apap.tugas1.model;

import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

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
@Table(name="pegawai")
public class PegawaiModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=255)
    @Column(name="NIP", nullable=false)
    private String nip;

    @NotNull
    @Size(max=255)
    @Column(name="nama", nullable=false)
    private String nama;

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
    @JoinColumn(name = "id_instansi", referencedColumnName = "id", nullable=false) //name=kenapa namanya harus id_instansi???
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private InstansiModel instansi;

    //source: https://hellokoding.com/jpa-many-to-many-extra-columns-relationship-mapping-example-with-spring-boot-maven-and-mysql/
    @OneToMany(mappedBy="pegawai", fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
    private List<JabatanPegawaiModel> jabatanPegawai;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNip() {
        return this.nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempatLahir() {
        return this.tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTanggalLahir() {
        return this.tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTahunMasuk() {
        return this.tahunMasuk;
    }

    public void setTahunMasuk(String tahunMasuk) {
        this.tahunMasuk = tahunMasuk;
    }

    public InstansiModel getInstansi() {
        return this.instansi;
    }

    public void setInstansi(InstansiModel instansi) {
        this.instansi = instansi;
    }

    public List<JabatanPegawaiModel> getJabatanPegawai() {
        return this.jabatanPegawai;
    }

    public void setJabatanPegawai(List<JabatanPegawaiModel> jabatanPegawai) {
        this.jabatanPegawai = jabatanPegawai;
    }

    public String tanggalLahirToStr() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		String tglLahir = dateFormat.format(tanggalLahir);
		return tglLahir;
	}
	
	public String tahunLahirToStr() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		String tahunLahir = dateFormat.format(tanggalLahir);
		return tahunLahir;
	}






}