package com.myretail.mapper;

import com.myretail.model.Image;
import com.myretail.model.Product;
import org.eclipse.rdf4j.query.BindingSet;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public   Product toProduct(BindingSet bs) {
        Product product = new Product();

        if (bs.hasBinding("id"))
            product.setId(bs.getValue("id").stringValue());

        if (bs.hasBinding("name"))
            product.setName(bs.getValue("name").stringValue());

        if (bs.hasBinding("brand"))
            product.setBrand(bs.getValue("brand").stringValue());

        if (bs.hasBinding("category"))
            product.setCategory(bs.getValue("category").stringValue());

        if (bs.hasBinding("imageURL")) {
            Image img = new Image();
            img.setUrl(bs.getValue("imageURL").stringValue());
            product.setImage(img);
        }

        return product;
    }
}
