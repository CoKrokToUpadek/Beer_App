package com.cokroktoupadek.beer_ap.service.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.MashTempEntity;
import com.cokroktoupadek.beer_ap.repository.beer.MashTempRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MashTempDbService {

   private MashTempRepository mashTempRepository;

    public MashTempDbService(MashTempRepository mashTempRepository) {
        this.mashTempRepository = mashTempRepository;
    }

    MashTempEntity save(MashTempEntity mashTempEntity){
        return mashTempRepository.save(mashTempEntity);
    }

    MashTempEntity findById(Long id) throws Exception {
        return mashTempRepository.findById(id).orElseThrow(Exception::new);
    }

    List<MashTempEntity> findById() {
        return mashTempRepository.findAll();
    }

    void deleteById(Long id){
        mashTempRepository.deleteById(id);
    }

    void deleteAll(){
        mashTempRepository.deleteAll();
    }
}
