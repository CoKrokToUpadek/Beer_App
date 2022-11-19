package com.cokroktoupadek.beer_ap.service.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.IngredientsEntity;
import com.cokroktoupadek.beer_ap.domain.entity.beer.MaltEntity;
import com.cokroktoupadek.beer_ap.repository.beer.MaltRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MaltDbService {
    @Autowired
    MaltRepository maltRepository;

    MaltEntity save(MaltEntity maltEntity){
        return maltRepository.save(maltEntity);
    }

    MaltEntity findById(Long id) throws Exception {
        return maltRepository.findById(id).orElseThrow(Exception::new);
    }

    List<MaltEntity> findById() {
        return maltRepository.findAll();
    }

    void DeleteById(Long id){
        maltRepository.deleteById(id);
    }

    void DeleteAll(){
        maltRepository.deleteAll();
    }
}
