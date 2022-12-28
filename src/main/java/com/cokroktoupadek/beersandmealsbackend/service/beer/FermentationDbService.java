package com.cokroktoupadek.beersandmealsbackend.service.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.FermentationEntity;
import com.cokroktoupadek.beersandmealsbackend.repository.beer.FermentationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FermentationDbService {

    private FermentationRepository fermentationRepository;
    @Autowired
    public FermentationDbService(FermentationRepository fermentationRepository) {
        this.fermentationRepository = fermentationRepository;
    }

    public FermentationEntity save(FermentationEntity fermentationEntity){
        return fermentationRepository.save(fermentationEntity);
    }

    public Optional<FermentationEntity> findById(Long id) {
        return fermentationRepository.findById(id);
    }

    public List<FermentationEntity> findById() {
        return fermentationRepository.findAll();
    }

    public void deleteById(Long id){
        fermentationRepository.deleteById(id);
    }

    public void deleteAll(){
        fermentationRepository.deleteAll();
    }
}
