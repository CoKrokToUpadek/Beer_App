package com.cokroktoupadek.beer_ap.service.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.HopsEntity;
import com.cokroktoupadek.beer_ap.repository.beer.HopsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HopsDbService {

   private HopsRepository hopsRepository;
    @Autowired
    public HopsDbService(HopsRepository hopsRepository) {
        this.hopsRepository = hopsRepository;
    }

    HopsEntity save(HopsEntity hopsEntity){
        return hopsRepository.save(hopsEntity);
    }

    HopsEntity findById(Long id) throws Exception {
        return hopsRepository.findById(id).orElseThrow(Exception::new);
    }

    List<HopsEntity> findById() {
        return hopsRepository.findAll();
    }

    void deleteById(Long id){
        hopsRepository.deleteById(id);
    }

    void deleteAll(){
        hopsRepository.deleteAll();
    }
}
