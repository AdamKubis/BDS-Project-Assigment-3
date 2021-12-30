package org.but.feec.project3.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.but.feec.project3.api.ProductBasicView;
import org.but.feec.project3.api.ProductCreateView;
import org.but.feec.project3.api.ProductEditView;
import org.but.feec.project3.data.ProductRepository;

import java.util.List;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductBasicView> getProductBasicView() {
        return productRepository.getProductBasicView();
    }

    public void createProduct(ProductCreateView productCreateView) {
        // the following three lines can be writteBDSn in one code line (only for more clear explanation it is written in three lines
        //productRepository.createProduct(productCreateView);
    }

    public void editProduct(ProductEditView productEditView) { productRepository.editProduct(productEditView);
    }

    /**
     * <p>
     * Note: For implementation details see: https://github.com/patrickfav/bcrypt
     * </p>
     *
     * @param password to be hashed
     * @return hashed password
     */
    private char[] hashPassword(char[] password) {
        return BCrypt.withDefaults().hashToChar(12, password);
    }


}
