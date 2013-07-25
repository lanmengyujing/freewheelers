package functional.com.trailblazers.freewheelers.apis;

import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Address;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ItemType;
import com.trailblazers.freewheelers.security.PasswordEncoding;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import com.trailblazers.freewheelers.service.impl.ReserveOrderServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;

import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.SOME_PHONE_NUMBER;
import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.emailFor;


public class AdminApi {

    private AccountService accountService;
    private ItemService itemService;
    private ReserveOrderService reserveOrderService;
    private final SqlSession sqlSession;


    private PasswordEncoding passwordEncoding;

    public AdminApi() {
        sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        this.accountService = new AccountServiceImpl(sqlSession);
        this.itemService = new ItemServiceImpl(sqlSession);
        this.reserveOrderService = new ReserveOrderServiceImpl(sqlSession);
        this.passwordEncoding = new PasswordEncoding(new BCryptPasswordEncoder());
    }

    public AdminApi there_is_no_account_for(String accountName) {
        Account account = accountService.getAccountIdByName(accountName);
        if (account != null) {
            accountService.delete(account);
        }

        return this;
    }

    public AdminApi there_is_a_user(String userName, String password) {
        there_is_no_account_for(userName);
        Address address = new Address();
        address.setCountry("UK");
        accountService.createAccount(account_for(userName, password, address));

        return this;
    }

    public AdminApi there_is_an_admin(String userName, String password) {
        there_is_no_account_for(userName);
        accountService.createAdmin(account_for(userName, password));

        return this;
    }

    public AdminApi there_is_no_item(String itemName) {
        Item toBeDeleted = itemService.getByName(itemName);

        while (toBeDeleted != null) {
            itemService.delete(toBeDeleted);
            toBeDeleted = itemService.getByName(itemName);
        }

        return this;
    }

    public AdminApi there_is_a_frame(String itemName, Long quantity) {
        there_is_no_item(itemName);
        itemService.saveItem(itemFor(itemName, quantity, new BigDecimal(1)));

        return this;
    }

    private Item itemFor(String itemName, Long quantity, BigDecimal itemPrice) {
        return new Item()
                .setName(itemName)
                .setQuantity(quantity)
                .setDescription("A very nice item.")
                .setPrice(itemPrice)
                .setType(ItemType.FRAME);
    }

    private Account account_for(String userName, String password, Address address) {
        return new Account()
                .setAccount_name(userName)
                .setPassword(passwordEncoding.encode(password))
                .setEmailAddress(emailFor(userName))
                .setPhoneNumber(SOME_PHONE_NUMBER)
                .setAddress(address)
                .setAcceptedTerms("on")
                .setEnabled(true);
    }

    private Account account_for(String userName, String password) {
        Address address = new Address();
        address.setCountry("UK");
        return account_for(userName, password, address);
    }

    public AdminApi there_is_a_user_with_an_address(String userName, String somePassword, Address address) {
        there_is_no_account_for(userName);

        accountService.createAccount(account_for(userName, somePassword, address));
        return this;
    }

    public AdminApi there_is_an_item_with_price(String itemName, long onlyOneLeft, BigDecimal itemPrice) {
        there_is_no_item(itemName);

        itemService.saveItem(itemFor(itemName, onlyOneLeft, itemPrice));
        return this;

    }

    public AdminApi there_is_no_order() {
        reserveOrderService.deleteAllOrders();
        return this;
    }

    public void closeConnections() {
        sqlSession.close();
    }
}
