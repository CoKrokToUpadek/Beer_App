package com.cokroktoupadek.beersandmealsbackend.service.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.AmountEntity;
import com.cokroktoupadek.beersandmealsbackend.repository.beer.AmountRepository;
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

   public AmountEntity save(AmountEntity amountEntity){
        return amountRepository.save(amountEntity);
    }

   public Optional<AmountEntity> findById(Long id)  {
        return amountRepository.findById(id);
    }

   public  Optional<AmountEntity> findByValueAndUnit(Double value, String unit){
        return amountRepository.findByValueAndUnit(value,unit);
    }

    void deleteById(Long id){
        amountRepository.deleteById(id);
    }

   public void deleteAll(){
        amountRepository.deleteAll();
    }

    public List<AmountEntity> findAll() {
        return amountRepository.findAll();
    }
}
