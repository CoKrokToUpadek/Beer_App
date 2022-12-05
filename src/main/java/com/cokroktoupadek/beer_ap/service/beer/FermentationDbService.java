package com.cokroktoupadek.beer_ap.service.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.FermentationEntity;
import com.cokroktoupadek.beer_ap.repository.beer.FermentationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FermentationDbService {

    private FermentationRepository fermentationRepository;
    @Autowired
    public FermentationDbService(FermentationRepository fermentationRepository) {
        this.fermentationRepository = fermentationRepository;
    }

    FermentationEntity save(FermentationEntity fermentationEntity){
        return fermentationRepository.save(fermentationEntity);
    }

    FermentationEntity findById(Long id) throws Exception {
        return fermentationRepository.findById(id).orElseThrow(Exception::new);
    }

    List<FermentationEntity> findById() {
        return fermentationRepository.findAll();
    }

    void deleteById(Long id){
        fermentationRepository.deleteById(id);
    }

    void deleteAll(){
        fermentationRepository.deleteAll();
    }
}
