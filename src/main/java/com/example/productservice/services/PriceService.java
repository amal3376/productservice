package com.example.productservice.services;
import com.example.productservice.dtos.PriceDTO;
import com.example.productservice.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PriceService {

    public List<PriceDTO> getAllPriceList();

    public PriceDTO getPriceById(Long id) throws NotFoundException;

    public PriceDTO createPrice(PriceDTO priceDTO);

    public PriceDTO updatePriceById(Long id, PriceDTO priceDTO) throws NotFoundException;

    public PriceDTO deletePriceById(Long id);

    public List<PriceDTO> deleteAllPrice();

}