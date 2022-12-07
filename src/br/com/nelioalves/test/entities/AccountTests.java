package br.com.nelioalves.test.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.nelioalves.entities.Account;
import br.com.nelioalves.tests.factory.AccountFactory;

public class AccountTests {

	@Test
	public void depositShouldIncreaseBalanceAndDiscountFeeWhenPositiveAmount() {

		double amount = 200.0;
		double expectedValue = 196.0;
		Account acc = AccountFactory.createEmptyAccount();

		acc.deposit(amount);

		Assertions.assertEquals(expectedValue, acc.getBalance());
	}

	@Test
	public void depositShouldDoNothingWhenNegativeAmount() {

		double expectedValue = 100.0;
		Account accNegative = AccountFactory.createAccount(expectedValue);
		double amountNegative = -200.0;

		accNegative.deposit(amountNegative);

		Assertions.assertEquals(expectedValue, accNegative.getBalance());

	}

	@Test
	public void fullWithDrawShouldClearBalanceAndReturnFullBalance() {

		double expectedValue = 0.0;
		double initialBalance = 800.0;
		Account accFull = AccountFactory.createAccount(initialBalance);

		double result = accFull.fullWithDraw();
		Assertions.assertTrue(expectedValue == accFull.getBalance());
		Assertions.assertTrue(result == initialBalance);

	}

	@Test
	public void withdrawShouldDecreaseBalanceWhenSufficentBalance() {

		Account acc = AccountFactory.createAccount(800.0);

		acc.withdraw(500.0);
		Assertions.assertEquals(300.0, acc.getBalance());
	}

	@Test
	public void withdrawShouldThrowExceptionWhenSufficentBalance() {

		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			Account acc = AccountFactory.createAccount(800.0);

			acc.withdraw(900.0);
		});

	}

}
