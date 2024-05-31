package com.product.productproj;


import com.product.productproj.vo.ResultVO;

import java.util.List;

public interface ProductService {

    void query();

    ResultVO productList(List<String> itemIds);

}
