package com.vdnt.demo.services;

import com.vdnt.demo.entity.JounralEntity;
import com.vdnt.demo.repository.JounralEntityRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JounralServices {

    @Autowired
    private JounralEntityRepository jounralEntityRepository;

    public boolean saveJounral(JounralEntity jounral) {
        jounralEntityRepository.save(jounral);
        return true;
    }

    public List<JounralEntity> getAll()
    {
        return jounralEntityRepository.findAll();
    }

    public Optional<JounralEntity> findById(ObjectId id)
    {
        return jounralEntityRepository.findById(id);
    }

    public void deleteById(ObjectId id)
    {
        jounralEntityRepository.deleteById(id);
    }

}
