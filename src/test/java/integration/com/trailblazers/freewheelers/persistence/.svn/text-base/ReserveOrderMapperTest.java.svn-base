package integration.com.trailblazers.freewheelers.persistence;

import com.trailblazers.freewheelers.mappers.ReserveOrderMapper;
import com.trailblazers.freewheelers.model.OrderStatus;
import com.trailblazers.freewheelers.model.ReserveOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ReserveOrderMapperTest extends MapperTestBase {

    private ReserveOrderMapper reserveOrderMapper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        reserveOrderMapper = getSqlSession().getMapper(ReserveOrderMapper.class);
    }

    private ReserveOrder someOrder() {
        return new ReserveOrder()
                .setAccount_id(1L)
                .setItem_id((long) 1)
                .setItem_quantity((long) 3)
                .setReservation_timestamp(new Date())
                .setTransactionId("11111");
    }

    @Test
    public void shouldInsertAndGetAnOrder() throws Exception {
        ReserveOrder tobeInserted = someOrder();

        reserveOrderMapper.insert(tobeInserted);
        ReserveOrder fetched = reserveOrderMapper.get(tobeInserted.getOrder_id());

        assertThat(fetched, is(not(nullValue())));
    }

    @Test
    public void shouldDeleteAnOrder() throws Exception {
        ReserveOrder tobeDeleted = someOrder();
        reserveOrderMapper.insert(tobeDeleted);

        reserveOrderMapper.delete(tobeDeleted);

        ReserveOrder fetched = reserveOrderMapper.get(tobeDeleted.getOrder_id());
        assertThat(fetched, is(nullValue()));
    }

    @Test
    public void shouldUpdateAnOrder() throws Exception {
        ReserveOrder toBeUpdated = someOrder().setNote("");
        reserveOrderMapper.insert(toBeUpdated);

        toBeUpdated.setNote("A very important note.").setTransactionId("newTransactionId");
        reserveOrderMapper.save(toBeUpdated);

        ReserveOrder fetched = reserveOrderMapper.get(toBeUpdated.getOrder_id());
        assertThat(fetched.getNote(), is("A very important note."));
        assertThat(fetched.getTransactionId(), is("newTransactionId"));
    }

    @Test
    public void shouldUpdateItemQuantity() throws Exception {
        ReserveOrder toBeUpdated = someOrder().setItem_quantity((long) 1);
        reserveOrderMapper.insert(toBeUpdated);

        toBeUpdated.setItem_quantity((long) 2);
        reserveOrderMapper.save(toBeUpdated);

        ReserveOrder fetched = reserveOrderMapper.get(toBeUpdated.getOrder_id());
        assertThat(fetched.getItem_quantity(), is((long) 2));
    }

    @Test
    public void shouldFindAllOrders() throws Exception {
        int before = reserveOrderMapper.findAll().size();
        reserveOrderMapper.insert(someOrder());

        List<ReserveOrder> all = reserveOrderMapper.findAll();

        assertThat(all.size(), is(before + 1));
    }

    @Test
    public void shouldFindAllOrdersForAnAccount() throws Exception {
        long someAccount = (long) 1;
        long anotherAccount = (long) 1;
        int before = reserveOrderMapper.findAllFor(someAccount).size();

        reserveOrderMapper.insert(someOrder().setAccount_id(someAccount));
        reserveOrderMapper.insert(someOrder().setAccount_id(anotherAccount));

        List<ReserveOrder> all = reserveOrderMapper.findAllFor(someAccount);

        assertThat(all.size(), is(before + 2));
    }

    @Test
    public void shouldSaveTransactionID() throws Exception {
        ReserveOrder order = someOrder();
        reserveOrderMapper.insert(order);

        ReserveOrder savedOrder = reserveOrderMapper.get(order.getOrder_id());
        savedOrder.setTransactionId("123456");
        reserveOrderMapper.save(savedOrder);

        assertThat(reserveOrderMapper.get(savedOrder.getOrder_id()).getTransactionId(), is("123456"));
    }

    @Test
    public void shouldNotOverWriteTransactionIdForPaidOrder() throws Exception {
        ReserveOrder order1 = new ReserveOrder()
                .setAccount_id(2L)
                .setItem_id(2L)
                .setItem_quantity(1L)
                .setReservation_timestamp(new Date())
                .setTransactionId("222222");
        order1.setStatus(OrderStatus.PAID);
        reserveOrderMapper.insert(order1);

        ReserveOrder order2 = new ReserveOrder()
                .setAccount_id(2L)
                .setItem_id(2L)
                .setItem_quantity(1L)
                .setReservation_timestamp(new Date());
        order2.setStatus(OrderStatus.PAID);
        reserveOrderMapper.insert(order2);

        ReserveOrder savedOrder1 = reserveOrderMapper.get(order1.getOrder_id());
        ReserveOrder savedOrder2 = reserveOrderMapper.get(order2.getOrder_id());

        assertThat(reserveOrderMapper.get(savedOrder1.getOrder_id()), not(is(reserveOrderMapper.get(savedOrder2.getOrder_id()))));

    }

}
