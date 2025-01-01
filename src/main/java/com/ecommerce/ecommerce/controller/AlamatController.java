package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.LoginRequest;
import com.ecommerce.ecommerce.model.Alamat;
import com.ecommerce.ecommerce.repository.AlamatRepository;
import com.ecommerce.ecommerce.service.AlamatService;
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
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/api/alamat")
public class AlamatController {

    @Autowired
    private AlamatService alamatService;

    @PostMapping
    public ResponseEntity<Alamat> saveAlamat(@RequestBody Alamat alamat) {
        return new ResponseEntity<Alamat>(alamatService.saveAlamat(alamat), HttpStatus.CREATED);
    }

    // GetAll Rest Api
    @GetMapping
    public List<Alamat> getAllAlamat(@RequestParam(required = false) String nama,
            @RequestParam(required = false) String icon) {

        return alamatService.getAllAlamat(nama, icon);
    }

    // Get by Id Rest Api
    @GetMapping("{id}")
    // localhost:8080/api/alamats/1
    public ResponseEntity<Alamat> getAlamatById(@PathVariable("id") long alamatID) {
        return new ResponseEntity<Alamat>(alamatService.getAlamatById(alamatID), HttpStatus.OK);
    }

    // Update Rest Api
    @PutMapping("{id}")
    public ResponseEntity<Alamat> updateAlamat(@PathVariable("id") long id,
            @RequestBody Alamat alamat) {
        return new ResponseEntity<Alamat>(alamatService.updateAlamat(alamat, id), HttpStatus.OK);
    }

    // Delete Rest Api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAlamat(@PathVariable("id") long id) {
        // delete alamat from db
        alamatService.deleteAlamat(id);
        return new ResponseEntity<String>("Alamat deleted Successfully.", HttpStatus.OK);
    }
}