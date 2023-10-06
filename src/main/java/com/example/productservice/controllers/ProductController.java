import com.example.productservice.dtos.ProductDTO;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.services.SelfProduct;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private SelfProduct selfProductService;

    public ProductController(SelfProduct selfProduct) {
        this.selfProductService = selfProduct;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return this.selfProductService.getAllProducts();
    }


    @GetMapping("{id}")
    public ProductDTO getProductById(@PathVariable("id") Long id) throws Exception{
        return this.selfProductService.getById(id);
    }


    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return this.selfProductService.createProduct(productDTO);
    }

    @DeleteMapping("{id}")
    public ProductDTO deleteById(@PathVariable("id") Long id) throws NotFoundException {

        ProductDTO productDTO= this.selfProductService.deleteProductById(id);

        if(productDTO==null)
            throw new NotFoundException("Product does not exist with id :  "+id);

        return productDTO;

    }

    @PutMapping("{id}")
    public  ProductDTO updateById(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) throws NotFoundException{
        return this.selfProductService.updateProductById(id, productDTO);
    }
}