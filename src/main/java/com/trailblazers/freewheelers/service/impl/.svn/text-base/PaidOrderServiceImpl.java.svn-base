package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.mappers.PaidOrderMapper;
import com.trailblazers.freewheelers.model.OrderStatus;
import com.trailblazers.freewheelers.model.PaidOrder;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.PaidOrderService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PaidOrderServiceImpl implements PaidOrderService {

    private final PaidOrderMapper paidOrderMapper;
    private final SqlSession sqlSession;

    private ItemService itemService;
    private ReserveOrderService reserveOrderService ;

    public PaidOrderServiceImpl() {
        this(MyBatisUtil.getSqlSession());
    }

    public PaidOrderServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.paidOrderMapper = sqlSession.getMapper(PaidOrderMapper.class);
        this.itemService = new ItemServiceImpl();
        this.reserveOrderService = new ReserveOrderServiceImpl();
    }

    public void setItemService(ItemService itemService){
        this.itemService = itemService;
    }

    @Override
    public void setReserveOrderService(ReserveOrderService reserveOrderService) {
        this.reserveOrderService = reserveOrderService;
    }

    @Override
    public List<PaidOrder> getAllPaidOrders() {

        return paidOrderMapper.getAllPaidOrders();
    }

    @Override
    public void moveItemsToPaidForAccountId(Long accountId) {
        List<ReserveOrder> reserveOrderList = reserveOrderService.findAllOrdersByAccountId(accountId);
        PaidOrder paidOrder = new PaidOrder();
        paidOrder.setAccount_id(accountId);
        paidOrder.setOrders(reserveOrderList);

        paidOrderMapper.savePaidOrderInfo(paidOrder);

        paidOrder.setPay_id(paidOrderMapper.getLastPayId());

        for (ReserveOrder reserveOrder : reserveOrderList) {
            reserveOrder.setStatus(OrderStatus.PAID);
            paidOrderMapper.saveReservedItem(reserveOrder, paidOrder.getPay_id());
            itemService.decreaseQuantityByOneForItemId(reserveOrder.getItem_id());
            reserveOrderService.delete(reserveOrder);
        }
    }


}

