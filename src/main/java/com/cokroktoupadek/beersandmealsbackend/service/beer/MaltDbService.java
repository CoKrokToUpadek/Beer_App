package com.cokroktoupadek.beersandmealsbackend.service.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.MaltEntity;
import com.cokroktoupadek.beersandmealsbackend.repository.beer.MaltRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MaltDbService {

    private MaltRepository maltRepository;
    @Autowired
    public MaltDbService(MaltRepository maltRepository) {
        this.maltRepository = maltRepository;
    }

    public MaltEntity save(MaltEntity maltEntity){
        return maltRepository.save(maltEntity);
    }

    public Optional<MaltEntity> findById(Long id)  {
        return maltRepository.findById(id);
    }

    public List<MaltEntity> findById() {
        return maltRepository.findAll();
    }

    public void deleteById(Long id){
        maltRepository.deleteById(id);
    }

    public void deleteAll(){
        maltRepository.deleteAll();
    }
}
