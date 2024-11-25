package ua.edu.chmnu.ki.network.property.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.chmnu.ki.network.property.error.PropertiesException;
import ua.edu.chmnu.ki.network.property.filter.dto.PropertiesFilterDTO;
import ua.edu.chmnu.ki.network.property.filter.mapper.EntitySpecFilterMapper;
import ua.edu.chmnu.ki.network.property.mapper.PropertiesMapper;
import ua.edu.chmnu.ki.network.property.persistence.entity.Properties;
import ua.edu.chmnu.ki.network.property.persistence.enums.Status;
import ua.edu.chmnu.ki.network.property.persistence.repository.PropertiesRepository;
import ua.edu.chmnu.ki.network.property.web.dto.PageDTO;
import ua.edu.chmnu.ki.network.property.web.dto.PropertiesDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertiesServiceImpl implements PropertiesService{
    private final PropertiesRepository propertiesRepository;

    private final PropertiesMapper propertiesMapper;

    private final EntitySpecFilterMapper<PropertiesFilterDTO, Properties> propertiesSpecFilterMapper;

    @Transactional(readOnly = true)
    @Override
    public PropertiesDTO getById(Integer id) {
        return propertiesRepository.findById(id)
                .map(propertiesMapper::mapTo)
                .orElseThrow(() -> new PropertiesException("Properties not found " + id));
    }

    //@Transactional(readOnly = true)
    //@Override
    //public List<PropertiesDTO> getAllBy(PropertiesFilterDTO filter) {
    //    return getAllBy(filter, Sort.by(Sort.Order.asc("id")));
    //}
    @Transactional(readOnly = true)
    @Override
    public List<PropertiesDTO> getAllBy(PropertiesFilterDTO filter) {
        return getAllBy(filter, Sort.by(Sort.Order.asc("id"))).stream()
                .filter(properties -> properties.getStatus() == Status.AVAILABLE)
                .toList();
    }


    @Transactional(readOnly = true)
    @Override
    public List<PropertiesDTO> getAllBy(PropertiesFilterDTO filter, Sort sort) {
        var specification = propertiesSpecFilterMapper.mapTo(filter);

        return propertiesRepository.findAll(specification, sort).stream()
                .map(propertiesMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public PageDTO<PropertiesDTO> getAllBy(PropertiesFilterDTO filter, Pageable pageable) {
        var specification = propertiesSpecFilterMapper.mapTo(filter);

        return PageDTO.of(propertiesRepository.findAll(specification, pageable), propertiesMapper::mapTo);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existById(Integer id) {
        return propertiesRepository.existsById(id);
    }

    @Transactional
    @Override
    public PropertiesDTO create(PropertiesDTO source) {
        var entity = propertiesMapper.mapTo(source);

        entity.setId(null);
        entity.setStatus(Status.AVAILABLE);

        return propertiesMapper.mapTo(propertiesRepository.save(entity));
    }

    @Transactional
    @Override
    public PropertiesDTO updateById(Integer id, PropertiesDTO source) {
        return propertiesRepository.findById(id)
                .map(e -> propertiesMapper.mapWith(source, e))
                .map(propertiesMapper::mapTo)
                .orElseThrow(() -> new PropertiesException("Properties not found " + id));
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        if (!existById(id)) {
            throw new PropertiesException("Properties not found " + id);
        }

        propertiesRepository.deleteById(id);
    }

    @Transactional
    public String setStatusSold(Integer propertiesId) {
        if (propertiesId == null) {
            throw new IllegalArgumentException("Properties ID must be provided");
        }
        // Find the book
        Properties properties = propertiesRepository.findById(propertiesId)
                .orElseThrow(() -> new PropertiesException("Properties with id " + propertiesId + " not found"));

        // Ensure the book is available
        if (!properties.getStatus().equals(Status.AVAILABLE)) {
            throw new PropertiesException("Properties with id " + propertiesId + " is already sold");
        }


        properties.setStatus(Status.SOLD);
        propertiesRepository.save(properties);


        return String.format("Properties with ID %d successfully sold", propertiesId);
    }
}


