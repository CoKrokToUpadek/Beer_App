package com.cokroktoupadek.beersandmealsbackend.service.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.MashTempEntity;
import com.cokroktoupadek.beersandmealsbackend.repository.beer.MashTempRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MashTempDbService {

   private MashTempRepository mashTempRepository;
    @Autowired
    public MashTempDbService(MashTempRepository mashTempRepository) {
        this.mashTempRepository = mashTempRepository;
    }

    public MashTempEntity save(MashTempEntity mashTempEntity){
        return mashTempRepository.save(mashTempEntity);
    }

    public Optional<MashTempEntity> findById(Long id)  {
        return mashTempRepository.findById(id);
    }

    public List<MashTempEntity> findAll() {
        return mashTempRepository.findAll();
    }

    public  void deleteById(Long id){
        mashTempRepository.deleteById(id);
    }

    public void deleteAll(){
        mashTempRepository.deleteAll();
    }
}
