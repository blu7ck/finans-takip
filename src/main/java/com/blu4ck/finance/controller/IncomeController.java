package com.blu4ck.finance.controller;

import com.blu4ck.finance.model.Income;
import com.blu4ck.finance.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/income")
@RequiredArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Income>> getIncomes(@PathVariable Long userId) {
        return ResponseEntity.ok(incomeService.getIncomesByUser(userId));
    }
    @GetMapping
    public ResponseEntity<List<Income>> getAllIncome() {
        List<Income> incomeList = incomeService.getAllIncome();
        return ResponseEntity.ok(incomeList);
    }
    @PostMapping
    public ResponseEntity<Income> addIncome(@RequestBody Income income) {
        Income savedIncome = incomeService.addIncome(income);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIncome);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Income> updateIncome(@PathVariable Long id, @RequestBody Income updatedIncome) {
        Income income = incomeService.updateIncome(id, updatedIncome);
        return ResponseEntity.ok(income);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }

}
