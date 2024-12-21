package com.ecommerce.ecommerce.controller;
import com.ecommerce.ecommerce.dto.LoginRequest;
import com.ecommerce.ecommerce.model.Keranjang;
import com.ecommerce.ecommerce.service.KeranjangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/keranjang")
public class KeranjangController {

    @Autowired
    private KeranjangService keranjangService;

    @PostMapping
    public ResponseEntity<Keranjang> saveKeranjang(@RequestBody Keranjang keranjang){
        return new ResponseEntity<Keranjang>(keranjangService.saveAndUpdateKeranjang(keranjang), HttpStatus.CREATED);
    }
    //GetAll Rest Api
    @GetMapping
    public List<Keranjang> getAllKeranjang(){
        return keranjangService.getAllKeranjang();
    }

    //Get by Id Rest Api
    @GetMapping("{id}")
    // localhost:8080/api/keranjangs/1
    public ResponseEntity<Keranjang> getKeranjangById(@PathVariable("id") long keranjangID){
        return new ResponseEntity<Keranjang>(keranjangService.getKeranjangById(keranjangID),HttpStatus.OK);
    }

    //Update Rest Api
    @PutMapping("{id}")
    public ResponseEntity<Keranjang> updateKeranjang(@PathVariable("id") long id,
                                                   @RequestBody Keranjang keranjang){
        return new ResponseEntity<Keranjang>(keranjangService.updateKeranjang(keranjang,id),HttpStatus.OK);
    }

    //Delete Rest Api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteKeranjang(@PathVariable("id") long id){
        //delete keranjang from db
        keranjangService.deleteKeranjang(id);
        return new ResponseEntity<String>("Keranjang deleted Successfully.",HttpStatus.OK);
    }
    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<Keranjang>> getKeranjangByUser(@PathVariable long idUser) {
        List<Keranjang> keranjang = keranjangService.getUserKeranjang(idUser);
        return ResponseEntity.ok(keranjang);
    }
}