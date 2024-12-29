package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.LoginRequest;
import com.ecommerce.ecommerce.model.Barang;
import com.ecommerce.ecommerce.repository.BarangRepository;
import com.ecommerce.ecommerce.service.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("/api/barang")
public class BarangController {

    @Autowired
    private BarangService barangService;

    @PostMapping
    public ResponseEntity<Barang> saveBarang(@RequestBody Barang barang) {
        return new ResponseEntity<Barang>(barangService.saveBarang(barang), HttpStatus.CREATED);
    }

    // GetAll Rest Api
    @GetMapping
    public List<Barang> getAllBarang() {
        return barangService.getAllBarang();
    }

    // Get by Id Rest Api
    @GetMapping("{id}")
    // localhost:8080/api/barangs/1
    public ResponseEntity<Barang> getBarangById(@PathVariable("id") long barangID) {
        return new ResponseEntity<Barang>(barangService.getBarangById(barangID), HttpStatus.OK);
    }

    // Update Rest Api
    @PutMapping("{id}")
    public ResponseEntity<Barang> updateBarang(@PathVariable("id") long id,
            @RequestBody Barang barang) {
        return new ResponseEntity<Barang>(barangService.updateBarang(barang, id), HttpStatus.OK);
    }

    // Delete Rest Api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBarang(@PathVariable("id") long id) {
        // delete barang from db
        barangService.deleteBarang(id);
        return new ResponseEntity<String>("Barang deleted Successfully.", HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveBarang(
            @RequestParam("nama") String nama,
            @RequestParam("harga") String harga,
            @RequestParam("toko") String toko,
            @RequestParam("kategori") String kategori,
            @RequestParam("gambar") MultipartFile file) {

        try {
            // Simpan file ke direktori
            String uploadDir = "uploads/";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());

            // Simpan data barang
            Barang barang = new Barang();
            barang.setNama(nama);
            barang.setHarga(harga);
            barang.setToko(toko);
            barang.setKategori(kategori);
            barang.setGambar(fileName);

            BarangRepository barangRepository;
            barangRepository.save(barang);

            return ResponseEntity.ok("Barang berhasil disimpan!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}