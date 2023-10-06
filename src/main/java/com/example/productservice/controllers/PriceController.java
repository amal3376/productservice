import com.example.productservice.dtos.PriceDTO;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.services.PriceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/price")
public class PriceController {

    private PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public List<PriceDTO> getAllPriceList(){
        return this.priceService.getAllPriceList();
    }

    @GetMapping("{id}")
    public PriceDTO getPriceById(@PathVariable("id") Long id) throws NotFoundException {
        return this.priceService.getPriceById(id);
    }

    @PostMapping()
    public PriceDTO createPrice(@RequestBody PriceDTO priceDTO){
        return this.priceService.createPrice(priceDTO);
    }

    @PutMapping("{id}")
    public PriceDTO updatePriceById(@PathVariable("id") Long id, @RequestBody PriceDTO priceDTO) throws NotFoundException{
        return this.priceService.updatePriceById(id,priceDTO);
    }

    @DeleteMapping("{id}")
    public PriceDTO deletePriceById(@PathVariable("id") Long id) throws NotFoundException{
        return this.priceService.deletePriceById(id);
    }

    @DeleteMapping
    public List<PriceDTO> deleteAllPrice(){
        return this.priceService.deleteAllPrice();
    }
}