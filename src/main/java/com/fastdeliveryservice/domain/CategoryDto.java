package com.fastdeliveryservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author  mGabellini
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDto {
    private String Id;

    private String code ;

    /**
     * Returns an void from setId.
     *
     * @param  id
     */

    public void setId(String id) {
        Id = id;
    }

    /**
     * Returns an void from getId.
     *
     */

    public String getId() {
        return Id;
    }

    /**
     * Returns an void from setCode.
     *
     * @param  code
     */

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns an void from getCode.
     *
     * @return code
     */

    public String getCode() {
        return code;
    }

    /**
     * Returns an void from setProducts.
     *
     * @param  products
     */

    public void setProducts(Collection<ProductDto> products) {
        this.products = products;
    }

    /**
     * Returns an void from setProducts.
     *
     * @return Collection<ProductDto>
     * @see Collection<ProductDto>
     */

    public Collection<ProductDto> getProducts() {
        return products;
    }

    public CategoryDto()
    {
        products=new ArrayList<>();
    }

    private Collection<ProductDto> products;
}
