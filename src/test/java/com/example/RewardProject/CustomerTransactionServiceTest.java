package com.example.RewardProject;


import com.example.RewardProject.Beans.CustomerPoints;
import com.example.RewardProject.Beans.CustomerTransaction;
import com.example.RewardProject.Repository.CustomerPointsRepository;
import com.example.RewardProject.Repository.CustomerTransactionRepository;
import com.example.RewardProject.Service.CustomerTransactionService;
import com.example.RewardProject.Utils.MonthUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerTransactionServiceTest {

    @InjectMocks
    private CustomerTransactionService customerTransactionService;

    @Mock
    private CustomerTransactionRepository transactionRepository;

    @Mock
    private CustomerPointsRepository customerPointsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateAndSaveRewardPoints() {
        Long customerId = 1L;
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 31);

        CustomerTransaction transaction1 = new CustomerTransaction();
        transaction1.setAmount(120.0);

        CustomerTransaction transaction2 = new CustomerTransaction();
        transaction2.setAmount(80.0);

        List<CustomerTransaction> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate)).thenReturn(transactions);

        CustomerPoints existingPoints = new CustomerPoints();
        existingPoints.setCustomerID(customerId);
        existingPoints.setRewardMonth(1);
        existingPoints.setRewardYear(2024);
        existingPoints.setRewardPoints(0);

        when(customerPointsRepository.findByCustomerIdAndMonthAndYear(customerId, "1", 2024)).thenReturn(existingPoints);

        customerTransactionService.calculateAndSaveRewardPoints(customerId, startDate, endDate);

        verify(customerPointsRepository).save(existingPoints);
        assertEquals(120, existingPoints.getRewardPoints());
    }

    @Test
    void testGetPointsByCustomerId() throws Exception {
        Long customerId = 1L;

        CustomerPoints points1 = new CustomerPoints();
        points1.setRewardMonth(1);
        points1.setRewardPoints(50);

        CustomerPoints points2 = new CustomerPoints();
        points2.setRewardMonth(2);
        points2.setRewardPoints(30);

        List<CustomerPoints> pointsList = Arrays.asList(points1, points2);

        when(customerPointsRepository.findByCustomerID(customerId)).thenReturn(pointsList);

        Map<String, String> expectedResponse = new LinkedHashMap<>();
        expectedResponse.put(MonthUtil.getMonthName(1), "50");
        expectedResponse.put(MonthUtil.getMonthName(2), "30");
        expectedResponse.put("TOTAL REWARD POINTS OF THREE MONTHS", "80");

        Map<String, String> actualResponse = customerTransactionService.getPointsByCustomerId(customerId);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testGetAllCustomerPoints() {
        // Arrange
        CustomerPoints points1 = new CustomerPoints();
        points1.setId(1L);
        points1.setRewardMonth(1);
        points1.setRewardPoints(40);

        CustomerPoints points2 = new CustomerPoints();
        points2.setId(1L);
        points2.setRewardMonth(2);
        points2.setRewardPoints(60);

        List<CustomerPoints> pointsList = Arrays.asList(points1, points2);

        // Mocking repository methods
        when(customerPointsRepository.findAll()).thenReturn(pointsList);
        when(customerPointsRepository.findByCustomerID(1L)).thenReturn(pointsList);

        // Define the expected result
        Map<String, String> expectedResponse = new LinkedHashMap<>();
        expectedResponse.put(MonthUtil.getMonthName(1), "40");
        expectedResponse.put(MonthUtil.getMonthName(2), "60");
        expectedResponse.put("TOTAL REWARD POINTS OF THREE MONTHS", "100");

        Map<String, Map<String, String>> expectedAllCustomerPoints = new LinkedHashMap<>();
        expectedAllCustomerPoints.put("1", expectedResponse);

        // Act
        Map<String, Map<String, String>> actualAllCustomerPoints = customerTransactionService.getAllCustomerPoints();

        // Assert
        assertEquals(expectedAllCustomerPoints, actualAllCustomerPoints);
    }
}