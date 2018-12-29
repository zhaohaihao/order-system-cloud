package com.hilox.order.service;

import com.hilox.order.config.WechatAccountConfig;
import com.hilox.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 消息推送Service
 * Created by Hilox on 2018/12/29 0029.
 */
@Service
@Slf4j
public class PushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("templateId"));
        wxMpTemplateMessage.setToUser(orderDTO.getBuyerOpenid());

        List<WxMpTemplateData> datas = Arrays.asList(
                new WxMpTemplateData("first", "亲, 请记得收货。"),
                new WxMpTemplateData("keyword1", "微信点餐"),
                new WxMpTemplateData("keyword2", "15088888879"),
                new WxMpTemplateData("keyword3", orderDTO.getId()),
                new WxMpTemplateData("keyword4", orderDTO.getOrderMasterStateEnum().getMessage()),
                new WxMpTemplateData("keyword5", "￥" + orderDTO.getAmount()),
                new WxMpTemplateData("remark", "祝您用餐愉快!")
        );

        wxMpTemplateMessage.setData(datas);

        try {
            wxMpService.getTemplateMsgService()
                    .sendTemplateMsg(wxMpTemplateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模板消息】发送失败, {}", e);
        }
    }
}
