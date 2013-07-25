package integration.com.trailblazers.freewheelers.persistence;


import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.mappers.ItemMapper;
import com.trailblazers.freewheelers.mappers.PaidOrderMapper;
import com.trailblazers.freewheelers.mappers.ReserveOrderMapper;
import com.trailblazers.freewheelers.model.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
public class PaidOrderMapperTest extends MapperTestBase {
    private PaidOrderMapper paidOrderMapper;
    private ReserveOrderMapper reserveOrderMapper;
    private AccountMapper accountMapper;
    private ItemMapper itemMapper;
    private final Account account = new Account();
    private final Item item = new Item();
    private final ReserveOrder reserveOrder = new ReserveOrder();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        paidOrderMapper = getSqlSession().getMapper(PaidOrderMapper.class);
        reserveOrderMapper = getSqlSession().getMapper(ReserveOrderMapper.class);
        accountMapper = getSqlSession().getMapper(AccountMapper.class);
        itemMapper = getSqlSession().getMapper(ItemMapper.class);

        account.setAccount_name("NameForAccount");
        account.setEmailAddress("email@test.com");
        account.setPassword("password");
        account.setPhoneNumber("");
        account.setAccount_id(2L);

        item.setItemId(3L);
        item.setName("name");
        item.setPrice(BigDecimal.ONE.setScale(2));
        item.setType(ItemType.FRAME);
        item.setQuantity(1L);
        item.setDescription("description");
        item.setImagePath("path/to/image");

        accountMapper.insert(account);
        itemMapper.insert(item);

        reserveOrder.setReservation_timestamp(new Date());
        reserveOrder.setAccount(account);
        reserveOrder.setItem(item);
        reserveOrder.setItem_id(itemMapper.getByName("name").getItemId());
        reserveOrder.setAccount_id(accountMapper.getByName("NameForAccount").getAccount_id());
        reserveOrder.setTransactionId("123456");
        reserveOrder.setOrder_id(1L);
        reserveOrder.setStatus(OrderStatus.PAID);

    }

    @Test
    public void shouldInsertAndGetPaidOrderInfo(){

        Long accountId = 1L;
        PaidOrder paidOrder = new PaidOrder();
        paidOrder.setAccount_id(accountId);
        paidOrder.setOrders(asList(mock(ReserveOrder.class)));
        paidOrderMapper.savePaidOrderInfo(paidOrder);
        Integer paidOrderId = paidOrderMapper.getLastPayId();
        paidOrder.setPay_id(paidOrderId);

        PaidOrder expectedPaidOrder = paidOrderMapper.getByPaidOrderId(paidOrderId);
        assertThat(expectedPaidOrder.getAccount_id(), is(paidOrder.getAccount_id()));
        assertThat(expectedPaidOrder.getPay_id(), is(paidOrder.getPay_id()));
        assertThat(expectedPaidOrder.getNet_total(), is(paidOrder.getNet_total()));
        assertThat(expectedPaidOrder.getGross_total(), is(paidOrder.getGross_total()));
        assertThat(expectedPaidOrder.getVat(), is(paidOrder.getVat()));
        assertThat(expectedPaidOrder.getDuty_tax(), is(paidOrder.getDuty_tax()));
    }

    @Test
    public void shouldInsertAndGetReservedItemsGivenPaidOrder() throws Exception {


        PaidOrder paidOrder = new PaidOrder();
        paidOrder.setAccount_id(2L);
        paidOrder.setOrders(asList(reserveOrder));


        paidOrderMapper.savePaidOrderInfo(paidOrder);
        Integer pay_id = paidOrderMapper.getLastPayId();
        paidOrderMapper.saveReservedItem(reserveOrder, pay_id);

        List<ReserveOrder> orderList = paidOrderMapper.getReserveItems(pay_id);

        assertTrue(orderList.contains(reserveOrder));
        assertThat(orderList.get(0).getAccount(), is(account));
        assertThat(orderList.get(0).getItem(), is(item));
        assertThat(orderList.get(0).getTransactionId(), is("123456"));
    }

    @Test
    public void shouldGetOnePaidOrder() throws Exception {
        ReserveOrder reserveOrder2 = new ReserveOrder();

        reserveOrder2.setReservation_timestamp(new Date());
        reserveOrder2.setAccount(account);
        reserveOrder2.setItem(item);
        reserveOrder2.setItem_id(itemMapper.getByName("name").getItemId());
        reserveOrder2.setAccount_id(accountMapper.getByName("NameForAccount").getAccount_id());
        reserveOrder2.setTransactionId("1234567");
        reserveOrder2.setOrder_id(1L);

        PaidOrder paidOrder = new PaidOrder();
        paidOrder.setAccount_id(2L);
        paidOrder.setOrders(asList(reserveOrder, reserveOrder2));

        reserveOrder.setOrder_id(1L);
        reserveOrder2.setOrder_id(3L);

        paidOrderMapper.savePaidOrderInfo(paidOrder);
        Integer pay_id = paidOrderMapper.getLastPayId();
        paidOrderMapper.saveReservedItem(reserveOrder, pay_id);
        paidOrderMapper.saveReservedItem(reserveOrder2, pay_id);

        PaidOrder expectPaidOrder = paidOrderMapper.getByPaidOrderId(pay_id);

        assertThat(expectPaidOrder.getOrders().size(), is(2));
    }

    @Test
    public void shouldGetPaidOrderList() throws Exception {
        ReserveOrder reserveOrder2 = new ReserveOrder(4L, 5L, new Date());

        PaidOrder paidOrder = new PaidOrder();
        paidOrder.setAccount_id(2L);
        paidOrder.setOrders(asList(reserveOrder));

        PaidOrder paidOrder2 = new PaidOrder();
        paidOrder2.setAccount_id(4L);
        paidOrder2.setOrders(asList(reserveOrder2));

        reserveOrder.setOrder_id(167L);
        reserveOrder2.setOrder_id(389L);

        paidOrderMapper.savePaidOrderInfo(paidOrder);
        Integer pay_id = paidOrderMapper.getLastPayId();

        paidOrderMapper.savePaidOrderInfo(paidOrder2);
        Integer pay_id2 = paidOrderMapper.getLastPayId();

        paidOrderMapper.saveReservedItem(reserveOrder, pay_id);
        paidOrderMapper.saveReservedItem(reserveOrder2, pay_id2);

        List<PaidOrder> paidOrders = paidOrderMapper.getAllPaidOrders();

        assertTrue(paidOrders.contains(paidOrder));
        assertTrue(paidOrders.contains(paidOrder2));
    }
}
