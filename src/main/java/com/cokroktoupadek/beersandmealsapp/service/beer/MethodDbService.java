package com.cokroktoupadek.beersandmealsapp.service.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.MethodEntity;
import com.cokroktoupadek.beersandmealsapp.repository.beer.MethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MethodDbService {

    private MethodRepository methodRepository;

    @Autowired
    public MethodDbService(MethodRepository methodRepository) {
        this.methodRepository = methodRepository;
    }

    public MethodEntity save(MethodEntity methodEntity) {
        return methodRepository.save(methodEntity);
    }

    public Optional<MethodEntity> findById(Long id)  {
        return methodRepository.findById(id);
    }

    public List<MethodEntity> findAll() {
        return methodRepository.findAll();
    }


    public void deleteById(Long id) {
        methodRepository.deleteById(id);
    }

    public void deleteAll() {
        methodRepository.deleteAll();
    }
}
