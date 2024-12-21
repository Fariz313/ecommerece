package com.ecommerce.ecommerce.controller;
import com.ecommerce.ecommerce.dto.LoginRequest;
import com.ecommerce.ecommerce.model.Barang;
import com.ecommerce.ecommerce.service.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/barang")
public class BarangController {

    @Autowired
    private BarangService barangService;

    @PostMapping
    public ResponseEntity<Barang> saveBarang(@RequestBody Barang barang){
        return new ResponseEntity<Barang>(barangService.saveBarang(barang), HttpStatus.CREATED);
    }
    //GetAll Rest Api
    @GetMapping
    public List<Barang> getAllBarang(){
        return barangService.getAllBarang();
    }

    //Get by Id Rest Api
    @GetMapping("{id}")
    // localhost:8080/api/barangs/1
    public ResponseEntity<Barang> getBarangById(@PathVariable("id") long barangID){
        return new ResponseEntity<Barang>(barangService.getBarangById(barangID),HttpStatus.OK);
    }

    //Update Rest Api
    @PutMapping("{id}")
    public ResponseEntity<Barang> updateBarang(@PathVariable("id") long id,
                                                   @RequestBody Barang barang){
        return new ResponseEntity<Barang>(barangService.updateBarang(barang,id),HttpStatus.OK);
    }

    //Delete Rest Api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBarang(@PathVariable("id") long id){
        //delete barang from db
        barangService.deleteBarang(id);
        return new ResponseEntity<String>("Barang deleted Successfully.",HttpStatus.OK);
    }

}