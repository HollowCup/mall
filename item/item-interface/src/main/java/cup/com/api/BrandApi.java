package cup.com.api;

import cup.com.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("brand")
public interface BrandApi {
    @GetMapping("{id}")
    Brand queryBrandById(@PathVariable("id") Long id);
}
