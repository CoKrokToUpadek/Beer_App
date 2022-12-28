package com.cokroktoupadek.beersandmealsbackend.service.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.HopsEntity;
import com.cokroktoupadek.beersandmealsbackend.repository.beer.HopsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class HopsDbService {

   private HopsRepository hopsRepository;
    @Autowired
    public HopsDbService(HopsRepository hopsRepository) {
        this.hopsRepository = hopsRepository;
    }

    public HopsEntity save(HopsEntity hopsEntity){
        return hopsRepository.save(hopsEntity);
    }

    public Optional<HopsEntity> findById(Long id) {
        return hopsRepository.findById(id);
    }

    public List<HopsEntity> findById() {
        return hopsRepository.findAll();
    }

    public void deleteById(Long id){
        hopsRepository.deleteById(id);
    }

    public void deleteAll(){
        hopsRepository.deleteAll();
    }
}
