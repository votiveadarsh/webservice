import { ProductOrder } from "../models/product-order.model";
import { Subject } from "rxjs/internal/Subject";
import { ProductOrders } from "../models/product-orders.model";
import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Payment } from "../models/payment";
import { map } from "rxjs/operators";

@Injectable()
export class EcommerceService {
    private productsUrl = "api/products";
    private ordersUrl = "api/orders";
    private paymentUrl = "/api/payment";
    private productDescrUrl = "http://localhost/api/product/";

    private productOrder: ProductOrder;
    private orders: ProductOrders = new ProductOrders();

    private productOrderSubject = new Subject();
    private ordersSubject = new Subject();
    private totalSubject = new Subject();

    private total: number;

    ProductOrderChanged = this.productOrderSubject.asObservable();
    OrdersChanged = this.ordersSubject.asObservable();
    TotalChanged = this.totalSubject.asObservable();

    constructor(private http: HttpClient) {
    }

    getAllProducts() {
        return this.http.get(this.productsUrl);
    }

    saveOrder(order: ProductOrders) {
        return this.http.post(this.ordersUrl, order);
    }

    getProductDescr(id:string) {
        return this.http.get(this.productDescrUrl+id).pipe(map(product =>{return product})) ; 
    }

    set SelectedProductOrder(value: ProductOrder) {
        this.productOrder = value;
        this.productOrderSubject.next();
    }

    get SelectedProductOrder() {
        return this.productOrder;
    }

    set ProductOrders(value: ProductOrders) {
        this.orders = value;
        this.ordersSubject.next();
    }

    get ProductOrders() {
        return this.orders;
    }

    get Total() {
        return this.total;
    }

    set Total(value: number) {
        this.total = value;
        this.totalSubject.next();
    }

    redirectPayment(payment: Payment){
        return this.http.post(this.paymentUrl, payment, { responseType: 'text'});
    }
}