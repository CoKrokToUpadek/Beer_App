package com.cokroktoupadek.beer_ap.service.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.AmountEntity;
import com.cokroktoupadek.beer_ap.repository.beer.AmountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AmountDbService {

    private AmountRepository amountRepository;
    @Autowired
    public AmountDbService(AmountRepository amountRepository) {
        this.amountRepository = amountRepository;
    }

    AmountEntity save(AmountEntity amountEntity){
        return amountRepository.save(amountEntity);
    }

    AmountEntity findById(Long id) throws Exception {
        return amountRepository.findById(id).orElseThrow(Exception::new);
    }

    Optional<AmountEntity> findByValueAndUnit(Double value, String unit){
        return amountRepository.findByValueAndUnit(value,unit);
    }



    List<AmountEntity> findById() {
        return amountRepository.findAll();
    }

    void deleteById(Long id){
        amountRepository.deleteById(id);
    }

    void deleteAll(){
        amountRepository.deleteAll();
    }

    public List<AmountEntity> findAll() {
        return amountRepository.findAll();
    }
}
