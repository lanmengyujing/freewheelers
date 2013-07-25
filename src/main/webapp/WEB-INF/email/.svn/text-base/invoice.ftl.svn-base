<!DOCTYPE html>
<html lang="en">
<head>

</head>
<body style="width: 650px; text-align: center;">

<div style="width: 100%; text-align: center;" align="center">
    <section><div style="width: 100%; text-align: center;" align="center">
        <table style="border-collapse: collapse; width: 650px; padding-left: 10px; border: 0px solid black;"><tbody>
        <tr>
            <td style="padding-left: 10px; text-align: left; width: 380px; border: 0px solid black;" align="left"><h1 style="text-align: left;" align="left"> Invoice </h1></td>
            <td style="padding-left: 10px; text-align: center; border: 0px solid black;" align="left"><img style="width: 150px;height: 150px;" src="http://qa-env.twu31proj1.university.thoughtworks.com:8080/images/freewheelers.png" alt="freewheeler_logo"></td>
        </tr>
        <tr>
            <td style="padding-left: 10px; text-align: left; width: 380px; border: 0px solid black;" align="left"><strong>To:</strong></td>
            <td style="padding-left: 10px; text-align: center; border: 0px solid black;" align="left">www.freewheelers.co.uk</td>
        </tr>
        <tr>
            <td style="padding-left: 10px; text-align: left; width: 380px; border: 0px solid black;" align="left">${invoice.account.account_name}</td>
            <td style="padding-left: 10px; text-align: left; border: 0px solid black;" align="left">Freewheelers Ltd.</td>
        </tr>
        <tr>
            <td style="padding-left: 10px; text-align: left; width: 380px; border: 0px solid black;" align="left">${invoice.account.address.streetOne}</td>
            <td style="padding-left: 10px; text-align: left; border: 0px solid black;" align="left">55 Canonbury Road</td>
        </tr>
        <tr>
            <td style="padding-left: 10px; text-align: left; width: 380px; border: 0px solid black;" align="left">${invoice.account.address.streetTwo}</td>
            <td style="padding-left: 10px; text-align: left; border: 0px solid black;" align="left">London</td>
        </tr>
        <#if invoice.account.address.city == "" || invoice.account.address.zip == "">
            <#assign delimiter="">
        <#else>
            <#assign delimiter=", ">
        </#if>
        <tr>
            <td style="padding-left: 10px; text-align: left; width: 380px; border: 0px solid black;" align="left">${invoice.account.address.city}${delimiter}${invoice.account.address.zip}</td>
            <td style="padding-left: 10px; text-align: left; border: 0px solid black;" align="left">N1 2DG</td>
        </tr>
        <tr>
            <td style="padding-left: 10px; text-align: left; width: 380px; border: 0px solid black;" align="left">${invoice.account.address.country}</td>
            <td style="padding-left: 10px; text-align: left; border: 0px solid black;" align="left">Company reg. no: 98765686</td>
        </tr>
        <tr>
            <td style="padding-left: 10px; text-align: left; width: 380px; border: 0px solid black;" align="left">${invoice.account.phoneNumber}</td>
            <td style="padding-left: 10px; text-align: left; border: 0px solid black;" align="left">EU VAT No.: GB98765686</td>
        </tr>
        </tbody></table>
    </div>
        <br><br><section><table border="1" cellspacing="0" style="border-collapse: collapse; width: 650px; border: 1px solid black;">
            <caption style="text-align: left; background-color: #39c7df; padding: 2px; border: 1px solid black;"><strong>Invoice details</strong></caption>
            <tr>
                <td style="text-align: right; width: 170px; border: 1px solid black;" align="right">Order/Invoice No.:</td>
                <td style="text-align: right; width: 155px; border: 1px solid black;" align="right">INV-${invoice.orderId}</td>
                <td style="text-align: right; width: 170px; border: 1px solid black;" align="right">Payment:</td>
                <td style="text-align: right; width: 155px; border: 1px solid black;" align="right">${invoice.paymentType}</td>
            </tr>
            <tr>
                <td style="text-align: right; width: 170px; border: 1px solid black;" align="right">Tax Date:</td>
                <td style="text-align: right; width: 155px; border: 1px solid black;" align="right">${invoice.taxDate?string("d MMMMM yyyy")}</td>
                <td style="text-align: right; width: 170px; border: 1px solid black;" align="right">Payment due by:</td>
                <td style="text-align: right; width: 155px; border: 1px solid black;" align="right">${invoice.dueBy?string("d MMMMM yyyy")}</td>
            </tr>
        </table></section></section>
</div>
<br><br><div style="width: 100%; text-align: center;" align="center">
    <table border="1" cellspacing="0" style="border-collapse: collapse; width: 650px; border: 1px solid black;">
        <thead style="background-color: #39c7df;"><tr>
            <th style="border: 1px solid black;">Item</th>
            <th style="border: 1px solid black;">Unit Price</th>
            <th style="border: 1px solid black;">Quantity</th>
            <th style="border: 1px solid black;">Net</th>
            <th style="border: 1px solid black;">VAT %</th>
            <th style="border: 1px solid black;">VAT</th>
            <th style="border: 1px solid black;">Gross</th>
        </tr></thead>
        <tbody>
        <#list invoice.items as item>
        <tr>
            <td style="text-align: left; border: 1px solid black;" align="left">${item.name}</td>
            <td style="text-align: right; border: 1px solid black;" align="right">&pound; ${item.unitPrice?string("0.00")}</td>
            <td style="text-align: right; border: 1px solid black;" align="right">${item.quantity}</td>
            <td style="text-align: right; border: 1px solid black;" align="right">&pound; ${item.net?string("0.00")}</td>
            <td style="text-align: right; border: 1px solid black;" align="right">${item.vatRate?string.percent}</td>
            <td style="text-align: right; border: 1px solid black;" align="right">&pound; ${item.vat?string("0.00")}</td>
            <td style="text-align: right; border: 1px solid black;" align="right">&pound; ${item.gross?string("0.00")}</td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>
<br><br><div style="width: 100%; text-align: center;" align="center">
    <table style="border-collapse: collapse; width: 250px; margin-left: 400px; border: 0px solid black;"><tbody>
    <tr>
        <td style="text-align: left; border: 0px solid black;" align="left"><strong>Net total:</strong></td>
        <td style="text-align: right; border: 0px solid black;" align="right">&pound; ${invoice.netTotal?string("0.00")}</td>
    </tr>
    <tr>
        <td style="text-align: left; border: 0px solid black;" align="left"><strong>VAT total:</strong></td>
    <#if invoice.vatTotal != 0>
        <td style="text-align: right; border: 0px solid black;" align="right">&pound; ${invoice.vatTotal?string("0.00")}</td>
    <#else >
        <td style="text-align: right; border: 0px solid black;" align="right">(exempt)</td>
    </#if>
    </tr>
    <tr style="border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #C0C0C0">
        <td style="text-align: left; border: 0px solid black;" align="left"><strong>Duty Tax:</strong></td>
        <td style="text-align: right; border: 0px solid black;" align="right">&pound; ${invoice.dutyTax?string("0.00")}</td>
    </tr>
    <tr style="border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #C0C0C0">
        <td style="text-align: left; border: 0px solid black;" align="left"><strong>Gross total:</strong></td>
        <td style="text-align: right; border: 0px solid black;" align="right">&pound; ${invoice.grossTotal?string("0.00")}</td>
    </tr>
    </tbody></table>
</div>
<br/>
<br/>


<footer style="margin-top: 50px; font-size: 14px;"><div style="width: 100%; text-align: center;" align="center">Registered office address: FreeWheelers Ltd. 55 Canonbury road, London N1 2DG</div>
    <div style="width: 100%; text-align: center;" align="center">www.freewheelers.co.uk tel: + 44 207 44477890</div>
</footer>
</body>
</html>
