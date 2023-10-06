package com.example.productservice.services;
import com.example.productservice.dtos.PriceDTO;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.models.Price;
import com.example.productservice.repositories.PriceRepository;
import com.example.productservice.utill.ObjectMapper;
import org.springframework.stereotype.Service;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    private PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<PriceDTO> getAllPriceList() {
        Iterable<Price> priceList = this.priceRepository.findAll();

        List<PriceDTO> priceDTOList = new ArrayList<>();
        for (Price prices : priceList) {
            priceDTOList.add(ObjectMapper.convertPriceToPriceDTO(prices));
        }
        return priceDTOList;
    }

    @Override
    public PriceDTO getPriceById(Long id) throws NotFoundException {
        Optional<Price> price = this.priceRepository.findById(id);
        if (price.isPresent())
            return ObjectMapper.convertPriceToPriceDTO(price.get());
        else
            throw new NotFoundException("Price does not exist with id : " + id);
    }

    @Override
    public PriceDTO createPrice(PriceDTO priceDTO) {
        Price price = new Price();
        price.setCurrency(priceDTO.getCurrency());
        price.setPrice(priceDTO.getPrice());
        Price createdPrice = this.priceRepository.save(price);
        return ObjectMapper.convertPriceToPriceDTO(createdPrice);
    }

    @Override
    public PriceDTO updatePriceById(Long id, PriceDTO priceDTO) throws NotFoundException {
        PriceDTO priceDTO1 = getPriceById(id);

        Price price = new Price();
        price.setCurrency(priceDTO.getCurrency());
        price.setPrice(price.getPrice());

        Price priceUpdate = this.priceRepository.save(price);
        return priceDTO1;
    }


    @Override
    public PriceDTO deletePriceById(Long id) {
        Optional<Price> price= this.priceRepository.findById(id);

        if(price.isPresent())
            this.priceRepository.deleteById(id);

        return ObjectMapper.convertPriceToPriceDTO(price.get());
    }

    @Override
    public List<PriceDTO> deleteAllPrice() {
        List<PriceDTO> priceDTOList=new ArrayList<>();
        for (Price price : this.priceRepository.findAll()) {
            priceDTOList.add(ObjectMapper.convertPriceToPriceDTO(price));
        }
        this.priceRepository.deleteAll();

        return priceDTOList;
    }
}