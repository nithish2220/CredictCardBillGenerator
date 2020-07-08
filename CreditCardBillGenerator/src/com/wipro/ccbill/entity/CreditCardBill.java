
package com.wipro.ccbill.entity;
import java.util.Date;
import com.wipro.ccbill.exception.InputValidationException;
public class CreditCardBill {
private String creditCardNo;
private String customerId;
@SuppressWarnings("unused")
private Date billDate;
private Transaction monthTransactions[];
public CreditCardBill ()
{

}
public CreditCardBill(String creditCardNo, String customerId, Date billDate, Transaction[] monthTransactions) {
super();
this.creditCardNo = creditCardNo;
this.customerId = customerId;
this.billDate = billDate;
this.monthTransactions = monthTransactions;
}
public double getTotalAmount(String transactionType)
{
int i,l;
double amount=0;
i=monthTransactions.length;
for(l=0;l<i;l++)
{
if((monthTransactions[l].getTransactionType()).equalsIgnoreCase(transactionType))
{
amount+=monthTransactions[l].getTransactionAmount();
}
}
return amount;
}
public double calculateBillAmount() throws InputValidationException
{
String a=validateData();
if(a.equalsIgnoreCase("invalid"))
return 0.0;
else
{
if(monthTransactions==null||monthTransactions.length==0)
return 0.0;
else
{
double CreditedAmount=0,DebitedAmount=0,os,FinalAmount;
DebitedAmount=getTotalAmount("DB");
CreditedAmount=getTotalAmount("CR");
os=DebitedAmount-CreditedAmount;
FinalAmount=os+(os*19.9/1200);
return FinalAmount;
}
}

}
public String validateData() throws InputValidationException
{
try
{
if(creditCardNo==null||creditCardNo.length()!=16)
throw new InputValidationException("Credit card no must be length of 16");
if(customerId.length()<6)
throw new InputValidationException("Customer Id must be atleast length of 6");
}catch (InputValidationException e)
{
return "invalid";
}
return "valid";
}
}

