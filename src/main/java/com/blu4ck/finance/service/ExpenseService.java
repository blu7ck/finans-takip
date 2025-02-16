package com.blu4ck.finance.service;

import com.blu4ck.finance.model.Expense;
import com.blu4ck.finance.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getExpensesByUser(Long userId) {
        return expenseRepository.findByUserId(userId);
    }
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
    public Expense updateExpense(Long id, Expense updatedExpense) {
        return expenseRepository.findById(id)
                .map(existingExpense -> {
                    existingExpense.setCategory(updatedExpense.getCategory());
                    existingExpense.setAmount(updatedExpense.getAmount());
                    existingExpense.setDate(updatedExpense.getDate());
                    return expenseRepository.save(existingExpense);
                }).orElseThrow(() -> new RuntimeException("Gider bulunamadı!"));
    }
    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new RuntimeException("Gider bulunamadı!");
        }
        expenseRepository.deleteById(id);
    }

}
