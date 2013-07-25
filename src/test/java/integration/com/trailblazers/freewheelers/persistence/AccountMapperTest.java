package integration.com.trailblazers.freewheelers.persistence;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Address;
import org.junit.Before;
import org.junit.Test;

import static java.util.UUID.randomUUID;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class AccountMapperTest extends MapperTestBase {
    public Address address;

    private AccountMapper accountMapper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        accountMapper = getSqlSession().getMapper(AccountMapper.class);
        address = new Address("Street One", "Street Two", "City", "State", "Country", "Zip");
    }

    @Test
    public void shouldInsertAndGetAccount() throws Exception {
        Account account = someAccount().setAccount_name("Johnny Cash");

        accountMapper.insert(account);
        Account fetchedFromDB = accountMapper.getById(account.getAccount_id());

        assertThat(fetchedFromDB.getAccount_name(), is("Johnny Cash"));
    }

    @Test
    public void shouldInsertAndGetByIdAccountWithAddress() throws Exception {
        Account account = someAccount();
        Address address = new Address("One", "Second", "New Jersey", "Sao Paulo", "UK", "000-0000");
        account.setAddress(address);

        accountMapper.insert(account);
        Account fetchedFromDB = accountMapper.getById(account.getAccount_id());
        assertEquals(account.getAddress(), fetchedFromDB.getAddress());
    }

    @Test
    public void shouldUpdateAccountWithNewAddress() throws Exception {
        Account account = someAccount();
        accountMapper.insert(account);

        Address address = new Address("new st1", "new st2", "new city", "new state", "new counry", "new zip");
        account.setAddress(address);

        accountMapper.update(account);
        Account fetchedFromDB = accountMapper.getById(account.getAccount_id());
        assertEquals(account.getAddress(), fetchedFromDB.getAddress());
    }

    @Test
    public void shouldGetAccountByID() throws Exception {
        Account account = someAccount();
        accountMapper.insert(account);
        Account fetchedFromDB = accountMapper.getById(account.getAccount_id());
        assertEquals(account.getAccount_id(), fetchedFromDB.getAccount_id());
    }

    @Test
    public void shouldInsertAndGetByNameAccountWithAddress() throws Exception {
        Account account = someAccount();
        Address address = new Address("One", "Second", "New Jersey", "Sao Paulo", "UK", "000-0000");
        account.setAddress(address);

        accountMapper.insert(account);
        Account fetchedFromDB = accountMapper.getByName(account.getAccount_name());
        assertEquals(account.getAddress(), fetchedFromDB.getAddress());
    }

//    //TODO : WRONG TEST NAME - DOSE NOT CHECK DB.
//    @Test
//    public void shouldUpdateDBWithNewAddress() throws Exception {
//        Account account = someAccount();
//        accountMapper.insert(account);
//        Address newAddress = new Address("newst1","newst2","city","state","UK","999");
//        account.setAddress(newAddress);
//        accountMapper.update(account);
//
//        assertEquals(newAddress.getCity(), account.getAddress().getCity());
//    }

    @Test
    public void shouldGetAccountByName() throws Exception {
        accountMapper.insert(someAccount().setAccount_name("Michael Stipe"));

        Account fetchedFromDB = accountMapper.getByName("Michael Stipe");

        assertThat(fetchedFromDB.getAccount_name(), is("Michael Stipe"));
    }

    @Test
    public void shouldUpdateAnExistingAccount() throws Exception {
        Account someAccount = someAccount().setAccount_name("Prince");
        accountMapper.insert(someAccount);

        someAccount.setAccount_name("TAFKAP");
        accountMapper.update(someAccount);

        Account fetched = accountMapper.getById(someAccount.getAccount_id());
        assertThat(fetched.getAccount_name(), is("TAFKAP"));
    }

    @Test
    public void shouldFindAllAccounts() throws Exception {
        int before = accountMapper.findAll().size();

        accountMapper.insert(someAccount());
        accountMapper.insert(someAccount());
        accountMapper.insert(someAccount());

        assertThat(accountMapper.findAll().size(), is(before + 3));
    }


    @Test
    public void shouldReturnNullIfAnAccountDoesNotExist() throws Exception {
        assertThat(accountMapper.getByName("Does Not Exist"), is(nullValue()));
    }

    @Test
    public void shouldDeleteAccount() throws Exception {
        Account account = someAccount();
        accountMapper.insert(account);

        accountMapper.delete(account);
        Account fetched = accountMapper.getById(account.getAccount_id());

        assertThat(fetched, is(nullValue()));
    }

    private Account someAccount() {
        return new Account()
                .setAccount_name("Some Body")
                .setEmailAddress(randomUUID() + "some.body@gmail.com")
                .setPassword("V3ry S3cret")
                .setPhoneNumber("12345")
                .setAddress(address)
                .setEnabled(true);
    }

}

