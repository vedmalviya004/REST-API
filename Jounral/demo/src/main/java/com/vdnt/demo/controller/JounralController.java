package com.vdnt.demo.controller;

import com.vdnt.demo.entity.JounralEntity;
import com.vdnt.demo.services.JounralServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/jounral")
public class JounralController {

    @Autowired
    private JounralServices jounralServices;

    @GetMapping
    public ResponseEntity<?> getAllJounral() {
     List<JounralEntity> getList = jounralServices.getAll();
     if(!getList.isEmpty() && getList != null)
     {
         return new ResponseEntity<>(getList , HttpStatus.NOT_FOUND);
     }
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JounralEntity> addJounral(@RequestBody JounralEntity jounral) {
     try {
         jounral.setDate(LocalDateTime.now());
         jounralServices.saveJounral(jounral);
         return new ResponseEntity<>( HttpStatus.CREATED);

     }
     catch (Exception e)
     {
         return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
     }
    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<JounralEntity> getJounralEntityById(@PathVariable ObjectId myid) {
        Optional<JounralEntity> jounralE = jounralServices.findById(myid);
        if (jounralE.isPresent())
        {
            return new ResponseEntity<>(jounralE.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> deleteEntityById(@PathVariable ObjectId myid) {
        jounralServices.deleteById(myid);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{myid}")
    public ResponseEntity<?> updateEntityById(@PathVariable ObjectId myid, @RequestBody JounralEntity newjounral) {
        JounralEntity old = jounralServices.findById(myid).orElse(null);
        if(old!=null)
        {
            old.setTitle(newjounral.getTitle() != null && !newjounral.getTitle().equals("") ? newjounral.getTitle() : old.getTitle());
            old.setContent(newjounral.getContent() != null && !newjounral.getContent().equals("") ? newjounral.getContent() : old.getContent());
            jounralServices.saveJounral(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
