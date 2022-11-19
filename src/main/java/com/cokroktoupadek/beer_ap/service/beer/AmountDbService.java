package com.cokroktoupadek.beer_ap.service.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.AmountEntity;
import com.cokroktoupadek.beer_ap.repository.beer.AmountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AmountDbService {
    @Autowired
    AmountRepository amountRepository;

    AmountEntity save(AmountEntity amountEntity){
        return amountRepository.save(amountEntity);
    }

    AmountEntity findById(Long id) throws Exception {
        return amountRepository.findById(id).orElseThrow(Exception::new);
    }

    List<AmountEntity> findById() {
        return amountRepository.findAll();
    }

    void DeleteById(Long id){
        amountRepository.deleteById(id);
    }

    void DeleteAll(){
        amountRepository.deleteAll();
    }

}
