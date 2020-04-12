package com.folaukaveinga.multidbs.expense;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "expense", produces = "Rest API for expense operations", tags = "Expense API")
@RestController
@RequestMapping("/expenses")
public class ExpenseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExpenseService expenseService;

	@ApiOperation(value = "Save Expense")
	@PostMapping
	public ResponseEntity<Expense> save(@RequestParam long memberId, @RequestBody Expense expense) {
		log.info("save({}), memberId={}", expense.toString(), memberId);
		expense.setMemberId(memberId);
		return new ResponseEntity<>(expenseService.save(expense), HttpStatus.OK);
	}
}
