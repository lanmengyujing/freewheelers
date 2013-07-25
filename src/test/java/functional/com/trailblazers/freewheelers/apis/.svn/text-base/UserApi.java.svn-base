package functional.com.trailblazers.freewheelers.apis;

import com.trailblazers.freewheelers.model.Address;
import functional.com.trailblazers.freewheelers.helpers.HomeTable;
import functional.com.trailblazers.freewheelers.helpers.ManageItemTable;
import functional.com.trailblazers.freewheelers.helpers.OrderTable;
import functional.com.trailblazers.freewheelers.helpers.URLs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Set;

import static functional.com.trailblazers.freewheelers.helpers.Controls.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserApi {

    public static final int WAITING_FOR_PAGE_LOAD_TIME_OUT_IN_SECONDS = 10;
    private WebDriver driver;

    public UserApi(WebDriver driver) {
        this.driver = driver;
    }

    public UserApi is_logged_out() {
        driver.get(URLs.logout());
        driver.findElement(By.linkText("Logout")).click();

        return this;
    }


    public UserApi logs_in_with(String userName, String userPassword) {
        driver.get(URLs.login());

        new WebDriverWait(driver, WAITING_FOR_PAGE_LOAD_TIME_OUT_IN_SECONDS).until(ExpectedConditions.presenceOfElementLocated(By.name("j_username")));
        driver.findElement(By.name("j_username")).sendKeys(userName);

        driver.findElement(By.name("j_password")).sendKeys(userPassword);

        driver.findElement(By.name("submit")).click();

        return this;
    }


    public UserApi creates_an_account(String name, String email, String password, String phoneNumber, Address address, String termsAccepted) {
        visit_signup_page();

        visit_terms_and_conditions();

        fill_create_account_inputs(name, email, password, phoneNumber, address, termsAccepted);

        driver.findElement(By.id("createAccount")).click();

        return this;
    }

    public UserApi creates_an_item(String name, String type, String quantity, String price, String description) {

        (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return driver.findElement(By.id("quantity")).isDisplayed();
            }
        });

        fillField(driver.findElement(By.id("name")), name);
        fillField(driver.findElement(By.id("price")), price);

        Select select = new Select(driver.findElement(By.id("type")));
        select.selectByVisibleText(type);

        fillField(driver.findElement(By.id("description")), description);
        fillField(driver.findElement(By.id("quantity")), quantity);

        driver.findElement(By.id("createItem")).click();

        return this;
    }

    public UserApi visits_his_profile() {
        (new WebDriverWait(driver, WAITING_FOR_PAGE_LOAD_TIME_OUT_IN_SECONDS)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return driver.findElement(By.linkText("User Profile")) != null;
            }
        });
        driver.findElement(By.linkText("User Profile")).click();
        return this;
    }

    public UserApi visits_order_summary() {
        driver.get(URLs.orderSummary());
        return this;
    }

    public UserApi chooses_to_pay_by_credit_card() {
        (new WebDriverWait(driver, WAITING_FOR_PAGE_LOAD_TIME_OUT_IN_SECONDS)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return driver.findElement(By.id("toPaymentByCreditCard")) != null;
            }
        });
        driver.findElement(By.id("toPaymentByCreditCard")).click();
        return this;
    }

    public UserApi input_credit_card_payment_detail(String creditCardNumber, String securityCode, String expiryDate, String holderName) {
        (new WebDriverWait(driver, WAITING_FOR_PAGE_LOAD_TIME_OUT_IN_SECONDS)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return driver.findElement(By.id("fld_creditCardNumber")) == null ? false : true;
            }
        });

        fillField(driver.findElement(By.id("fld_creditCardNumber")), creditCardNumber);

        fillField(driver.findElement(By.id("fld_securityCode")), securityCode);

        fillField(driver.findElement(By.id("fld_expiryDate")), expiryDate);

        fillField(driver.findElement(By.id("fld_holderName")), holderName);

        driver.findElement(By.id("payByCreditCard")).click();
        return this;
    }

    public UserApi visits_admin_profile() {
        (new WebDriverWait(driver, WAITING_FOR_PAGE_LOAD_TIME_OUT_IN_SECONDS)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return driver.findElement(By.linkText("Manage Orders")) == null ? false : true;
            }
        });
        driver.findElement(By.linkText("Manage Orders")).click();
        return this;
    }

    public UserApi go_to_admin_url() {
        driver.get(URLs.admin());
        return this;
    }

    public UserApi visits_profile_for(String name) {
        driver.get(URLs.userProfile() + "/" + encoded(name));
        return this;
    }

    public UserApi visits_home_page() {
        driver.get(URLs.home());
        return this;
    }

    public UserApi wants_to_manage_items() {
        driver.get(URLs.admin());
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return driver.findElement(By.id("manageItems")) == null ? false : true;
            }
        });
        driver.findElement(By.id("manageItems")).click();
        return this;
    }

    public UserApi changes_item_name(String from, String to) {
        check(driver.findElement(ManageItemTable.toggleAll()));

        WebElement input = driver.findElement(ManageItemTable.nameFieldFor(from));
        fillField(input, to);

        driver.findElement(By.name("update")).click();

        return this;
    }

    public UserApi delete_item(String itemName) {
        check(driver.findElement(ManageItemTable.checkBoxFor(itemName)));
        driver.findElement(By.name("delete")).click();

        return this;
    }

    public UserApi reserves_item(String name) {
        driver.findElement(HomeTable.reserveButtonFor(name)).click();
        return this;
    }

    public UserApi changes_order_status(String item, String toState) {
        select(toState, driver.findElement(OrderTable.selectFor(item)));
        driver.findElement(OrderTable.saveButtonFor(item)).click();

        return this;
    }

    private String encoded(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }


    public void edit_details(Address newAddress, String newPhoneNumber, String newEmailAddress) {
        visits_his_profile();

        (new WebDriverWait(driver, WAITING_FOR_PAGE_LOAD_TIME_OUT_IN_SECONDS)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return driver.findElement(By.name("editDetails")) == null ? false : true;
            }
        });
        driver.findElement(By.name("editDetails")).click();

        fillAddress(newAddress);

        fillField(driver.findElement(By.id("fld_phoneNumber")), newPhoneNumber);

        fillField(driver.findElement(By.id("fld_emailAddress")), newEmailAddress);

        (new WebDriverWait(driver, WAITING_FOR_PAGE_LOAD_TIME_OUT_IN_SECONDS)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return driver.findElement(By.id("editUserDetails")) == null ? false : true;
            }
        });

        driver.findElement(By.id("editUserDetails")).click();
    }

    private void fillAddress(Address address) {
        (new WebDriverWait(driver, WAITING_FOR_PAGE_LOAD_TIME_OUT_IN_SECONDS)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return driver.findElement(By.id("fld_zip")) == null ? false : true;
            }
        });

        fillField(driver.findElement(By.id("fld_streetOne")), address.getStreetOne());

        fillField(driver.findElement(By.id("fld_streetTwo")), address.getStreetTwo());

        fillField(driver.findElement(By.id("fld_city")), address.getCity());

        fillField(driver.findElement(By.id("fld_state")), address.getState());

        fillField(driver.findElement(By.id("fld_zip")), address.getZip());
    }

    public UserApi should_display_shopping_cart_link() {
        (new WebDriverWait(driver, WAITING_FOR_PAGE_LOAD_TIME_OUT_IN_SECONDS)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return driver.findElement(By.partialLinkText("Cart")) == null ? false : true;
            }
        });
        assertNotNull(driver.findElement(By.partialLinkText("Cart")));
        return this;
    }

    public UserApi visits_shopping_cart() {
        (new WebDriverWait(driver, WAITING_FOR_PAGE_LOAD_TIME_OUT_IN_SECONDS)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return driver.findElement(By.partialLinkText("Cart")) == null ? false : true;
            }
        });
        driver.findElement(By.partialLinkText("Cart")).click();
        return this;
    }

    public UserApi display_cart_with_item(int ITEM_COUNT) {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return driver.findElements(By.className("cart")).size() == 0 ? false : true;
            }
        });
        int itemCount = driver.findElements(By.className("cart")).size();
        assertEquals(ITEM_COUNT, itemCount);
        return this;
    }

    public UserApi visit_terms_and_conditions() {
        driver.findElement(By.id("terms_and_conditions_link")).click();
        close_terms_and_conditions_window();

        return this;
    }

    private void close_terms_and_conditions_window() {
        String base = driver.getWindowHandle();
        Set<String> set = driver.getWindowHandles();
        driver.switchTo().window((String) set.toArray()[1]);
        driver.close();
        driver.switchTo().window(base);
    }

    private void fill_create_account_inputs(String name, String email, String password, String phoneNumber, Address address, String termsAccepted) {
        fillField(driver.findElement(By.id("fld_email")), email);

        fillField(driver.findElement(By.id("fld_password")), password);

        fillField(driver.findElement(By.id("fld_name")), name);

        fillField(driver.findElement(By.id("fld_phoneNumber")), phoneNumber);

        fillField(driver.findElement(By.id("fld_streetOne")), address.getStreetOne());

        fillField(driver.findElement(By.id("fld_streetTwo")), address.getStreetTwo());

        fillField(driver.findElement(By.id("fld_city")), address.getCity());

        fillField(driver.findElement(By.id("fld_state")), address.getState());

        fillField(driver.findElement(By.id("fld_zip")), address.getZip());

        select(address.getCountry(), driver.findElement(By.id("fld_country")));

        if (termsAccepted != null) {
            check(driver.findElement(By.id("fld_termsConditions")));
        } else {
            uncheck(driver.findElement(By.id("fld_termsConditions")));
        }
    }

    private void visit_signup_page() {
        driver.get(URLs.signUp());
        (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return driver.findElement(By.id("fld_zip")).isDisplayed();
            }
        });
    }

    public UserApi display_cart_with_quantity(String item, long quantity) {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return driver.findElement(By.className("itemQuantity")).isDisplayed();
            }
        });
        String itemQuantity = driver.findElement(By.className("itemQuantity")).getText();
        assertEquals(itemQuantity, String.valueOf(quantity));
        return this;
    }

    public UserApi display_cart_total_amount(BigDecimal totalPriceOfCart) {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return driver.findElement(By.className("totalPriceOfCart")).isDisplayed();
            }
        });
        String price = driver.findElement(By.className("totalPriceOfCart")).getText();
        assertThat(price, is("Net Total: " + totalPriceOfCart.toString()));
        return this;
    }

    public UserApi fill_create_item_form_with_image_info(String name, String type, String quantity, String price, String description, String imagePath) {

        (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return driver.findElement(By.id("quantity")).isDisplayed();
            }
        });

        fillField(driver.findElement(By.id("name")), name);
        fillField(driver.findElement(By.id("price")), price);

        Select select = new Select(driver.findElement(By.id("type")));
        select.selectByVisibleText(type);

        fillField(driver.findElement(By.id("description")), description);
        fillField(driver.findElement(By.id("quantity")), quantity);

        driver.findElement(By.id("fileupload")).sendKeys(imagePath);

        return this;
    }

    public UserApi user_submit() {
        driver.findElement(By.id("createItem")).click();
        return this;
    }

    public UserApi chooses_to_delete_item_from_shopping_cart(String itemNameToRemove) {
        driver.findElement(By.cssSelector("input[data-delete-name='" + itemNameToRemove + "']")).click();
        return this;
    }
    public UserApi clicks_more_details() {
        driver.findElement(By.className("view_details")).click();
        return this;
    }

    public UserApi clicks_cancel_payment() {
        driver.findElement(By.className("cancel")).click();
        return this;
    }
}

