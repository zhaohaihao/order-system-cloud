package com.hilox.order.converter;

import com.hilox.order.dto.OrderDTO;
import com.hilox.order.model.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Hilox on 2018/12/21 0021.
 */
public class OrderMaster2OrderDTOConverter {

    /**
     * 转换器
     * @param orderMaster
     * @return
     */
    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    /**
     * 转换器
     * @param orderMasterList
     * @return
     */
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e ->
            convert(e)
        ).collect(Collectors.toList());
    }
}
