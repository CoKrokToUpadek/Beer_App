package com.cokroktoupadek.beersandmealsbackend.service.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.TempEntity;
import com.cokroktoupadek.beersandmealsbackend.repository.beer.TempRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TempDbService {

   private TempRepository tempRepository;
    @Autowired
    public TempDbService(TempRepository tempRepository) {
        this.tempRepository = tempRepository;
    }

    public TempEntity save(TempEntity tempEntity){
        return tempRepository.save(tempEntity);
    }


    public Optional<TempEntity> findByValueAndUnit(Integer value,String unit){
        return tempRepository.findByValueAndUnit(value,unit);
    }

    void deleteById(Long id){
        tempRepository.deleteById(id);
    }

    public void deleteAll(){
        tempRepository.deleteAll();
    }

    public List<TempEntity> findAll() {
        return tempRepository.findAll();
    }
}
