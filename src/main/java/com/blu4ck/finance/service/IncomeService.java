package com.blu4ck.finance.service;

import com.blu4ck.finance.model.Income;
import com.blu4ck.finance.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeService {

    private final IncomeRepository incomeRepository;

    public Income addIncome(Income income) {
        return incomeRepository.save(income);
    }

    public List<Income> getIncomesByUser(Long userId) {
        return incomeRepository.findByUserId(userId);
    }
    public List<Income> getAllIncome() {
        return incomeRepository.findAll();
    }
    public Income updateIncome(Long id, Income updatedIncome) {
        return incomeRepository.findById(id)
                .map(existingIncome -> {
                    existingIncome.setSource(updatedIncome.getSource());
                    existingIncome.setAmount(updatedIncome.getAmount());
                    existingIncome.setDate(updatedIncome.getDate());
                    return incomeRepository.save(existingIncome);
                }).orElseThrow(() -> new RuntimeException("Gelir bulunamadı!"));
    }
    public void deleteIncome(Long id) {
        if (!incomeRepository.existsById(id)) {
            throw new RuntimeException("Gelir bulunamadı!");
        }
        incomeRepository.deleteById(id);
    }


}
