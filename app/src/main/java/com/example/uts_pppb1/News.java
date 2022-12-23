package com.example.uts_pppb1;

public class News {
    private String judulBerita;
    private String isiBerita;
    private String minUsia;
    private String kategori;

    public News(String judul, String isiBerita, String minUsia, String kategori){
        this.judulBerita = judul;
        this.isiBerita = isiBerita;
        this.minUsia = minUsia;
        this.kategori = kategori;
    }

    public News() {

    }

    public String getJudul() {
        return judulBerita;
    }

    public void setJudul(String judul) {
        this.judulBerita = judul;
    }

    public String getIsiBerita() {
        return isiBerita;
    }

    public void setIsiBerita(String isiBerita) {
        this.isiBerita = isiBerita;
    }

    public String getMinUsia() {
        return minUsia;
    }

    public void setMinUsia(String minUsia) {
        this.minUsia = minUsia;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }


}
