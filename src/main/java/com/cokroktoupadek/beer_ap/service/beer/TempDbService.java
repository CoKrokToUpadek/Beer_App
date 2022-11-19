package com.cokroktoupadek.beer_ap.service.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.MethodEntity;
import com.cokroktoupadek.beer_ap.domain.entity.beer.TempEntity;
import com.cokroktoupadek.beer_ap.repository.beer.TempRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TempDbService {
    @Autowired
    TempRepository tempRepository;

    TempEntity save(TempEntity tempEntity){
        return tempRepository.save(tempEntity);
    }

    TempEntity findById(Long id) throws Exception {
        return tempRepository.findById(id).orElseThrow(Exception::new);
    }

    List<TempEntity> findById() {
        return tempRepository.findAll();
    }

    void DeleteById(Long id){
        tempRepository.deleteById(id);
    }

    void DeleteAll(){
        tempRepository.deleteAll();
    }
}
