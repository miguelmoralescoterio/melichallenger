/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/RestController.java to edit this template
 */
package com.melichallenger.loans.controllers;

import com.melichallenger.loans.entities.Loans;
import com.melichallenger.loans.entities.Payments;
import com.melichallenger.loans.entities.RequestLoan;
import com.melichallenger.loans.entities.RequestPayment;
import com.melichallenger.loans.entities.ResponseLoan;
import com.melichallenger.loans.entities.ResponsePayment;
import com.melichallenger.loans.entities.Targets;
import com.melichallenger.loans.repository.LoansRepository;
import com.melichallenger.loans.repository.PaymentsRepository;
import com.melichallenger.loans.repository.TargetsRepository;
import com.melichallenger.resources.ResourceNotFoundException;
import com.melichallenger.users.entities.Users;
import com.melichallenger.users.respository.UsersRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author biosx1706
 */
@RestController
@RequestMapping("/loans")
public class LoansRestController {
    @Autowired
    TargetsRepository targetsRepository;
    
    @Autowired
    LoansRepository loansRepository;
    
    @Autowired
    UsersRepository usersRepository;
    
    @Autowired
    PaymentsRepository paymentsRepository;
    
    @GetMapping("/all")
    @Operation(summary = "Obtener prestamos", description = "Obtiene la lista de todos los prestamos registrados")
    public List<Loans> list( @RequestParam(required = false, defaultValue = "10") int pageCount, @RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false) String dateFrom, @RequestParam(required = false) String dateTo) {
        if(page <= 0) {
            page = 0;
        } else {
            page -= 1;
        }
        if(pageCount > 0 && page >= 0 && dateFrom != null && !dateFrom.isEmpty() && dateTo != null && !dateTo.isEmpty()) {
            List<Loans> loans = loansRepository.findByPaginate(pageCount, pageCount*page, dateFrom, dateTo);
            return loans;
        } else if(pageCount > 0 && page >= 0 && dateFrom != null && !dateFrom.isEmpty() && ( dateTo == null || dateTo.isEmpty())) {
            List<Loans> loans = loansRepository.findByPaginate(pageCount, pageCount*page, dateFrom);
            return loans;
        } else if(pageCount > 0 && page >= 0 && ( dateFrom == null || dateFrom.isEmpty()) && ( dateTo == null || dateTo.isEmpty())) {
            List<Loans> loans = loansRepository.findByPaginate(pageCount, pageCount*page);
            return loans;
        } else {
            List<Loans> loans = loansRepository.findAll();
            return loans;
        }
    }
    
    @GetMapping("/actives")
    @Operation(summary = "Obtener prestamos activos", description = "Obtiene la lista de todos los prestamos activos registrados")
    public List<Loans> actives( @RequestParam(required = false, defaultValue = "10") int pageCount, @RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false) String dateFrom, @RequestParam(required = false) String dateTo) {
        if(page <= 0) {
            page = 0;
        } else {
            page -= 1;
        }
        if(pageCount > 0 && page >= 0 && dateFrom != null && !dateFrom.isEmpty() && dateTo != null && !dateTo.isEmpty()) {
            List<Loans> loans = loansRepository.findByStatus("active", pageCount, pageCount*page, dateFrom, dateTo);
            return loans;
        } else if(pageCount > 0 && page >= 0 && dateFrom != null && !dateFrom.isEmpty() && ( dateTo == null || dateTo.isEmpty())) {
            List<Loans> loans = loansRepository.findByStatus("active", pageCount, pageCount*page, dateFrom);
            return loans;
        } else if(pageCount > 0 && page >= 0 && ( dateFrom == null || dateFrom.isEmpty()) && ( dateTo == null || dateTo.isEmpty())) {
            List<Loans> loans = loansRepository.findByStatus("active", pageCount, pageCount*page);
            return loans;
        } else {
            List<Loans> loans = loansRepository.findByStatus("active");
            return loans;
        }
    }
    
    @GetMapping("/inactives")
    @Operation(summary = "Obtener prestamos terminados", description = "Obtiene la lista de todos los prestamos terminados registrados")
    public List<Loans> inactives( @RequestParam(required = false, defaultValue = "10") int pageCount, @RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false) String dateFrom, @RequestParam(required = false) String dateTo) {
        if(page <= 0) {
            page = 0;
        } else {
            page -= 1;
        }
        if(pageCount > 0 && page >= 0 && dateFrom != null && !dateFrom.isEmpty() && dateTo != null && !dateTo.isEmpty()) {
            List<Loans> loans = loansRepository.findByStatus("inactive", pageCount, pageCount*page, dateFrom, dateTo);
            return loans;
        } else if(pageCount > 0 && page >= 0 && dateFrom != null && !dateFrom.isEmpty() && ( dateTo == null || dateTo.isEmpty())) {
            List<Loans> loans = loansRepository.findByStatus("inactive", pageCount, pageCount*page, dateFrom);
            return loans;
        } else if(pageCount > 0 && page >= 0 && ( dateFrom == null || dateFrom.isEmpty()) && ( dateTo == null || dateTo.isEmpty())) {
            List<Loans> loans = loansRepository.findByStatus("inactive", pageCount, pageCount*page);
            return loans;
        } else {
            List<Loans> loans = loansRepository.findByStatus("inactive");
            return loans;
        }
    }
    
    @GetMapping("/deleteds")
    @Operation(summary = "Obtener prestamos borrados", description = "Obtiene la lista de todos los prestamos cancelados o borrdos registrados")
    public List<Loans> deleteds( @RequestParam(required = false, defaultValue = "10") int pageCount, @RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false) String dateFrom, @RequestParam(required = false) String dateTo) {
        if(page <= 0) {
            page = 0;
        } else {
            page -= 1;
        }
        if(pageCount > 0 && page >= 0 && dateFrom != null && !dateFrom.isEmpty() && dateTo != null && !dateTo.isEmpty()) {
            List<Loans> loans = loansRepository.findByStatus("deleted", pageCount, pageCount*page, dateFrom, dateTo);
            return loans;
        } else if(pageCount > 0 && page >= 0 && dateFrom != null && !dateFrom.isEmpty() && ( dateTo == null || dateTo.isEmpty())) {
            List<Loans> loans = loansRepository.findByStatus("deleted", pageCount, pageCount*page, dateFrom);
            return loans;
        } else if(pageCount > 0 && page >= 0 && ( dateFrom == null || dateFrom.isEmpty()) && ( dateTo == null || dateTo.isEmpty())) {
            List<Loans> loans = loansRepository.findByStatus("deleted", pageCount, pageCount*page);
            return loans;
        } else {
            List<Loans> loans = loansRepository.findByStatus("deleted");
            return loans;
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener datos de prestamo", description = "Obtiene los detalles de un prestamo por id")
    public Loans  get(@Parameter(description="ID del usuario a buscar") @PathVariable long id) throws ResourceNotFoundException {
        Loans loan = loansRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Presmo no encontrado, con id :: " + id));
        return loan;
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Prestamos", description = "Actualizar datos de un prestamo por id")
    public ResponseEntity<Loans> put(@PathVariable long id, @RequestBody Loans input) throws ResourceNotFoundException {
        Loans loan = loansRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prestamo no encontrado, con id :: " + id));
        loan.setAmount_total(input.getAmount_total());
        loan.setInstallment(input.getInstallment());
        loan.setTaget_id(input.getTaget_id());
        loan.setTerm(input.getTerm());
        loan.setUser_id(input.getUser_id());
        loan.setObservation(((input.getObservation() == null || input.getObservation().length()==0 || input.getObservation().isEmpty()) ? loan.getObservation() : input.getObservation()));
        loan.setStatus(((input.getStatus() == null || input.getStatus().length()==0 || input.getStatus().isEmpty()) ? loan.getStatus(): input.getStatus()));
        
        final Loans updatedLoan = loansRepository.save(loan);
        return ResponseEntity.ok(updatedLoan);
    }
    
    @PostMapping("/request")
    @Operation(summary = "Agregar un prestamo", description = "Crear un prestamo nuevo")
    public ResponseEntity<?> post(@RequestBody RequestLoan input) throws ResourceNotFoundException {
        //Loans save = loansRepository.save(input);
        Users user = usersRepository.findById(input.getUser_id())
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado, con id :: " + input.getUser_id()));
        List<Loans> loansUser = loansRepository.findActiveLoansuser(user.getId());
        int cantLoans = loansUser.size();
        Targets targetApply = targetsRepository.findTarget(cantLoans, input.getAmount());
        if(null == targetApply) {
            throw new ResourceNotFoundException(String.format("Target no encontrado, para el monto:  %d, Prestamos solicitados del año: %d, para el usuario: %d", input.getAmount(), cantLoans, user.getId()));
        }
        //  Calcular el interes, y la cuota
        float r = targetApply.getRate() / input.getTerm();
        double installment = (r + (r / (Math.pow((1 + r), (input.getTerm()))-1))) * input.getAmount();
        Loans savedLoan = new Loans();
        savedLoan.setAmount_total(input.getAmount());
        savedLoan.setInstallment(installment);
        savedLoan.setTaget_id(targetApply.getId());
        savedLoan.setTerm(input.getTerm());
        savedLoan.setUser_id(user.getId());
        savedLoan.setAmount_total(installment*input.getTerm());
        savedLoan.setInterest(savedLoan.getAmount_total()-input.getAmount());
        savedLoan.setRate(targetApply.getRate());
        savedLoan.setAmount_request(input.getAmount());
        savedLoan.setStatus("active");
        final Loans saveddLoan = loansRepository.save(savedLoan);
        ResponseLoan responseLoan = new ResponseLoan();
        responseLoan.setLoan_id(saveddLoan.getId());
        responseLoan.setInstallment(saveddLoan.getInstallment());
        return ResponseEntity.ok(responseLoan);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar prestasmo", description = "Actualizar datos de un target por id marcandolo para eliminar ")
    public ResponseEntity<Loans> delete(@PathVariable long id) throws ResourceNotFoundException, Exception {
        Loans loan = loansRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prestamo no encontrado, con id :: " + id));
        if("deleted".equals(loan.getStatus())) {
            throw new Exception("El prestamo ya ha sido marcado para eliminar, con id :: " + id);
        }
        loan.setStatus("deleted");
        
        final Loans updatedLoan = loansRepository.save(loan);
        return ResponseEntity.ok(updatedLoan);
    }
    
    @PostMapping("/paid/{id}")
    @Operation(summary = "Pagar un prestamo", description = "Registrar pagos a un prestamo nuevo")
    public ResponseEntity<?> paid(@PathVariable long id, @RequestBody RequestPayment input) throws ResourceNotFoundException, Exception {
        if(input.getAmount() <= 0) {
            throw new Exception(String.format("El monto del pago (%f) debe ser superior a  0", input.getAmount()));
        }
        Loans loan = loansRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prestamo no encontrado, con id :: " + id));
        
        Double paidAmount = paymentsRepository.paidAmount(loan.getId());
        Double debtAmount = loan.getAmount_total() - paidAmount;
        
        if((paidAmount+input.getAmount()) > loan.getAmount_total()) {
            throw new Exception(String.format("El monto del pago (%f) excede el total de la deuda (%f), ya se ha pagado (%f)", input.getAmount(), debtAmount, paidAmount));
        }
        
        Payments savePayment = new Payments();
        savePayment.setLoan_id(loan.getId());
        savePayment.setAmount(input.getAmount());
        savePayment.setReference(input.getReference());
        savePayment.setObservation(input.getObservation());
        savePayment.setStatus("paid");
                
        final Payments savedPayment = paymentsRepository.save(savePayment);
        ResponsePayment responsePayment = new ResponsePayment();
        responsePayment.setId(savedPayment.getId());
        responsePayment.setAmount(savedPayment.getAmount());
        paidAmount = paymentsRepository.paidAmount(loan.getId());
        debtAmount = loan.getAmount_total() - paidAmount;
        responsePayment.setDebt(debtAmount);
        responsePayment.setLoan_id(loan.getId());
        return ResponseEntity.ok(responsePayment);
    }
    
}
