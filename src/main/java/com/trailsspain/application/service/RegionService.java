package com.trailsspain.application.service;

import com.trailsspain.api.dto.request.CreateRegionRequest;
import com.trailsspain.api.dto.request.UpdateRegionRequest;
import com.trailsspain.api.dto.response.RegionResponse;
import com.trailsspain.domain.model.Region;
import com.trailsspain.domain.repository.RegionRepository;
import com.trailsspain.api.mapper.RegionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RegionService {

    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    @Transactional(readOnly = true)
    public RegionResponse findById(Long id) {
        return regionRepository.findById(id)
                .map(regionMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Region not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<RegionResponse> findAll() {
        return regionRepository.findAll().stream()
                .map(regionMapper::toResponse)
                .toList();
    }

    public RegionResponse create(CreateRegionRequest request) {
        Region region = regionMapper.toEntity(request);
        Region saved = regionRepository.save(region);
        return regionMapper.toResponse(saved);
    }

    public RegionResponse update(Long id, UpdateRegionRequest request) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Region not found with id: " + id));
        regionMapper.updateEntity(request, region);
        Region updated = regionRepository.save(region);
        return regionMapper.toResponse(updated);
    }

    public void delete(Long id) {
        if (!regionRepository.existsById(id)) {
            throw new RuntimeException("Region not found with id: " + id);
        }
        regionRepository.deleteById(id);
    }
}
