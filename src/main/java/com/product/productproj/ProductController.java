package com.product.productproj;

import com.product.productproj.vo.ParamVO;
import com.product.productproj.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/query")
    public ResultVO query(@RequestBody ParamVO paramVO){
        ResultVO result=new ResultVO();
        if(paramVO==null || paramVO.getItemIds().size()==0 || paramVO.getItemIds().size()>100){
            return result;
        }
        return productService.productList(paramVO.getItemIds());
    }
}
