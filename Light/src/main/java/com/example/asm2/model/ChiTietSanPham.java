package com.example.asm2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ctsp")
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_sp")
    private SanPham idSanPham;
    @ManyToOne
    @JoinColumn(name = "id_mau_sac")
    private MauSac idMauSac;
    @ManyToOne
    @JoinColumn(name = "id_size")
    private Size idSize;
    @Column(name = "gia_ban")
    private Double giaBan;
    @Column(name = "so_luong_ton")
    private Integer soLuong;
    @Column(name = "trang_thai")
    private String trangThai;
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @Column(name = "ngay_sua")
    private Date ngaySua;
}


