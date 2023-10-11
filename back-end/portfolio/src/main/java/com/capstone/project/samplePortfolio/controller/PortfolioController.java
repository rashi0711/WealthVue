package com.capstone.project.samplePortfolio.controller;

import com.capstone.project.samplePortfolio.Vo.StockTemplate;
import com.capstone.project.samplePortfolio.entity.Transactions;
import com.capstone.project.samplePortfolio.exception.InvalidNumberOfStocks;
import com.capstone.project.samplePortfolio.exception.PortfolioNotFoundException;
import com.capstone.project.samplePortfolio.exception.StockNotFoundException;
import com.capstone.project.samplePortfolio.models.PortfolioData;
import com.capstone.project.samplePortfolio.models.StockdetailsData;
import com.capstone.project.samplePortfolio.dto.PortfolioRequest;
import com.capstone.project.samplePortfolio.dto.PortfolioResponse;
import com.capstone.project.samplePortfolio.models.TransactionsDetails;
import com.capstone.project.samplePortfolio.services.PortfolioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/portfolio")

public class PortfolioController {
    @Autowired
    private PortfolioServices portfolioServices;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPortfolio(@RequestBody @Valid PortfolioRequest request, @RequestParam("userId") String userId){
        portfolioServices.addPortfolio(request,userId);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<PortfolioResponse>> getAllPortfolio(@RequestParam("userId") String userId){
        return new ResponseEntity<>(portfolioServices.getAllPortfolio(userId),HttpStatus.OK);
    }
    @GetMapping("/get/{portfolioId}")
    public ResponseEntity<PortfolioResponse> getPortfolioById(@PathVariable("portfolioId") String portfolioId) throws PortfolioNotFoundException {
        return new ResponseEntity<>(portfolioServices.getPortfolioById(portfolioId),portfolioServices.getPortfolioById(portfolioId)!=null?HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deletePortfolio(@RequestParam("portfolioId") String portfolioId) throws PortfolioNotFoundException {
        portfolioServices.deletePortfolio(portfolioId);
    }

    @PutMapping("/update")
    public ResponseEntity<PortfolioResponse> updatePortfolio(@RequestBody PortfolioRequest portfolio, @RequestParam("portfolioId") String portfolioId) throws PortfolioNotFoundException {
        return new ResponseEntity<>(portfolioServices.updatePortfolio(portfolioId,portfolio),HttpStatus.OK);
    }

    @PutMapping("/buyStock/{quantity}")
    @ResponseStatus(HttpStatus.CREATED)
    public void buyStock(@PathVariable("quantity") int quantity, @RequestParam("stockId") String stockId, @RequestParam("portfolioId") String portfolioId) throws PortfolioNotFoundException, StockNotFoundException {
        System.out.println(quantity+" "+stockId+" "+portfolioId);
        portfolioServices.buyStock(quantity,stockId,portfolioId);
    }
    @PutMapping("/sellStock/{quantity}")
    @ResponseStatus(HttpStatus.OK)
    public void sellStock(@PathVariable("quantity") int quantity, @RequestParam("stockId") String stockId, @RequestParam("portfolioId") String portfolioId) throws PortfolioNotFoundException, InvalidNumberOfStocks, StockNotFoundException {
        portfolioServices.sellStock(quantity,stockId,portfolioId);
    }
    @GetMapping("/getAllStocks")
    public ResponseEntity<StockdetailsData> getAllStocks(@RequestParam("portfolioId") String portfolioId) throws PortfolioNotFoundException {
        return new ResponseEntity<>(portfolioServices.getAllStocks(portfolioId),HttpStatus.OK);
    }
    @GetMapping("/getStockById/{id}")
    public ResponseEntity<StockTemplate> getAllStocksDetails(@PathVariable("id") String id){
        return new ResponseEntity<>(portfolioServices.getAllStocksDetails(id),HttpStatus.OK);
    }
    @GetMapping("/getTransaction")
    public ResponseEntity<TransactionsDetails> getTransactionsHistory(@RequestParam("portfolioId") String portfolioId, @RequestParam("stockId") String stockId){
        return new ResponseEntity<>(portfolioServices.getTransactionsHistory(portfolioId,stockId),HttpStatus.OK);
    }
    @GetMapping("/getPortfolioData")
    public List<PortfolioData> getPortfolioData(@RequestParam("userId") String userId){
        return portfolioServices.getPortfolioData(userId);
    }
}
